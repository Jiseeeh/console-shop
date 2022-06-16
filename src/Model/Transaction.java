package Model;

public class Transaction {
    private final Customer customer;
    private final Product product;
    private int productQuantity;

    public Transaction(Customer customer, Product product , int quantity) {
        this.customer = customer;
        this.product = product;
        this.productQuantity = quantity;
    }

    public Double getCustomerBalance() {
        return customer.getBalance();
    }

    public Double getProductPrice() {
        return product.getProductPrice() * product.getProductQuantity();
    }

    public String getCustomerName () {
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public String getProductInfo () {
        return productQuantity + "x of " + product.getProductName();
    }

}
