/**
 * Created by mo on 10/22/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class ScalingRecipes {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int nmax = 20;
        String[] rs = new String[nmax];
        double[] ws = new double[nmax];
        double[] ps = new double[nmax];

        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int r = sc.nextInt(), p = sc.nextInt(), d = sc.nextInt();
            double mainW = 0;
            for (int i = 0; i < r; i++) {
                rs[i] = sc.next();
                ws[i] = sc.nextDouble();
                ps[i] = 0.01 * sc.nextDouble();
                if (abs(ps[i] - 1) < 1e-6)
                    mainW = ws[i] * d / p;
            }
            System.out.printf("Recipe # %d\n", t + 1);
            for (int i = 0; i < r; i++) {
                System.out.printf("%s %.1f\n", rs[i], ps[i] * mainW);
            }
            System.out.println("----------------------------------------");
        }
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}
