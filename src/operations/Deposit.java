package operations;

import classes.Account;
import classes.Transaction;

public class Deposit extends Transaction {

    private double depositAmount = 0.0;

    public Deposit(){
        super();
        depositAmount = 0.0;
    }

    public Deposit(String currency, String transactionHour, String transactionDay,
                   String transactionMonth, String transactionYear, double depositAmount){

        super(currency,transactionHour,transactionDay,transactionMonth,transactionYear);
        this.depositAmount = depositAmount;
    }

    public void deposit(double amount, Account account){
        account.setBalance(account.getBalance()+amount);
        depositAmount = amount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Override
    public String toString() {
        return "Transaction:  " + "ID = " + id + ",  Currency = " + currency + ",  TransactionDate = " +
                transactionMonth + "/" + transactionDay + "/" + transactionYear + " " + transactionHour +
                "\nTransaction type -> Deposit:  " + "Deposited amount = " + depositAmount + "\n";
    }

}
