public class Runner {
    private static String[] exp;

    private static void populate() {
        exp = new String[]{
                ")6-6",
                "6-(6-6)",
                "-6-6-6",
                "6-(6-6("};

//        exp = new String[]{"6-6-6-"};
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
