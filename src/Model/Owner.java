package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    public static final List<Customer> CUSTOMERS_LIST = new ArrayList<>();
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();

    private Owner() {
        this.setFirstName("JohnCarlo");
        this.setLastName("Camara");
        this.setUsername("owner");
        this.setPassword("123");
    }
}
