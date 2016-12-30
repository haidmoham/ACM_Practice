/**
 * Created on 10/10/16.
 */

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class AplusB {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        FFT fft = new FFT(n);
        long[] in = fft.makePoly();
        long[] freq = fft.makePoly();
        for (int i = 0; i < n; i++) {
            in[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            freq[(int) in[i]-1]++;
        }
        fft.fastMultiply(in, freq);
        System.out.println(in[in.length-2]);
    }

    static class FFT {
        public static final long mod = (5 << 25) + 1;   // 167,772,161
        private static final BigInteger bmod = BigInteger.valueOf(mod);
        private int levels;
        private int len = 1 << levels;
        private BigInteger gen;

        /**
         * Prepare for FFT of n x n -> 2n * arrays.
         */
        FFT(int n) {
            levels = 1;
            while (2 * n > 1 << levels)     // need 2*n <= len
                levels++;
            len = 1 << levels;
            gen = BigInteger.valueOf(243)
                    .modPow(BigInteger.valueOf(1 << (25 - levels)), bmod);
        }

        /* Return an array sized for FFT */
        long[] makePoly() {
            return new long[len];
        }

        void transform(long[] a, long gen) {
            for (int i = 0; i < len; i++) {
                int j = Integer.reverse(i) >>> (32 - levels);
                if (j < i) {
                    long t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }

            long[] w = new long[levels + 1];
            w[levels] = gen;
            for (int i = levels - 1; i >= 0; i--) {
                w[i] = w[i + 1] * w[i + 1] % mod;
            }
            for (int l = 1, hs = 1; (1 << l) <= len; l++, hs <<= 1) {
                long[] fr = new long[hs];
                fr[0] = 1;
                for (int j = 1; j < hs; j++) fr[j] = fr[j - 1] * w[l] % mod;
                for (int i = 0; i < len; i += (1 << l)) {
                    for (int j = i; j < i + hs; j++) {
                        long tre = a[j + hs] * fr[j - i] % mod;
                        a[j + hs] = a[j] + mod - tre;
                        if (a[j + hs] >= mod) a[j + hs] -= mod;
                        a[j] += tre;
                        if (a[j] >= mod) a[j] -= mod;
                    }
                }
            }
        }

        /**
         * Compute arr1 x arr2.  Result is in arr1.
         * arr1 and arr2 can point to the same array.
         */
        void fastMultiply(long[] arr1, long[] arr2) {
            transform(arr1, gen.longValue());
            if (arr1 != arr2)
                transform(arr2, gen.longValue());

            long scale = BigInteger.valueOf(len).modInverse(bmod).longValue();
            for (int i = 0; i < len; i++)
                arr1[i] = arr1[i] * arr2[i] % mod * scale % mod;
            transform(arr1, gen.modInverse(bmod).longValue());
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
}
