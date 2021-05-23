package classes;

import exceptions.validations.CardValidation;
import files.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static classes.CardCrud.createCard;
import static classes.CardCrud.updateCard;

public class CardCrud {

    private static CardCrud instance = null;

    public static CardCrud getInstance(){
        if (instance == null)
            instance = new CardCrud();
        return instance;
    }

    private static Connection getConnection() {
        try {
            Timestamp.timestamp("CardCrud,getConnection");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "<hm51f/nLwao");
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conectarea la baza de date.");
//            e.printStackTrace();
//            return null;
        }
    }

    public static void createCard(Card card) throws Exception{
        try (Connection connection = getConnection()) {
            Timestamp.timestamp("CardCrud,createCard");

            CardValidation validateCard = new CardValidation();

            validateCard.cardValidation(card);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(1) as NumberOfRows from card");

            resultSet.next();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into card values(?,?,?,?,?,?)");

            preparedStatement.setInt(1, resultSet.getInt("NumberOfRows")+1);
            preparedStatement.setString(2, card.getCardNumber());
            preparedStatement.setString(3, card.getExpirationMonth());
            preparedStatement.setString(4, card.getExpirationYear());
            preparedStatement.setString(5, card.getCustomerName());
            preparedStatement.setString(6, card.getPin());

            preparedStatement.executeUpdate();
            System.out.println("Cardul " + card.getCardNumber() + " a fost introdus in baza de date cu succes.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la inserarea cardului " + card.getCardNumber() + " in baza de date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Card> readCard() throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CardCrud,readCard");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from card");

            List<Card> cardList = new ArrayList<>();

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
                cardList.add(card);
            }

            return cardList;
        } catch (SQLException e){
            throw new RuntimeException("Eroarea la citirea cardurilor din baza de date.");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateCard(Card card) throws Exception{
        try (Connection connection = getConnection()){
            Timestamp.timestamp("CardCrud,updateCard");

            CardValidation validateCard = new CardValidation();

            validateCard.cardValidation(card);

            PreparedStatement preparedStatement = connection.prepareStatement("update card set card_number=?,expiration_month=?," +
                    "expiration_year=?,customer_name=?,pin=? where card_number like ?");

            preparedStatement.setString(2,card.getCardNumber());
            preparedStatement.setString(3,card.getExpirationMonth());
            preparedStatement.setString(4,card.getExpirationYear());
            preparedStatement.setString(5,card.getCustomerName());
            preparedStatement.setString(6,card.getPin());

            preparedStatement.executeUpdate();
            System.out.println("Cardul " + card.getCardNumber() + " a fost modificat cu succes.");
        } catch (SQLException e){
            throw new RuntimeException("Eroare la modificarea cardului " + card.getCardNumber());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

class Test{
    public static void main(String[] args) throws Exception {

//        System.out.println(CardCrud.getInstance().readCard().size());
//        Card card = new Card("5555367890987654","12","30","Popescu Mircea","4555");
//        updateCard(card);
//        for(int i=0; i<CardCrud.getInstance().readCard().size(); i++)
//            System.out.println(CardCrud.getInstance().readCard().get(i).toString());
    }
}