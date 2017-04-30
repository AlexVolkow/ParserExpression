package expression.operator.unchecked;

import expression.TripleExpression;
import expression.operator.BinOperator;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public class Divide extends BinOperator {
    public Divide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) {
        return left / right;
    }
}
