/**
 * Created by mo on 10/6/16.
 */

import java.util.*;
import java.io.*;

public class CrosswordsInsider {
    static boolean[][] puzzle;
    static String[] words;
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(), N = sc.nextInt();
        words = new String[M];
        for (int i = 0; i < M; i++)
            words[i] = sc.next();
        puzzle = new boolean[N][N];
        for (int i = 0; i < N; i++){
            String in = sc.next();
            for (int j = 0; j < in.length(); j++){
                if (in.charAt(j) == '.')
                    puzzle[i][j] = true;
            }
        }
        System.out.println(Arrays.toString(words));
    }
    public static void print2D(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; i < arr.length; j++){
                System.out.print(arr[i][j] ? "1" : "0");
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
