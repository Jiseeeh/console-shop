package Helper;

import Model.Customer;
import Model.Owner;

import java.io.*;
import java.util.List;

public class FileHelper {
    private static final List<Customer> CUSTOMERS_LIST = Owner.CUSTOMERS_LIST;

    public static void makeFile(String filePath, String header) {
        File file = new File(filePath);

        try {
            if (new File("src/CSV").mkdir())
                if (file.createNewFile()) writeToFile(file, header);

                else {
                    if (file.createNewFile()) writeToFile(file, header);
                    // read
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writeAccountsHeader(writer);
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeAccountsHeader(FileWriter writer) {
        try {
            writer.append("FirstName,LastName,Username,Password,Balance\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile(File file) {
        if (!file.exists()) return;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            bufferedReader.readLine(); // eats the header

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
}
