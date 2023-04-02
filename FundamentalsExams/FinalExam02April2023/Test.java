package FundamentalsExams.FinalExam02April2023;

public class Test {
    public static void main(String[] args) {
        printText("Java");

        int a1 = 10;
        double b1 = 5.5;
        a1 += b1;
        System.out.println(a1);

        int a2 = 5;
        int b2 = a2++;
        int c2 = ++a2;
        System.out.println(c2);

//        String name = "George";
//        name.charAt(2) = "m";
//        System.out.println(name.charAt(2));
//
//        boolean isTrue = true;
//        int value = Integer.parseInt(isTrue);
//        System.out.println(value);

        boolean isTrue1 = 100f == 100d;
        boolean isTrue2 = 100f != 100d;
        boolean isTrue3 = "string".equals("String");
        boolean isTrue4 = 5 > 'a';

        String greet = "Hello World";
        System.out.println(greet.substring(3, 5));

        for (int i = 10; i > 3; i -= 2) {
            System.out.printf("%d ", i);
        }
    }

    public static void printText(String text) {
        System.out.println("I love" + text);
    }
}
