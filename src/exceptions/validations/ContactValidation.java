package exceptions.validations;


import classes.Contact;
import exceptions.InvalidInitializationException;

public class ContactValidation {

    public void contactValidation(Contact contact) throws InvalidInitializationException {
        if(!contact.getPhone_number().matches("[0-9]+"))
            throw new InvalidInitializationException("Numar de telefon invalid");
        if(!contact.getMail_address().matches("[a-zA-Z0-9@.]+"))
            throw new InvalidInitializationException("Adresa de mail invalida");
        if(!contact.getFacebook_address().matches("[\\s+a-zA-Z]+"))
            throw new InvalidInitializationException("Adresa de facebook invalida");
    }
}
