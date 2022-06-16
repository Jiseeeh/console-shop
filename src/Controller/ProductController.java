package Controller;

import Model.Owner;
import Model.Product;

import java.util.List;

public class ProductController {
    private Product product;
    private List<Product> productList = Owner.PRODUCT_LIST;

    public ProductController (Product product) {
        this.product = product;
    }
    public void updateProductQuantity (int bought) {
        product.setProductQuantity(product.getProductQuantity() - bought);
    }
    public void updateProducts() {
        if (product.getProductQuantity() == 0) {
            productList.remove(product);
        }
    }
}
