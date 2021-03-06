/**
 * Created by mo on 10/17/16.
 */

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Bits {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            String in = sc.next();
            int bc = -1;
            for (int i = 1; i < in.length() + 1; i++) {
                int p = Integer.parseInt(in.substring(0, i));
                //System.out.println(p);
                bc = max(bc, countOnes(p));
            }
            System.out.println(bc);
        }
    }
    public static int countOnes(int p) {
        int count = 0;
        String str = Integer.toBinaryString(p);
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '1')
                count++;
        return count;
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
