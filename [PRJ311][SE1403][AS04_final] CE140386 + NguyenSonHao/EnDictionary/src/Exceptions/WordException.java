package Exceptions;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class WordException extends Exception{

    /**
     * Exception Word
     * @param message
     */
    public WordException(String message) {
        super("WordException::" + message);
    }
    
}
