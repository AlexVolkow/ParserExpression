package expression.parser;


import expression.generic.ParsingException;

/**
 * Created by Alexandr Volkov on 01.04.2017.
 */
public class ConstantOverflowException extends ParsingException {
    public ConstantOverflowException(String message) {
        super(String.format("constant overflow %s",message));
    }

    public ConstantOverflowException(String message, int idx) {
        super(String.format("constant overflow %s at position %d",message, idx + 1));
    }
}
