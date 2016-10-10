/**
 * Created by mo on 9/24/16.
 */

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class InverseFactorial {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        String match = sc.readNextLine();
        int count = 2;
        int i = 2;
        while (true) {
            if (bigFactorial(i).toString().equals(match)) {
                System.out.println(count);
                break;
            }
            count++;
            i++;
        }
    }
    public static BigInteger bigFactorial(int n) {
        BigInteger[] fact = new BigInteger[n+1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i-1].multiply(new BigInteger(i + ""));
        }
        return fact[n];
    }

    public static long factorial(int N){
        long[] fact = new long[N+1];
        fact[0] = 1;
        for (int i = 1; i < N+1; i++){
            fact[i] = i * fact[i-1];
        }
        return fact[N];
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
