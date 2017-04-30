package expression.operator.checked;

import expression.operator.BinOperator;
import expression.TripleExpression;
import expression.exceptions.OverflowException;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class CheckedMultiply extends BinOperator {
    public CheckedMultiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws ArithmeticException {
        if (left > 0 && right > 0 && Integer.MAX_VALUE / right < left ||
                left > 0 && right < 0 && Integer.MIN_VALUE / left > right ||
                left < 0 && right > 0 && Integer.MIN_VALUE / right > left ||
                left < 0 && right < 0 && Integer.MAX_VALUE / right > left) {
            throw new OverflowException(left, "*", right);
        }
        return left * right ;
    }
}
