/**
 * Created by mo on 10/17/16.
 */

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class FlowerGarden {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] isPrime = sieve(20002);
        out: for (int i = 0; i < n; i++){
            int N = sc.nextInt();
            long D = sc.nextLong();
            int px = 0, py = 0;
            double dist = 0;

            int lastPrime = 0;
            in: for (int j = 1; j <= N; j++) {
                int x = sc.nextInt(), y = sc.nextInt();
                double dx = x - px;
                double dy = y - py;
                double d = sqrt((dx * dx) + (dy * dy));
                dist += d;
                px = x;
                py = y;
                if (isPrime[j])
                    lastPrime = j;
            }
            System.out.println(lastPrime);
        }
    }
    static boolean[] sieve (int N){
        boolean [] a = new boolean[N+1];
        Arrays.fill(a, true);
        a[0] = a[1] = false;
        for (int p = 2; p * p <= N; p++)
            if (a[p]) {
                // Iterate through all multiples m of the prime and mark
                // them as not prime.
                for (int m = p * p; m <= N; m += p)
                    a[m] = false;
            }
        return a;
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
