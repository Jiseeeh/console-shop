package Helper;

import Controller.CustomerController;
import Model.Customer;

import java.util.List;
import java.util.Scanner;

public class ShopHelper {
    private static final Scanner SCAN = new Scanner(System.in);
    public static void login (List<Customer> customers) {
        System.out.print("Enter your username: ");
        String username = SCAN.nextLine();

        System.out.print("Enter your password: ");
        String password = SCAN.nextLine();

        String status = "Success!";
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                CustomerController customerController = new CustomerController();
            }
            else status = "Not Found";
        }

        System.out.println(status);
    }

    public  static void register (List<Customer> customers) {
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
            register(customers);
        }

        customers.add(new Customer(firstName,lastName,username,password));

    }
}
