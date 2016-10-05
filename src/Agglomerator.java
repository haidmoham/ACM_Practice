/**
 * Created by mo on 10/1/16.
 */

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Agglomerator {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        Circle[] circ = new Circle[n];
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++){
            int x = sc.nextInt(), y = sc.nextInt(), vx = sc.nextInt(), vy = sc.nextInt(), r = sc.nextInt();
            P point = new P(x, y);
            circ[i] = new Circle(point, r);
            P vx2 = new P(0, vx);
            P vy2 = new P(0, vy);
            Line l = new Line(vx2, vy2);
            lines[i] = l;
        }
        for (int i = 0; i < n; i++){
        }
    }
    static class P {
        final double x, y;
        P(double x, double y)    { this.x = x; this.y = y; }
        P sub(P that)            { return new P(x - that.x, y - that.y); }
        P add(P that)            { return new P(x + that.x, y + that.y); }
        double dot(P that)       { return x * that.x + y * that.y; }
        P mult(double s)         { return new P(x * s, y * s); }
        // Use hypot() only if intermediate overflow must be avoided
        double length()          { return sqrt(x*x + y*y); }
        double length2()         { return x * x + y * y; }
        P leftNormal()           { return new P(-y, x); }   // rotateCCW(90deg)
        P rightNormal()          { return new P(y, -x); }   // rotateCW(90deg)
        P normalize()            { double n = length(); return n > 0 ? new P(x/n, y/n) : origin(); }
        P scale(double l)        { return normalize().mult(l); }
        P project(P a)           { return mult(a.dot(this) / length2()); }
        P reflect(P a)           { return project(a).mult(2.0).sub(a); }
        // use if sin, cos are known
        P rotateCCW(double sinT, double cosT) {
            return new P(x * cosT - y * sinT,
                    x * sinT + y * cosT); }
        P rotateCW(double sinT, double cosT) { return rotateCCW(-sinT, cosT); }
        P rotate(double theta)   { return rotateCCW(sin(theta), cos(theta)); }
        double theta()           { return atan2(y, x); }    // from -pi to +pi
        double angleTo(P a)      { return acos(this.dot(a) / this.length() / a.length()); }
        boolean isOrigin()       { return x == 0 && y == 0; }
        public String toString() { return String.format("(%f,%f)", this.x, this.y); }
        static P read(Scanner s) { return new P(s.nextDouble(), s.nextDouble()); }
        static P origin()        { return new P(0, 0); }
        double det(P that)       { return this.x * that.y - this.y * that.x; }
        double crossproduct(P that) { return this.det(that); }
        P half(P q)              { return normalize().add(q.normalize()); }

        double dist(P to)        { return sub(to).length(); }
        double signedParallelogramArea(P b, P c) { return (b.sub(this).crossproduct(c.sub(this))); }
        boolean isCollinearWith(P b, P c) { return abs(signedParallelogramArea(b, c)) <= EPS; }
        // is going from this to b to c a CCW turn? Do not use if points may be collinear
        boolean isCCW(P b, P c)  { return signedParallelogramArea(b, c) > 0; }
        double signedTriangleArea(P b, P c) { return signedParallelogramArea(b, c) / 2.0; }

        // memory-optimized version of this.sub(to).length2() that avoids an intermediate object
        double dist2(P to)       {
            double dx = this.x - to.x;
            double dy = this.y - to.y;
            return dx * dx + dy * dy;
        }

        /**
         * Compute x for a * x + b = 0 and ||x|| = C
         * where 'this' is a.
         * Care must be taken to handle the case where
         * either a.x or a.y is near zero.
         */
        P [] solveDotProductConstrainedByNorm(double b, double C) {
            P a = this;
            if (a.isOrigin())
                throw new Error("degenerate case");

            boolean transpose = abs(a.x) > abs(a.y);
            a = transpose ? new P(a.y, a.x) : a;

            Double [] x = solvequadratic(a.length2(), 2.0 * b * a.x, b * b - a.y * a.y * C * C);
            P [] p = new P[x.length];
            for (int i = 0; i < x.length; i++) {
                double x1 = x[i];
                double x2 = ((-b - a.x * x1) / a.y);
                p[i] = transpose ? new P(x2, x1) : new P(x1, x2);
            }
            return p;
        }
    }

    static class HP extends P { // Hashable Point
        HP(double x, double y) { super(x, y); }
        @Override
        public int hashCode() { return Double.hashCode(x + 32768*y); }
        @Override
        public boolean equals (Object _that) {
            HP that = (HP)_that;
            return this.x == that.x && this.y == that.y;
        }
    }

    /* Solve a * x^2 + b * x + c == 0
     * Returns 0, 1, or 2 solutions. If 2 solutions x1, x2, guarantees that x1 < x2
     */
    static Double [] solvequadratic(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        if (D < -EPS)
            return new Double [] { };
        D = max(D, 0);
        if (D == 0)
            return new Double [] { -b / 2.0 / a };
        double d = sqrt(D);
        // Numerically more stable, see
        // https://en.wikipedia.org/wiki/Loss_of_significance#A_better_algorithm
        if (signum(b) == 0)
            return new Double[] { d / 2.0 / a, -d / 2.0 / a };
        double x1 = (-b - signum(b) * d) / (2 * a);
        double x2 = c / (a * x1);
        return new Double[] { Math.min(x1, x2), Math.max(x1, x2) };
    }

    /* The Line/Circle classes provide a number of methods that require dealing
     * with floating point precision issues.  Default EPS to a suitable value,
     * such as 1e-6, which should work for many problems in which the input
     * coordinates are in integers and subsequently inexact floating point
     * values are being computed.
     */
    static double EPS = 1e-6;

    /* A line denoted by two points p and q.
     * For internal computations, we use the parametric representation
     * of the line as p + t d where d = q - p.
     * For convenience, we compute and store d in the constructor.
     * Most methods hide the parametric representation of the
     * line, but it is exposed via getPointFromParameter and
     * intersectionParameters for those problems that need it.
     *
     * The line may be interpreted either as a line segment denoted
     * by the two end points, or as the infinite line determined
     * by these two points.  Intersection methods are provided
     * for both cases.
     */
    static class Line {
        private P p, q, d;
        Line(P p, P q) { this.p = p; this.q = q; d = q.sub(p); }

        P getPointFromParameter(double t) { return p.add(d.mult(t)); }

        // reflect vector across vector (as if line originated at (0, 0))
        P reflect(P d2) { return d.reflect(d2); }

        // reflect point across (infinite) line
        P reflectPoint(P r) { return reflect(r.sub(p)).add(p); }

        // project p onto this (infinite) line. Returns point on line
        P project(P a) { return p.add(d.project(a.sub(p))); }

        // return distance of point P from this (infinite) line.
        double distance(P a) { return project(a).dist(a); }

        @Override
        public String toString() { return String.format("[%s => %s]", p, q); }

        /* Point of intersection of this line segment
         * with another line segment.  Returns only points
         * that lie inside both line segments, else null.
         *
         * Result may include points "just outside" the bounds,
         * given EPS.
         */
        P intersectsInBounds(Line l) {
            double [] st = intersectionParameters(l);
            if (st == null)
                return null;

            // check that point of intersection is in direction 'd'
            // and within segment bounds
            double s = st[0];
            double t = st[1];
            if (s >= -EPS && s <= 1+EPS && -EPS <= t && t <= 1+EPS)
                return getPointFromParameter(s);

            return null;
        }

        /* Point of intersection of this (infinite) line
         * with other (infinite) line.  Return null if collinear.
         */
        P intersects(Line l) {
            double [] st = intersectionParameters(l);
            if (st != null)
                return getPointFromParameter(st[0]);
            return null;
        }

        /* Intersect this line with that line
         * Solves:  this.p + s * this.d == l.p + t l.d
         * Return null if lines are collinear
         * Else returns [s, t].
         */
        double [] intersectionParameters(Line l) {
            P dneg = p.sub(q);
            double D = l.d.det(dneg);
            // Use Cramer's rule; see text
            if (D == 0.0)
                return null;

            P rp = p.sub(l.p);
            return new double[] { l.d.det(rp) / D, rp.det(dneg) / D };
        }

        /* Compute points of intersection of this infinite line
         * with a circle.
         * Computes projection 'x' of c.c onto line, then computes
         * x +/- d.scale(h) where h is computed via Pythagoras.
         * Sorted by decreasing 't'
         *
         * May return two points even if line is a tangent.
         */
        P [] intersectsCircle(Circle c) {
            P x = project(c.c);
            double D = x.dist(c.c);
            // outside by more than EPS
            if (D > c.R + EPS) return new P[0];
            double h = sqrt(max(0, c.R * c.R - D * D));
            if (h == 0) return new P[] { x };   // EPS (!?)
            return new P[] { x.add(d.scale(h)), x.add(d.scale(-h)) };
        }

        /* Compute points of intersection of this infinite line
         * with a circle.
         *
         * Solves a + t * b = c + r subject to ||r|| = R
         * Returns zero, one, or two points on the periphery,
         * e.g. c + r[0,1], sorted by decreasing 't'.
         * Alternative version which requires solving quadratic
         * equation.
         *
         * Careful: set EPS if you need to handle round-off error
         * in discriminant.
         */
        P [] intersectsCircleAlternative(Circle c) {
            P ca = c.c.sub(p);
            P d = q.sub(p);
            Double [] t = solvequadratic(d.length2(), -2 * d.dot(ca), ca.length2() - c.R * c.R);
            P [] r = new P[t.length];
            for (int i = 0; i < t.length; i++)
                r[i] = p.add(d.mult(t[i]));
            return r;
        }

        /**
         * Is r contained within the line segment spanned by p/q,
         * including their endpoints?
         */
        boolean contains(P r) {
            double t = p.signedParallelogramArea(q, r);
            if (abs(t) > EPS)   // point not on line
                return false;

            // check that point's distance from p is less than the
            // distance between p and q, and that it lies in the same
            // direction.  We use >= 0 in case r == p.
            return p.dist(r) < p.dist(q) + EPS && r.sub(p).dot(d) >= EPS;
        }
    }

    static class Circle {
        P c;
        double R;
        Circle(P c, double R) { this.c = c; this.R = R; }
        @Override
        public String toString() { return String.format("{%s, %.03f}", c, R); }
        boolean contains(P p) { return R > p.dist(c) + EPS; }
        /* a line segment is outside a circle if both end points are outside and
         * if any intersection points are outside the bounds of the line segment. */
        boolean isOutside(Line l) {
            if (contains(l.p) || contains(l.q))
                return false;
            P [] _is = l.intersectsCircle(this);
            if (_is.length > 1)
                for (P is : _is)
                    if (l.contains(is))
                        return false;
            return true;
        }

        /* Returns the tangent lines that the point p makes with this circle, if any. */
        Line [] tangentLines(P p)
        {
            // Let c +/- r be the tangent points.  Then there's a 'd' such that
            // p + d - r = c
            // Since d r = 0, we multiply by r and get
            // (p - c) r - ||r|| = 0  subject to ||r|| = R
            P [] r = p.sub(c).solveDotProductConstrainedByNorm(-R*R, R);
            Line [] tangents = new Line[r.length];
            for (int i = 0; i < tangents.length; i++)
                tangents[i] = new Line(p, c.add(r[i]));
            return tangents;
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
