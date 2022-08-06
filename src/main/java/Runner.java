import java.util.Arrays;
import java.util.Scanner;

public class Runner {
    private static final Scanner sc = new Scanner(System.in);
    private static String[] exp;

    private static void populate() {
        exp = new String[]{
                "( 6 + 2 ) * 5 - 8 % 4",
                "-(6--6)-6",
                "(-6-6*5"};
    }

    public static void main(String[] args) {
        InfixToPostfix itp = new InfixToPostfix();
        populate();
        int maxlength = 0;

        for (String e : exp)
            if (e.length() > maxlength)
                maxlength = e.length();

        boolean b = true;

        while (b) {
            System.out.println("""
                    1) Enter expression
                    2) Check test expressions
                    3) To Exit.""");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.println("Enter an expression: ");
                    String user = sc.nextLine();
                    System.out.println(itp.evaluate(user));
                }
                case "2" -> {
                    System.out.println("Test expressions: ");
                    for (String e : exp)
                        System.out.printf("%-" + maxlength + "s : %s\n", e, itp.evaluate(e));
                }
                case "3" -> {
                    System.out.println("Goodbye!");
                    b = false;
                    sc.close();
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
