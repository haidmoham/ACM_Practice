import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/22/2016.
 */
public class PopularVote {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int total = 0;
            int max = 0;
            int maxCount = 0;
            int maxIdx = -1;

            for(int j = 1; j <= n; j++) {
                int x = sc.nextInt();
                total += x;
                if (x > max) {
                    max = x;
                    maxIdx = j;
                    maxCount = 1;
                }
                else if (x == max) {
                    maxCount++;
                }
            }
            if (maxCount > 1) {
                sb.append("no winner");
            }
            else if (max > total / 2) {
                sb.append("majority winner ");
                sb.append(maxIdx);
            }
            else {
                sb.append("minority winner ");
                sb.append(maxIdx);
            }


            sb.append("\n");
        }
        System.out.print(sb);

    }
}
