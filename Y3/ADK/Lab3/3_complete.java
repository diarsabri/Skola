import java.util.*;

public class complete {
    private Kattio io;
    private ArrayList<ArrayList<Integer>> bgraph;
    private ArrayList<ArrayList<Edge>> fgraph1,fgraph2;
    private ArrayList<ArrayList<Integer>> res;


    private int x, y, e;
    private int v, s, t, ee;
    private Edge[] path;
    private boolean[] visited;
    private int flow;

    public complete() {
        io = new Kattio(System.in,System.out);
        readBipartiteGraph();
        writeFlowGraph();
        transformFGraph();
        findMaxFlow();
        io.close();
    }
    public void readBipartiteGraph() {
        x = io.getInt();
        y = io.getInt();
        e = io.getInt();

        bgraph = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp;

        for (int i = 0; i < e; i++) {
            int a = io.getInt();
            int b = io.getInt();
            temp = new ArrayList<>();
            temp.add(a);
            temp.add(b);
            bgraph.add(temp);
        }

    }



    public void writeFlowGraph() {
        v = x + y + 2;
        s = x + y + 1;
        t = x + y + 2;
        ee = e + x + y;

        fgraph1 = new ArrayList<ArrayList<Edge>>(v + 1);
        for (int i = 0; i < v + 1; i++) {
            fgraph1.add(new ArrayList<Edge>());
        }

        for (int i = 1; i <= x; i++) {
            int from = s;
            int to = i;
            Edge edge = new Edge(from, to, 1);
            fgraph1.get(from).add(edge);
        }

        for (int i = 0; i < e; i++) {
            int from = bgraph.get(i).get(0);
            int to = bgraph.get(i).get(1);
            Edge edge = new Edge(from, to, 1);
            fgraph1.get(from).add(edge);
        }

        for (int i = x + 1; i <= x + y; i++) {
            int from = i;
            int to = t;
            Edge edge = new Edge(from, to, 1);
            fgraph1.get(from).add(edge);
        }
    }

    public void transformFGraph() {

        fgraph2 = new ArrayList<ArrayList<Edge>>(v + 1);
        for (int i = 0; i < v + 1; i++) {
            fgraph2.add(i, new ArrayList<Edge>());
        }

        for (ArrayList<Edge> list : fgraph1) {
            for (Edge edge : list) {
                int from = edge.from();
                int to   = edge.to();

                Edge newEdge = new Edge(from, to, 1);

                fgraph2.get(from).add(newEdge);
                fgraph2.get(to).add(newEdge);
            }
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
        printGraph(fgraph2, flow);
    }

    public boolean hasPath() {
        path = new Edge[v + 1];
        visited = new boolean[v + 1];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.addLast(s);

        while (queue.size() != 0 && !visited[t]) {
            int curr = queue.pollFirst();
            for (Edge currEdge : fgraph2.get(curr)) {

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

        io.println(x + " " + y);
        int counter = 0;

        Set<Edge> set = new LinkedHashSet<>();
        for (ArrayList<Edge> edges : residual) {
            for (Edge edge : edges) {
                if (edge.flow() > 0) {
                    set.add(edge);
                }
            }
        }

        res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp;

        
        for (Edge edge : set) {
            int a = edge.from();
            int b = edge.to();
            
            if ((a != s) && (b != t)) {
                temp = new ArrayList<>();
                temp.add(a);
                temp.add(b);
                res.add(temp);
                counter++;
            }



        }

        io.println(counter);

        for (int i = 0; i < res.size(); i++) {
            io.println(res.get(i).get(0) + " " + res.get(i).get(1));
        }
        io.flush();
    }

    public static void main(String[] args) {
        new complete();
    }
}