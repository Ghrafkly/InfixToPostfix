public class Runner {
    private static String[] exp;

    private static void populate() {
        exp = new String[]{
                "(7-(8*2)%4)/(1+2)^(2+2)9*1",   // Good
                "8+2-3*4%(7/(8-2)+1)-(3+2)",    // Good
                "(7-2+3(4-5*6)+7/(6-3)%4",      // Bad, no closing bracket
                "4*(9+2)-8%(8-2)+*9-(2/4)+1"};  // Bad, double up operators
    }

    public static void main(String[] args) {
        InfixToPostfix itp = new InfixToPostfix();
        populate();
        int maxlength = 0;

        for (String e : exp)
            if (e.length() > maxlength)
                maxlength = e.length();

        for (String e : exp)
            System.out.printf("%" + maxlength + "s : %s\n", e, itp.evaluate(e));
    }
}
