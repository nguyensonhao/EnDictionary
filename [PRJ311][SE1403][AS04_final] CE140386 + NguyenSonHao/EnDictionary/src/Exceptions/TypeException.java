package Exceptions;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class TypeException extends Exception{

    /**
     * Exception Type
     * @param message
     */
    public TypeException(String message) {
        super("TypeException::" + message);
    }
    
}
