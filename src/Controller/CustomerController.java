package Controller;

import Model.*;
import View.CustomerView;

import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private Customer customer;
    private CustomerView customerView = new CustomerView();
    private Cart customerCart;
    private static final List<Product> OWNER_PRODUCT_LIST = Owner.PRODUCT_LIST;
    private static final List<Transaction> TRANSACTION_LIST = Owner.TRANSACTION_LIST;
    private final Scanner SCAN = new Scanner(System.in);
    public CustomerController (Customer customer) {
        this.customer = customer;
        customerCart = customer.getMyCart();
    }

    public void chooseFromDashboard () {
        while (true) {
            customerView.showCustomerDashboard();

            String input = SCAN.nextLine();

            //VALIDATION

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> cashIn();
                case 2 -> goShopping();
                case 3 -> customerView.viewMyInfo(customer);
                case 4 -> customerView.viewMyBalance(customer);
                case 5 -> {return;}
            }

        }
    }

    private void cashIn () {
        int max = 1000;
        int min = 500;
        System.out.println("Enter the amount of money (Min: 500 ; Max: 1000)");
        double inputMoney = Double.parseDouble(SCAN.nextLine());

        if (inputMoney < min || inputMoney > max ) {
            System.out.println("Out of range!");
            cashIn();
        }
        else {
            System.out.println("\nSuccess!\n");
            customer.setBalance(customer.getBalance() + inputMoney);
        }
    }

    private void goShopping () {
        Product chosenProduct;
        System.out.println("""
                _________________________
                |                       |
                | WELCOME TO THE SHOP ! |
                |                       |
                -------------------------
                """);
        int i = 0;
        for (Product product : OWNER_PRODUCT_LIST) {
            System.out.println("----------------------------");
            System.out.printf("""
                    index: %d
                    product name: %s
                    price: %.1f
                    quantity: %d
                    """,i,product.getProductName(),product.getProductPrice(),product.getProductQuantity());
            System.out.println("----------------------------");
            i++;
        }

        System.out.print("Enter the product index: ");
        int choice = Integer.parseInt(SCAN.nextLine());
        chosenProduct = OWNER_PRODUCT_LIST.get(choice);

        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(SCAN.nextLine());

        if (qty == 0) qty = 1;
        if (qty > chosenProduct.getProductQuantity()) {
            System.out.println("We don't have enough stock for that quantity");
            return;
        }

        Transaction transaction = new Transaction(customer, chosenProduct,qty);

        TransactionController transactionController = new TransactionController(transaction);
        customer.setBalance(transactionController.startTransaction());

        TRANSACTION_LIST.add(transaction);

        ProductController productController = new ProductController(chosenProduct);
        productController.updateProductQuantity(qty);
        productController.updateProducts();
    }
    //TODO: 15 Jun 2022
    // add to cart
    // buy (process thru transaction model)
    // cash in
    // update their account
    // check for products

}
