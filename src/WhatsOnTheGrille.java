/**
 * Created by mo on 10/8/16.
 */

import java.util.*;
import java.io.*;

public class WhatsOnTheGrille {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] grille = new char[n][n];
        for (int i = 0; i < n; i++) {
            String in = sc.nextLine();
            for (int j = 0; j < n; j++) {
                grille[i][j] = in.charAt(j);
            }
        }
        String encrypted = sc.nextLine();
        char[] ch = encrypted.toCharArray();

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
