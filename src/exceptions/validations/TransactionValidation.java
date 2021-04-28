package exceptions.validations;


import classes.Transaction;
import exceptions.InvalidInitializationException;

public class TransactionValidation {

    public void transactionValidation(Transaction transaction) throws InvalidInitializationException {
        if(!transaction.getCurrency().matches("[a-zA-Z]+"))
            throw new InvalidInitializationException("Valuta invalida");
        if(!transaction.getTransactionHour().matches("[0-9:]+")) // 16:25
            throw new InvalidInitializationException("Ora invalida");
        if(!transaction.getTransactionDay().matches("^[0-9]{2}"))
            throw new InvalidInitializationException("Ziua invalida");
        if(!transaction.getTransactionMonth().matches("01|02|03|04|05|06|07|08|09|10|11|12"))
            throw new InvalidInitializationException("Luna invalida");
        if(!transaction.getTransactionYear().matches("^[0-9]{4}")) // 2020
            throw new InvalidInitializationException("An invalid");
    }
}
