public class InfixToPostfix {
    //  Evaluates an infix expression and returns the result as a postfix expression.
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

        StringBuilder error = errorCheck(exp);

        return error.toString().equals("Clear")
                ? convertToPostfix(queue).toString().trim()
                : error.toString();
    }

    /*
     Checks for error patterns in the expression. Bracket errors are handled in the conversion method

     Patterns:
     Operator at the start of the expression,
     Operator at the end of the expression,
     Two integers in a row,
     Two operators in a row
    */
    public StringBuilder errorCheck(String[] exp) {
        QueueADT queue = new QueueADT();

        if (isOperator(exp[0]))
            return new StringBuilder("Operator at start of expression");

        for (String s : exp)
            queue.enqueue(s);


        while (!queue.isEmpty()) {
            String s = queue.dequeue();

            if (!isNumeric(s) && queue.isEmpty() && !s.equals(")") && !s.equals("("))
                return new StringBuilder("Operator at end of expression");
            else if (queue.isEmpty())
                break;

            if (isNumeric(s) && isNumeric(queue.queueFront()))
                return new StringBuilder("Extra Integer detected");
            else if (isOperator(s) && isOperator(queue.queueFront()))
                return new StringBuilder("Extra Operator detected");
        }

        return new StringBuilder("Clear");
    }

    // Converts infix expression to postfix expression.
    private StringBuilder convertToPostfix(QueueADT exp) {
        StackADT stack = new StackADT();
        QueueADT postfix = new QueueADT();
        StringBuilder sb = new StringBuilder();

        stack.push("(");
        exp.enqueue(")");

        boolean bracketCheck = exp.queueFront().equals(")");

        while (!stack.isEmpty()) {
            // Check if there is an extra bracket
            if (exp.isEmpty() || bracketCheck)
                return new StringBuilder("Issues with Brackets detected");

            String val = exp.dequeue();

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

    // Checks if string is an operator.
    private boolean isOperator(String val) {
        return val.matches("[+-/*%^]");
    }

    // Checks precedence of operator.
    private int precedence(String str) {
        return switch (str) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    // Checks if string is a number.
    public boolean isNumeric(String str) {
        return str.matches("^\\d$");
    }
}
