/**
 * Created by mo on 10/1/16.
 */

import java.util.*;
import java.io.*;

public class DanceRecital {
    static String[] in;
    static int n;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        n = sc.nextInt();
        in = new String[n];
        for (int i = 0; i < n; i++)
            in[i] = sc.next();
        int[] tsp = solve();

        int min = Arrays.stream(tsp).min().getAsInt();
        System.out.println(min);
    }
    static int[] solve() {
        // precompute/cache dist to avoid calls in inner loop
        final int [][]D = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                D[j][i] = dist(in[i], in[j]);

        int[][] dp = new int[1 << n][n];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE / 2);

        for (int i = 0; i < n; i++)
            dp[1<<i][i] = 0;

        for (int mask = 0; mask < 1 << n; mask++)
            for (int i = 0; i < n; i++)
                if ((mask & 1 << i) > 0)
                    for (int j = 0; j < n; j++)
                        if (i != j && (mask & 1 << j) > 0)
                            dp[mask][i] = Math.min(dp[mask][i],
                                    dp[mask ^ (1<<i)][j] + D[j][i]);
        return dp[(1<<n)-1];
    }
    static int dist(String to, String from){
        char[] ch1 = to.toCharArray();
        char[] ch2 = from.toCharArray();
        HashSet<Character> toSet = new HashSet<>();
        HashSet<Character> fromSet = new HashSet<>();
        for (int i = 0; i < ch1.length; i++)
            toSet.add(ch1[i]);
        for (int i = 0; i < ch2.length; i++)
            fromSet.add(ch2[i]);
        HashSet<Character> combined = new HashSet<>();
        combined.addAll(toSet);
        combined.addAll(fromSet);
        int count = 0;
        for (Character c : combined){
            if (toSet.contains(c) && fromSet.contains(c))
                count++;
        }
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

