package database;

import classes.Card;
import classes.Customer;
import exceptions.validations.CardValidation;
import exceptions.validations.CustomerValidation;
import files.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {

    private static CustomerService instance = null;

    public static CustomerService getInstance(){
        if (instance == null)
            instance = new CustomerService();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("CustomerService,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public static void createCustomer(Customer customer, int account_id_fk3) throws Exception{
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("CustomerService,createCustomer");

            CustomerValidation validateCustomer = new CustomerValidation();

            validateCustomer.customerValidation(customer);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(first_name,last_name,cnp," +
                    "address,email,account_id3) values(?,?,?,?,?,?)");

            preparedStatement.setString(1, customer.getFirst_name());
            preparedStatement.setString(2, customer.getLast_name());
            preparedStatement.setString(3, customer.getCnp());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6,account_id_fk3);

            preparedStatement.executeUpdate();
            System.out.println("Clientul " + customer.getLast_name() + " " + customer.getFirst_name() + " a fost introdus in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea clientului " + customer.getLast_name() + " " + customer.getFirst_name() + " in baza de date.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Customer, Integer> readCustomer() throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CustomerService,readCustomer");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from customer");

            Map<Customer, Integer> customerIntegerMap = new HashMap<Customer, Integer>();

            CustomerValidation validateCustomer = new CustomerValidation();

            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getString(1));
                customer.setFirst_name(resultSet.getString(2));
                customer.setLast_name(resultSet.getString(3));
                customer.setCnp(resultSet.getString(4));
                customer.setAddress(resultSet.getString(5));
                customer.setEmail(resultSet.getString(6));
                validateCustomer.customerValidation(customer);
                customerIntegerMap.put(customer,resultSet.getInt(7));
            }

            return customerIntegerMap;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea clientilor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateCustomer(Customer customer, int id_customer, int account_id_fk3) throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CustomerService,updateCustomer");

            CustomerValidation validateCustomer = new CustomerValidation();

            validateCustomer.customerValidation(customer);

            PreparedStatement preparedStatement = connection.prepareStatement("update customer set first_name=?,last_name=?," +
                    "cnp=?,address=?,email=?,account_id3=? where idcustomer=?");

            preparedStatement.setString(1,customer.getFirst_name());
            preparedStatement.setString(2,customer.getLast_name());
            preparedStatement.setString(3,customer.getCnp());
            preparedStatement.setString(4,customer.getAddress());
            preparedStatement.setString(5,customer.getEmail());
            preparedStatement.setInt(6,account_id_fk3);
            preparedStatement.setInt(7,id_customer);

            preparedStatement.executeUpdate();
            System.out.println("Clientul cu id-ul " + id_customer + " a fost modificat cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea clientului cu id-ul " + id_customer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int id_customer){
        try(Connection connection = getConnection()){
            Timestamp.timestamp("CustomerService,deleteCustomer");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where idcustomer=?");

            preparedStatement.setInt(1,id_customer);

            preparedStatement.executeUpdate();
            System.out.println("Stergerea clientului cu id-ul " + id_customer + " a avut succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la stergerea clientului cu id-ul " + id_customer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

//class Test{
//    public static void main(String[] args) throws Exception{
//
//        Customer customer = new Customer("Popescu","Ion","5001212934855","Apusului, 7","popescu@gmail.com");
//        CustomerService.createCustomer(customer,2);
//        CustomerService.updateCustomer(customer,2,2);
//        CustomerService.deleteCustomer(1);
//
//        for(Map.Entry entry : CustomerService.getInstance().readCustomer().entrySet()){
//            System.out.println(entry.getKey() + "\naccount_id_fk3: " + entry.getValue());
//        }
//
//    }
//}
