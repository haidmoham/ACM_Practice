package data; /**
 * Created by mo on 10/25/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class test {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
            System.out.println(toLong(sc.next()));
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

    static long toLong(String in) {
        char[] ch = in.toCharArray();
        long temp = 0;
        for (int i = 0; i < ch.length; i++) {
            long c = (ch[i] == 'A' ? 1 : ch[i] == 'C' ? 2 : ch[i] == 'T' ? 3 : ch[i] == 'G' ? 4 : -1);
            //System.out.println(c);
            temp += (Math.pow(10, i)) * c;
        }
        return reverse(temp);
    }
    public static long reverse(long x) {
        long rev = 0;
        while(x != 0){
            rev = rev*10 + x%10;
            x /= 10;
        }
        return rev;
    }
}
