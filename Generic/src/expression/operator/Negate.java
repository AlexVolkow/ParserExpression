package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * Created by Alexandr Volkov on 05.04.2017.
 */
public class Negate<T extends Number> extends UnaryOperator<T> {
    public Negate(TripleExpression<T> val) {
        super(val);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> val) {
        return val.negate();
    }
}
