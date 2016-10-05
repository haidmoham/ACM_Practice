/**
 * Created by mo on 10/1/16.
 */

import java.util.*;
import java.io.*;

public class PyroTubesFaster {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int tubes = 18;
        List<Integer> seq = new ArrayList<Integer>();
        HashSet<Integer> allowed = new HashSet<Integer>();
        int n = sc.nextInt();
        while (n > 0){
            seq.add(n);
            allowed.add(n);
            n = sc.nextInt();
        }
        for (int L : seq){
            n = 0;
            for (int b1 = 1 << (tubes - 1); b1 > 0; b1>>= 1){
                int L1 = L ^ b1;
                //System.err.println(b1 + " " + L1);
                if (L1 < L) continue;
                if (allowed.contains(L1)) n++;
                for (int b2 = b1 >> 1; b2 > 0; b2>>= 1){
                    int L2 = L1 ^ b2;
                    //System.err.println(b2 + " " + L2);
                    if (L2 > L && allowed.contains(L2)) n++;
                }
            }
            System.out.println(L + ":" + n);
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
