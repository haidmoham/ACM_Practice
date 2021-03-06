/**
 * Created by mo on 10/22/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class MatrixKeypad {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        out: for (int t = 0; t < T; t++) {
            int r = sc.nextInt(), c = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                String in = sc.next();
                for (int j = 0; j < c; j++) {
                    sb.append(in.charAt(j) == '1' ? 'I' : 'N');
                }
                sb.append('\n');
            }
            if (r + c >= 6) {
                System.out.print(sb.toString());
                System.out.println("----------");
            }
            else if (r + c > 4) {
                System.out.print(sb.toString().replaceAll("I", "P"));
                System.out.println("----------");
            }
            else {
                System.out.println("impossible");
                System.out.println("----------");
            }
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
