package classes;

import exceptions.validations.CardValidation;
import operations.Service;

public class Card implements Service {

    private String id;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String customerName;
    private String pin;

    public Card(){
        this.id="";
        this.cardNumber="";
        this.expirationMonth="";
        this.expirationYear="";
        this.customerName="";
        this.pin="";
    }

    public Card(String cardNumber, String expirationMonth, String expirationYear, String customerName, String pin){
        this.id = genID();
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.customerName = customerName;
        this.pin = pin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString(){
        return "Card:  " + "ID = " + id + ",  CardNumber = " + cardNumber + ",  ExpirationMonth = " + expirationMonth +
                ",  ExpirationYear = " + expirationYear + ",  CustomerName = " + customerName + ",  PIN = " + pin + "\n";
    }

    @Override
    public String getId() { return id; }

}