package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public abstract class BinOperator<T extends Number> implements TripleExpression<T> {
    private TripleExpression<T> left;
    private TripleExpression<T> right;

    public BinOperator(TripleExpression<T> left, TripleExpression<T> right) {
        this.left = left;
        this.right = right;
    }

    protected abstract Type<T> evaluateImpl(Type<T> left, Type<T> right);

    @Override
    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

}
