import org.codehaus.plexus.util.StringUtils;

import java.util.DoubleSummaryStatistics;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * The Postfix class implements an evaluator for integer postfix expressions.
 *
 * Postfix notation is a simple way to define and write arithmetic expressions
 * without the need for parentheses or priority rules. For example, the postfix
 * expression "1 2 - 3 4 + *" corresponds to the ordinary infix expression
 * "(1 - 2) * (3 + 4)". The expressions may contain decimal 32-bit integer
 * operands and the four operators +, -, *, and /. Operators and operands must
 * be separated by whitespace.
 *
 * @author  NN // TODO
 * @version 2013-02-01
 */
public class Postfix
{
    private static class ExpressionException extends Exception {
        public ExpressionException(String message) {
            super(message);
        }
    }

    /**
     * Evaluates the given postfix expression.
     *
     * @param expr  Arithmetic expression in postfix notation
     * @return      The value of the evaluated expression
     * @throws      ExpressionException if the expression is wrong
     */
    public static Double evaluate(String expr) throws ExpressionException
    {
        if ( (expr.startsWith("-0") && expr.length()>2)) {
            throw new ExpressionException("testa annat");
        }
        LinkedStack<Double> stack = new LinkedStack<>();
        String[] items = expr.trim().replaceAll("\t", " ").split("\\s+");
        int balancer1 = 0, balancer2 = 0;

        for (int i = 0; i<items.length;i++) {
            StringUtils.strip(items[i]);
            if (isOperator(items[i]) && stack.size() <2) {
                throw new ExpressionException("testa annat");
            }
            if (isInteger(items[i])) {
                if (items[i].length() > 1 && (items[i].startsWith("0"))) {
                    throw new ExpressionException("testa annat");
                }
                if (Long.parseLong(items[i]) > Integer.MAX_VALUE) {
                    throw new ExpressionException("testa annat");
                }
                double x = Double.parseDouble(items[i]);
                stack.push(x);
                balancer1++;
            } else if (isOperator(items[i])) {
                balancer2++;
                if ((balancer1 <= balancer2)) {
                    throw new ExpressionException("That doesn't work");
                }
                double a = stack.pop();
                double b = stack.pop();
                double result = 0;
                if (items[i].equals("+")) {
                    result = a+b;
                    stack.push(result);
                } else if (items[i].equals("-")) {
                    result = b-a;
                    stack.push(result);
                } else if (items[i].equals("*")) {
                    result = b*a;
                    stack.push(result);
                } else if (items[i].equals("/")) {
                    if (a == 0) {
                        throw new ExpressionException("kan ej dela med 0");
                    }
                    result = 1/a * b;
                    stack.push(result);
                }
            } else {
                throw new ExpressionException("The array is incorrect");
            }
        }
        double x = stack.pop();
        if (!stack.isEmpty()) {
            throw new ExpressionException("Stack not empty after final pop, something's wrong");
        }
        return x;
    }

    /**
     * Returns true if s is an operator.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * An operator is one of '+', '-', '*', '/'.
     */
    private static boolean isOperator(String s)
    {
        if  (s.matches("^[+\\-*/]$")) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if s is an integer.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * We accept two types of integers:
     *
     * - the first type consists of an optional '-'
     *   followed by a non-zero digit
     *   followed by zero or more digits,
     *
     * - the second type consists of an optional '-'
     *   followed by a single '0'.
     */
    private static boolean isInteger(String s) {
        if (!s.matches("^(-)?+[0-9]+$\\d*-?")) {
            return false;
        }
        return true;
    }

    /**
     * Unit test. Run with "java -ea Postfix".
     */
    public static void main(String[] args) throws ExpressionException
    {

        assert evaluate("0") == 0;
        assert evaluate("-0") == -0;
        assert evaluate("1234567890") == 1234567890;
        assert evaluate("-1234567890") == -1234567890;
        assert evaluate("1 23 +") == 1 + 23;
        assert evaluate("1	23	+") == 1 + 23; // tabs instead of spaces
        assert evaluate("0 1 /") == 0 / 1;
        assert evaluate("1 2 + -3 *") == (1 + 2) * -3;
        assert evaluate("12 34 - 56 -78 + *") == (12 - 34) * (56 + -78);
        assert evaluate("1 2 + 3 * 4 - 5 /") == (((1 + 2) * 3) - 4) / 5;
        assert evaluate("2 3 4 -0 + - *") == 2 * (3 - (4 + -0));
        assert evaluate("  		1 	-2	 + ") == 1 - 2; // tabs and spaces

        assert explodes("");
        assert explodes("+");
        assert explodes("--1");
        assert explodes("-1-0");
        assert explodes("-0-1");
        assert explodes("1 +");
        assert explodes("1 2 ,");
        assert explodes("1 2 .");
        assert explodes("1 2 3 +");
        assert evaluate("4") == 4;
        assert explodes("1 2 + +");
        assert explodes("017");
        assert explodes("0x17");
        assert explodes("-03");
        assert explodes("x");
        assert explodes("1234L");
        assert explodes("9876543210"); // larger than maxint
        assert explodes("1 0 /");
        assert explodes("1 2+");
        assert explodes("1 2 3 +*");
    }

    /**
     * Returns true if <code>evaluate(expr)</code> throws
     * a subclass of RuntimeException.
     */
    private static boolean explodes(String expr) {
        try {
            evaluate(expr);
        } catch (ExpressionException e) {
            return true;
        }
        return false;
    }
}