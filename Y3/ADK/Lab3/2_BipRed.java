import java.util.ArrayList;
import java.util.Arrays;

/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen ADK.
 *
 * Använder Kattio.java för in- och utläsning. Se
 * http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;
    ArrayList<ArrayList<Integer>> graph1, graph2;
    int x, y, e;
    int s, t;

    void readBipartiteGraph() {
        // Läs antal hörn och kanter
        x = io.getInt();
        y = io.getInt();
        e = io.getInt();

        graph1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp;

        // Läs in kanterna
        for (int i = 0; i < e; i++) {
            int a = io.getInt();
            int b = io.getInt();
            temp = new ArrayList<>();
            temp.add(a);
            temp.add(b);
            graph1.add(temp);
        }

    }

    void writeFlowGraph() {
        s = x + y + 1;
        t = x + y + 2;

        int c = 1;
        int counter = x;

        // Skriv ut antal hörn och kanter samt källa och sänka
        io.println(x + y + 2);
        io.println(s + " " + t);
        io.println(e + x + y);

        for (int i = 1; i <= x; i++) {
            io.println(s + " " + i + " " + c);
        }

        for (int i = 0; i < e; i++) {
            if (graph1.get(i).get(0) <= x) {
                io.println(graph1.get(i).get(0) + " " + graph1.get(i).get(1) + " " + c);
            }
        }

        for (int i = x + 1; i <= x + y; i++) {
            io.println(i + " " + t + " " + 1);
        }

        // Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
        io.flush();

        // Debugutskrift
        // System.err.println("Skickade iväg flödesgrafen");

    }

    void readMaxFlowSolution() {
        // Läs in antal hörn, kanter, källa, sänka, och totalt flöde
        // (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
        // skickade iväg)
        int v = io.getInt();
        int s = io.getInt();
        int t = io.getInt();
        int totflow = io.getInt();
        int ee = io.getInt();

        graph2 = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp;

        int xxx = 0;
        io.println(x + " " + y);

        for (int i = 0; i < ee; i++) {
            // Flöde f från a till b
            int a = io.getInt();
            int b = io.getInt();
            int f = io.getInt();

            if ((a != s) && (b != t)) {
                temp = new ArrayList<>();
                temp.add(a);
                temp.add(b);
                graph2.add(temp);
                xxx++;

            }
        }

        io.println(xxx);

        for (int i = 0; i < graph2.size(); i++) {
            io.println(graph2.get(i).get(0) + " " + graph2.get(i).get(1));
        }

        io.flush();
    }

    BipRed() {
        io = new Kattio(System.in, System.out);

        readBipartiteGraph();

        writeFlowGraph();

        readMaxFlowSolution();

        io.close();
    }

    public static void main(String args[]) {
        new BipRed();
    }
}
