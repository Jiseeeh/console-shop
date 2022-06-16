package Model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Product> myCart = new ArrayList<>();
    private Double balance = 0.0;

    public Customer(String firstName, String lastName, String username, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public List<Product> getMyCart() {
        return myCart;
    }
}
