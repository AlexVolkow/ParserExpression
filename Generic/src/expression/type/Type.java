package expression.type;

/**
 * Created by Alexandr Volkov on 05.04.2017.
 */
public interface Type<T extends Number> {
    T value();

    Type<T> add(Type<T> v);

    Type<T> subtract(Type<T> v);

    Type<T> multiply(Type<T> v);

    Type<T> divide(Type<T> v);

    Type<T> negate();

    Type<T> abs();

    Type<T> square();

    Type<T> mod(Type<T> v);
}
