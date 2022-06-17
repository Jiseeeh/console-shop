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

    public static void makeFile(String filePath, String header) {
        File file = new File(filePath);

        try {
            if (new File("src/CSV").mkdir()) {
                if (file.createNewFile()) writeToFile(file, header);
            } else {
                if (file.createNewFile()) writeToFile(file, header);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                TRANSACTION_LIST.add(new Transaction(customer, product));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
