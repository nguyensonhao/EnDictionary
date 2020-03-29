package Exceptions;

/**
 *
 * @author Nguyen Son HAo CE140386
 */
public class MeaningException extends Exception{
    
    /**
     *Exception Meaning
     * @param message
     */
    public MeaningException (String message){
        super("MeaningException::" + message);
    } 
}
