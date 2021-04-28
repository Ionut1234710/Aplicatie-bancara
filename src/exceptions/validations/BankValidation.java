package exceptions.validations;


import classes.Bank;
import exceptions.InvalidInitializationException;

public class BankValidation {

    public void bankValidation(Bank bank) throws InvalidInitializationException {
        if (!bank.getBankName().matches("[\\s+a-zA-Z]+"))
            throw new InvalidInitializationException("Nume banca invalid");
    }
}
