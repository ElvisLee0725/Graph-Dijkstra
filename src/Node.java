public class Node {
    private String name;
    private int distToStart;

    public Node(String name, int distToStart) {
        this.name = name;
        this.distToStart = distToStart;
    }

    public String getName() {
        return name;
    }

    public int getDistToStart() {
        return distToStart;
    }
}
