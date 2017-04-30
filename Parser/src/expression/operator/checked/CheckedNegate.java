package expression.operator.checked;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operator.UnaryOperator;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class CheckedNegate extends UnaryOperator {
    public CheckedNegate(TripleExpression left) {
        super(left);
    }

    @Override
    protected int evaluateImpl(int v) throws ArithmeticException {
        if (v == Integer.MIN_VALUE) {
            throw new OverflowException("-", v);
        }
        return -v;
    }
}
