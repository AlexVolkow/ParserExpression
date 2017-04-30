package expression.operator.checked;

import expression.operator.BinOperator;
import expression.TripleExpression;
import expression.exceptions.DivideByZeroException;
import expression.exceptions.OverflowException;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class CheckedDivide extends BinOperator {
    public CheckedDivide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws ArithmeticException {
        if (right == 0) {
            throw new DivideByZeroException(left);
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException(left, "/", right);
        }
        return left / right;
    }
}
