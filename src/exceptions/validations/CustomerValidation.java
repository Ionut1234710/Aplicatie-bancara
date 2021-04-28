package exceptions.validations;

import classes.Customer;
import exceptions.InvalidInitializationException;

public class CustomerValidation {

    public void customerValidation(Customer customer) throws InvalidInitializationException {
        if(!customer.getLast_name().matches("[a-zA-Z]+"))
            throw new InvalidInitializationException("Nume client invalid");
        if(!customer.getFirst_name().matches("[a-zA-Z]+"))
            throw new InvalidInitializationException("Prenume client invalid");
        if(!customer.getCnp().matches("[0-9]{13}"))
            throw new InvalidInitializationException("CNP client invalid");
        if(!customer.getAddress().matches("[\\s+a-zA-Z0-9,]+")) //Nume strada, numar  -->  Vlad Tepes, 20
            throw new InvalidInitializationException("Adresa invalida");
        if(!customer.getEmail().matches("[a-zA-Z0-9.@]+"))
            throw new InvalidInitializationException("Email invalid");
    }
}
