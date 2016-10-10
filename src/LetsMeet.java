/**
 * Created by mo on 10/9/16.
 */

import java.util.*;
import java.io.*;

public class LetsMeet {
    /**
     * Based on a BFS, but there's more to this problem
     * Perhaps binary search through the times???
     */
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i <  n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++){
            int a = sc.nextInt(), b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int start = sc.nextInt(), goal = sc.nextInt();
        int out = bfs(graph, start, goal) / 2;
        System.out.println(out == 0 ? "never meet" : out);
    }


    public static int bfs(ArrayList<ArrayList<Integer>> graph, int start, int goal) {
        int[] dist = new int[graph.size()];
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> frontier = new ArrayDeque<>();

        dist[start] = 0;
        frontier.add(start);
        while (!frontier.isEmpty()) {
            int current = frontier.poll();
            int d = dist[current];
            if (current == goal)
                return d;

            for (int adj : graph.get(current)) {
                if (!visited.contains(adj)) {
                    frontier.offer(adj);
                    visited.add(adj);
                    dist[adj] = d + 1;
                }
            }
        }
        return -1;
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
