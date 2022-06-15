package Controller;

import Model.Transaction;
import View.TransactionView;

public class TransactionController {
    private Transaction transaction;
    private TransactionView transactionView = new TransactionView();

    public  TransactionController (Transaction transaction) {
        this.transaction = transaction;
    }

    public void startTransaction () {

    }
}
