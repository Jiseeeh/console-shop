package Controller;

import Helper.ShopHelper;
import Model.Customer;
import Model.Owner;
import Model.Product;
import Model.Transaction;
import View.OwnerView;

import java.util.List;
import java.util.Scanner;

public class OwnerController {
    private Owner owner;
    private final List<Customer> OWNER_CUSTOMER_LIST;
    private final List<Customer> OWNER_BLOCKED_CUSTOMERS_LIST;
    private final List<Product> OWNER_PRODUCT_LIST;
    private final List<Transaction> OWNER_TRANSACTION_LIST;
    private final OwnerView OWNER_VIEW = new OwnerView();
    private final Scanner SCAN = new Scanner(System.in);
    public OwnerController(Owner owner) {
        this.owner = owner;
        OWNER_PRODUCT_LIST = owner.getProductList();
        OWNER_TRANSACTION_LIST = owner.getTransactionList();
        OWNER_CUSTOMER_LIST = owner.getCustomersList();
        OWNER_BLOCKED_CUSTOMERS_LIST = owner.getBlockedCustomersList();
    }

    public void openShop () {
        while (true) {
            System.out.println("""
                    Welcome!
                    1 -> Login
                    2 -> Register
                    3 -> Exit
                    """);

            String input = SCAN.nextLine();

            // Validation

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> ShopHelper.login(OWNER_CUSTOMER_LIST);
                case 2 -> ShopHelper.register(OWNER_CUSTOMER_LIST);
                case 3 -> {return;}
            }
        }
    }
    public void start () {
        chooseFromDashboard();
    }

    private void chooseFromDashboard () {
        while (true) {
            OWNER_VIEW.showOwnerDashboard();
            String input = SCAN.nextLine();

            // Validation here
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> OWNER_VIEW.viewTransactions(OWNER_TRANSACTION_LIST);
                case 3 -> OWNER_VIEW.viewCustomerDetail(OWNER_CUSTOMER_LIST);
                case 4 -> OWNER_VIEW.viewOwnerProducts(OWNER_PRODUCT_LIST);
                case 5 -> blockACustomer();
                case 6 -> {return;}
            }
        }
    }

    public void addProduct () {
        System.out.print("Enter the product name: ");
        String productName = SCAN.nextLine();

        System.out.print("Enter the product name: ");
        Double productPrice = SCAN.nextDouble();

        System.out.print("Enter the product name: ");
        Integer productQuantity = SCAN.nextInt();

        OWNER_PRODUCT_LIST.add(new Product(productName,productPrice,productQuantity));
    }
    public void blockACustomer () {
        System.out.print("Enter the customer first name: ");
        String customerName = SCAN.nextLine();

        for (Customer customer : OWNER_CUSTOMER_LIST) {
            if (customer.getFirstName().equals(customerName)) {
                System.out.println("Customer successfully blocked!");

                OWNER_CUSTOMER_LIST.remove(customer);
                OWNER_BLOCKED_CUSTOMERS_LIST.add(customer);
            }
        }
    }
    //TODO: 15 Jun 2022
    // view customer details
    // block a user
    // add product
    // show transactions
}
