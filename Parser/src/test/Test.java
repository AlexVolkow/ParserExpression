package test;

import expression.TripleExpression;
import expression.parser.ExpressionParser;
import expression.exceptions.DivideByZeroException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

/**
 * Created by Alexandr Volkov on 30.03.2017.
 */
public class Test {
    public static void main(String[] args) throws ParsingException{
        String expr = "1000000*x*x*x*x*x/(x-1)";
        ExpressionParser parser = new ExpressionParser();
        TripleExpression res = parser.parse(expr);
        System.out.format("%-8s%s\n", "x", "f");
        for (int x = 0; x <= 10; x++) {
            try {
                System.out.format("%-8d%d\n", x, res.evaluate(x, 0, 0));
            } catch (ArithmeticException e) {
                if (e instanceof OverflowException) {
                    System.out.format("%-8d%s\n", x, "overflow");
                }
                if (e instanceof DivideByZeroException) {
                    System.out.format("%-8d%s\n",x,"division by zero");
                }
            }
        }
    }
}
