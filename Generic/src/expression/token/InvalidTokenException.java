package expression.token;

import expression.generic.ParsingException;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class InvalidTokenException extends ParsingException {
    public InvalidTokenException(String message, int idx) {
        super(String.format("unknown token %s at position %d", message, idx + 1));    }

    public InvalidTokenException(char message, int idx) {
        super(String.format("unknown token %s at position %d", message, idx + 1));
    }
}
