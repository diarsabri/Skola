package kth.csc.inda;

import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Diar on 2017-02-23.
 */
public class RandomGraphGenerator
{
    private int nodes;                                  //antal noder
    private static long time;                           //tidstagare
    private Graph graph;                                //grafen som ska genereras
    private Random random;
    private static int largestC,counter,size;           //ints för att hålla koll på antal, och storlek.

    RandomGraphGenerator(int nodes,Graph graph)
    {
        this.nodes = nodes;
        this.graph = graph;
        random = new Random();
        randomGraph();
        time = 0;
    }

    public void randomGraph()                           //skapar graf med slumpat antal kanter till n hörn
    {
        for (int i = 0;i<nodes;i++) {
            int v = random.nextInt(nodes);
            int w = random.nextInt(nodes);
            graph.addBi(v,w);
        }
    }

    /**
     * The print method, taken by graphalgorithms class with some slight modifications to keep track of largest component, and the time it takes for the method to traverse the graph.
     * @param g the given graph to print
     */
    public  void printComponents(Graph g)
    {

        long start = System.nanoTime();
        VertexAction printVertex = (g1, v) -> System.out.print(v + " ");
        int n = g.numVertices();
        largestC = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) {
            size = 0;
            if (!visited[v]) {
                dfs(g, v, visited, printVertex);
            }
        }
        long end;
        end = System.nanoTime();
        time = end - start;
    }

    /**
     * The actual dfs method, taken by graphalgorithms class with some slight modifications to keep track of largest component.
     * @param g
     * @param v
     * @param visited
     * @param action
     */
    public static void dfs(Graph g, int v, boolean[] visited, VertexAction action)
    {
        if (visited[v])
            return;
        visited[v] = true;
        size++;
        if (size > largestC) { largestC = size; }
        action.act(g, v);
        for (VertexIterator it = g.neighbors(v); it.hasNext();) {
            dfs(g, it.next(), visited, action);
            counter++;
        }
    }

    public static void main(String[] args)
    {
        //tests the two different graphs on a size of 10, prints the graph and prints the runtime & biggest component.
        HashGraph h = new HashGraph(10);
        MatrixGraph m = new MatrixGraph(10);
        RandomGraphGenerator g1 = new RandomGraphGenerator(10, h);
        RandomGraphGenerator g2 = new RandomGraphGenerator(10, m);

        counter = 0;
        System.out.println("HashGraph " +h +"\nHas components: ");
        g1.printComponents(h);
        System.out.println("\nRuntime for this graph is: " + g1.time +" nanoseconds");
        System.out.println("Number of components in the graph: " + g1.counter );
        System.out.println("Biggest component (\"map\"):  " + g1.largestC );
        counter = 0;
        System.out.println("\n");

        System.out.println("MatrixGraph " +m +"\nHas components: ");
        g2.printComponents(m);
        System.out.println("\nRuntime for this graph is: " + g2.time +" nanoseconds");
        System.out.println("Number of components in the graph: " + g2.counter );
        System.out.println("Biggest component (\"map\"):  " + g2.largestC );
    }

    //både dfg och printcomponents är tagna från graphlgorithms klassen, med lite modifikationer.

}
