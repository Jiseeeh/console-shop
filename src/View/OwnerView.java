package View;

import Model.Customer;
import Model.Product;
import Model.Transaction;

import java.util.List;

public class OwnerView {
    public void showOwnerDashboard() {
        System.out.println("""
                What do you want to do?
                1 -> Add a product
                2 -> View transactions
                3 -> View customers details
                4 -> View your products
                5 -> Block a customer
                6 -> Logout
                """);
        System.out.print(": ");
    }

    public void viewTransactions (List<Transaction> transactions) {
        transactions.forEach(System.out::println);
    }

    public void viewCustomerDetail (List<Customer> customers) {
        customers.forEach(customer -> {
            System.out.println("----------------------------");
            System.out.printf("""
                    Firstname: %s
                    Lastname: %s
                    """,customer.getFirstName(),customer.getLastName());
            System.out.println("----------------------------");
        });
    }

    public void viewOwnerProducts (List<Product> products) {
        products.forEach(product -> {
            System.out.println("----------------------------");
            System.out.printf("""
                    Product name: %s
                    Product price: %f
                    Product quantity: %d
                    """,product.getProductName(),product.getProductPrice(),product.getProductQuantity());
            System.out.println("----------------------------");
        });
    }
}
