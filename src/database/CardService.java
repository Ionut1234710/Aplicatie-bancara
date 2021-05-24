package database;

import classes.Card;
import exceptions.validations.CardValidation;
import files.Timestamp;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CardService {

    private static CardService instance = null;

    public static CardService getInstance(){
        if (instance == null)
            instance = new CardService();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("CardService,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
        }
    }

    public static void createCard(Card card, int account_id_fk2) throws Exception{
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("CardService,createCard");

            CardValidation validateCard = new CardValidation();

            validateCard.cardValidation(card);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into card(card_number,expiration_month,expiration_year," +
                    "customer_name,pin,account_id2) values(?,?,?,?,?,?)");

            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setString(2, card.getExpirationMonth());
            preparedStatement.setString(3, card.getExpirationYear());
            preparedStatement.setString(4, card.getCustomerName());
            preparedStatement.setString(5, card.getPin());
            preparedStatement.setInt(6,account_id_fk2);

            preparedStatement.executeUpdate();
            System.out.println("Cardul " + card.getCardNumber() + " a fost introdus in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea cardului " + card.getCardNumber() + " in baza de date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Card, Integer> readCard() throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CardService,readCard");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from card");

            Map<Card, Integer> cardIntegerMap = new HashMap<Card, Integer>();

            CardValidation validateCard = new CardValidation();

            while(resultSet.next()){
                Card card = new Card();
                card.setId(resultSet.getString(1));
                card.setCardNumber(resultSet.getString(2));
                card.setExpirationMonth(resultSet.getString(3));
                card.setExpirationYear(resultSet.getString(4));
                card.setCustomerName(resultSet.getString(5));
                card.setPin(resultSet.getString(6));
                validateCard.cardValidation(card);
                cardIntegerMap.put(card,resultSet.getInt(7));
            }

            return cardIntegerMap;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea cardurilor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateCard(Card card, int id_card, int account_id_fk2) throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CardService,updateCard");

            CardValidation validateCard = new CardValidation();

            validateCard.cardValidation(card);

            PreparedStatement preparedStatement = connection.prepareStatement("update card set card_number=?,expiration_month=?," +
                    "expiration_year=?,customer_name=?,pin=?,account_id2=? where idcard=?");

            preparedStatement.setString(1,card.getCardNumber());
            preparedStatement.setString(2,card.getExpirationMonth());
            preparedStatement.setString(3,card.getExpirationYear());
            preparedStatement.setString(4,card.getCustomerName());
            preparedStatement.setString(5,card.getPin());
            preparedStatement.setInt(6,account_id_fk2);
            preparedStatement.setInt(7,id_card);

            preparedStatement.executeUpdate();
            System.out.println("Cardul cu id-ul " + id_card + " a fost modificat cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea cardului cu id-ul " + id_card);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCard(int id_card){
        try(Connection connection = getConnection()){
            Timestamp.timestamp("CardService,deleteCard");

            PreparedStatement preparedStatement = connection.prepareStatement("delete from card where idcard=?");

            preparedStatement.setInt(1,id_card);

            preparedStatement.executeUpdate();
            System.out.println("Stergerea cardului cu id-ul " + id_card + " a avut succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la stergerea cardului cu id-ul " + id_card);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}


//class Test{
//    public static void main(String[] args) throws Exception {
//        Card card = new Card("2938474738811100","01","21","Popescu Ion","1555");
//        CardService.updateCard(card,2,2);
//        CardService.createCard(card,1);
//        CardService.deleteCard(1);
//        for(Map.Entry entry : CardService.getInstance().readCard().entrySet())
//        {
//            System.out.println(entry.getKey() + "account_id_fk: " + entry.getValue());
//        }
//    }
//}