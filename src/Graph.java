import java.util.*;

public class Graph implements Iterable<Vertex>{
    private HashMap<String, Vertex> vertexMap;  // Key is the name, Value is the Vertex
    private int numOfVertex;

    private PriorityQueue<Node> minHeap;
    private HashMap<String, Integer> shortestPath; // Record name and distance to start

    public Graph() {
        this.vertexMap = new HashMap<String, Vertex>();
        this.numOfVertex = 0;

        this.minHeap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.getDistToStart() == o2.getDistToStart()) {
                    return 0;
                }
                return o1.getDistToStart() < o2.getDistToStart() ? -1 : 1;
            }
        });
        this.shortestPath = new HashMap<String, Integer>();
    }

    public void runDijkstra(String startName) {
        if(!vertexMap.containsKey(startName)) {
            System.out.println(startName + " is not in the graph!");
            return ;
        }
        minHeap.clear();
        shortestPath.clear();
        minHeap.offer(new Node(startName, 0));

        while(!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            // Prevent duplicate keys being expanded
            if(!shortestPath.containsKey(cur.getName())) {
                shortestPath.put(cur.getName(), cur.getDistToStart());
                List<String> neighborList = vertexMap.get(cur.getName()).getNeighborNames();
                for(String nei : neighborList) {
                    // Prevent nodes in result being generated again
                    if(!shortestPath.containsKey(nei)) {
                        minHeap.offer(new Node(nei, cur.getDistToStart() + vertexMap.get(cur.getName()).getCost(vertexMap.get(nei))));
                    }
                }
            }
        }

        System.out.println("The shortest path from " + startName + " to:");
        for(Map.Entry<String, Integer> entry : shortestPath.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }



    // Add Vertex to the map
    public boolean addVertex(String name) {
        Vertex newVertex = new Vertex(name);
        if(vertexMap.containsKey(name)) {
            return false;
        }

        vertexMap.put(name, newVertex);
        numOfVertex += 1;
        return true;
    }

    // Given 2 vertex names, add them to the map
    public boolean addEdge(String from, String to, int cost) {
        // New vertex if it's not in the map yet
        if(!vertexMap.containsKey(from)) {
            addVertex(from);
        }

        if(!vertexMap.containsKey(to)) {
            addVertex(to);
        }

        Vertex fromV = vertexMap.get(from);
        Vertex toV = vertexMap.get(to);

        fromV.addNeighbor(toV, cost);
        toV.addNeighbor(fromV, cost);
        return true;
    }

    public Vertex getVertex(String name) {
        if(!vertexMap.containsKey(name)) {
            return null;
        }
        return vertexMap.get(name);
    }

    // Return the list of Vertex names in this graph
    public List<String> getVertexNames() {
        List<String> vertexList = new ArrayList<String>();
        for(Map.Entry<String, Vertex> entry : vertexMap.entrySet()) {
            vertexList.add(entry.getKey());
        }
        return vertexList;
    }

    // Return the list of all Vertices in this graph
    public List<Vertex>  getVertexList() {
        List<Vertex> vertexList = new ArrayList<Vertex>();
        for(Map.Entry<String, Vertex> entry : vertexMap.entrySet()) {
            vertexList.add(entry.getValue());
        }
        return vertexList;
    }

    // Print all vertices and their neighbors
    public void printAllVerticesWithNeighbors() {
        List<Vertex> list = getVertexList();
        for(Vertex cur : list) {
            List<Vertex> neiList = cur.getConnections();
            System.out.println(cur.getName() + " connects to:");
            for(Vertex nei : neiList) {
                System.out.print(nei.getName() +"(" + cur.getCost(nei) + ")" + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public int getNumOfVertex() {
        return numOfVertex;
    }

    @Override
    public Iterator<Vertex> iterator() {
        return new VertexIterator();
    }

    public class VertexIterator implements Iterator<Vertex> {
        List<Vertex> listAllVertex = getVertexList();
        int index = 0;

        @Override
        public boolean hasNext() {
            if(index != listAllVertex.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Vertex next() {
            if(hasNext()) {
                Vertex v = listAllVertex.get(index);
                index++;
                return v;
            }
            return null;
        }
    }


}
