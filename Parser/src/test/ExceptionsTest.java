package test;

import expression.TripleExpression;
import expression.variable.Variable;
import expression.operator.checked.*;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;

import static test.Util.*;

/**
 * @author Niyaz Nigmatullin
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ExceptionsTest extends ParserTest {
    private final static int D = 5;
    private final static List<Integer> OVERFLOW_VALUES = new ArrayList<>();
    private final char[] CHARS = "AZ-+*%()[]<>".toCharArray();

    public static final Variable VX = new Variable("x");
    public static final Variable VY = new Variable("y");
    public static final Reason OVERFLOW = new Reason("Overflow");

    static {
        addRange(OVERFLOW_VALUES, D, Integer.MIN_VALUE + D);
        addRange(OVERFLOW_VALUES, D, Integer.MIN_VALUE / 2);
        addRange(OVERFLOW_VALUES, D, (int) -Math.sqrt(Integer.MAX_VALUE));
        addRange(OVERFLOW_VALUES, D, 0);
        addRange(OVERFLOW_VALUES, D, (int) Math.sqrt(Integer.MAX_VALUE));
        addRange(OVERFLOW_VALUES, D, Integer.MAX_VALUE / 2);
        addRange(OVERFLOW_VALUES, D, Integer.MAX_VALUE - D);
    }

    private int subtests = 0;

    protected final List<Op<String>> parsingTest = new ArrayList<>(Arrays.asList(
            op("No first argument", "* y * z"),
            op("No middle argument", "x *  * z"),
            op("No last argument", "x * y * "),
            op("No first argument'", "1 + (* y * z) + 2"),
            op("No middle argument'", "1 + (x *  / 9) + 3"),
            op("No last argument'", "1 + (x * y - ) + 3"),
            op("No opening parenthesis", "x * y)"),
            op("No closing parenthesis", "(x * y"),
            op("Start symbol", "@x * y"),
            op("Middle symbol", "x @ * y"),
            op("End symbol", "x * y@"),
            op("Constant overflow 1", Integer.MIN_VALUE - 1L + ""),
            op("Constant overflow 2", Integer.MAX_VALUE + 1L + "")
    ));

    public static void main(final String[] args) {
        new ExceptionsTest().run();
    }

    private void testParsingErrors() {
        for (final Op<String> op : parsingTest) {
            total++;
            try {
                new ExpressionParser().parse(op.f);
                assert false : "Successfully parsed " + op.f;
            } catch (final Exception e) {
                System.out.format("%-30s %s", op.name, e.getClass().getSimpleName() + ": " + e.getMessage());
                System.out.println();
            }
        }
    }

    protected void testOverflow() {
        testOverflow((a, b) -> a + b, "+", new CheckedAdd(VX, VY));
        testOverflow((a, b) -> a - b, "-", new CheckedSubtract(VX, VY));
        testOverflow((a, b) -> a * b, "*", new CheckedMultiply(VX, VY));
        testOverflow((a, b) -> b == 0 ? Long.MAX_VALUE : a / b, "/", new CheckedDivide(VX, VY));
        testOverflow((a, b) -> -b, "<- ignore first argument, unary -", new CheckedNegate(VY));

        System.out.println("OK, " + subtests + " subtests");
    }

    protected void testOverflow(final LongBinaryOperator f, final String op, final TripleExpression expression) {
        for (final int a : OVERFLOW_VALUES) {
            for (final int b : OVERFLOW_VALUES) {
                final long expected = f.applyAsLong(a, b);
                try {
                    final int actual = expression.evaluate(a, b, 0);
                    assert actual == expected : a + " " + op + " " + b + " == " + actual;
                } catch (final Exception e) {
                    if (Integer.MIN_VALUE <= expected && expected <= Integer.MAX_VALUE) {
                        throw new AssertionError("Unexpected error in " + a + " " + op + " " + b, e);
                    }
                }
                ++subtests;
            }
        }
    }

    @Override
    protected void test() {
        testOverflow();
        super.test();
        testParsingErrors();
    }

    protected TripleExpression parse(final String expression, final boolean reparse) {
        final Parser parser = new ExpressionParser();
        if (expression.length() > 10) {
            loop: for (final char ch : CHARS) {
                for (int i = 0; i < 10; i++) {
                    final int index = 1 + randomInt(expression.length() - 2);
                    final char c = expression.charAt(index);
                    if ("-( *".indexOf(c) < 0 && !Character.isLetterOrDigit(c)) {
                        final String input = expression.substring(0, index) + ch + expression.substring(index);
                        try {
                            parser.parse(input);
                            throw new AssertionError("Parsing error expected for " + expression.substring(0, index) + "<ERROR_INSERTED -->" + ch + "<-- ERROR_INSERTED>" + expression.substring(index));
                        } catch (final Exception e) {
                            // Ok
                        }
                        continue loop;
                    }
                }
            }
        }
        try {
            return parser.parse(expression);
        } catch (final Exception e) {
            throw new AssertionError("Parser failed", e);
        }
    }

    @Override
    protected Either<Reason, Integer> lift(final long value) {
        return value < Integer.MIN_VALUE || Integer.MAX_VALUE < value
                ? Either.left(OVERFLOW)
                : super.lift(value);
    }
}