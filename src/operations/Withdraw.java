package operations;

import classes.Account;
import classes.Transaction;
import exceptions.InsufficientFundsException;

public class Withdraw extends Transaction {

    private double withdrawAmount = 0.0;

    public Withdraw() {
        super();
        withdrawAmount = 0.0;
    }

    public Withdraw(String currency, String transactionHour, String transactionDay,
                    String transactionMonth, String transactionYear, double withdrawAmount) {

        super(currency, transactionHour, transactionDay, transactionMonth, transactionYear);
        this.withdrawAmount = withdrawAmount;
    }

    public void withdraw(double amount, Account account) throws InsufficientFundsException {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient Funds for withdraw");
        }
        account.setBalance(account.getBalance()-amount);
        this.withdrawAmount = amount;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public String toString() {
        return "Transaction:  " + "ID = " + id + ",  Currency = " + currency + ",  TransactionDate = " +
                transactionMonth + "/" + transactionDay + "/" + transactionYear + " " + transactionHour +
                "\nTransaction type -> Withdraw:  " + "Withdrawed amount = " + withdrawAmount + "\n";
    }
}
