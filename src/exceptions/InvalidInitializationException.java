package exceptions;

import javax.naming.NamingException;

public class InvalidInitializationException extends NamingException {

    private static final long serialVersionUID = -1L;

    public InvalidInitializationException(){
        super();
    }

    public InvalidInitializationException(String message){
        super(message);
    }
}
