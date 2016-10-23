/**
 * Created by mo on 10/22/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class FlippingCardsUnionFind {
    private static int[] s, c;
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        s = new int[200200]; //not sure why 200200 works
        c = new int[200200];
        for (int t = 1; t <= T; t++) {
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++){
                s[i] = i;
                s[i+n] = i + n;
                c[i] = c[i+n] = 0;
            }
            for (int i = 0; i < n; i++) {
                int p = sc.nextInt(), q = sc.nextInt();
                union(p, q);
            }
            boolean bool = true;
            for (int i = n + n; i > 0; i--) {
                if (c[find(i)] > 1) {
                    bool = false;
                    break;
                }
            }
            System.out.println(bool ? "possible" : "impossible");
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
    public static void union (int x, int y) {
        int sx = find(x);
        int sy = find(y);
        if (sx == sy)
            c[sx] = c[sx] + 1;
        else {
            s[sy] = sx;
            c[sx] = c[sx] + c[sy];
        }
    }
    public static int find(int x) {
        while (x != s[x]){
            int y = s[x];
            s[x] = s[s[x]];
            x = y;
        }
        return x;
    }
}
