/**
 * Created by mo on 10/22/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class MagicalThree {
    static boolean[] isPrime = sieve(43641);
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        //System.out.println(Integer.toString(104, 27));
        StringBuilder sb = new StringBuilder();
        input: while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            sb.append(solve(n)).append('\n');
        }
        System.out.println(sb.toString());
    }
    public static boolean[] sieve(int N)
    {

        boolean[] isPrime = new boolean[N + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        // Iterate through all numbers up to sqrt(N) to
        // locate all of the prime numbers.
        for (int i = 4; i * i <= N; i++)
        {

            if (!isPrime[i])
            {
                continue;
            }

            // Iterate through all multiples of the prime and mark them as
            // not prime.
            for (int multiples = i*i; multiples <= N; multiples += i)
            {
                isPrime[multiples] = false;
            }

        }
        isPrime[2] = isPrime[3] = false;
        return isPrime;

    }

    public static String solve(int n){
        if (n == 3)
            return "4";
        if (n < 7)
            return "No such base";
        n = n - 3;
        for (int i = 4; i <= 10; i++) {
            if (n % i == 0)
                return Integer.toString(i);
        }
        while (n % 3 == 0)
            n /= 3;
        while (n % 2 == 0)
            n /= 2;
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i])
                if (n % i == 0)
                    return Integer.toString(i);
        }
        return Integer.toString(n);
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
