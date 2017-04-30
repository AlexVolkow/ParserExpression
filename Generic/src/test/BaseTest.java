package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class BaseTest {
    public final Random random = new Random(7240958270458L);

    private int ops;

    protected BaseTest() {
        checkAssert(getClass());
    }

    public void assertTrue(final String message, final boolean condition) {
        assert condition : message;
        op();
    }

    protected void op() {
        ops(1);
    }

    protected void ops(final int ops) {
        this.ops += ops;
    }

    public void assertEquals(final String message, final int actual, final int expected) {
        assertTrue(String.format("%s: Expected %d, found %d", message, expected, actual), actual == expected);
    }

    public void assertEquals(final String message, final Object actual, final Object expected) {
        assertTrue(String.format("%s: Expected \"%s\", found \"%s\"", message, expected, actual), actual != null && actual.equals(expected) || expected == null);
    }

    public void assertEquals(final String message, final double precision, final double actual, final double expected) {
        assertTrue(
                String.format("%s: Expected %.12f, found %.12f", message, expected, actual),
                Math.abs(actual - expected) < precision ||
                        Math.abs(actual - expected) < precision * Math.abs(actual) ||
                        (Double.isNaN(actual) || Double.isInfinite(actual)) &&
                        (Double.isNaN(expected) || Double.isInfinite(expected))
        );
    }

    private void checkAssert(final Class<?> c) {
        Locale.setDefault(Locale.US);

        boolean assertsEnabled = false;
        assert assertsEnabled = true;
        if (!assertsEnabled) {
            throw new AssertionError("You should enable assertions by running 'java -ea " + c.getName() + "'");
        }
    }

    public static String repeat(final String s, final int n) {
        return Stream.generate(() -> s).limit(n).collect(Collectors.joining());
    }

    public <T> T random(final List<T> variants) {
        return variants.get(random.nextInt(variants.size()));
    }

    @SafeVarargs
    public final <T> T random(final T... variants) {
        return random(Arrays.asList(variants));
    }

    public int randomInt(final int n) {
        return random.nextInt(n);
    }

    public void run() {
        test();
        System.out.format("%s OK: %d%n", getClass().getSimpleName(), ops);
    }

    protected abstract void test();

    @SafeVarargs
    public static <T> List<T> list(final T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static void addRange(final List<Integer> values, final int d, final int c) {
        for (int i = -d; i <= d; i++) {
            values.add(c + i);
        }
    }

    public static final class Op<T> {
        public final String name;
        public final T f;

        private Op(final String name, final T f) {
            this.name = name;
            this.f = f;
        }
    }

    public static <T> Op<T> op(final String name, final T f) {
        return new Op<>(name, f);
    }
}
