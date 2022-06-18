package Model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Product> MY_CART = new ArrayList<>();
    private final List<Product> BOUGHT_PRODUCTS = new ArrayList<>();
    private Double balance = 0.0;

    public Customer(String firstName, String lastName, String username, String password) {
        super(firstName, lastName, username, password);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%.1f", this.getFirstName(), this.getLastName(), this.getUsername(), this.getPassword(), this.getBalance());
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public List<Product> getMyCart() {
        return MY_CART;
    }

    public List<Product> getBoughtProducts() {
        return BOUGHT_PRODUCTS;
    }
}
