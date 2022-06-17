package Helper;

import Model.Customer;
import Model.Product;

import java.util.List;

public class ValidationHelper {
    public static boolean hasInvalidInput(String... params) {
        // FIXME: 17 Jun 2022 
        boolean result = false;

        for (String param : params) {
            if (param == null || param.trim().isEmpty()) {
                result = true;
                System.out.println("Please answer properly!");
                break;
            }
        }

        return result;
    }

    public static boolean hasLetterInput(String input) {
        if (input.matches("[A-Za-z]*")) {
            System.out.println("""
                                            
                    |-----------------------------------|
                    |*   Please enter numbers only!!   *|
                    |-----------------------------------|
                    """);
            return true;
        }
        return false;
    }

    public static void printNumberFormatExceptionMessage() {
        System.out.println("""
                                
                |-------------------------------------------|
                |*   Please enter the appropriate type!!   *|
                |-------------------------------------------|
                """);
        UIHelper.sleep(2, "Redirecting you back to the shop...");
    }

    public static void printIndexOutOfBoundsExceptionMessage() {
        System.out.println("""
                                
                |-------------------------------------------|
                |*   Please input from the range only!!!   *|
                |-------------------------------------------|
                """);
        UIHelper.sleep(2, "Redirecting you back to the shop...");
    }

    public static boolean hasCustomers(List<Customer> customers) {
        boolean result = true;

        if (customers.size() == 0) {
            UIHelper.sleep(1, "No customers for now!");
            result = false;
        }
        return result;
    }

    public static boolean isCartEmpty(List<Product> products) {
        boolean result = false;

        if (products.size() == 0) {
            UIHelper.sleep(1, "Your cart is empty!");
            result = true;
        }
        return result;
    }
}
