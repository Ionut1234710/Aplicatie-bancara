package classes;

import operations.Service;

import java.util.ArrayList;
import java.util.List;

public class Account implements Service {

    private String id;
    private String iban;
    private String bic;
    private double balance;
    private String currency;
    List<Card> cardList;
    private Customer customer;
    List<Transaction> transactionList;

    public Account(){
        this.id="";
        this.iban="";
        this.bic="";
        this.balance=0;
        this.currency="";
        this.cardList=new ArrayList<>();
        this.customer=new Customer();
        this.transactionList=new ArrayList<>();
    }

    public Account(String iban, String bic, double balance, String currency, List<Card> cardList, Customer customer, List<Transaction> transactionList) {
        this.id = genID();
        this.iban = iban;
        this.bic = bic;
        this.balance = balance;
        this.currency = currency;
        this.cardList = cardList;
        this.customer = customer;
        this.transactionList = transactionList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void addCard(Card card){
        if(cardList.contains(card))
            System.out.println("Card " + card.getCardNumber() + " already exist.");
        else
            cardList.add(card);
    }

    public void removeCard(Card card){
        if(!cardList.contains(card))
            System.out.println("Card " + card.getCardNumber() + " doesn't exist.");
        else
            cardList.remove(card);
    }

    public void addTransaction(Transaction transaction){
        if(transactionList.contains(transaction))
            System.out.println("Transaction " + transaction.getId() + " already exist.");
        else
            transactionList.add(transaction);
    }

    public boolean checkCards(List<Card> cardList){
        return cardList.isEmpty(); //returneaza true daca lista este goala
    }

    public boolean checkTransactions(List<Transaction> transactionList){
        return transactionList.isEmpty(); //returneaza true daca lista este goala
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\nAccount:  " + "ID = " + id + ",  IBAN = " + iban + ",  BIC = " + bic + ",  Balance = " + balance +
                ",  Currency = " + currency + "\nThe account corresponds to the next customer -> " + customer.toString());
        if(!checkCards(cardList)){
            s.append("\nAccount has " + cardList.size() + " cards:\n");
            for(int i=0; i<cardList.size(); i++)
                s.append(cardList.get(i).toString());
        }
        else s.append("\nAccount has no cards\n");
        if(!checkTransactions(transactionList)){
            s.append("Account has " + transactionList.size() + " transactions:\n");
            for(int i=0; i<transactionList.size(); i++)
                s.append(transactionList.get(i).toString());
        }
        else s.append("\nAccount has no transactions\n");

        return s.toString();
    }

    @Override
    public String getId() { return id;}
}
