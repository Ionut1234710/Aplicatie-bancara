package database;

import classes.Contact;
import classes.Transaction;
import exceptions.validations.ContactValidation;
import exceptions.validations.TransactionValidation;
import files.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactService {

    private static ContactService instance = null;

    public static ContactService getInstance(){
        if (instance == null)
            instance = new ContactService();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("ContactService,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public static void createContact(Contact contact, int bank_id_fk) throws Exception{
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("ContactService,createContact");

            ContactValidation validateContact = new ContactValidation();

            validateContact.contactValidation(contact);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into contact values(?,?,?,?)");

            preparedStatement.setString(1,contact.getPhone_number());
            preparedStatement.setString(2,contact.getMail_address());
            preparedStatement.setString(3,contact.getFacebook_address());
            preparedStatement.setInt(4,bank_id_fk);

            preparedStatement.executeUpdate();
            System.out.println("Contactul a fost introdus in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea contactului in baza de date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Contact, Integer> readContact() throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("ContactService,readContact");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from contact");

            Map<Contact, Integer> contactIntegerMap = new HashMap<Contact, Integer>();

            ContactValidation validateContact = new ContactValidation();

            while(resultSet.next()){
                Contact contact = new Contact();
                contact.setPhone_number(resultSet.getString(1));
                contact.setMail_address(resultSet.getString(2));
                contact.setFacebook_address(resultSet.getString(3));
                validateContact.contactValidation(contact);
                contactIntegerMap.put(contact,resultSet.getInt(4));
            }

            return contactIntegerMap;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea contactelor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateContact(Contact contact, String phone_number, int bank_id_fk) throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("ContactService,updateContact");

            ContactValidation validateContact = new ContactValidation();

            validateContact.contactValidation(contact);

            PreparedStatement preparedStatement = connection.prepareStatement("update contact set phone_number=?,mail_address=?,facebook_address=?," +
                    "bank_id=? where phone_number=?");

            preparedStatement.setString(1,contact.getPhone_number());
            preparedStatement.setString(2,contact.getMail_address());
            preparedStatement.setString(3,contact.getFacebook_address());
            preparedStatement.setInt(4,bank_id_fk);
            preparedStatement.setString(5,phone_number);

            preparedStatement.executeUpdate();
            System.out.println("Contactul cu numarul de telefon " + phone_number + " a fost modificat cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea contactului cu numarul de telefon " + phone_number);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteContact(String phone_number){
        try(Connection connection = getConnection()){
            Timestamp.timestamp("ContactService,deleteContact");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from contact where phone_number=?");

            preparedStatement.setString(1,phone_number);

            preparedStatement.executeUpdate();
            System.out.println("Stergerea contactului cu numarul de telefon " + phone_number + " a avut succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la stergerea contactului cu numarul de telefon " + phone_number);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

//class Test{
//    public static void main(String[] args) throws Exception {
//
//        Contact contact = new Contact("2440","infoBCR@gmail.com","Banca Comerciala Romana");
//        ContactService.createContact(contact,7);
//        ContactService.updateContact(contact,"1552",7);
//        for(Map.Entry entry : ContactService.getInstance().readContact().entrySet())
//        {
//            System.out.println(entry.getKey() + ";\nbank_id_fk: " + entry.getValue());
//        }
//        ContactService.deleteContact("0224");
//    }
//}
