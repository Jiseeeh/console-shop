package Controller;

import Model.Cart;
import Model.Customer;
import View.CustomerView;

import java.util.Scanner;

public class CustomerController {
    private Customer customer;
    private CustomerView customerView = new CustomerView();
    private Cart customerCart;
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
                case 5 -> {return;}
            }

        }
    }

    private void cashIn () {

    }

    private void goShopping () {

    }
    //TODO: 15 Jun 2022
    // add to cart
    // buy (process thru transaction model)
    // cash in
    // update their account
    // check for products

}
