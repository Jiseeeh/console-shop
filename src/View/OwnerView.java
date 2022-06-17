package View;

import Helper.UIHelper;
import Helper.ValidationHelper;
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
                5 -> Remove a customer
                6 -> Logout
                """);
        System.out.print(": ");
    }

    public void viewTransactions(List<Transaction> transactions) {
        if (transactions.size() == 0) {
            UIHelper.sleep(1, "No transactions for now!");
            return;
        }

        transactions.forEach(transaction -> {
            System.out.println("\n----------------------------");
            System.out.println(transaction.getCustomer().getFirstName() + " " + transaction.getCustomer().getLastName() + " bought " + transaction.getProductInfo());
            System.out.println("----------------------------\n");
        });
    }

    public void viewCustomersDetail(List<Customer> customers) {
        if (!ValidationHelper.hasCustomers(customers)) return;

        customers.forEach(customer -> {
            System.out.println("----------------------------");
            System.out.printf("""
                    Firstname: %s
                    Lastname: %s
                    """, customer.getFirstName(), customer.getLastName());
            System.out.println("----------------------------");
        });
    }

    public void viewOwnerProducts(List<Product> products) {
        if (products.size() == 0) {
            UIHelper.sleep(1, "You haven't add products yet!");
            return;
        }

        products.forEach(product -> {
            System.out.println("----------------------------");
            System.out.printf("""
                                        
                    Product name: %s
                    Product price: %.1f
                    Product quantity: %d
                    """, product.getProductName(), product.getProductPrice(), product.getProductQuantity());
            System.out.println("----------------------------");
        });
    }
}
