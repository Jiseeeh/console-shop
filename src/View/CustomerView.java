package View;

import Helper.UIHelper;
import Helper.ValidationHelper;
import Model.Customer;
import Model.Product;

import java.util.List;

public class CustomerView {
    public void showCustomerDashboard() {
        System.out.println("""
                                
                What do you want to do?
                1 -> Cash in
                2 -> Go shopping (can add to cart or buy)
                3 -> View Info
                4 -> View balance
                5 -> View my cart
                6 -> Clear my cart
                7 -> Checkout
                8 -> View my bought products
                9 -> Logout
                """);
        System.out.print(": ");
    }

    public void viewMyInfo(Customer customer) {
        System.out.println("\n----------------------------");
        System.out.printf("""
                Firstname : %s
                Lastname : %s
                """, customer.getFirstName(), customer.getLastName());
        System.out.println("----------------------------");
    }

    public void viewMyBalance(Customer customer) {
        UIHelper.sleep(1, "Balance: P" + customer.getBalance());
    }

    public void viewMyCart(List<Product> customerCart) {
        if (ValidationHelper.isCartEmpty(customerCart)) return;

        System.out.println("\n** YOUR CART **");
        customerCart.forEach(cart -> {
            System.out.println("\n----------------------------");
            System.out.printf("""
                    Product name: %s
                    Product price: %.1f
                    Quantity: %d
                    """, cart.getProductName(), cart.getProductPrice(), cart.getBOUGHT_QUANTITY());
            System.out.println("----------------------------");
        });
    }

    public void viewMyBoughtProducts(List<Product> boughtProducts) {
        if (boughtProducts.size() == 0) {
            UIHelper.sleep(1, "You haven't bought any products!");
            return;
        }

        System.out.println("\n** Your Bought Products **");
        boughtProducts.forEach(boughtProduct -> {
            System.out.println("\n----------------------------");
            System.out.printf("""
                    Product name: %s
                    Product price: %.1f
                    Bought Quantity: %d
                    """, boughtProduct.getProductName(), boughtProduct.getProductPrice(), boughtProduct.getBOUGHT_QUANTITY());
            System.out.println("----------------------------");
        });
    }
}
