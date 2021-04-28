package exceptions.validations;


import classes.Card;
import exceptions.InvalidInitializationException;

public class CardValidation {

    public void cardValidation(Card card) throws InvalidInitializationException {
        if(!card.getCardNumber().matches("[0-9]{16}"))
            throw new InvalidInitializationException("Numar card invalid");
        if(!card.getExpirationMonth().matches("01|02|03|04|05|06|07|08|09|10|11|12"))
            throw new InvalidInitializationException("Luna expirarii invalida");
        if(!card.getExpirationYear().matches("[0-9]{2}"))
            throw new InvalidInitializationException("Anul expirarii invalid");
        if(!card.getCustomerName().matches("[\\s+a-zA-z]+"))
            throw new InvalidInitializationException("Nume client invalid");
        if(!card.getPin().matches("[0-9]{4}"))
            throw new InvalidInitializationException("PIN invalid");
    }
}
