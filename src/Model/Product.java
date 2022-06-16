package Model;

public class Product {
    private final String PRODUCT_NAME;
    private final Double PRODUCT_PRICE;
    private Integer PRODUCT_QUANTITY;
    private Integer BOUGHT_QUANTITY;

    public Product(String productName, Double productPrice, Integer productQuantity) {
        this.PRODUCT_NAME = productName;
        this.PRODUCT_PRICE = productPrice;
        this.PRODUCT_QUANTITY = productQuantity;
    }

    @Override
    public String toString () {
        return String.format("%s,%.1f,%d", getProductName(),getProductPrice(),getProductQuantity());
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

    public void setProductQuantity(Integer i) {
        PRODUCT_QUANTITY = i;
    }

    public Integer getBOUGHT_QUANTITY() {
        return BOUGHT_QUANTITY;
    }

    public void setBOUGHT_QUANTITY(Integer BOUGHT_QUANTITY) {
        this.BOUGHT_QUANTITY = BOUGHT_QUANTITY;
    }
}
