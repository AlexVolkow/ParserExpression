package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * Created by Alexandr Volkov on 10.04.2017.
 */
public class Abs<T extends Number> extends UnaryOperator<T> {
    public Abs(TripleExpression<T> val) {
        super(val);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> val) {
        return val.abs();
    }
}
