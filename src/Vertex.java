import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Vertex {
    private String name;
    private List<Vertex> neighbors;
    private HashMap<Vertex, Integer> edgeMap;

    public Vertex(String name) {
        this.name = name;
        this.neighbors = new ArrayList<Vertex>();
        this.edgeMap = new HashMap<Vertex, Integer>();
    }


    public boolean addNeighbor(Vertex nei, int cost) {
        if(neighbors.contains(nei)) {
            return false;
        }
        neighbors.add(nei);
        edgeMap.put(nei, cost);
        return true;
    }

    public String getName() {
        return name;
    }

    // Return the cost to a neighbor
    public int getCost(Vertex nei) {
        if(!edgeMap.containsKey(nei)) {
            return -1;
        }
        return edgeMap.get(nei);
    }

    public List<Vertex> getConnections() {
        return neighbors;
    }

    public List<String> getNeighborNames() {
        List<String> list = new ArrayList<String>();
        for(Vertex v : neighbors) {
            list.add(v.getName());
        }
        return list;
    }

}
