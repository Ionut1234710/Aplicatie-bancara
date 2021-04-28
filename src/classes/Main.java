package classes;

import exceptions.InsufficientFundsException;
import exceptions.InvalidInitializationException;
import exceptions.validations.*;
import files.Reader;
import files.Writer;
import operations.CurrencyExchange;
import operations.Deposit;
import operations.Transfer;
import operations.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        //Contact
        Contact contact = new Contact("2108","infoBCR@gmail.com","Banca Comerciala Romana");

        ContactValidation validateContact = new ContactValidation();
        validateContact.contactValidation(contact);

        //Customers
        Customer customer1 = new Customer("Lunganu", "Catalin", "5000303340933", "Vlad Tepes, 20", "ionutcaty23@gmail.com");
        Customer customer2 = new Customer("Popescu", "George", "1980211152743", "Apusului, 3", "Popescu5@gmail.com");
        Customer customer3 = new Customer("Preda","Gabriel","1931208215437","Rasaritului, 13","PredaGabriel11@yahoo.com");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        CustomerValidation validateCustomer = new CustomerValidation();
        validateCustomer.customerValidation(customer1);
        validateCustomer.customerValidation(customer2);
        validateCustomer.customerValidation(customer3);

        //Cards
        Card card1 = new Card("1234567890232456","05","22","Lunganu Catalin","1234");
        Card card2 = new Card("1234567890123456","12","23","Popescu George","3748");
        Card card3 = new Card("2947193002932667","03","24","Lunganu Catalin","0331");

        CardValidation validateCard = new CardValidation();
        validateCard.cardValidation(card1);
        validateCard.cardValidation(card2);
        validateCard.cardValidation(card3);

        //List of cards
        List<Card> cardList1 = new ArrayList<>();
        List<Card> cardList2 = new ArrayList<>();
        List<Card> cardList3 = new ArrayList<>();
        cardList1.add(card1);
        cardList1.add(card3);
        cardList2.add(card2);

        //Transactions
        Transaction transaction1 = new Deposit("RON","13:25","03","03","2020",1000);
        Transaction transaction2 = new Withdraw("RON","14:33","02","05","2021",500);
        Transaction transaction3 = new Transfer("RON","18:00","15","11","2020",1800);
        Transaction transaction4 = new Deposit("RON","10:25","03","04","2020",800);
        Transaction transaction5 = new Withdraw("RON","11:30","08","10","2021",300);
        Transaction transaction6 = new CurrencyExchange("EURO","18:35","10","12","2019",750);

        TransactionValidation validateTransaction = new TransactionValidation();
        validateTransaction.transactionValidation(transaction1);
        validateTransaction.transactionValidation(transaction2);
        validateTransaction.transactionValidation(transaction3);
        validateTransaction.transactionValidation(transaction4);
        validateTransaction.transactionValidation(transaction5);
        validateTransaction.transactionValidation(transaction6);

        //List of transactions
        List<Transaction> transactionList1 = new ArrayList<>();
        List<Transaction> transactionList2 = new ArrayList<>();
        List<Transaction> transactionList3 = new ArrayList<>();
        transactionList1.add(transaction1);
        transactionList1.add(transaction2);
        transactionList1.add(transaction3);
        transactionList2.add(transaction4);
        transactionList2.add(transaction5);
        transactionList3.add(transaction6);

        //Accounts
        Account account1 = new Account("RO03RNCB7263394909236421", "RNCBORMN", 3000, "RON", cardList1, customer1, transactionList1);
        Account account2 = new Account("RO02RNCB0582009427340001", "RNCBROBU", 1500, "RON", cardList2, customer2, transactionList2);
        Account account3 = new Account("RO03RNCB3829573920481000","EURORYNA",750,"EURO",cardList3,customer3,transactionList3);

        AccountValidation validateAccount = new AccountValidation();
        validateAccount.accountValidation(account1);
        validateAccount.accountValidation(account2);
        validateAccount.accountValidation(account3);

        //List of accounts
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);

        //Deposits, Withdraws and Transfers
        ((Deposit) transaction1).deposit(((Deposit) transaction1).getDepositAmount(), account1);
        ((Withdraw) transaction2).withdraw(((Withdraw) transaction2).getWithdrawAmount(), account1);
        ((Deposit) transaction4).deposit(((Deposit) transaction4).getDepositAmount(), account2);
        ((Withdraw) transaction5).withdraw(((Withdraw) transaction5).getWithdrawAmount(), account2);
        ((Transfer) transaction3).transfer(((Transfer) transaction3).getTransferAmount(), account1, account2);
        ((CurrencyExchange) transaction6).exchange(((CurrencyExchange) transaction6).getExchangeAmount(), "RON",account3);

        Bank bank = new Bank("Banca Comerciala Romana", contact, accountList);

        BankValidation validateBank = new BankValidation();
        validateBank.bankValidation(bank);

        //System.out.println(bank.toString());
//        Writer.getInstance().writerBank(bank);
//        Writer.getInstance().writerAccount(accountList);
//        Writer.getInstance().writerCard(cardList1);
//        Writer.getInstance().writerContact(contact);
//        Writer.getInstance().writerCustomer(customerList);

//        System.out.println(Reader.getInstance().contactReader().toString());
//        System.out.println(Reader.getInstance().cardsReader().toString());
//        System.out.println(Reader.getInstance().customersReader().toString());
//        System.out.println(Reader.getInstance().accountReader().toString());
//        System.out.println(Reader.getInstance().bankReader());
//        for(int i=0; i<Reader.getInstance().customersReader().size(); i++)
//            System.out.println(Reader.getInstance().customersReader().get(i).toString());

    }
}
