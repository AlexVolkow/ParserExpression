package expression.exceptions;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException(int left) {
        super(String.format("division by zero %d / 0", left));
    }
}
