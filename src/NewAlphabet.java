/**
 * Created by mo on 9/24/16.
 */

import java.util.*;
import java.io.*;

public class NewAlphabet {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        String in = sc.readNextLine().toLowerCase();
        System.out.println(translate(in));
    }
    public static String translate(String in){
        if (in.contains("a")) in = in.replaceAll("a", "@");
        if (in.contains("b")) in = in.replaceAll("b", "8");
        if (in.contains("c")) in = in.replaceAll("c", "(");
        if (in.contains("d")) in = in.replaceAll("d", "|)");
        if (in.contains("e")) in = in.replaceAll("e", "3");
        if (in.contains("f")) in = in.replaceAll("f", "#");
        if (in.contains("g")) in = in.replaceAll("g", "6");
        if (in.contains("h")) in = in.replaceAll("h", "[-]");
        if (in.contains("i")) in = in.replaceAll("i", "|");
        if (in.contains("j")) in = in.replaceAll("j", "_|");
        if (in.contains("k")) in = in.replaceAll("k", "|<");
        if (in.contains("l")) in = in.replaceAll("l", "1");
        if (in.contains("m")) in = in.replaceAll("m", "[]\\\\/[]");
        if (in.contains("n")) in = in.replaceAll("n", "[]\\\\[]");
        if (in.contains("o")) in = in.replaceAll("o", "0");
        if (in.contains("p")) in = in.replaceAll("p", "|D");
        if (in.contains("q")) in = in.replaceAll("q", "(,)");
        if (in.contains("r")) in = in.replaceAll("r", "|Z");
        if (in.contains("s")) in = in.replaceAll("s", "\\$");
        if (in.contains("t")) in = in.replaceAll("t", "']['");
        if (in.contains("u")) in = in.replaceAll("u", "|_|");
        if (in.contains("v")) in = in.replaceAll("v", "\\\\/");
        if (in.contains("w")) in = in.replaceAll("w", "\\\\/\\\\/");
        if (in.contains("x")) in = in.replaceAll("x", "}{");
        if (in.contains("y")) in = in.replaceAll("y", "`/");
        if (in.contains("z")) in = in.replaceAll("z", "2");
        return in;
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
