package exceptions.validations;

import classes.Account;
import exceptions.InsufficientFundsException;
import exceptions.InvalidInitializationException;

public class AccountValidation {

    public void accountValidation(Account account) throws InvalidInitializationException, InsufficientFundsException {
        if(!account.getIban().matches("^RO[0-9]{2}[A-Z]{4}[0-9]{16}")) //ROXXABCDXXXXXXXXXXXXXXXX
            throw new InvalidInitializationException("IBAN invalid");
        if(!account.getBic().matches("^[A-Z0-9]{8}"))                  //RNCBROBU
            throw new InvalidInitializationException("BIC invalid");
        if(account.getBalance() < 0)
            throw new InsufficientFundsException("Balanta invalida");
        if(!account.getCurrency().matches("[a-zA-Z]+"))
            throw new InvalidInitializationException("Valuta invalida");
    }

}
