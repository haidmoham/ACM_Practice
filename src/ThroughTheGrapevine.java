/**
 * Created on 12/6/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class ThroughTheGrapevine {
    static HashMap<Integer, Double>[] adjacent;
    static HashMap<Integer, Double>[] adjacent2;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        adjacent = new HashMap[n];
        adjacent2 = new HashMap[n];
        for (int i = 0; i < n; i++) {
            adjacent[i] = new HashMap<>();
            adjacent2[i] = new HashMap<>();
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            double p = sc.nextDouble();
            adjacent2[u].put(v, p);
            adjacent2[v].put(u, p);
            p *= -1;
            adjacent[u].put(v, p);
            adjacent[v].put(u, p);
        }
        int s = sc.nextInt(), t = sc.nextInt();
        State fin = dijkstra(new State(s, 1), t);
        State fin2 = dijkstra(new State(s, 1), t);
        System.out.println(abs(fin.prob + fin2.prob) / 2);
    }
    static State dijkstra(State start, int end){
        PriorityQueue<State> pq = new PriorityQueue<>();
        HashMap<Integer, Double> map = new HashMap<>();
        pq.offer(start);
        map.put(start.node, 1.);
        while(!pq.isEmpty()){
            State curr = pq.poll();
            if (curr.node == end)
                return curr;
            for (State s : curr.getNeighbors()) {
                if (!map.containsKey(s.node) || s.prob < map.get(s.node)) {
                    pq.offer(new State(s.node, s.prob));
                    map.put(s.node, s.prob);
                }
            }
        }
        return null;
    }
    static class State implements Comparable<State> {
        int node;
        double prob;

        public State(int n, double p){
            node = n;
            prob = p;
        }

        public int HashCode() {
            return node;
        }

        public int compareTo(State s){
            return Double.compare(prob, s.prob);
        }

        public boolean equals(Object o){
            State s = (State) o;
            if (node == s.node) return true;
            return false;
        }

        public ArrayList<State> getNeighbors() {
            ArrayList<State> neighbors = new ArrayList<State>();
            for (Integer i : adjacent[node].keySet()) {
                State s = new State(i, prob * adjacent[node].get(i));
                neighbors.add(s);
            }
            return neighbors;
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
