package Controller;

import Helper.UIHelper;
import Model.Customer;
import Model.Product;
import Model.Transaction;

public class TransactionController {
    private final Transaction TRANSACTION;

    public TransactionController(Transaction transaction) {
        this.TRANSACTION = transaction;
    }

    public boolean startTransaction() {
        Customer customer = TRANSACTION.getCustomer();
        Product product = TRANSACTION.getProduct();

        if (customer.getBalance() < product.getProductPrice() * product.getBOUGHT_QUANTITY()) {
            System.out.println("You have insufficient amount of money. ");
            return false;
        }

        double newBalance = customer.getBalance() - product.getProductPrice() * product.getBOUGHT_QUANTITY();
        customer.setBalance(newBalance);

        UIHelper.sleep(1, String.format("Success! your new balance is P%.1f", newBalance));
        return true;
    }
}
