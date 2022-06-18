package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User {
    public static final List<Customer> CUSTOMERS_LIST = new ArrayList<>();
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();

    public Owner() {
        super("JohnCarlo", "Camara", "Owner", "123");
    }
}
