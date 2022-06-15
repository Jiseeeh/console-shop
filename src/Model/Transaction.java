package Model;

public class Transaction {
    private final Double customerBalance;
    private final Double productPrice;

    public Transaction(Double customerBalance, Double productPrice) {
        this.customerBalance = customerBalance;
        this.productPrice = productPrice;
    }

    public Double getCustomerBalance() {
        return customerBalance;
    }

    public Double getProductPrice() {
        return productPrice;
    }
}
