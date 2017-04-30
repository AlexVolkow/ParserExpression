package expression.parser;

/**
 * Created by Alexandr Volkov on 06.04.2017.
 */
public class UnsupportedTypeException extends Exception {
    public UnsupportedTypeException(String message) {
        super("unsupported type" + message);
    }
}
