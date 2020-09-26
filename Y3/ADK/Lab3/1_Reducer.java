import java.io.IOException;
import java.util.ArrayList;

public class Reducer {

	public static void main(String[] args) throws IOException {
		new Reducer();
	}

	Kattio io; 

	private static final boolean DEBUG_TIME = false;
	private static final boolean DEBUG = false;

	private int nodes; 
	private int edges; 
	private int colors; 

	private ArrayList<Edge> graph = new ArrayList<Edge>();

	StringBuilder sb = new StringBuilder();

	public Reducer() {
		try {
			io = new Kattio(System.in, System.out);
			readColor();
			reduceColor();
			writeColor();
			io.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Läster in från kattio
	private void readColor() {

		nodes = io.getInt(); 
		edges = io.getInt(); 
		colors = io.getInt();
		if (colors > nodes){
			colors = nodes;
		}

		//här fångar vi in kanterna, dvs a representerar vänstra ledet och b högra i indatan.
		for (int i = 0; i < edges; ++i) { 
			int a = io.getInt(); 
			int b = io.getInt();

			//if (a == b) { // Felkoll, öglor
			//	continue;
			//}

			addEdge(a, b); //skapa kanter
		}
	}

	//Här sker reduktionen som tar linjär tid då vi måste gå igenom hela grafen och skapa nya kanter.
	private void reduceColor() {
		//Lägger till två extra färger och noder, hänvisar till teorin.
		nodes = nodes + 2; 
		colors = colors + 2; 

		//Kopplar näst-sista noden med alla andra noder
		for (int i = 1; i < (nodes - 1); ++i) {
			addEdge((nodes - 1), i);
			edges++; 
		}
		//nu är det en komplett graf
		addEdge((nodes - 1), nodes); 
		edges++;
	}

	//Här printat vi ut indatan som skickas till kattis som i sin tur löser problemet. 
	private void writeColor() {
		sb = printGraph(sb);
		//enligt uppgift är det ok att printa informationen om roller, scener och skådespelare på en rad.
		io.println(nodes + " " + edges + " " + colors); 

		String s = "";

		for (int i = 1;i<=colors;i++) {
			s = s + " " + i;
		}

		for (int i = 0;i<nodes;i++) {
			io.println(colors+s);
		}
		//case 2
		io.println(sb.toString()); 

		io.flush();
	}

	//printar ut grafen. 
	private StringBuilder printGraph(StringBuilder sb) {
		for (int i = 0; i < graph.size(); ++i) {
			sb.append("2 " + graph.get(i).getStart() + " "
					+ graph.get(i).getEnd());
			sb.append("\n"); 
		}
		return sb;
	}

	
	private Edge addEdge(int a, int b) {
		Edge e = new Edge(a, b); 
		if (e != null) { 
			graph.add(e); 
		}
		return e;
	}
}
