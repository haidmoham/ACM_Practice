/**
 * Created on 12/29/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class RubiksRevenge {
    public static char[][] solved = {
            {'R', 'R', 'R', 'R'},
            {'G', 'G', 'G', 'G'},
            {'B', 'B', 'B', 'B'},
            {'Y', 'Y', 'Y', 'Y'}
    };
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        char[][] in = new char[4][4];
        for (int i = 0; i < in.length; i++) {
            String s = sc.readNextLine();
            for (int j = 0; j < in[0].length; j++) {
                in[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < 4; i++)
            System.out.println(Arrays.toString(in[i]));
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
