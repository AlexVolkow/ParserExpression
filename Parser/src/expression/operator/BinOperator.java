package expression.operator;

import expression.TripleExpression;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public abstract class BinOperator implements TripleExpression {
    private TripleExpression left;
    private TripleExpression right;

    public BinOperator(TripleExpression left, TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract int evaluateImpl(int left, int right);

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

}
