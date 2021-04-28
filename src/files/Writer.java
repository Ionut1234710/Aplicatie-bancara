package files;

import classes.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    public static String path = System.getProperty("user.dir") + "\\src\\files\\resources\\writers";

    private static Writer instance = null;

    private Writer() {

    }

    public static Writer getInstance() {
        if (instance == null)
            instance = new Writer();
        return instance;
    }

    public static void writerBank(Bank bank){
        try{
            Timestamp.timestamp("Writer,writerBank");
            FileWriter fileWriter = new FileWriter(path + "\\Bank.csv");
            fileWriter.append(bank.getBankName());
            fileWriter.append("\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Bank.csv");
        }
    }

    public static void writerAccount(List<Account> accountList){
        try{
            Timestamp.timestamp("Writer,writerAccount");
            FileWriter fileWriter = new FileWriter(path + "\\Accounts.csv");
            for(int i=0; i<accountList.size(); i++){
                fileWriter.append(accountList.get(i).getIban() + ", ");
                fileWriter.append(accountList.get(i).getBic() + ", ");
                fileWriter.append(String.valueOf(accountList.get(i).getBalance()) + ", ");
                fileWriter.append(accountList.get(i).getCurrency() + "\n");
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Accounts.csv");
        }
    }

    public static void writerCard(List<Card> cardList){
        try{
            Timestamp.timestamp("Writer,writerCard");
            FileWriter fileWriter = new FileWriter(path + "\\Cards.csv");
            for(int i=0; i<cardList.size(); i++){
                fileWriter.append(cardList.get(i).getCardNumber() + ", ");
                fileWriter.append(cardList.get(i).getExpirationMonth() + "/" + cardList.get(i).getExpirationYear() + ", ");
                fileWriter.append(cardList.get(i).getCustomerName() + ", ");
                fileWriter.append(cardList.get(i).getPin() + "\n");
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Cards.csv");
        }
    }

    public static void writerContact(Contact contact){
        try{
            Timestamp.timestamp("Writer,writerContact");
            FileWriter fileWriter = new FileWriter(path + "\\Contact.csv");
            fileWriter.append(contact.getPhone_number() + ", ");
            fileWriter.append(contact.getMail_address() + ", ");
            fileWriter.append(contact.getFacebook_address() + "\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Contact.csv");
        }
    }

    public static void writerCustomer(List<Customer> customerList){
        try{
            Timestamp.timestamp("Writer,writerCustomer");
            FileWriter fileWriter = new FileWriter(path + "\\Customers.csv");
            for(int i=0; i<customerList.size(); i++){
                fileWriter.append(customerList.get(i).getLast_name() + ", ");
                fileWriter.append(customerList.get(i).getFirst_name() + ", ");
                fileWriter.append(customerList.get(i).getCnp() + ", ");
                fileWriter.append(customerList.get(i).getAddress() + ", ");
                fileWriter.append(customerList.get(i).getEmail() + "\n");
            }
            fileWriter.close();
        } catch (IOException e){
            System.out.println("Eroare la scrierea in fisierul Customers.csv");
        }
    }
}
