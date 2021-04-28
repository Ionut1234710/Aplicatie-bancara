package operations;


import classes.Account;
import classes.Transaction;
import exceptions.InsufficientFundsException;

public class CurrencyExchange extends Transaction {

    private static String[] currency = {"RON", "DOLLAR", "EURO"};
    private static double[][] exchangeRate = {{1.0, 0.24, 0.20}, {4.15, 1.0, 0.85}, {4.90, 1.18, 1.0}};
    private double exchangeAmount=0.0;
    private double exchangedAmount=0.0;
    private int indexCurrent=0;
    private int indexWanted=0;

    public CurrencyExchange(){
        super();
        this.exchangeAmount=0.0;
        this.exchangedAmount=0.0;
    }

    public CurrencyExchange(String currency, String transactionHour, String transactionDay,
                            String transactionMonth, String transactionYear, double exchangeAmount){
        super(currency,transactionHour,transactionDay,transactionMonth,transactionYear);
        this.exchangeAmount = exchangeAmount;
    }

    public double getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(double exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public double getExchangedAmount() {
        return exchangedAmount;
    }

    public void setExchangedAmount(double exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public void exchange(double valueForExchange, String wantedCurrency, Account account) throws InsufficientFundsException {

        if(account.getBalance() < valueForExchange){
            throw new InsufficientFundsException("Insufficient Funds for exchange");
        }
        for(int i=0; i<currency.length; i++){
            if(currency[i].equals(account.getCurrency())) {
                indexCurrent = i;
            }
            if(currency[i].equals(wantedCurrency)){
                indexWanted = i;
            }
        }
        account.setBalance(account.getBalance()-valueForExchange);
        this.exchangeAmount = valueForExchange;
        this.exchangedAmount = valueForExchange*exchangeRate[indexCurrent][indexWanted];
    }

    @Override
    public String toString() {
        return "Transaction:  " + "ID = " + id + ",  Currency = " + currency[indexCurrent] + ",  TransactionDate = " +
                transactionMonth + "/" + transactionDay + "/" + transactionYear + " " + transactionHour +
                "\nTransaction type -> Exchange:  " + "Exchange amount = " + exchangeAmount + " " + currency[indexCurrent] +
                ",  Exchanged amount = " + exchangedAmount + " " + currency[indexWanted] + "\n";
    }
}
