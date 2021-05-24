package database;


import classes.Transaction;
import exceptions.validations.TransactionValidation;
import files.Timestamp;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TransactionService {

    private static TransactionService instance = null;

    public static TransactionService getInstance(){
        if (instance == null)
            instance = new TransactionService();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("TransactionService,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public static void createTransaction(Transaction transaction, int account_id_fk) throws Exception{
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("TransactionService,createTransaction");

            TransactionValidation validateTransaction = new TransactionValidation();

            validateTransaction.transactionValidation(transaction);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into transaction(currency,transaction_hour," +
                    "transaction_day,transaction_month,transaction_year,account_id) values(?,?,?,?,?,?)");

            preparedStatement.setString(1, transaction.getCurrency());
            preparedStatement.setString(2, transaction.getTransactionHour());
            preparedStatement.setString(3, transaction.getTransactionDay());
            preparedStatement.setString(4, transaction.getTransactionMonth());
            preparedStatement.setString(5, transaction.getTransactionYear());
            preparedStatement.setInt(6,account_id_fk);

            preparedStatement.executeUpdate();
            System.out.println("Tranzactia cu valuta " + transaction.getCurrency() + " a fost introdusa in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea tranzactiei cu valuta " + transaction.getCurrency() + " in baza de date.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Transaction, Integer> readTransaction() throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("TransactionService,readTransaction");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from transaction");

            Map<Transaction, Integer> transactionIntegerMap = new HashMap<Transaction, Integer>();

            TransactionValidation validateTransaction = new TransactionValidation();

            while(resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getString(1));
                transaction.setCurrency(resultSet.getString(2));
                transaction.setTransactionHour(resultSet.getString(3));
                transaction.setTransactionDay(resultSet.getString(4));
                transaction.setTransactionMonth(resultSet.getString(5));
                transaction.setTransactionYear(resultSet.getString(6));
                validateTransaction.transactionValidation(transaction);
                transactionIntegerMap.put(transaction,resultSet.getInt(7));
            }

            return transactionIntegerMap;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea tranzactiilor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateTransaction(Transaction transaction, int id_transaction, int account_id_fk) throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("TransactionService,updateTransaction");

            TransactionValidation validateTransaction = new TransactionValidation();

            validateTransaction.transactionValidation(transaction);

            PreparedStatement preparedStatement = connection.prepareStatement("update transaction set currency=?,transaction_hour=?," +
                    "transaction_day=?,transaction_month=?,transaction_year=?,account_id=? where idtransaction=?");

            preparedStatement.setString(1,transaction.getCurrency());
            preparedStatement.setString(2,transaction.getTransactionHour());
            preparedStatement.setString(3,transaction.getTransactionDay());
            preparedStatement.setString(4,transaction.getTransactionMonth());
            preparedStatement.setString(5,transaction.getTransactionYear());
            preparedStatement.setInt(6,account_id_fk);
            preparedStatement.setInt(7,id_transaction);

            preparedStatement.executeUpdate();
            System.out.println("Tranzactia cu id-ul " + id_transaction + " a fost modificata cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea tranzactiei cu id-ul " + id_transaction);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteTransaction(int id_transaction){
        try(Connection connection = getConnection()){
            Timestamp.timestamp("TransactionService,deleteTransaction");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from transaction where idtransaction=?");

            preparedStatement.setInt(1,id_transaction);

            preparedStatement.executeUpdate();
            System.out.println("Stergerea tranzactiei cu id-ul " + id_transaction + " a avut succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la stergerea tranzactiei cu id-ul " + id_transaction);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

//class Test{
//    public static void main(String[] args) throws Exception{
//        Transaction transaction = new Transaction("RON","01:30","02","11","2019");
//        TransactionService.createTransaction(transaction,2);
//        TransactionService.updateTransaction(transaction,2,2);
//
//        TransactionService.deleteTransaction(1);
//        for(Map.Entry entry : TransactionService.getInstance().readTransaction().entrySet())
//        {
//            System.out.println(entry.getKey() + ";\naccount_id_fk: " + entry.getValue());
//        }
//
//    }
//}
