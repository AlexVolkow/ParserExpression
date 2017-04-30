package expression.operator.checked;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operator.UnaryOperator;

/**
 * Created by Alexandr Volkov on 03.04.2017.
 */
public class CheckedPow2 extends UnaryOperator {
    public CheckedPow2(TripleExpression left) {
        super(left);
    }

    @Override
    protected int evaluateImpl(int v) throws OverflowException {
        if (v < 0) {
            throw new IllegalArgumentException("pow2 " + v);
        }
        if (v > 31) {
            throw new OverflowException("pow2", v);
        }
        return 1 << v;
    }
}
