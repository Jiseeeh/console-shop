package Helper;

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
    }
}
