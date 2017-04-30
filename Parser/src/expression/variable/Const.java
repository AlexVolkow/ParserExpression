package expression.variable;

import expression.TripleExpression;

/**
 * Created by Alexandr Volkov on 15.03.2017.
 */
public class Const implements TripleExpression {
    public static final Const MINUS_ONE = new Const(-1);
    public static final Const ZERO = new Const(0);

    private int val;

    public Const(int val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val;
    }
}
