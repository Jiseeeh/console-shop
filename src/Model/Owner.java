package Model;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User{
    public static final List<Customer> CUSTOMERS_LIST = new ArrayList<>();
    private final List<Customer> BUYERS = new ArrayList<>();
    private final List<Customer> BLOCKED_CUSTOMERS_LIST = new ArrayList<>();
    private final List<Product> PRODUCT_LIST = new ArrayList<>();
    private final List<Transaction> TRANSACTION_LIST = new ArrayList<>();
    public Owner () {
        this.setUsername("owner");
        this.setPassword("jc123");
    }

    public List<Customer> getBlockedCustomersList() {
        return BLOCKED_CUSTOMERS_LIST;
    }

    public List<Product> getProductList() {
        return PRODUCT_LIST;
    }
    public List<Transaction> getTransactionList () {
        return TRANSACTION_LIST;
    }
}
