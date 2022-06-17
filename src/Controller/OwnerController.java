package Controller;

import Helper.UIHelper;
import Helper.ValidationHelper;
import Model.Customer;
import Model.Owner;
import Model.Product;
import Model.Transaction;
import View.OwnerView;

import java.util.List;
import java.util.Scanner;

public class OwnerController {
    private static final List<Customer> OWNER_CUSTOMER_LIST = Owner.CUSTOMERS_LIST;
    private final List<Product> OWNER_PRODUCT_LIST = Owner.PRODUCT_LIST;
    private final List<Transaction> OWNER_TRANSACTION_LIST = Owner.TRANSACTION_LIST;
    private final OwnerView OWNER_VIEW = new OwnerView();
    private final Scanner SCAN = new Scanner(System.in);

    public void chooseFromDashboard() {
        while (true) {
            OWNER_VIEW.showOwnerDashboard();
            String input = SCAN.nextLine().trim();

            if (ValidationHelper.hasLetterInput(input)) continue;

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> OWNER_VIEW.viewTransactions(OWNER_TRANSACTION_LIST);
                case 3 -> OWNER_VIEW.viewCustomersDetail(OWNER_CUSTOMER_LIST);
                case 4 -> OWNER_VIEW.viewOwnerProducts(OWNER_PRODUCT_LIST);
                case 5 -> removeACustomer();
                case 6 -> {
                    return;
                }
                default -> UIHelper.sleep(1, "Please enter from 1-6 only!");
            }
        }
    }

    public void addProduct() {
        try {
            System.out.print("\nEnter the product name: ");
            String productName = SCAN.nextLine();

            System.out.print("Enter the product price: ");
            Double productPrice = Double.parseDouble(SCAN.nextLine());

            System.out.print("Enter the product quantity: ");
            Integer productQuantity = Integer.parseInt(SCAN.nextLine());

            OWNER_PRODUCT_LIST.add(new Product(productName, productPrice, productQuantity));

            UIHelper.sleep(1, String.format("%d %ss was added!", productQuantity, productName));
        } catch (NumberFormatException e) {
            ValidationHelper.printNumberFormatExceptionMessage();
            addProduct();
        }
    }

    public void removeACustomer() {
        if (!ValidationHelper.hasCustomers(OWNER_CUSTOMER_LIST)) return;

        OWNER_VIEW.viewCustomersDetail(OWNER_CUSTOMER_LIST);

        System.out.print("Enter the customer first name: ");
        String customerName = SCAN.nextLine();

        if (ValidationHelper.hasInvalidInput(customerName)) return;

        for (Customer customer : OWNER_CUSTOMER_LIST) {
            if (customer.getFirstName().equals(customerName)) {
                System.out.printf("\n%s was successfully blocked!\n", customer.getFirstName());

                OWNER_CUSTOMER_LIST.remove(customer);
                return;
            }
        }

        System.out.println("\nNo user found!\n");
    }
}
