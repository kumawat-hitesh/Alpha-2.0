import java.util.*;
import java.util.LinkedList;

public class Graphs {
    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int s, int d, int w) {
            this.source = s;
            this.destination = d;
            this.weight = w;
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
    }
}
