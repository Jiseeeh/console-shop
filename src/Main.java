import Helper.ShopHelper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ShopHelper.openShop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}