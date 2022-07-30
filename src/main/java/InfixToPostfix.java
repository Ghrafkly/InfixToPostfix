/**
 * Coverts an Infix expression to a Postfix expression.
 *
 * version 1.0
 * @author Kyle
 */
public class InfixToPostfix
{
    /**
     * Evaluates an infix expression and returns the result as a postfix expression.
     *
     * @param infix     The infix expression to evaluate.
     * @return          The postfix expression.
     */
    public String evaluate(String infix)
    {
        if (infix.contains(" "))
            infix = infix.replaceAll(" ", "");

        String[] exp = infix.split("");

        QueueADT queue = normaliseInput(exp);

        return convertToPostfix(queue).toString().trim();
    }

    /**
     * Normalises input. Keeps expression parity when putting it into a queue.
     * i.e. [-, -, 1] -> [-, -1]
     *
     * @param exp       expression to normalise
     * @return          queue with normalised expression
     */
    private QueueADT normaliseInput(String[] exp)
    {
        for (int i = 0; i < exp.length; i++)
        {
            /*
             Deals with negative numbers i.e. -1. Or statement deals with negatives at the start of the expression.
             First AND ensures that the element (i) is negative and the next element is a number.
             Second AND links the next check
             Set of ANDs before the OR ensures that the element (i) is not at the start of the expression AND the preceding element is not a number, AND not a closing bracket.
             After OR ensures that the element (i) is in the first position of the expression.

             If checks pass, i+1 is concatted with the negative sign and the number, the position the negative used to be at is replaced with an empty string
            */
            if (exp[i].equals("-") && isNumeric(exp[i + 1])
                    && (i > 1 && !isNumeric(exp[i - 1]) && !exp[i - 1].equals(")") || i == 0))
            {
                exp[i + 1] = "-" + exp[i + 1];
                exp[i] = "";
            }
        }

        /*
         Puts expression into a queue (enqueue).
         If element in expression is blank, it is not enqueued.
        */
        QueueADT infix = new QueueADT();
        for (String s : exp)
            if (!s.equals(""))
                infix.enqueue(s);

        return infix;
    }

    /**
     * Converts infix expression to postfix expression.
     *
     * @param exp       infix expression to convert
     * @return          postfix expression
     */
    private StringBuilder convertToPostfix(QueueADT exp)
    {
        StackADT stack = new StackADT();
        stack.push("(");
        exp.enqueue(")");
        QueueADT postfix = new QueueADT();

        while (!stack.isEmpty())
        {
            String val = exp.dequeue();
            if (isNumeric(val))
            {
                postfix.enqueue(val);
            }
            else if (val.equals("("))
            {
                stack.push(val);
            }
            else if (val.equals(")"))
            {
                while (!stack.stackTop().equals("(") && !stack.isEmpty())
                {
                    postfix.enqueue(stack.pop());
                }
                stack.pop();
            }
            else if (isOperator(val))
            {
                while (!stack.isEmpty() && precedence(val) <= precedence(stack.stackTop()))
                {
                    postfix.enqueue(stack.pop());
                }
                stack.push(val);
            }
        }

        // Any remaining elements in the stack are added to the postfix expression.
        while (!stack.isEmpty())
            postfix.enqueue(stack.pop());

        // Converts postfix expression to string.
        StringBuilder sb = new StringBuilder();
        while (!postfix.isEmpty())
        {
            sb.append(postfix.dequeue());
            sb.append(" ");
        }

        return sb;
    }

    /**
     * Checks if string is an operator.
     *
     * @param val       string to check
     * @return          true if string is an operator, false otherwise
     */
    private boolean isOperator(String val)
    {
        return val.matches("[+-/*%^]");
    }

    /**
     * Checks precedence of operator.
     *
     * @param str       operator to check
     * @return          precedence of operator
     */
    private int precedence(String str)
    {
        return switch (str)
        {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    /**
     * Checks if string is a number.
     *
     * @param str       string to check
     * @return          true if string is a number, false otherwise
     */
    public boolean isNumeric(String str)
    {
        return str.matches("^-?\\d+(\\.\\d+)?$");
    }
}
