package database;

import files.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    private static BankService instance = null;

    public static BankService getInstance(){
        if (instance == null)
            instance = new BankService();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("BankService,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public static void createBank(String bank_name){
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("BankService,createBank");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into bank(bank_name) values(?)");

            preparedStatement.setString(1, bank_name);

            preparedStatement.executeUpdate();
            System.out.println("Banca a fost introdus in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea Bancii in baza de date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> readBank(){
        try (Connection connection = getConnection()){
            Timestamp.timestamp("BankService,readBank");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from bank");

            List<String> bankList = new ArrayList<>();

            while(resultSet.next()){
                bankList.add(resultSet.getString(2));
            }

            return bankList;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea Bancilor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateBank(String new_bank_name, int id){
        try (Connection connection = getConnection()){
            Timestamp.timestamp("BankService,updateBank");

            PreparedStatement preparedStatement = connection.prepareStatement("update bank set bank_name=? where idbank=?");

            preparedStatement.setString(1,new_bank_name);
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();
            System.out.println("Banca cu id-ul " + id + " a fost modificata cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea Bancii cu id-ul " + id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteBank(int id){
        try(Connection connection = getConnection()){
            Timestamp.timestamp("BankService,deleteBank");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from bank where idbank=?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            System.out.println("Stergerea bancii cu id-ul " + id + " a avut succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la stergerea bancii cu id-ul " + id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

//class Test{
//    public static void main(String[] args){
//
////        BankService.createBank("Banca Comerciala Romana");
////        for(int i=0; i<BankService.getInstance().readBank().size(); i++)
////            System.out.println(BankService.getInstance().readBank().get(i));
//
////        BankService.updateBank("Raiffeisen Bank",5);
////        BankService.deleteBank(6);
//    }
//}