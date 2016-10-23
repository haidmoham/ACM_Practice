/**
 * Created by mo on 10/21/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class WoodCutting {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            ArrayList<Double> in = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int n = sc.nextInt();
                in.add(sc.nextDouble());
                for (int j = 1; j < n; j++) {
                    in.add(in.get(j-1) + sc.nextDouble());
                }
            }
            Collections.sort(in);
            System.out.println(in);
            double w = 0;
            double ps = 0;
            for (Double p : in){
                ps += p;
                w += ps;
            }
            System.out.printf("%.17f", w / ((double) in.size()));
            System.out.println();
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
