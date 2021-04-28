package operations;

import classes.Account;
import classes.Transaction;
import exceptions.InsufficientFundsException;

public class Transfer extends Transaction {

    private double transferAmount;

    public Transfer(){
        super();
        this.transferAmount =0.0;
    }

    public Transfer(String currency, String transactionHour, String transactionDay,
                    String transactionMonth, String transactionYear, double transferAmount){

        super(currency,transactionHour,transactionDay,transactionMonth,transactionYear);
        this.transferAmount = transferAmount;
    }

    public void transfer(double amount, Account accPayer, Account accReceiver) throws InsufficientFundsException {
        if(accPayer.getBalance() < amount){
            throw new InsufficientFundsException("Insufficient Funds for transfer");
        }
        accPayer.setBalance(accPayer.getBalance()-amount);
        accReceiver.setBalance(accReceiver.getBalance()+amount);
        this.transferAmount = amount;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction:  " + "ID = " + id + ",  Currency = " + currency + ",  TransactionDate = " +
                transactionMonth + "/" + transactionDay + "/" + transactionYear + " " + transactionHour +
                "\nTransaction type -> Transfer:  " + "Transferred amount = " + transferAmount + "\n";
    }
}
