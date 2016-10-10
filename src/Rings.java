/**
 * Created by mo on 10/8/16.
 */

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Rings {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt();
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String in = sc.readNextLine();
            for (int j = 0; j < c; j++) {
                grid[i][j] = in.charAt(j);
            }
        }
        //print2D(grid, r, c);
        String[][] out = new String[r][c];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < r-1; i++) {
            for (int j = 1; j < c-1; j++) {
                if (grid[i][j] == 'T' && (grid[i][j+1] == '.' || grid[i][j-1] == '.'
                        || grid[i+1][j] == '.' || grid[i-1][j] == '.')){
                    out[i][j] = ".1";
                }
            }
        }
        print2D(grid, r, c);
        print2DStr(out, r, c);
    }
    public static void print2D(char[][] grid, int r, int c) {
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print2DStr(String[][] grid, int r, int c) {
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++) {
                System.out.print(grid[i][j] + " ");
            }
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
