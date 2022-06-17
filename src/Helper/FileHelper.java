package Helper;

import Model.Customer;
import Model.Owner;
import Model.Product;
import Model.Transaction;

import java.io.*;
import java.util.List;

public class FileHelper {
    private static final List<Customer> CUSTOMERS_LIST = Owner.CUSTOMERS_LIST;
    private static final List<Product> PRODUCT_LIST = Owner.PRODUCT_LIST;
    private static final List<Transaction> TRANSACTION_LIST = Owner.TRANSACTION_LIST;

    /*
     * @param filePath {where you want your file to go.}
     * @param header {The content you want to initially add.}
     */
    public static void makeFile(String filePath, String header) {
        File file = new File(filePath);

        try {
            if (new File("src/CSV").mkdir()) {
                if (file.createNewFile()) writeToFile(file, header);
            } else {
                if (file.createNewFile()) writeToFile(file, header);
                System.out.print("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @param file {Where you want to write.}
     * @param content {The content you want to write.}
     */
    public static void writeToFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAccounts(File ACCOUNTS_CSV) {
        if (!ACCOUNTS_CSV.exists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNTS_CSV))) {
            String line;

            String header = bufferedReader.readLine(); // eats the header

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                //? Makes a customer based on the data to imitate loading from database.
                Customer customer = new Customer(data[0], data[1], data[2], data[3]);
                customer.setBalance(Double.parseDouble(data[4]));

                CUSTOMERS_LIST.add(customer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadProducts(File PRODUCTS_CSV) {
        if (!PRODUCTS_CSV.exists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PRODUCTS_CSV))) {
            String line;

            String header = bufferedReader.readLine(); // eats the header
            if (header == null) return;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                //? Makes a product based on the data to imitate loading from database.
                Product product = new Product(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]));
                PRODUCT_LIST.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTransactions(String content) {
        File file = new File("src/CSV/transactions.csv");

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTransactions(File TRANSACTIONS_CSV) {
        if (!TRANSACTIONS_CSV.exists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TRANSACTIONS_CSV))) {
            String line;
            Customer customer = null;
            Product product = null;

            String header = bufferedReader.readLine(); // eats the header
            if (header == null) return;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                String customerName = data[0];
                String productName = data[1];

                for (Customer c : CUSTOMERS_LIST) {
                    if (c.getFirstName().equals(customerName)) customer = c;
                }

                for (Product p : PRODUCT_LIST) {
                    if (p.getProductName().equals(productName)) product = p;
                }

                if (customer == null || product == null) return;

                //? Makes a transaction based on the data to imitate loading from database.
                TRANSACTION_LIST.add(new Transaction(customer, product));

                product.setBOUGHT_QUANTITY(Integer.valueOf(data[3]));

                List<Product> CUSTOMER_BOUGHT_PRODUCTS = customer.getBoughtProducts();
                CUSTOMER_BOUGHT_PRODUCTS.add(product);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCustomerCart(File CUSTOMER_CART_CSV) {
        if (!CUSTOMER_CART_CSV.exists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMER_CART_CSV))) {
            String line;
            Customer customer = null;
            List<Product> customerCart = null;
            Product product = null;

            String header = bufferedReader.readLine(); // eats the header
            if (header == null) return;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                String customerName = data[0];
                String productName = data[1];

                for (Customer c : CUSTOMERS_LIST) {
                    if (c.getFirstName().equals(customerName)) {
                        customer = c;
                        customerCart = customer.getMyCart();
                    }
                }

                for (Product p : PRODUCT_LIST) {
                    if (p.getProductName().equals(productName)) product = p;
                }

                if (customer == null || product == null || customerCart == null) return;

                //? Makes a product and store to the customerCart based on the data to imitate loading from database.
                Product loadedProduct = new Product(productName, Double.parseDouble(data[2]), product.getProductQuantity());

                loadedProduct.setBOUGHT_QUANTITY(Integer.valueOf(data[3]));
                customerCart.add(loadedProduct);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // * @param username {the customer to be skipped/removed}
    public static void updateCustomerCartCSV(String username) {
        File tempFile = new File("src/CSV/tempCustomerCart.csv");
        File CUSTOMER_CART_CSV = new File("src/CSV/customerCart.csv");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMER_CART_CSV))) {
            if (tempFile.createNewFile()) {
                FileWriter writer = new FileWriter(tempFile, true);

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split(",");
                    String customerName = data[0];

                    //? Skips the customer who just checked out (that means his cart is empty).
                    //? To imitate removing of accounts.
                    if (customerName.equals(username)) continue;

                    //? Write other customer to the tempFile.
                    writer.write(line + "\n");
                }
                writer.close();

                //? Copies the content of the tempFile and write it to the new File to
                //? Simulate updating of file.
                replaceFile(tempFile.toString(), CUSTOMER_CART_CSV.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @param pathOfOldFile {The file to be deleted.}
     * @param pathOfNewFile {The file to be replaced with the contents of the file that is going to be deleted.}
     */
    private static void replaceFile(String pathOfOldFile, String pathOfNewFile) {
        String sCurrentLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathOfOldFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathOfNewFile));

            while ((sCurrentLine = br.readLine()) != null) {
                bw.write(sCurrentLine);
                bw.newLine();
            }

            br.close();
            bw.close();

            // delete the old file
            File org = new File(pathOfOldFile);
            org.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
