package kth.csc.inda;

import java.io.File;
import java.util.*;

/**
 * A graph with a fixed number of vertices implemented using adjacency maps.
 * Space complexity is &Theta;(n + m) where n is the number of vertices and m
 * the number of edges.
 * 
 * @author [Name]
 * @version [Date]
 */
public class HashGraph implements Graph {
	/**
	 * The map edges[v] contains the key-value pair (w, c) if there is an edge
	 * from v to w; c is the cost assigned to this edge. The maps may be null
	 * and are allocated only when needed.
	 */
	private final Map<Integer, Integer>[] edges;
	private final static int INITIAL_MAP_SIZE = 4;
	private final static int EMPTY = -2;

	/** Number of edges in the graph. */
	private int numEdges;

	/**
	 * Constructs a HashGraph with n vertices and no edges. Time complexity:
	 * O(n)
	 * 
	 * @throws IllegalArgumentException
	 *             if n < 0
	 */
	public HashGraph(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n = " + n);

		// The array will contain only Map<Integer, Integer> instances created
		// in addEdge(). This is sufficient to ensure type safety.
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] a = new HashMap[n];
		edges = a;
	}

	/**
	 * Add an edge without checking parameters.
	 */
	private void addEdge(int from, int to, int cost) {
		if (edges[from] == null)
			edges[from] = new HashMap<Integer, Integer>(INITIAL_MAP_SIZE);
		if (edges[from].put(to, cost) == null)
			numEdges++;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public int numVertices() {
		return edges.length;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public int numEdges() {
		return numEdges;
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public int degree(int v) throws IllegalArgumentException
    {
        checkVertexParameter(v);
	    if (edges[v] != null) {
	        return edges[v].size();
        }
		return 0;
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public VertexIterator neighbors(int v)
    {
		checkVertexParameter(v);
		return new NeighborIterator(v);
	}

	private class NeighborIterator implements VertexIterator
    {

        Map<Integer,Integer> row;
        Iterator<Integer> entries;

        NeighborIterator(int v)
        {
            row = edges[v];
            if (edges[v] == null) {
                entries = null;
            } else {
                entries = row.keySet().iterator();
            }
        }

        @Override
        public boolean hasNext()
        {
            if (entries != null) {
                return entries.hasNext();
            } else {
                return false;
            }
        }

        @Override
        public int next() throws NoSuchElementException
        {
            if (hasNext()) {
                return entries.next();
            }
            else {
                throw new NoSuchElementException("No more elements");
            }
        }
    }

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public boolean hasEdge(int v, int w) {
        checkVertexParameters(v, w);
        if (edges[v] != null) {
            if (edges[v].containsKey(w)) {
                return true;
            }
        }
		return false;
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public int cost(int v, int w) throws IllegalArgumentException
    {
        checkVertexParameters(v,w);
		if (edges[v].containsKey(w)) {
		    int x = edges[v].get(w);
		    return x;
        }
		return NO_COST;
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void add(int from, int to)
    {
        checkVertexParameters(from,to);
        addEdge(from,to,NO_COST);
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void add(int from, int to, int c)
    {
        checkVertexParameters(from,to);
        if (c<0) {
            throw new IllegalArgumentException("Assign a cost");
        } else {
            addEdge(from,to,c);
        }
    }

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void addBi(int v, int w)
    {
        checkVertexParameters(v,w);
        checkVertexParameters(w,v);

        if (v == w) {
            addEdge(v,w,NO_COST);
        } else {
            addEdge(v,w,NO_COST);
            addEdge(w,v,NO_COST);
        }

    }

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void addBi(int v, int w, int c)
    {
        checkVertexParameters(v,w);
        checkVertexParameters(w,v);

        if (c <0) {
            throw new IllegalArgumentException("Assign a cost");
        }
        if (v == w) {
            addEdge(v,w,c);
        } else {
            addEdge(v,w,c);
            addEdge(w,v,c);
        }
    }

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void remove(int from, int to)
    {
        checkVertexParameters(from,to);
		if (hasEdge(from,to)) {
		    edges[from].remove(to);
		    numEdges--;
        }
	}

	/**
	 * {@inheritDoc Graph}
	 */
	@Override
	public void removeBi(int v, int w)
    {
        checkVertexParameters(v,w);
        if (hasEdge(v,w) || hasEdge(w,v)) {
            edges[v].remove(w);
            edges[w].remove(v);
            numEdges = numEdges -2;
        }
	}

	/**
	 * Returns a string representation of this graph.
	 * 
	 * @return a String representation of this graph
	 */
	@Override
	public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i<numVertices();i++) {
            NeighborIterator iterator = new NeighborIterator(i);
            while (iterator.hasNext()) {
                int x = iterator.next();
                if (cost(i,x) == NO_COST) {
                    sb.append("(" + i + "," + x + "), "); //taget direkt från matrixgraph
                } else {
                    sb.append("(" + i + "," + x + "," + cost(i,x) + "), "); //taget direkt från matrixgraph,tillagt cost
                }
            }
        }
        if (numEdges() > 0)
            sb.setLength(sb.length() -2);
        sb.append("}");
        return sb.toString();


        /*
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0;i<numVertices();i++) {
            Map<Integer, Integer> row = edges[i];
            for (int j = 0; j< numVertices();j++) {
                int x = row.get(j).intValue();

                switch (x) {
                    case EMPTY:
                        break;
                    case NO_COST:
                        sb.append("(" + i + "," + j + "), ");
                        break;
                    default:
                        sb.append("(" + i + "," + j + "," + x + "), ");
                }
            }
        }
        if (numEdges() > 0)
            sb.setLength(sb.length() - 2);
            sb.append("}");

        return sb.toString();
        */
	}


	//metoderna här under tagna direkt från matrixgraph

    private void checkVertexParameter(int v) {
        if (v < 0 || v >= numVertices())
            throw new IllegalArgumentException("Out of range: v = " + v + ".");
    }

    private void checkVertexParameters(int v, int w) {
        if (v < 0 || v >= numVertices() || w < 0 || w >= numVertices())
            throw new IllegalArgumentException("Out of range: v = " + v
                    + ", w = " + w + ".");
    }
    // källor:
    // http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
    // http://www.sergiy.ca/how-to-iterate-over-a-map-in-java
}