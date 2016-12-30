/**
 * Created on 11/23/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class MirrorImages {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        char[][] ch;
        for (int t = 0; t < T; t++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            ch = new char[R][C];
            for (int i = 0; i < R; i++) {
                String in = sc.readNextLine();
                for (int j = 0; j < C; j++) {
                    ch[i][j] = in.charAt(j);
                }
            }
            char[][] out = mirror(ch, R, C);
            System.out.println("Test " + (t + 1));
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(out[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static char[][] mirrorH(char[][] ch, int R, int C) {
        char[][] out = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                out[i][C - j - 1] = ch[i][j];
        }
        return out;
    }
    public static char[][] mirrorV(char[][] ch, int R, int C) {
        char[][] out = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                out[R - i - 1][j] = ch[i][j];
            }
        }
        return out;
    }
    public static char[][] mirror(char[][] ch, int R, int C) {
        char[][] out = new char[R][C];
        out = mirrorV(mirrorH(ch, R, C), R, C);
        return out;
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
