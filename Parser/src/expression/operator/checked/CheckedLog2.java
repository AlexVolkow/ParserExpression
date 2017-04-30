package expression.operator.checked;

import expression.TripleExpression;
import expression.operator.UnaryOperator;

/**
 * Created by Alexandr Volkov on 03.04.2017.
 */
public class CheckedLog2 extends UnaryOperator {
    public CheckedLog2(TripleExpression val) {
        super(val);
    }

    @Override
    protected int evaluateImpl(int val) {
        if (val <= 0) {
            throw new IllegalArgumentException("log2 " + val);
        }
        int result = -1;
        while (val > 0) {
            val /= 2;
            result++;
        }
        return result;
    }
}
