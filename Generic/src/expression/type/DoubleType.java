package expression.type;


/**
 * Created by Alexandr Volkov on 06.04.2017.
 */
public class DoubleType extends AbstractType<Double> {
    public DoubleType(Double value) {
        super(value);
    }

    public static DoubleType parse(String s) {
        return new DoubleType(Double.parseDouble(s));
    }

    @Override
    protected Type<Double> valueOf(Double value) {
        return new DoubleType(value);
    }

    @Override
    public Double absImpl() {
        return Math.abs(value);
    }

    @Override
    public Double modImpl(Double v) {
        return value % v;
    }

    @Override
    public Double addImpl(Double v) {
        return value + v;
    }

    @Override
    public Double subtractImpl(Double v) {
        return value - v;
    }

    @Override
    public Double multiplyImpl(Double v) {
        return value * v;
    }

    @Override
    public Double divideImpl(Double v) {
        return value / v;
    }

    @Override
    public Double negateImpl() {
        return -value;
    }
}
