package Model;

public class Product {
    private final String PRODUCT_NAME;
    private final Double PRODUCT_PRICE;
    private final Integer PRODUCT_QUANTITY;

    public Product(String productName, Double productPrice, Integer productQuantity) {
        this.PRODUCT_NAME = productName;
        this.PRODUCT_PRICE = productPrice;
        this.PRODUCT_QUANTITY = productQuantity;
    }

    public String getProductName() {
        return PRODUCT_NAME;
    }

    public Double getProductPrice() {
        return PRODUCT_PRICE;
    }

    public Integer getProductQuantity() {
        return PRODUCT_QUANTITY;
    }
}
