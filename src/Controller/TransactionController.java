package Controller;

import Model.Transaction;

public class TransactionController {
    private final Transaction transaction;

    public  TransactionController (Transaction transaction) {
        this.transaction = transaction;
    }

    public double startTransaction () {
        double customerBalance = transaction.getCustomerBalance();
        double productPrice = transaction.getProductPrice();

        if (customerBalance < productPrice) {
            System.out.println("You have insufficient amount of money. ");
            return 0;
        }

        customerBalance -= productPrice;

        System.out.println("Success! your new balance is " + customerBalance);
        return customerBalance;
    }
}
