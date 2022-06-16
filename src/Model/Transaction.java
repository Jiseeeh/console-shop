package Model;

public class Transaction {
    private final Customer customer;
    private final Product product;

    public Transaction(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
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
