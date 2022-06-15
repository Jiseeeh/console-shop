package View;

import Model.Customer;

public class CustomerView {
    public void showCustomerDashboard () {
        System.out.println("""
                What do you want to do?
                1 -> Cash in
                2 -> Go shopping (can add to cart or buy)
                3 -> View Info
                5 -> Logout
                """);
        System.out.print(": ");
    }

    public void viewMyInfo (Customer customer) {
        System.out.println(customer.getFirstName());
    }
}
