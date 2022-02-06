
public class NumberSystem {
    public static int gcf(int a, int b) {
        if(a%b == 0) return b;
        else return gcf(b, a%b);
    }

    public static void reduceFraction(int numerator, int denominator) {
        if(numerator % denominator == 0) {
            System.out.printf("%d/%d reduces to %d", numerator, denominator, numerator/denominator);
        } else {
            int gcf = gcf(numerator, denominator);
            System.out.printf("%d/%d reduces to %d/%d", numerator, denominator, numerator/gcf, denominator/gcf);
        }
    }
}
