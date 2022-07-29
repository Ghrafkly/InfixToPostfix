import java.util.Arrays;

public class InfixToPostfix {
    private StackADT stack;
    private QueueADT queue;
    public String evaluate(String infix) {
        if (infix.contains(" "))
            infix = infix.replaceAll(" ", "");

        String[] exp = infix.split("");

        System.out.println(Arrays.toString(exp));

        this.queue = normaliseInput(exp);

        System.out.println(convertToPostfix(this.queue));

        return queue.toString();
    }

    private QueueADT normaliseInput(String[] exp) {
        for (int i = 0; i < exp.length; i++) {
            if (exp[i].equals("-") && exp[i + 1].matches("\\d")) {
                if (i > 1) {
                    if (!exp[i - 1].matches("\\d")) {
                        exp[i+1] = "-" + exp[i + 1];
                        exp[i] = "";
                    }
                } else {
                    exp[i+1] = "-" + exp[i + 1];
                    exp[i] = "";
                }
            }
        }

        QueueADT infix = new QueueADT();
        for (String s : exp) {
            if (!s.equals("")) {
                infix.enqueue(s);
            }
        }

        return infix;
    }

    private StringBuilder convertToPostfix(QueueADT exp) {
        stack = new StackADT();
        stack.push("(");
        exp.enqueue(")");
        QueueADT postfix = new QueueADT();
        while (!stack.isEmpty()) {
            String val = exp.dequeue();
            if (val.matches("-?\\d")) {
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

        while (!stack.isEmpty()) {
            postfix.enqueue(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!postfix.isEmpty()) {
            sb.append(postfix.dequeue());
            sb.append(" ");
        }

        return sb;
    }

    private boolean isOperator(String val) {
        return val.matches("[+-/*%^]");
    }

    private int precedence(String str) {
        return switch (str) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    public boolean isNumeric(String str) {
        return str.matches("^-?\\d+(\\.\\d+)?$");
    }

    public static void main(String[] args) {
        InfixToPostfix itp = new InfixToPostfix();
        System.out.println(itp.evaluate("-1+(2--3)--4"));
    }
}
