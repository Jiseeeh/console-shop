package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    public static final List<Customer> CUSTOMERS_LIST = new ArrayList<>();
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();
    private final List<Customer> BLOCKED_CUSTOMERS_LIST = new ArrayList<>();

    public Owner() {
        this.setFirstName("JohnCarlo");
        this.setLastName("Camara");
        this.setUsername("owner");
        this.setPassword("123");
    }

    public List<Customer> getBlockedCustomersList() {
        return BLOCKED_CUSTOMERS_LIST;
    }
}
