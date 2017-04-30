package expression.parser;

import expression.TripleExpression;
import expression.generic.ParsingException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T extends Number> {
    TripleExpression<T> parse(String expression) throws ParsingException;
}
