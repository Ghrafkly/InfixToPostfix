public class Runner {
    public static void main(String[] args) {
        InfixToPostfix itp = new InfixToPostfix();
        String exp = " (6 + 2) * 5 - 8 / 4 ";
        String exp2 = "-(6--6)-6";
        System.out.println(itp.evaluate(exp));
        System.out.println(itp.evaluate(exp2));
    }
}
