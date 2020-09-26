public class Edge {
  int from;
  int to;
  int capacity;
  int flow;

  public Edge(int from, int to, int capacity) {
    this.from = from;
    this.to = to;
    this.capacity = capacity;
    this.flow = 0;
  }

  public int from() {
    return from;
  }

  public int to() {
    return to;
  }

  public int flow() {
    return flow;
  }

  public int oppositeNode(int node) {
    if (node == to) {
      return from;
    } else {
      return to;
    }
  }

  public void addResFlow(int node, int bottle) {
    if (node == from) {
      flow -= bottle;
    } else {
      flow += bottle;
    }
  }

  public int resCap(int node) {
    if (node == from) {
      return flow;
    } else {
      return capacity - flow;
    }
  }
}