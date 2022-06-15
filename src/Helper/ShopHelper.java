package Helper;

import Controller.CustomerController;
import Controller.OwnerController;
import Model.Customer;
import Model.Owner;

import java.util.List;
import java.util.Scanner;

public class ShopHelper {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final List<Customer> customerList = Owner.CUSTOMERS_LIST;
    public static void openShop () {
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
                case 1 -> ShopHelper.login();
                case 2 -> ShopHelper.register();
                case 3 -> {return;}
            }
        }
    }
    private static void login () {
        System.out.print("Enter your username: ");
        String username = SCAN.nextLine();

        System.out.print("Enter your password: ");
        String password = SCAN.nextLine();


        if (username.equals("owner") && password.equals("jc123")) {
            OwnerController ownerController = new OwnerController(new Owner());
            ownerController.chooseFromDashboard();
            return;
        }

        for (Customer customer : ShopHelper.customerList) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                CustomerController customerController = new CustomerController(customer);
                customerController.chooseFromDashboard();
            }
        }
    }

    private  static void register () {
        System.out.print("Enter your first name: ");
        String firstName = SCAN.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = SCAN.nextLine();

        System.out.print("Enter your username: ");
        String username = SCAN.nextLine();

        System.out.print("Enter your password: ");
        String password = SCAN.nextLine();

        if (ValidationHelper.hasInvalidInput(firstName,lastName,username,password)) {
            System.out.println("Please answer properly");
            register();
        }

        customerList.add(new Customer(firstName,lastName,username,password));

    }
}
