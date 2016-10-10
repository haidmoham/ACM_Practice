/**
 * Created by mo on 10/8/16.
 */

import java.util.*;
import java.io.*;

public class BigTruck {
    static HashMap<Integer, Long>[] adjacent;
    /*
     * Off by one errors on certain cases
     * Time to start writing test cases
     */
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] items = new int[n];
        for (int i = 0; i < n; i++){
            items[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        adjacent = new HashMap[n];
        for (int i = 0; i < n; i++)
            adjacent[i] = new HashMap<>();
        for (int i = 0; i < m; i++) {
            //Attempting the bit folding method suggested by Peter
            int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
            long w = sc.nextLong();
            w *= 1000000;
            //System.out.println((w - items[b]) + " " + (w - items[a]));
            //adding points and distances, subtracting items so that
            //the edges that have more items automatically are picked
            //without the need for tie breakers
            adjacent[a].put(b, w - items[b]);
            adjacent[b].put(a, w - items[a]);
        }
        //since everything was subtracted by 1, to calculate the distance and items
        //from 1 to n, we run dijkstra's from 0 to n-1
        //pass the initial amount of items as a parameter, to let
        //dijkstra's initialize with a negative starting edge weight to reflect the items at start (line 55)
        State fin = dijkstra(new State(0, -1 * items[0]), n - 1, items[0]);
        if (fin == null) {
            System.out.println("impossible");
        }
        else {
            long itemsout = (1000000 - fin.distance % 1000000);
            System.out.println(((fin.distance - 1) / 1000000 + 1) + " " + (itemsout));
        }

    }
    static State dijkstra(State start, int end, long init){
        PriorityQueue<State> pq = new PriorityQueue<>();
        HashMap<Integer, Long> map = new HashMap<>(); //stores the distances in a hashmap of int keys long values
        pq.offer(start);
        map.put(start.node, -1 * init); //initialize with negative equal to number of items for consistent modeling of states
        while(!pq.isEmpty()){
            State curr = pq.poll();
            if (curr.node == end)
                return curr;
            for (State s : curr.getNeighbors()) {
                if (!map.containsKey(s.node) || s.distance < map.get(s.node)) {
                    pq.offer(new State(s.node, s.distance));
                    map.put(s.node, s.distance);
                }
            }
        }
        return null;
    }

    static class State implements Comparable<State> {
        int node;
        long distance;

        public State(int n, long d){
            node = n;
            distance = d;
        }

        public int HashCode() {
            return node;
        }

        public int compareTo(State s){
            return Long.compare(distance, s.distance);
        }

        public boolean equals(Object o){
            State s = (State) o;
            if (node == s.node) return true;
            return false;
        }

        public ArrayList<State> getNeighbors() {
            ArrayList<State> neighbors = new ArrayList<State>();
            for (Integer i : adjacent[node].keySet()) {
                State s = new State(i, distance + adjacent[node].get(i));
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
