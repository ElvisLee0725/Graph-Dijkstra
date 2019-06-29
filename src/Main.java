public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addEdge("A", "B", 6);
        g.addEdge("C", "D", 1);
        g.addEdge("A", "C", 2);
        g.addEdge("B", "D", 2);

        System.out.println("Total Vertices: " + g.getNumOfVertex());
        g.printAllVerticesWithNeighbors();

        g.runDijkstra("A");
        g.runDijkstra("B");
        g.runDijkstra("C");
        g.runDijkstra("D");
    }
}
