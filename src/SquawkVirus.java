/**
<<<<<<< HEAD
 * Created by mo on 10/10/16.
=======
 * Created by mo on 10/8/16.
>>>>>>> origin/master
 */

import java.util.*;
import java.io.*;

public class SquawkVirus {
<<<<<<< HEAD
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        long[] sqin = new long[n];
        long[] sqout = new long[n];
        sqout[s] = 1;
        for (int i = 0; i < t; i++){
            for (int v = 0; v < n; v++) {
                for (int w : adj[v]) {
                    sqin[w] += sqout[v];
                }
            }
            for (int v = 0; v < n; v++) {
                sqout[v] = sqin[v];
                sqin[v] = 0;
            }
        }
        long total = 0;
        for (int i = 0; i < n; i++)
            total += sqout[i];
        System.out.println(total);
=======
    public static int count = 0;
    public static HashMap<Integer, Integer> squawks;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        squawks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            squawks.put(i, 0);
        }
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        squawks.put(s, 1);
        bfsMod(graph, s, t);
        int out = 0;
        for (Integer i : squawks.keySet())
            System.out.println(i + " " + squawks.get(i));
        for (Integer i : squawks.keySet()) {
            out += squawks.get(i);
        }
        System.out.println(out);

    }

    public static void bfsMod(ArrayList<ArrayList<Integer>> graph, int start, int time) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> frontier = new ArrayDeque<>();

        frontier.add(start);
        while (count < time) {
            int current = frontier.poll();
            squawks.put(current, squawks.get(current)+ 1);
            for (int adj : graph.get(current)) {
                if (!visited.contains(adj)) {
                    frontier.offer(adj);
                    visited.add(adj);
                    squawks.put(adj, squawks.get(adj) + 1);
                }
            }
            count++;
        }
>>>>>>> origin/master
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
