/**
 * Created by mo on 9/24/16.
 */

import java.util.*;

public class FasterInverseFactorial {
    static double E = Math.E;
    static double PI = Math.PI;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        int goal = in.length();
        int n = 0;
        if (in.equals("1")) System.out.println(1);
        else if (in.equals("2")) System.out.println(2);
        else if (in.equals("6")) System.out.println(3);
        else if (in.equals("24")) System.out.println(4);
        else if (in.equals("120")) System.out.println(5);
        else if (in.equals("720")) System.out.println(6);
        else {
            for (int i = 7; n < goal; i++){
                n = Kamenetsky(i);
                if (Kamenetsky(i) == goal) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }

    public static int Kamenetsky(int n) {
        /*Kamenetsky's formula for the number of digits in n-factorial, where n is the nth factorial
        as a workaround for overflow, cases up to n = 7 can be hard coded, and n can be passed as
        the string length of the number (scanned in as a string)

        Note - Kamenetsky's formula has a counter-example at n = 6561101970383, which is worth noting,
        but not likely to come up
         */
        double x = (Math.floor(((n * Math.log10(n/E) + Math.log10(2 * PI * n) / 2.0))) + 1);
        return (int) (x);
    }
}
