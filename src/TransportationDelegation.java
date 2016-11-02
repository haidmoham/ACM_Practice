/**
 * Created on 10/31/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class TransportationDelegation {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        int s = sc.nextInt(), r = sc.nextInt(), f = sc.nextInt(), t = sc.nextInt();
        MaxFlowSolver graph = new Dinic();
        Node src = graph.addNode();
        Node snk = graph.addNode();
        Node[] in = new Node[t];
        Node[] out = new Node[t];
        for (int i = 0; i < t; i++) {
            in[i] = graph.addNode();
            out[i] = graph.addNode();
            graph.link(in[i], out[i], 1);
        }
        HashMap<String, Node> raw = new HashMap<>();
        HashMap<String, Node> fct = new HashMap<>();
        HashMap<String, Node> inter = new HashMap<>();
        for (int i = 0; i < r; i++) {
            String tmp = sc.next();
            raw.put(tmp, graph.addNode());
            graph.link(src, raw.get(tmp), 1);
        }
        for (int i = 0; i < f; i++) {
            String tmp = sc.next();
            fct.put(tmp, graph.addNode());
            graph.link(fct.get(tmp), snk, 1);
        }
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                String str = sc.next();
                if (raw.containsKey(str)) {
                    //System.out.println(str + " " + i);
                    graph.link(raw.get(str), in[i], 1);
                }
                if (fct.containsKey(str)) {
                    //System.out.println(i + " " + str);
                    graph.link(out[i], fct.get(str), 1);
                }
                else {
                    //System.out.println(i + " " + str + " " + i);
                    inter.putIfAbsent(str, graph.addNode());
                    graph.link(out[i], inter.get(str), 1);
                    graph.link(inter.get(str), in[i], 1);
                }
            }
        }
        System.out.println(graph.getMaxFlow(src, snk));
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
    public static class Node {
        List<Edge> edges = new ArrayList<Edge>();
        int index;      // index in nodes array

        // thou shall not create nodes except through addNode()
        private Node() {
        }
    }

    public static class Edge {
        final int capacity;
        boolean forward; // true: edge is in original graph
        // false: edge is a backward edge
        Node from, to;   // nodes connected
        int flow;        // current flow
        Edge dual;      // reference to this edge's dual
        long cost;      // only used for MinCost.

        // thou shall not create edges except through link()
        protected Edge(Node s, Node d, int c, boolean f) {
            forward = f;
            from = s;
            to = d;
            capacity = c;
        }

        // remaining capacity()
        int remaining() {
            return capacity - flow;
        }

        // increase flow and adjust dual
        void addFlow(int amount) {
            flow += amount;
            dual.flow -= amount;
        }
    }

    /* A Max Flow solver base class. */
    public static abstract class MaxFlowSolver {
        /* List of nodes, indexed. */
        List<Node> nodes = new ArrayList<Node>();

        /* Create a graph with n nodes. */
        protected MaxFlowSolver(int n) {
            for (int i = 0; i < n; i++)
                addNode();
        }

        protected MaxFlowSolver() {
            this(0);
        }

        /* Create an edge between nodes n1 and n2 */
        public void link(Node n1, Node n2, int capacity) {
        /*
         * Only the EdmondsKarp solver takes cost into account
         * during getMaxFlow().  Setting it to 1 for problems
         * that do not involve cost means it uses a shortest path
         * search when finding augmenting paths.  In practice,
         * this does not seem to make a difference.
         */
            link(n1, n2, capacity, 1);
        }

        /* Create an edge between nodes n1 and n2 and assign cost */
        public void link(Node n1, Node n2, int capacity, long cost) {
            Edge e12 = new Edge(n1, n2, capacity, true);
            Edge e21 = new Edge(n2, n1, 0, false);
            e12.dual = e21;
            e21.dual = e12;
            n1.edges.add(e12);
            n2.edges.add(e21);
            e12.cost = cost;
            e21.cost = -cost;
        }

        /* Create an edge between nodes n1 and n2 */
        void link(int n1, int n2, int capacity) {
            link(nodes.get(n1), nodes.get(n2), capacity);
        }

        public abstract int getMaxFlow(Node src, Node snk);

        /* Add a new node. */
        public Node addNode() {
            Node n = new Node();
            n.index = nodes.size();
            nodes.add(n);
            return n;
        }

        /* OPTIONAL: Returns the edges associated with the Min Cut.
         * Must be run immediately after a getMaxFlow() call.  */
        List<Edge> getMinCut(Node src) {
            Queue<Node> bfs = new ArrayDeque<Node>();
            Set<Node> visited = new HashSet<Node>();
            bfs.offer(src);
            visited.add(src);
            while (!bfs.isEmpty()) {
                Node u = bfs.poll();
                for (Edge e : u.edges) {
                    if (e.remaining() > 0 && !visited.contains(e.to)) {
                        visited.add(e.to);
                        bfs.offer(e.to);
                    }
                }
            }
            List<Edge> minCut = new ArrayList<Edge>();
            for (Node s : visited) {
                for (Edge e : s.edges)
                    if (e.forward && !visited.contains(e.to))
                        minCut.add(e);
            }
            return minCut;
        }

        /* OPTIONAL/DEPRECATED: for min-cost, Klein's cycle canceling algorithm.
         * Must be run after getMaxFlow() call.
         *
         * For most problems, you should be using Edmonds-Karp instead, which minimizes
         * cost as it computes the maximum flow (so no call to minimizeFlow is necessary).
         *
         * The only reason to use Klein's would be to benefit from a speed advantage
         * of Dinic/PushRelabel during the max flow phase, if Klein's can terminate
         * in one iteration.  This is something you in general do not know, however,
         * and we've seen problems where Klein's times out if an unfortunate maximum
         * flow was found by the maxflow solver.
         *
         * Given any max flow (with edge costs), cancel negative cycles
         * until the flow is a min-cost max-flow.
         * According to CS226 slides, this is O(E^2 C U) where C and U
         * are max cost and capacity, respectively.
         * @return total cost of flow
         */
        long minimizeFlow(Node src) {
            final long SUITABLE_INF = Long.MAX_VALUE / 2 - 1;

            // While there are negative cycles
            while (true) {

                // Find a negative cycle (sparse Bellman-Ford)
                // Initialize the graph
                int V = nodes.size();
                long[] dist = new long[V];
                Node[] pred = new Node[V];
                Arrays.fill(dist, SUITABLE_INF);
                dist[src.index] = 0;

                // Run up to V times
                boolean change = true;
                for (int i = 0; change && i < V; i++) {
                    change = false;
                    // For each (u, v) edge, try to relax
                    for (Node from : nodes) {
                        for (Edge e : from.edges) {
                            if (e.remaining() == 0) {
                                continue;
                            }
                            Node to = e.to;
                            if (dist[from.index] + e.cost < dist[to.index]) {
                                change = true;
                                dist[to.index] = dist[from.index] + e.cost;
                                pred[to.index] = from;
                            }
                        }
                    }
                }

                // Find a negative-weight cycle
                ArrayList<Node> cycle = null;
                for (Node startingNode : nodes) {
                    boolean[] seen = new boolean[V];
                    Node currNode = startingNode;
                    while (pred[currNode.index] != null && !seen[currNode.index]) {
                        seen[currNode.index] = true;
                        currNode = pred[currNode.index];
                    }
                    if (pred[currNode.index] == null) {
                        continue;
                    }

                    Node endNode = currNode;
                    cycle = new ArrayList<Node>();
                    while (true) {
                        cycle.add(currNode);
                        currNode = pred[currNode.index];
                        if (currNode == endNode) {
                            break;
                        }
                    }
                }

                // If there's no negative weight cycle, we're done
                if (cycle == null) {
                    break;
                }

                // Get a list of edges in the cycle
                ArrayList<Edge> edgeCycle = new ArrayList<Edge>();
                for (int i = 0; i < cycle.size(); i++) {
                    Node to = cycle.get(i);
                    Node from = cycle.get((i + 1) % cycle.size());
                    for (Edge e : from.edges) {
                        if (e.to == to) {
                            edgeCycle.add(e);
                            break;
                        }
                    }
                }

                // Find the minimum residual capacity in the cycle
                int minResidual = Integer.MAX_VALUE;
                for (Edge e : edgeCycle) {
                    minResidual = Math.min(minResidual, e.remaining());
                }

                if (minResidual == 0) {
                    break;
                }

                // Add this flow to every edge on the cycle
                for (Edge e : edgeCycle) {
                    e.addFlow(minResidual);
                }
            }

            return calcFlowCost();
        }

        // Calculate the cost of a flow, assuming that edge costs are given in the network.
        long calcFlowCost() {
            // Calculate the cost of the flow
            long cost = 0;
            for (Node n : nodes) {
                for (Edge e : n.edges) {
                    if (e.flow > 0) {
                        cost += e.cost * e.flow;
                    }
                }
            }
            return cost;
        }
    }

    /**
     * Implements Ford Fulkerson.
     */
    static class FordFulkerson extends MaxFlowSolver {
        /* Create a graph with n nodes. */
        FordFulkerson() {
            this(0);
        }

        FordFulkerson(int n) {
            super(n);
        }

        @Override
        public int getMaxFlow(Node src, Node snk) {
            int total = 0;

            for (; ; ) {
                // find an augmenting path and record its edges in prev
                Edge[] prev = new Edge[nodes.size()];
                int addedFlow = findAugmentingPath(src, snk, prev);
                if (addedFlow == 0) break;

                total += addedFlow;

                // Go back along the path, and for each edge, move the
                // added flow from the edge to its dual.
                for (Edge edge = prev[snk.index];
                     edge != null;
                     edge = prev[edge.dual.to.index]) {
                    edge.addFlow(addedFlow);
                }
            }

            return total;
        }

        /**
         * Find an augmenting path and return its minimum capacity.
         *
         * @return 0 if no augmenting path could be found.
         * <p>
         * Compute minimum capacity as we go to save a second pass
         * along the path.
         */
        int findAugmentingPath(Node src, Node snk, Edge[] from) {
            Deque<Node> queue = new ArrayDeque<Node>();
            queue.offer(src);

            int N = nodes.size();
            int[] minCapacity = new int[N];
            boolean[] visited = new boolean[N];
            visited[src.index] = true;
            Arrays.fill(minCapacity, Integer.MAX_VALUE);

            while (queue.size() > 0) {
                Node node = queue.poll();
                if (node == snk)
                    return minCapacity[snk.index];

                for (Edge edge : node.edges) {
                    Node dest = edge.to;
                    if (edge.remaining() > 0 && !visited[dest.index]) {
                        visited[dest.index] = true;
                        from[dest.index] = edge;
                        minCapacity[dest.index] = min(minCapacity[node.index],
                                edge.remaining());

                        // can bail as soon as we see sink.
                        if (dest == snk)
                            return minCapacity[snk.index];

                        queue.push(dest);
                    }
                }
            }

            return 0;   // no path.
        }
    }

    /**
     * Implements Edmonds/Karp min-cost max-flow.
     * See Theoretical Improvements in Algorithmic Efficiency for
     * Network Flow Problems by Edmonds and Karp,
     * Journal of the Association for Computing Machinery,
     * Vol. 19, No. 2, Apri; 1972. pp. 248-264.
     */
    static class EdmondsKarp extends MaxFlowSolver {
        static final long INF = Long.MAX_VALUE / 4;
        long minCost;
        Node lastSrc;       // sanity check, not needed for correctness

        EdmondsKarp() {
            this(0);
        }

        /* Create a graph with n nodes. */
        EdmondsKarp(int n) {
            super(n);
        }

        @Override
        long minimizeFlow(Node src) {
            // we have already minimized the flow cost during the maxflow phase
            if (src == lastSrc) return minCost;
            throw new Error("must use same source as the one used to maximize flow");
        }

        /**
         * Maximize the flow, and simultaneously minimize its cost.
         * Code taken from judge solution to Chicago 2013/Job Postings
         * http://serjudging.vanb.org/wp-content/uploads/jobpostings_artur.java
         */
        @Override
        public int getMaxFlow(Node src, Node snk) {
            lastSrc = src;
            final int n = nodes.size();
            final int source = src.index;
            final int sink = snk.index;
            int flow = 0;
            long cost = 0;
            long[] potential = new long[n]; // allows Dijkstra to work with negative edge weights

            while (true) {
                Edge[] parent = new Edge[n]; // used to store an augmenting path
                long[] dist = new long[n]; // minimal cost to vertex
                Arrays.fill(dist, INF);
                dist[source] = 0;

                // Dijkstra on cost
                PriorityQueue<Item> que = new PriorityQueue<Item>();
                que.add(new Item(0, source));
                while (!que.isEmpty()) {
                    Item item = que.poll();
                    if (item.dist != dist[item.v])
                        continue;

                    for (Edge e : nodes.get(item.v).edges) {
                        long temp = dist[item.v] + e.cost + potential[item.v] - potential[e.to.index];
                        // if can push some flow, and new cost is cheaper than push
                        if (e.capacity > e.flow && dist[e.to.index] > temp) {
                            dist[e.to.index] = temp;
                            parent[e.to.index] = e;
                            que.add(new Item(temp, e.to.index));
                        }
                    }
                }

                // couldn't reach sink
                if (parent[sink] == null)
                    break;
                // update potentials for Dijkstra
                for (int i = 0; i < n; i++)
                    if (parent[i] != null)
                        potential[i] += dist[i];

                // maximum flow that can be pushed
                int augFlow = Integer.MAX_VALUE;
                for (int i = sink; i != source; i = parent[i].from.index)
                    augFlow = Math.min(augFlow, parent[i].capacity - parent[i].flow);

                // push the flow
                for (int i = sink; i != source; i = parent[i].from.index) {
                    Edge e = parent[i];
                    e.addFlow(augFlow);
                    cost += augFlow * e.cost;
                }
                flow += augFlow;
            }

            minCost = cost;
            return flow;
        }

        static class Item implements Comparable<Item> {
            long dist;
            int v;

            public Item(long dist, int v) {
                this.dist = dist;
                this.v = v;
            }

            public int compareTo(Item that) {
                return Long.compare(this.dist, that.dist);
            }
        }
    }

    /**
     * Dinic's algorithm, Shimon Even variant.
     */
    public static class Dinic extends MaxFlowSolver {
        int[] dist;          // distance from src for level graph
        boolean[] blocked;   // true if all outgoing edges blocked in residual graph

        public Dinic() {
            this(0);
        }

        public Dinic(int n) {
            super(n);
        }

        int BlockingFlow(Node src, Node snk) {
            int N = nodes.size();
            dist = new int[N];
            Arrays.fill(dist, -1);
            int[] Q = new int[N];
            int s = src.index;
            int t = snk.index;

        /* Step 1.  BFS from source to compute levels */
            dist[s] = 0;
            int head = 0, tail = 0;
            Q[tail++] = s;
            while (head < tail) {
                int x = Q[head++];
                List<Edge> succ = nodes.get(x).edges;
                for (Edge e : succ) {
                    if (dist[e.to.index] == -1 && e.remaining() > 0) {
                        dist[e.to.index] = dist[e.from.index] + 1;
                        Q[tail++] = e.to.index;
                    }
                }
            }

            if (dist[t] == -1)     // no flow if sink is not reachable
                return 0;

        /* Step 2. Push flow down until we have a blocking flow */
            int flow, totflow = 0;
            blocked = new boolean[N];
            do {
                flow = dfs(src, snk, Integer.MAX_VALUE);
                totflow += flow;
            } while (flow > 0);
            return totflow;
        }

        /*
         * Run DFS on the BFS level graph.
         */
        int dfs(Node v, Node snk, int mincap) {
            // reached sink
            if (v == snk)
                return mincap;

            for (Edge e : v.edges) {
                // standard DFS, but consider an edge only if
                if (!blocked[e.to.index]    // the path to the sink is not already blocked
                        && dist[e.from.index] + 1 == dist[e.to.index] // it's in the BFS level graph
                        && e.remaining() > 0) {  // the edge has remaining capacity
                    int flow = dfs(e.to, snk, min(mincap, e.remaining()));
                    if (flow > 0) {
                        e.addFlow(flow);
                        return flow;
                    }
                }
            }
            // if we couldn't add any flow then there is no point in ever going
            // past this node again.  Mark it blocked.
            blocked[v.index] = true;
            return 0;
        }

        @Override
        public int getMaxFlow(Node src, Node snk) {
            int flow, totflow = 0;
            while ((flow = BlockingFlow(src, snk)) != 0)
                totflow += flow;
            return totflow;
        }
    }

    // Source: http://web.stanford.edu/~liszt90/acm/notebook.html#file3
