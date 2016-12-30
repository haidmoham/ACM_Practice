/**
 * Created by mo on 10/8/16.
 */

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


public class Rings {
    public static int[][] grid;
    public static char[][] ch;
    public static int[] dr = new int[]{-1, 0, 1, 0};
    public static int[] dc = new int[]{0, 1, 0, -1};
    public static int r, c;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        grid = new int[r][c];
        ch = new char[r][c];
        for (int i = 0; i < r; i++) {
            char[] in = sc.readNextLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (in[j] == '.') {
                    grid[i][j] = 0;
                    ch[i][j] = '.';
                }
                else if (in[j] == 'T') {
                    ch[i][j] = 'T';
                    if (i == 0 || j == 0 || i == r - 1 || j == c - 1)
                        grid[i][j] = 1;
                }
                else
                    grid[i][j] = -1;
            }
        }
        print2D(ch);
        print2D(grid);
    }
    static void print2D(int[][] grid) {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void print2D(char[][] grid) {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean valid(int row, int col) {
        return row >= 0 && row < r && col >= 0 && col < c;
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
