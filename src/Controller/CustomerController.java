package Controller;

import Helper.FileHelper;
import Helper.UIHelper;
import Helper.ValidationHelper;
import Model.Customer;
import Model.Owner;
import Model.Product;
import Model.Transaction;
import View.CustomerView;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private static final List<Product> OWNER_PRODUCT_LIST = Owner.PRODUCT_LIST;
    private static final List<Transaction> TRANSACTION_LIST = Owner.TRANSACTION_LIST;
    private final List<Product> customerCart;
    private final List<Product> CUSTOMER_BOUGHT_PRODUCTS;
    private final Scanner SCAN = new Scanner(System.in);
    private final Customer customer;
    private final CustomerView customerView = new CustomerView();

    public CustomerController(Customer customer) {
        this.customer = customer;
        customerCart = customer.getMyCart();
        CUSTOMER_BOUGHT_PRODUCTS = customer.getBoughtProducts();
    }

    public void chooseFromDashboard() {
        while (true) {
            customerView.showCustomerDashboard();
            String input = SCAN.nextLine();

            if (ValidationHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> cashIn();
                case 2 -> goShopping();
                case 3 -> customerView.viewMyInfo(customer);
                case 4 -> customerView.viewMyBalance(customer);
                case 5 -> customerView.viewMyCart(customerCart);
                case 6 -> clearCart();
                case 7 -> checkOut();
                case 8 -> customerView.viewMyBoughtProducts(CUSTOMER_BOUGHT_PRODUCTS);
                case 9 -> {
                    return;
                }
            }

        }
    }

    private void cashIn() {
        try {
            int max = 1000;
            int min = 500;

            System.out.println("Enter the amount of money (Min: 500 ; Max: 1000)");
            double inputMoney = Double.parseDouble(SCAN.nextLine());

            // Calls the cashIn method again if the money was out of range.
            if (inputMoney < min || inputMoney > max) {
                System.out.println("Out of range!");
                cashIn();
            } else {
                UIHelper.sleep(1, String.format("Success! you cashed in P%.1f", inputMoney));
                customer.setBalance(customer.getBalance() + inputMoney);
            }
            // Code would reach here if the user input a numeric char.
        } catch (NumberFormatException e) {
            ValidationHelper.printNumberFormatExceptionMessage();
            cashIn();
        }
    }

    private void goShopping() {
        Product chosenProduct;

        //?  This is for java to immediately exit the method if there are no products.
        if (OWNER_PRODUCT_LIST.size() == 0) {
            UIHelper.sleep(1, "No Available products as of now!");
            return;
        }

        System.out.println("""
                _________________________
                |                       |
                | WELCOME TO THE SHOP ! |
                |                       |
                -------------------------
                """);

        int i = 0;
        for (Product product : OWNER_PRODUCT_LIST) {
            if (product.getProductQuantity() == 0) continue;

            System.out.println("----------------------------");
            System.out.printf("""
                    product number: %d
                    product name: %s
                    price: %.1f
                    quantity: %d
                    """, i, product.getProductName(), product.getProductPrice(), product.getProductQuantity());
            System.out.println("----------------------------");
            i++;
        }
        try {
            System.out.print("\nEnter the product number: ");
            int productNumber = Integer.parseInt(SCAN.nextLine());
            chosenProduct = OWNER_PRODUCT_LIST.get(productNumber);

            System.out.println("""
                    \nDo you want to add to cart or buy now?
                    1 -> Add to cart
                    2 -> Buy now
                    """);

            int choice = Integer.parseInt(SCAN.nextLine());

            if (choice == 1) addToCart(chosenProduct);
            else if (choice == 2) buyNow(chosenProduct);
            else {
                ValidationHelper.printIndexOutOfBoundsExceptionMessage();
                goShopping();
            }
        } catch (NumberFormatException e) {
            ValidationHelper.printNumberFormatExceptionMessage();
            goShopping();
        } catch (IndexOutOfBoundsException e) {
            ValidationHelper.printIndexOutOfBoundsExceptionMessage();
            goShopping();
        }
    }

    private void addToCart(Product chosenProduct) {
        try {
            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(SCAN.nextLine());
            chosenProduct.setBOUGHT_QUANTITY(qty);

            if (qty > chosenProduct.getProductQuantity()) {
                System.out.println("We don't have enough stock for that quantity");
                return;
            }

            // ? sets the new quantity of that product
            chosenProduct.setProductQuantity(chosenProduct.getProductQuantity() - qty);

            // ? make and write the file to where that product will be stored.
            FileHelper.makeFile("src/CSV/customerCart.csv", "CustomerName,ProductName,ProductPrice,ProductBoughtQuantity\n");
            FileHelper.writeToFile(new File("src/CSV/customerCart.csv"), String.format("%s,%s,%.1f,%d\n", customer.getFirstName(), chosenProduct.getProductName(), chosenProduct.getProductPrice(), chosenProduct.getBOUGHT_QUANTITY()));

            customerCart.add(chosenProduct);

        } catch (NumberFormatException e) {
            ValidationHelper.printNumberFormatExceptionMessage();
            addToCart(chosenProduct);
        }
    }

    private void clearCart() {
        if (ValidationHelper.isCartEmpty(customerCart)) return;

        UIHelper.sleep(1, "Cart successfully cleared!");
        customerCart.clear();
    }

    private void buyNow(Product chosenProduct) {
        System.out.print("\nEnter quantity: ");
        int qty = Integer.parseInt(SCAN.nextLine());
        chosenProduct.setBOUGHT_QUANTITY(qty);

        //? This is for when the user input 0 , we set it to 1 to avoid multiplying to 0
        if (qty == 0) qty = 1;
        if (qty > chosenProduct.getProductQuantity()) {
            UIHelper.sleep(1, "We don't have enough stock for that quantity!");
            return;
        }

        Transaction transaction = new Transaction(customer, chosenProduct);
        TransactionController transactionController = new TransactionController(transaction);

        //  if transaction failed
        if (!transactionController.startTransaction()) return;

        TRANSACTION_LIST.add(transaction);
        CUSTOMER_BOUGHT_PRODUCTS.add(chosenProduct);

        ProductController productController = new ProductController(chosenProduct);
        productController.updateProductQuantity(qty);
        productController.updateProduct();

        // ? This is for making/writing to transactionsCSV to load later when the program runs again
        FileHelper.makeFile("src/CSV/transactions.csv", "CustomerName,ProductName,ProductPrice,ProductQuantity\n");
        FileHelper.writeTransactions(transaction + "\n");
    }

    private void checkOut() {
        int totalPrice = 0;

        if (customerCart.size() == 0) {
            UIHelper.sleep(1, "You have nothing in cart!");
            return;
        }

        // ? This totals the products from the customer cart
        for (Product product : customerCart) {
            if (product.getProductQuantity() <= 0) {
                UIHelper.sleep(1, "No stocks for " + product.getProductName());
                return;
            }
            totalPrice += product.getProductPrice() * product.getBOUGHT_QUANTITY();
        }

        if (customer.getBalance() < totalPrice) {
            System.out.println("You don't have enough money to pay for that!");
            return;
        }

        // ? Loops throughout the customerCart and make transactions in each of the products
        for (Product product : customerCart) {
            Transaction transaction = new Transaction(customer, product);
            TransactionController transactionController = new TransactionController(transaction);

            UIHelper.sleep(2, "Processing your request..");
            transactionController.startTransaction();


            TRANSACTION_LIST.add(transaction);
            CUSTOMER_BOUGHT_PRODUCTS.add(product);

            ProductController productController = new ProductController(product);
            productController.updateProduct();

            FileHelper.makeFile("src/CSV/transactions.csv", "CustomerName,ProductName,ProductPrice,ProductQuantity\n");
            FileHelper.writeTransactions(transaction + "\n");
        }

        UIHelper.sleep(1, "Checkout done!");

        // ? Updates the file (customerCart.csv) by removing the customer who just checked out.
        FileHelper.updateCustomerCartCSV(customer.getFirstName());
        customerCart.clear();
    }
}
