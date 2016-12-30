/**
 * Created on 12/6/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class NumberFun {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++){
            double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
            HashSet<Double> set = generate(a, b);
            System.out.println(set.contains(c) ? "Possible" : "Impossible");
        }
    }

    public static HashSet<Double> generate(double a, double b) {
        HashSet<Double> ans = new HashSet<>();
        ans.add(a * b);
        ans.add(a + b);
        ans.add(b / a);
        ans.add(a / b);
        ans.add(a - b);
        ans.add(b - a);
        return ans;
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
