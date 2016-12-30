/**
 * Created on 11/2/16.
 *
 * protip
 *
 * make sure you're actually caching when you think you're caching
 * caching is important for dynamic programming
 *
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class CityDestruction {
    static int[] H;
    static int[] E;
    static int N;
    static int D;
    static long[][] cache;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++){
            N = sc.nextInt();
            D = sc.nextInt();

            cache = new long[2][N+1];
            Arrays.fill(cache[0], -1L);
            Arrays.fill(cache[1], -1L);
            H = new int[N+1];
            E = new int[N+1];
            for (int i = 0; i < N; i++)
                H[i] = sc.nextInt();
            for (int i = 0; i < N; i++)
                E[i] = sc.nextInt();
            if (N == 1)
                System.out.println(need(H[0]));
            else System.out.println(dp(0, true, 0));
        }
    }

    public static long dp(int p, boolean left, long count) {
        if (p == N)
            return 0;
        if (cache[left ? 1 : 0][p] != -1)
            return cache[left ? 1 : 0][p];

        long v1 = max(0, (H[p] - count + D - 1) / D);
        long v2 = max(0, (H[p] - E[p + 1] - count + D - 1)/ D);


        long ans = min(v1 + dp(p + 1, true, E[p]), v2 + dp(p + 1, false, 0));
        cache[left ? 1 : 0][p] = ans;

        return ans;
    }

    public static long need(long delta) {
        return (delta + D - 1)/D;
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