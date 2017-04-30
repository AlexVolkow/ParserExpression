package expression.type;

import java.math.BigInteger;

/**
 * Created by Alexandr Volkov on 06.04.2017.
 */
public class BigIntType extends AbstractType<BigInteger> {
    public BigIntType(BigInteger value) {
        super(value);
    }

    public static Type<BigInteger> parse(String s) {
        return new BigIntType(new BigInteger(s));
    }

    @Override
    protected Type<BigInteger> valueOf(BigInteger value) {
        return new BigIntType(value);
    }

    @Override
    public BigInteger modImpl(BigInteger v) {
        return value.remainder(v);
    }

    @Override
    public BigInteger addImpl(BigInteger v) {
        return value.add(v);
    }

    @Override
    public BigInteger subtractImpl(BigInteger v) {
        return value.subtract(v);
    }

    @Override
    public BigInteger multiplyImpl(BigInteger v) {
        return value.multiply(v);
    }

    @Override
    public BigInteger divideImpl(BigInteger v) {
        return value.divide(v);
    }

    @Override
    public BigInteger negateImpl() {
        return value.negate();
    }

    @Override
    protected BigInteger absImpl() {
        return value.abs();
    }
}
