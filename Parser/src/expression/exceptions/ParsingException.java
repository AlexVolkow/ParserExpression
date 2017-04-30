package expression.exceptions;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class ParsingException extends Exception {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message,int idx) {
        super(String.format("%s at position %d",message,idx + 1));
    }
}
