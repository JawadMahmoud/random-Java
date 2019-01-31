import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Stack;


public class BTPath {

    private LinkedList<Vertex> pathnodes;       //List of vertices in the path
    private int pathDistance = 0;       //distance covered by the path

    /**
     * Constructor for initialising BTPath with an initial vector
     */
    public BTPath(Vertex start) {
        pathnodes = new LinkedList<Vertex>();
        pathnodes.add(start);
    }

    /**
     * Constructor for initialising BTPath without any initial vectors and with MAX distance
     */
    public BTPath() {
        pathnodes = null;
        pathDistance = Integer.MAX_VALUE;
    }

    public int getPathDistance() {
        return pathDistance;
    }

    public LinkedList<Vertex> getPathList() {
        return pathnodes;
    }

    /**
     * Adds a vertex to the path list along its distance from source,
     * and sets the vertex to visited
     */
    public void addToPath(Vertex v, int d) {
        pathnodes.add(v);
        pathDistance += d;
        v.visited = true;
    }

    /**
     * Removes a vertex from the path list and its distance from source,
     * and sets the vertex to unvisited
     */
    public void removeFromPath(Vertex v, int d) {
        pathnodes.remove(v);
        pathDistance -= d;
        v.visited = false;
    }

    /**
     * Gets the most recent vertex added to the path
     */
    public Vertex lastVertexAdded() {
        return pathnodes.getLast();
    }

    public int getSize() {
        return pathnodes.size();
    }

    /**
     * Change the path list and distance
     */
    public void updatePath(LinkedList<Vertex> p, int d) {
        pathnodes = p;
        pathDistance = d;
    }

}

