import java.util.*;

public class flowSolve {
    private Kattio io;
    private int v, s, t, e;
    private ArrayList<ArrayList<Edge>> graph;
    private Edge[] path;
    private boolean[] visited;
    private int flow;

    public flowSolve() {
        io = new Kattio(System.in, System.out);
        readGraph();
        findMaxFlow();
        io.close();
    }

    public void readGraph() {
        v = io.getInt();
        s = io.getInt();
        t = io.getInt();
        e = io.getInt();

        graph = new ArrayList<ArrayList<Edge>>(v + 1);
        for (int i = 0; i < v + 1; i++) {
            graph.add(i, new ArrayList<Edge>());
        }

        for (int i = 0; i < e; i++) {
            int a, b, c;
            a = io.getInt();
            b = io.getInt();
            c = io.getInt();

            Edge edge = new Edge(a, b, c);
            graph.get(a).add(edge);
            graph.get(b).add(edge);
        }
    }

    public void findMaxFlow() {

        while (hasPath() == true) {
            int bottle = Integer.MAX_VALUE;

            for (int i = t; i != s; i = path[i].oppositeNode(i)) {
                bottle = Math.min(bottle, path[i].resCap(i));
            }

            for (int i = t; i != s; i = path[i].oppositeNode(i)) {
                path[i].addResFlow(i, bottle);
            }
            flow += bottle;
        }
        printGraph(graph, flow);
    }

    public boolean hasPath() {
        path = new Edge[v + 1];
        visited = new boolean[v + 1];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.addLast(s);

        while (queue.size() != 0 && !visited[t]) {
            int curr = queue.pollFirst();
            for (Edge currEdge : graph.get(curr)) {

                int next = currEdge.oppositeNode(curr);

                if (currEdge.resCap(next) > 0 && !visited[next]) {
                    path[next] = currEdge;
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return visited[t];
    }

    private void printGraph(ArrayList<ArrayList<Edge>> residual, int maxFlow) {

        io.println(v);
        io.println(s + " " + t + " " + maxFlow);

        Set<Edge> set = new LinkedHashSet<>();
        for (ArrayList<Edge> edges : residual) {
            for (Edge edge : edges) {
                if (edge.flow() > 0) {
                    set.add(edge);
                }
            }
        }
        io.println(set.size());
        for (Edge edge : set) {
            io.println(edge.from() + " " + edge.to() + " " + edge.flow());
        }
        io.flush();
    }

    public static void main(String[] args) {
        new flowSolve();
    }
}