//
// Adjacency list implementation of FIFO push relabel maximum flow
// with the gap relabeling heuristic.  This implementation is
// significantly faster than straight Ford-Fulkerson.  It solves
// random problems with 10000 vertices and 1000000 edges in a few
// seconds, though it is possible to construct test cases that
// achieve the worst-case.
//
    static class PushRelabel extends MaxFlowSolver {
        int[] count;
        int[] dist;
        int[] excess;
        boolean[] active;
        Queue<Integer> Q;
        int N;

        PushRelabel() {
            this(0);
        }

        PushRelabel(int n) {
            super(n);
        }

        void Enqueue(int v) {
            if (!active[v] && excess[v] > 0) {
                active[v] = true;
                Q.offer(v);
            }
        }

        void Push(Edge e) {
            int amt = min(excess[e.from.index], e.remaining());
            if (dist[e.from.index] <= dist[e.to.index] || amt == 0)
                return;

            e.addFlow(amt);

            excess[e.to.index] += amt;
            excess[e.from.index] -= amt;
            Enqueue(e.to.index);
        }

        void Gap(int k) {
            for (int v = 0; v < N; v++) {
                if (dist[v] < k) continue;
                count[dist[v]]--;
                dist[v] = max(dist[v], N + 1);
                count[dist[v]]++;
                Enqueue(v);
            }
        }

        void Relabel(int v) {
            count[dist[v]]--;
            dist[v] = 2 * N;
            for (Edge e : nodes.get(v).edges) {
                if (e.remaining() > 0)
                    dist[v] = min(dist[v], dist[e.to.index] + 1);
            }
            count[dist[v]]++;
            Enqueue(v);
        }

        void Discharge(int v) {
            for (Edge e : nodes.get(v).edges) {
                if (excess[v] > 0)
                    Push(e);
                else
                    break;
            }

            if (excess[v] > 0) {
                if (count[dist[v]] == 1)
                    Gap(dist[v]);
                else
                    Relabel(v);
            }
        }

        @Override
        public int getMaxFlow(Node src, Node snk) {
            N = nodes.size();
            count = new int[2 * N];
            dist = new int[N];
            excess = new int[N];
            active = new boolean[N];
            Q = new ArrayDeque<Integer>();

            count[0] = N - 1;
            count[N] = 1;

            dist[src.index] = N;
            active[src.index] = active[snk.index] = true;

            for (Edge e : src.edges) {
                excess[src.index] += e.capacity;
                Push(e);
            }

            // FIFO selection rule
            while (!Q.isEmpty()) {
                int v = Q.poll();
                active[v] = false;
                Discharge(v);
            }

            int totflow = 0;
            for (Edge e : src.edges)
                totflow += e.flow;
            return totflow;
        }
    }
}
