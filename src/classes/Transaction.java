package classes;

import operations.Service;

public class Transaction implements Service {

    protected String id;
    protected String currency;
    protected String transactionHour;
    protected String transactionDay;
    protected String transactionMonth;
    protected String transactionYear;

    public Transaction(){
        this.id="";
        this.currency="";
        this.transactionHour="";
        this.transactionDay="";
        this.transactionMonth="";
        this.transactionYear="";
    }

    public Transaction(String currency, String transactionHour, String transactionDay,
                       String transactionMonth, String transactionYear) {
        this.id = genID();
        this.currency = currency;
        this.transactionHour = transactionHour;
        this.transactionDay = transactionDay;
        this.transactionMonth = transactionMonth;
        this.transactionYear = transactionYear;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionHour() {
        return transactionHour;
    }

    public void setTransactionHour(String transactionHour) {
        this.transactionHour = transactionHour;
    }

    public String getTransactionDay() {
        return transactionDay;
    }

    public void setTransactionDay(String transactionDay) {
        this.transactionDay = transactionDay;
    }

    public String getTransactionMonth() {
        return transactionMonth;
    }

    public void setTransactionMonth(String transactionMonth) {
        this.transactionMonth = transactionMonth;
    }

    public String getTransactionYear() {
        return transactionYear;
    }

    public void setTransactionYear(String transactionYear) {
        this.transactionYear = transactionYear;
    }

    @Override
    public String toString(){
        return "Transaction:  " + "ID = " + id + ",  Currency = " + currency + ",  TransactionDate = " +
                transactionMonth + "/" + transactionDay + "/" + transactionYear + " " + transactionHour;
    }

    @Override
    public String getId() { return id;}
}
