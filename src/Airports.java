/**
 * Created by mo on 11/2/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Airports {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
    }
    static class BipartiteMatching {
        int n1, n2;
        int edges = 0;
        int[] last;
        List<Integer> prev;
        List<Integer> head;
        int[] matching;
        int[] dist;
        int[] Q;
        boolean[] used;
        boolean[] vis;

        BipartiteMatching(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;

            last = new int[n1];
            prev = new ArrayList<>();
            head = new ArrayList<>();
            matching = new int[n2];
            dist = new int[n1];
            Q = new int[n1];
            used = new boolean[n1];
            vis = new boolean[n1];

            Arrays.fill(last, -1);
        }

        void addEdge(int u, int v) {
            head.add(v);
            prev.add(last[u]);
            last[u] = edges++;
        }

        void bfs() {
            Arrays.fill(dist, -1);
            int sizeQ = 0;
            for (int u = 0; u < n1; u++) {
                if (!used[u]) {
                    Q[sizeQ++] = u;
                    dist[u] = 0;
                }
            }
            for (int i = 0; i < sizeQ; i++) {
                int u1 = Q[i];
                for (int e = last[u1]; e >= 0; e = prev.get(e)) {
                    int u2 = matching[head.get(e)];
                    if (u2 >= 0 && dist[u2] < 0) {
                        dist[u2] = dist[u1] + 1;
                        Q[sizeQ++] = u2;
                    }
                }
            }
        }

        boolean dfs(int u1) {
            vis[u1] = true;
            for (int e = last[u1]; e >= 0; e = prev.get(e)) {
                int v = head.get(e);
                int u2 = matching[v];
                if (u2 < 0 || !vis[u2] && dist[u2] == dist[u1] + 1 && dfs(u2)) {
                    matching[v] = u1;
                    used[u1] = true;
                    return true;
                }
            }
            return false;
        }

        int findMaxMatching() {
            Arrays.fill(matching, -1);
            int numMatches = 0;
            int f;
            do {
                bfs();
                Arrays.fill(vis, false);
                f = 0;
                for (int u = 0; u < n1; u++) {
                    if (!used[u] && dfs(u)) {
                        f++;
                    }
                }
                numMatches += f;
            } while (f > 0);
            return numMatches;
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
