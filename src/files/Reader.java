package files;

import classes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public String path = System.getProperty("user.dir") + "\\src\\files\\resources\\readers";

    private static Reader instance = null;

    private Reader(){

    }

    public static Reader getInstance(){
        if (instance == null)
            instance = new Reader();
        return instance;
    }

    public Bank bankReader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\Bank.csv"));
            Bank bank = new Bank();
            bank.setBankName(bufferedReader.readLine());
            bufferedReader.close();
            return bank;
        } catch (IOException e){
            System.out.println("Eroare la citirea din fisierul Bank.csv");
            return null;
        }
    }

    public List<Account> accountReader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\Accounts.csv"));
            String linie="";
            List<Account> accountList = new ArrayList<>();
            while((linie = bufferedReader.readLine()) != null){
                String[] tokens = linie.split(",");
                Account account = new Account();
                account.setIban(tokens[0]);
                account.setBic(tokens[1]);
                account.setBalance(Double.parseDouble(tokens[2]));
                account.setCurrency(tokens[3]);
                accountList.add(account);
            }
            bufferedReader.close();
            return accountList;
        } catch (IOException e){
            System.out.println("Eroare la citirea din fisierul Accounts.csv");
            return null;
        }
    }

    public List<Customer> customersReader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\Customers.csv"));
            String linie="";
            List<Customer> customerList = new ArrayList<>();
            while((linie = bufferedReader.readLine()) != null){
                String[] tokens = linie.split(",");
                Customer customer = new Customer();
                customer.setLast_name(tokens[0]);
                customer.setFirst_name(tokens[1]);
                customer.setCnp(tokens[2]);
                customer.setAddress(tokens[3] + ", " + tokens[4]);
                customer.setEmail(tokens[5]);
                customerList.add(customer);
            }
            bufferedReader.close();
            return customerList;
        } catch (IOException e){
            System.out.println("Eroare la citirea din fisierul Customers.csv");
            return null;
        }
    }

    public List<Card> cardsReader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\Cards.csv"));
            String linie="";
            List<Card> cardList = new ArrayList<>();
            while((linie = bufferedReader.readLine()) != null){
                String[] tokens = linie.split("[,/]+");
                Card card = new Card();
                card.setCardNumber(tokens[0]);
                card.setExpirationMonth(tokens[1]);
                card.setExpirationYear(tokens[2]);
                card.setCustomerName(tokens[3]);
                card.setPin(tokens[4]);
                cardList.add(card);
            }
            bufferedReader.close();
            return cardList;
        } catch (IOException e){
            System.out.println("Eroare la citirea din fisierul Cards.csv");
            return null;
        }
    }

    public Contact contactReader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\Contact.csv"));
            String linie="";
            Contact contact = new Contact();
            while((linie = bufferedReader.readLine()) != null ){
                String[] tokens = linie.split(",");
                contact.setPhone_number(tokens[0]);
                contact.setMail_address(tokens[1]);
                contact.setFacebook_address(tokens[2]);
            }
            bufferedReader.close();
            return contact;
        } catch (IOException e){
            System.out.println("Eroare la citirea din fisierul Contact.csv");
            return null;
        }
    }

}
