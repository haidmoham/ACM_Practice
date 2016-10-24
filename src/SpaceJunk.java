import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import  java.util.*;

/**
 * Created by Alyssa on 10/22/2016.
 */
public class SpaceJunk {

    static int x;
    static int y;
    static int z;
    static int r;
    static int vx;
    static int vy;
    static int vz;

    static int x2;
    static int y2;
    static int z2;
    static int r2;
    static int vx2;
    static int vy2;
    static int vz2;
    /* Outputs 1.000000
 * (the exact value is 1.000000010409562)
 */
    public static void main(String []av) {

        FastScanner sc = new FastScanner();
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            r = sc.nextInt();
            vx = sc.nextInt();
            vy = sc.nextInt();
            vz = sc.nextInt();

            x2 = sc.nextInt();
            y2 = sc.nextInt();
            z2 = sc.nextInt();
            r2 = sc.nextInt();
            vx2 = sc.nextInt();
            vy2 = sc.nextInt();
            vz2 = sc.nextInt();

            // parabola; root at 1, we start at [3,16]
            double closestTime = ternarySearch(new Func() {
                public double apply(double t) {
                    return dist(t);
                }
                }, 0, 100000);


            if(isIntersecting(closestTime)) {
                double time = binarySearch(0,   closestTime);

                sb.append(time).append('\n');
            }
            else {
                sb.append("No collision").append('\n');
            }
        }

        System.out.print(sb);
    }

    static double dist(double t) {
        return Math.sqrt(
                        Math.pow(fx2(t) - fx1(t), 2) +
                        Math.pow(fy2(t) - fy1(t), 2) +
                        Math.pow(fz2(t) - fz1(t), 2));
    }

    static boolean isIntersecting(double t) {
        double dist = dist(t);
        return (dist - (r + r2)) <= 100 * Math.ulp(dist);
    }

    static double fx1(double t) {
        return x + vx*t;
    }

    static double fy1(double t) {
        return y + vy*t;
    }

    static double fz1(double t) {
        return z + vz*t;
    }

    static double fx2(double t) {
        return x2 + vx2*t;
    }

    static double fy2(double t) {
        return y2 + vy2*t;
    }

    static double fz2(double t) {
        return z2 + vz2*t;
    }


    /**
     * Return x in [a, b] such that f(x) is minimal.
     * f() must have exactly one minimum in [a, b]
     */
    static double ternarySearch(Func f, double left, double right) {
        while (true) {
            if ((right - left) < 10 * Math.ulp(right))
                return (left + right)/2.0;

            double leftThird = (2*left + right)/3;
            double rightThird = (left + 2*right)/3;

            if (f.apply(leftThird) < f.apply(rightThird))
                right = rightThird; // discard right third
            else
                left = leftThird;   // discard left third
        }
    }
    static interface Func { double apply(double t); }

    //left should be not intersecting, right IS itnersecting
    static double binarySearch(double left, double right) {
        while(true) {
            if((right - left) < 10*Math.ulp(right))
                return (left + right) / 2.0;

            double mid = (left + right) / 2.0;

            if(isIntersecting(mid)) {
                right = mid;
            }
            else {
                left = mid;
            }
        }

    }

//    /* excerpt from java.util.Arrays.binarySearch
// * @return index of the search key, if it is contained in the array
// *         within the specified range;
// *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.
// */
//    private static int binarySearch(int[] a, int fromIndex, int toIndex,
//                                    int key) {
//        int low = fromIndex;
//        int high = toIndex - 1;
//
//        while (low <= high) {
//            int mid = (low + high) >>> 1; // avoid overflow
//            int midVal = a[mid];
//
//            if (midVal < key)
//                low = mid + 1;
//            else if (midVal > key)
//                high = mid - 1;
//            else
//                return mid; // key found
//        }
//        return -(low + 1);  // key not found.
//    }
//




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
