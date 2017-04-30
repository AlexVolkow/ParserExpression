package expression.variable;

import expression.TripleExpression;
import expression.type.Type;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public class Const<T extends Number> implements TripleExpression<T> {

    private Type<T> val;

    public Const(Type<T> val) {
        this.val = val;
    }

    @Override
    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return val;
    }
}
