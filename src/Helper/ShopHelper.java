package Helper;

import Controller.CustomerController;
import Controller.OwnerController;
import Model.Customer;
import Model.Owner;
import Model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ShopHelper {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final List<Customer> CUSTOMERS_LIST = Owner.CUSTOMERS_LIST;
    private static final List<Product> PRODUCT_LIST = Owner.PRODUCT_LIST;
    private static final File ACCOUNTS_CSV = new File("src/CSV/accounts.csv");
    private static final File PRODUCTS_CSV = new File("src/CSV/products.csv");
    private static final File TRANSACTION_CSV = new File("src/CSV/transactions.csv");
    private static final File CUSTOMER_CART_CSV = new File("src/CSV/customerCart.csv");

    public static void openShop() throws IOException {
        // ? loads the files (if they're present) before opening the shop.
        loadFiles();

        while (true) {
            System.out.println("""
                                        
                    Welcome! (Always exit here in option 3!)
                    1 -> Login
                    2 -> Register
                    3 -> Exit
                    """);

            String input = SCAN.nextLine();

            if (ValidationHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> ShopHelper.login();
                case 2 -> ShopHelper.register();
                case 3 -> {
                    updateFilesOnExit();
                    return;
                }
                default -> {
                    System.out.println("""
                            Please choose from 1-3 only!
                            """);
                }
            }
        }
    }

    private static void loadFiles() {
        FileHelper.loadAccounts(ACCOUNTS_CSV);
        FileHelper.loadProducts(PRODUCTS_CSV);
        FileHelper.loadCustomerCart(CUSTOMER_CART_CSV);
        FileHelper.loadTransactions(TRANSACTION_CSV);
    }

    private static void updateFilesOnExit() {
        if (ACCOUNTS_CSV.exists()) ACCOUNTS_CSV.delete();
        FileHelper.makeFile(ACCOUNTS_CSV.toString(), "FirstName,LastName,Username,Password,Balance\n");

        if (PRODUCTS_CSV.exists()) PRODUCTS_CSV.delete();
        FileHelper.makeFile(PRODUCTS_CSV.toString(), "ProductName,ProductPrice,ProductQuantity\n");

        for (Customer customer : CUSTOMERS_LIST) {
            FileHelper.writeToFile(ACCOUNTS_CSV, customer + "\n");
        }

        for (Product product : PRODUCT_LIST) {
            if (product.getProductQuantity() != 0) {
                FileHelper.writeToFile(PRODUCTS_CSV, product + "\n");
            }
        }
    }

    private static void login() {
        System.out.print("\nEnter your username: ");
        String username = SCAN.nextLine();

        System.out.print("Enter your password: ");
        String password = SCAN.nextLine();

        if (ValidationHelper.hasInvalidInput(username, password)) return;

        if (username.equals("owner") && password.equals("123")) {
            UIHelper.loginSuccess();

            OwnerController ownerController = new OwnerController();
            ownerController.chooseFromDashboard();
            return;
        }

        for (Customer customer : ShopHelper.CUSTOMERS_LIST) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                UIHelper.loginSuccess();

                CustomerController customerController = new CustomerController(customer);
                customerController.chooseFromDashboard();
                return;
            }
        }

        UIHelper.sleep(1, "No account found!");
    }

    private static void register() {
        System.out.print("\nEnter your first name: ");
        String firstName = SCAN.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = SCAN.nextLine();

        System.out.print("Enter your username: ");
        String username = SCAN.nextLine();

        System.out.print("Enter your password: ");
        String password = SCAN.nextLine();

        if (ValidationHelper.hasInvalidInput(firstName, lastName, username, password)) return;

        UIHelper.sleep(1, "Registration success!");
        CUSTOMERS_LIST.add(new Customer(firstName, lastName, username, password));
    }
}
