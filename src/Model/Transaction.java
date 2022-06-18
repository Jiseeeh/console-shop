package Model;

public class Transaction {
    private final Customer CUSTOMER;
    private final Product PRODUCT;

    public Transaction(Customer customer, Product product) {
        this.CUSTOMER = customer;
        this.PRODUCT = product;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%.1f,%d", CUSTOMER.getFirstName(), PRODUCT.getProductName(), PRODUCT.getProductPrice(), PRODUCT.getBOUGHT_QUANTITY());
    }

    public String getProductInfo() {
        return PRODUCT.getProductQuantity() + "x of " + PRODUCT.getProductName();
    }

    public Customer getCustomer() {
        return CUSTOMER;
    }

    public Product getProduct() {
        return PRODUCT;
    }

}
