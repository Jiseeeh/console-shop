package Model;

public class Transaction {
    private final Customer customer;
    private final Product product;

    public Transaction(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%.1f,%d", customer.getFirstName(), product.getProductName(), product.getProductPrice(), product.getBOUGHT_QUANTITY());
    }

    public String getProductInfo() {
        return product.getProductQuantity() + "x of " + product.getProductName();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

}
