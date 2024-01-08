import java.util.*;
import java.util.LinkedList;

public class Graphs {
    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        Edge(int s, int d) {
            this.source = s;
            this.destination = d;
        }

        Edge(int s, int d, int w) {
            this.source = s;
            this.destination = d;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.weight - e2.weight;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) { // initilize AL
            graph[i] = new ArrayList<>();
        }
        // 0 - vertex
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        // 1 - vertex
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));
        // 2 - vertex
        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));
        // 3 - vertex
        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));
        // 4 - vertex
        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));
        // 5 - vertex
        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));
    }

    // bfs
    public static void bfs(ArrayList<Edge>[] graph) { // O(V+E)
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                bfsUtil(graph, vis);
            }
        }
    }

    public static void bfsUtil(ArrayList<Edge>[] graph, boolean[] vis) {
        Queue<Integer> q = new PriorityQueue<>();
        q.add(0); // source = 0
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.destination);
                }
            }
        }
    }

    // dfs
    public static void dfs(ArrayList<Edge>[] graph) {// O(V+E)
        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            dfsUtil(graph, i, vis);
        }
    }

    public static void dfsUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        vis[curr] = true;
        System.out.print(curr + " ");
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.destination]) {
                dfsUtil(graph, e.destination, vis);
            }
        }
    }

    // Has path?
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return true;
        }
        vis[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            // e.desti = neighbour
            if (!vis[e.destination] && hasPath(graph, e.destination, dest, vis)) {
                return true;
            }
        }
        return false;
    }

    // Detect Cycle in Undirected graph
    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (detectCycleUtil(graph, vis, i, -1)) {
                    return true; // cycle exist in one of the parts
                }
            }
        }
        return false;
    }

    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, boolean[] vis, int curr, int parent) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            // case 3
            if (!vis[e.destination]) {
                if (detectCycleUtil(graph, vis, e.destination, curr)) {
                    return true;
                }
            }
            // case 1
            else if (vis[e.destination] && e.destination != parent) {
                return true;
            }
            // case 2 -> do nothing -> continue
        }
        return false;
    }

    // Bipartite graph
    public static boolean isBipartite(ArrayList<Edge>[] graph) { // O(V+E)
        int colour[] = new int[graph.length];
        for (int i = 0; i < colour.length; i++) {
            colour[i] = -1; // no colour
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (colour[i] == -1) { // BFS
                q.add(i);
                colour[i] = 0; // yellow
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j); // e.dest
                        if (colour[e.destination] == -1) {
                            int nextColour = colour[curr] == 0 ? 1 : 0;
                            colour[e.destination] = nextColour;
                            q.add(e.destination);
                        } else if (colour[e.destination] == colour[curr]) {
                            return false; // Not Bipartite
                        }
                    }
                }
            }
        }
        return true;
    }

    // Cycle Detection (Directed Graph)
    public static boolean isCycle(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        boolean[] stack = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (isCycleUtil(graph, i, vis, stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.destination]) { // cycle
                return true;
            }
            if (!vis[e.destination] && isCycleUtil(graph, e.destination, vis, stack)) {
                return true;
            }
        }
        stack[curr] = false;
        stack[curr] = false;
        return false;
    }

    // Topological Sorting Using DFS
    // public static void topologicalSorting(ArrayList<Edge>[] graph) {
    // boolean[] vis = new boolean[graph.length];
    // Stack<Integer> s = new Stack<>();
    // for (int i = 0; i < graph.length; i++) {
    // if (!vis[i]) {
    // topologicalSortingUtil(graph, i, vis, s);
    // }
    // }
    // while (!s.isEmpty()) {
    // System.out.println(s.pop() + " ");
    // }
    // }

    // public static void topologicalSortingUtil(ArrayList<Edge>[] graph, int curr,
    // boolean[] vis, Stack<Integer> s) {
    // vis[curr] = true;
    // for (int i = 0; i < graph[curr].size(); i++) {
    // Edge e = graph[curr].get(i);
    // if (!vis[e.destination]) {
    // topologicalSortingUtil(graph, e.destination, vis, s);
    // }
    // }
    // s.push(curr);
    // }

    // Topological Sorting Using BFS
    public static void calcInDegree(ArrayList<Edge>[] graph, int[] indeg) {
        for (int v = 0; v < graph.length; v++) {
            for (int j = 0; j < graph[v].size(); j++) {
                Edge e = graph[v].get(j);
                indeg[e.destination]++;
            }
        }
    }

    public static void topoSortBFS(ArrayList<Edge>[] graph) {
        int indeg[] = new int[graph.length];
        calcInDegree(graph, indeg);
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }
        // bfs
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.println(curr + " "); // topological sort print
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.destination]--;
                if (indeg[e.destination] == 0) {
                    q.add(e.destination);
                }
            }
        }
        System.out.println();
    }

    // Dijkstra Algorithm
    static class Pair implements Comparable<Pair> {
        int node;
        int path;

        public Pair(int node, int path) {
            this.node = node;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path; // path based sorting
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int[] dist = new int[graph.length]; // dist[i] = src -> i
        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE; // +infinity
            }
        }
        boolean[] vis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                // neighbours
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    int u = e.source;
                    int v = e.destination;
                    int wt = e.weight;
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            System.out.println(dist[i] + " ");
        }
        System.out.println();
    }

    // Bellmanford's Algorithm
    public static void bellmanFord(ArrayList<Edge>[] graph, int src) {
        int dist[] = new int[graph.length];
        for (int i = 0; i < dist.length; i++) {
            if (i != src)
                dist[i] = Integer.MAX_VALUE;
        }
        int V = graph.length;
        // algo
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < graph.length; j++) {
                for (int k = 0; k < graph[j].size(); k++) {
                    Edge e = graph[j].get(k);
                    // u, v, wt
                    int u = e.source;
                    int v = e.destination;
                    int wt = e.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    // Prims Algorithm for finding minimum spanning tree
    static class Pair2 implements Comparable<Pair2> {
        int v;
        int cost;

        public Pair2(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair2 p2) {
            return this.cost - p2.cost;
        }
    }

    public static void primsAlgorithm(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        PriorityQueue<Pair2> pq = new PriorityQueue<>();
        pq.add(new Pair2(0, 0));
        int finalCost = 0; // MST cost/total min weight
        while (!pq.isEmpty()) {
            Pair2 curr = pq.remove();
            if (!vis[curr.v]) {
                vis[curr.v] = true;
                finalCost += curr.cost;
                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair2(e.destination, e.weight));
                }
            }
        }
        System.out.print("Final Cost : " + finalCost);
    }

    // Cheapest Flights Within K stops
    static class Info {
        int v;
        int cost;
        int stops;

        public Info(int v, int cost, int stops) {
            this.v = v;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public static void createGraphForCheapestFlights(int[][] flights, ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < flights.length; i++) {
            int src = flights[i][0];
            int dest = flights[i][1];
            int wt = flights[i][2];
            Edge e = new Edge(src, dest, wt);
            graph[src].add(e);
        }
    }

    public static int cheapestFlights(int n, int flights[][], int src, int dest, int k) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraphForCheapestFlights(flights, graph);
        int dist[] = new int[graph.length];
        for (int i = 0; i < n; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));
        while (!q.isEmpty()) {
            Info curr = q.remove();
            if (curr.stops > k)
                break;
            for (int i = 0; i < graph[curr.v].size(); i++) {
                Edge e = graph[curr.v].get(i);
                int u = e.source;
                int v = e.destination;
                int wt = e.weight;
                if (curr.cost + wt < dist[v] && curr.stops <= k) {
                    dist[v] = curr.cost + wt;
                    q.add(new Info(v, dist[v], curr.stops + 1));
                }
            }
        }
        if (dist[dest] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dist[dest];
        }
    }

    // Connecting Cities
    static class EdgeForConnectingCities implements Comparable<EdgeForConnectingCities> {
        int dest;
        int cost;

        public EdgeForConnectingCities(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(EdgeForConnectingCities e2) {
            return this.cost - e2.cost; // ascending
        }
    }

    public static int connectingCities(int cities[][]) {
        PriorityQueue<EdgeForConnectingCities> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[cities.length];
        pq.add(new EdgeForConnectingCities(0, 0));
        int finalCost = 0;
        while (!pq.isEmpty()) {
            EdgeForConnectingCities curr = pq.remove();
            if (!vis[curr.dest]) {
                vis[curr.dest] = true;
                finalCost += curr.cost;
                for (int i = 0; i < cities[curr.dest].length; i++) {
                    if (cities[curr.dest][i] != 0) {
                        pq.add(new EdgeForConnectingCities(i, cities[curr.dest][i]));
                    }
                }
            }
        }
        return finalCost;
    }

    // Disjoint set DS
    static int n = 7; // vertices
    static int parent[] = new int[n];
    static int rank[] = new int[n];

    public static void init() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return find(parent[x]);
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (rank[parentA] == rank[parentB]) {
            parent[parentB] = parentA;
            rank[parentA]++;
        } else if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }
    }

    // Kruskal's Algorithm
    public static void kruskalMST(ArrayList<Edge> edges, int V) {
        init();
        Collections.sort(edges);
        int mstCost = 0;
        int count = 0;
        for (int i = 0; count < V - 1; i++) {
            Edge e = edges.get(i);
            int parentA = find(e.source);
            int parentB = find(e.destination);
            if (parentA != parentB) {
                union(e.source, e.destination);
                mstCost += e.weight;
                count++;
            }
        }
        System.out.println(mstCost);
    }

    // Flood Fill Algorithm
    public static void helper(int[][] image, int sr, int sc, int colour, boolean[][] vis, int originalColour) {
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || vis[sr][sc]
                || image[sr][sc] != originalColour) {
            return;
        }
        // left
        helper(image, sr, sc - 1, colour, vis, originalColour);
        // right
        helper(image, sr, sc + 1, colour, vis, originalColour);
        // up
        helper(image, sr - 1, sc, colour, vis, originalColour);
        // down
        helper(image, sr + 1, sc, colour, vis, originalColour);
    }

    public static int[][] floodFillAlgorithm(int[][] image, int sr, int sc, int colour) {
        boolean[][] vis = new boolean[image.length][image[0].length];
        helper(image, sr, sc, colour, vis, image[sr][sc]);
        return image;
    }

    // Strongly Connected Components
    public static void topSort(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.destination]) {
                topSort(graph, e.destination, vis, s);
            }
        }
        s.push(curr);
    }

    public static void dfsForSCC(ArrayList<Edge>[] graph, int curr, boolean[] vis) {

        vis[curr] = true;
        System.out.print(curr + " ");
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.destination]) {
                dfsForSCC(graph, e.destination, vis);
            }
        }
    }

    public static void kosarajuAlgorithm(ArrayList<Edge>[] graph, int V) {
        // Step 1
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSort(graph, i, vis, s);
            }
        }
        // Step 2
        ArrayList<Edge>[] transpose = new ArrayList[V];
        for (int i = 0; i < graph.length; i++) {
            vis[i] = false;
            transpose[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j); // e.src -> e.dest
                transpose[e.destination].add(new Edge(e.destination, e.source)); // reverse edge
            }
        }
        // Step 3
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!vis[curr]) {
                System.out.print("SCC -> ");
                dfsForSCC(transpose, curr, vis);
                System.out.println();
            }
        }

    }

    // Bridge in graph using Tarjan's Algorithm
    public static void dfsForBridge(ArrayList<Edge>[] graph, int curr, int par, int dt[], int[] lowdt, boolean[] vis,
            int time) {
        vis[curr] = true;
        dt[curr] = lowdt[curr] = ++time;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neigh = e.destination;
            if (neigh == par) {
                continue;
            } else if (!vis[neigh]) {
                dfsForBridge(graph, neigh, curr, dt, lowdt, vis, time);
                lowdt[curr] = Math.min(lowdt[curr], lowdt[neigh]);
                if (dt[curr] < lowdt[neigh]) {
                    System.out.println("Bridge : " + curr + "----" + neigh);
                }
            } else {
                lowdt[curr] = Math.min(lowdt[curr], dt[neigh]);
            }
        }
    }

    public static void tarjanBridge(ArrayList<Edge>[] graph, int V) {
        int discoveryTime[] = new int[V];
        int lowestDiscoveryTime[] = new int[V];
        int time = 0;
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsForBridge(graph, i, -1, discoveryTime, lowestDiscoveryTime, vis, time);
            }
        }
    }

    // Articulation Point using Tarjan's Algorithm O(V + E)
    public static void dfsForAP(ArrayList<Edge>[] graph, int curr, int par, int dt[], int[] lowdt, boolean[] vis,
            boolean[] ap, int time) {
        vis[curr] = true;
        dt[curr] = lowdt[curr] = ++time;
        int children = 0;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            int neigh = e.destination;
            if (neigh == par) {
                continue;
            } else if (vis[neigh]) {
                lowdt[curr] = Math.min(lowdt[curr], dt[neigh]);
            } else {
                dfsForAP(graph, neigh, curr, dt, lowdt, vis, ap, time);
                lowdt[curr] = Math.min(lowdt[curr], lowdt[neigh]);
                if (par != -1 && dt[curr] <= lowdt[neigh]) {
                    ap[curr] = true;
                }
                children++;
            }
        }
        if (par == -1 && children > 1) {
            ap[curr] = true;
        }
    }

    public static void getAP(ArrayList<Edge>[] graph, int V) {
        int discoveryTime[] = new int[V];
        int lowestDiscoveryTime[] = new int[V];
        int time = 0;
        boolean[] vis = new boolean[V];
        boolean[] ap = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsForAP(graph, i, -1, discoveryTime, lowestDiscoveryTime, vis, ap, time);
            }
        }
        // print all AP's
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("AP : " + i);
            }
        }
    }

    public static void main(String[] args) {
        // int V = 7; // no of vertices
        // ArrayList<Edge>[] graph = new ArrayList[V]; // null -> empty arraylist
        // for (int i = 0; i < V; i++) { // initilize AL
        // graph[i] = new ArrayList<>();
        // }

        // // 0 - vertex
        // graph[0].add(new Edge(0, 1, 5));
        // // 1 - vertex
        // graph[1].add(new Edge(1, 0, 5));
        // graph[1].add(new Edge(1, 2, 1));
        // graph[1].add(new Edge(1, 3, 3));
        // // 2 - vertex
        // graph[2].add(new Edge(2, 1, 1));
        // graph[2].add(new Edge(2, 3, 1));
        // graph[2].add(new Edge(2, 4, 4));
        // // 3 - vertex
        // graph[3].add(new Edge(3, 1, 3));
        // graph[3].add(new Edge(3, 2, 1));
        // // 4 - vertex
        // graph[4].add(new Edge(4, 2, 2));
        // // 2's neighbour
        // for (int i = 0; i < graph[2].size(); i++) {
        // Edge e = graph[2].get(i);
        // System.out.println(e.destination);
        // }

        // createGraph(graph);
        // bfs(graph);
        // dfs(graph, 0, new boolean[V]);
        // System.out.println(hasPath(graph, 0, 5, new boolean[V])); //true
        // System.out.println(hasPath(graph, 0, 9, new boolean[V])); //false
        // System.out.println(detectCycle(graph));

        // Cheapest Flights
        // int n = 4;
        // int flights[][] = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600
        // }, { 2, 3, 200 } };
        // int src = 0;
        // int dest = 3;
        // int k = 1;
        // ArrayList<Edge>[] graph = new ArrayList[n];
        // System.out.println(cheapestFlights(n, flights, src, dest, k));

        // Connecting Cities
        // int cities[][] = { { 0, 1, 2, 3, 4 }, { 1, 0, 5, 0, 7 }, { 2, 5, 0, 6, 0 }, {
        // 3, 0, 6, 0, 0 },
        // { 4, 7, 0, 0, 0 } };
        // System.out.println(connectingCities(cities));

        // Disjoint set DS
        // init();
        // System.out.println(find(3)); //3
        // union(2, 4);
        // union(3, 6);
        // union(1, 4);
        // System.out.println(find(4)); //2
    }
}
