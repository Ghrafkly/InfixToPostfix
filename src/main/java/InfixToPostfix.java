/**
 * Coverts an Infix expression to a Postfix expression.
 *
 * version 1.0
 * @author Kyle
 */
public class InfixToPostfix {
    /**
     * Evaluates an infix expression and returns the result as a postfix expression.
     *
     * @param infix The infix expression to evaluate.
     * @return The postfix expression.
     */
    public String evaluate(String infix) {
        // If spaces exist in the infix expression, remove them.
        if (infix.contains(" "))
            infix = infix.replaceAll(" ", "");

        /*
         Split the infix expression into tokens.
         This will destroy negative numbers, they will be recreated at a later step.
        */
        String[] exp = infix.split("");

        // Create a queue to hold the tokens.
        QueueADT queue = new QueueADT();
        for (String s : exp) {
            queue.enqueue(s);
        }

        // Recombines the tokens into their appropriate negative, decimal, or multi-digit number.
        StringBuilder sb = normaliseInput(queue);
        if (!sb.isEmpty())
            return sb.toString();
        else
            return convertToPostfix(queue).toString().trim();
    }

    /**
     * Normalises the input into a queue. Maintains parity of negative numbers.
     *
     * @param exp The infix expression to normalise.
     */
    public StringBuilder normaliseInput(QueueADT exp) {
        StringBuilder hold = new StringBuilder();
        int size = exp.size();
        boolean negCheck = false;
        boolean opCheck = false;

        for (int i = 0; i < size; i++) {
            String val = exp.dequeue();

            // Maintains parity of negative numbers.
            if (val.equals("-") && (negCheck || i == 0)) {
                hold.append(val);
            } else {
                if (isNumeric(val)) {
                    hold.append(val);
                    exp.enqueue(hold.toString());
                    opCheck = false;
                } else if (!opCheck) {
                    exp.enqueue(hold.toString());
                    exp.enqueue(val);
                    if (isOperator(val))
                        opCheck = true;
                } else {
                    hold = new StringBuilder("Extra Operator");
                    return hold;
                }

                hold.setLength(0);
                negCheck = isOperator(val) || val.equals("(");
            }
        }
        exp.enqueue(hold.toString());
        return new StringBuilder();
    }

    /**
     * Converts infix expression to postfix expression.
     *
     * @param exp infix expression to convert
     * @return postfix expression
     */
    private StringBuilder convertToPostfix(QueueADT exp) {
        StackADT stack = new StackADT();
        QueueADT postfix = new QueueADT();
        StringBuilder sb = new StringBuilder();

        int size = exp.size();

        stack.push("(");
        exp.enqueue(")");


        while (!stack.isEmpty()) {
            // Check if there is an extra bracket
            if (exp.isEmpty())
                return new StringBuilder("Extra Bracket");

            String val = exp.dequeue();

            // Check if the first token is a closing bracket
            if (val.equals(")") && (exp.size() + 1) == size)
                return new StringBuilder("Extra Bracket");

            if (isNumeric(val)) {
                postfix.enqueue(val);
            } else if (val.equals("(")) {
                stack.push(val);
            } else if (val.equals(")")) {
                while (!stack.stackTop().equals("(") && !stack.isEmpty()) {
                    postfix.enqueue(stack.pop());
                }
                stack.pop();
            } else if (isOperator(val)) {
                while (!stack.isEmpty() && precedence(val) <= precedence(stack.stackTop())) {
                    postfix.enqueue(stack.pop());
                }
                stack.push(val);
            }
        }

        // Any remaining elements in the stack are added to the postfix expression.
        while (!stack.isEmpty())
            postfix.enqueue(stack.pop());

        // Converts postfix expression to string.
        while (!postfix.isEmpty()) {
            sb.append(postfix.dequeue());
            sb.append(" ");
        }

        return sb;
    }

    /**
     * Checks if string is an operator.
     *
     * @param val string to check
     * @return true if string is an operator, false otherwise
     */
    private boolean isOperator(String val) {
        return val.matches("[+-/*%^]");
    }

    /**
     * Checks precedence of operator.
     *
     * @param str operator to check
     * @return precedence of operator
     */
    private int precedence(String str) {
        return switch (str) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    /**
     * Checks if string is a number.
     *
     * @param str string to check
     * @return true if string is a number, false otherwise
     */
    public boolean isNumeric(String str) {
        return str.matches("^-?\\d+(\\.\\d+)?$");
    }
}
