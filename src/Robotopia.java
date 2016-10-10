/**
 * Created by mo on 9/24/16.
 */

import java.util.*;
import java.io.*;

public class Robotopia {
    /**
     * Unsolved so far, still in the process of upsolving.
     * /
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            int l1 = sc.nextInt(), a1 = sc.nextInt(), l2 = sc.nextInt(),
                    a2 = sc.nextInt(), lt = sc.nextInt(), at = sc.nextInt();
            int count = 0;
            int x = 0, y = 0;
            for (int j = 1; j < 10000; j++){
                if (count > 1) break;
                if (j * l1 < lt) {
                    x = j;
                    count++;
                }
            }
            y = (lt - l1 * x)/l2;
            //System.out.println("x = " + x + "y =" + y);
            //System.err.println('\n' + count);
            if ((l1 * x + l2 * y == lt && a1 * x + a2 * y == at))
                sb.append(x).append(" ").append(y).append('\n');
            else {
                sb.append("?").append("\n");
            }
        }
        System.out.println(sb.toString());
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