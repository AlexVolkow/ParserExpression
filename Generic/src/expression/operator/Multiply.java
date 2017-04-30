package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public class Multiply<T extends Number> extends BinOperator<T> {
    public Multiply(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> left, Type<T> right) {
        return left.multiply(right);
    }
}
