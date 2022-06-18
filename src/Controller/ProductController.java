package Controller;

import Model.Owner;
import Model.Product;

import java.util.List;

public class ProductController {
    private final Product PRODUCT;
    private final List<Product> PRODUCT_LIST = Owner.PRODUCT_LIST;

    public ProductController(Product product) {
        this.PRODUCT = product;
    }

    public void updateProductQuantity(int bought) {
        PRODUCT.setProductQuantity(PRODUCT.getProductQuantity() - bought);
    }

    public void updateProduct() {
        if (PRODUCT.getProductQuantity() == 0) {
            PRODUCT_LIST.remove(PRODUCT);
        }
    }
}
