//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Set;
//import java.util.Collection;
//import java.util.Collections;

//import com.sun.javafx.collections.MappingChange.Map;

/**
 class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/** 
	 creates a new instance of Graph with n vertices
	*/
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexIndex();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 carry out a breadth first search/traversal of the graph
	 psedocode version
	 */
	/**
	public void bfs() {
		assign each vertex to be unvisited;
  		set up an initially empty queue of  
         		visited but unprocessed vertices;
  		for each vertex v {
    		if v is not visited {
      			assign v to be visited;
      			assign the predecessor of v;
      			add v to the queue;
      			while the queue is not empty {
        			remove vertex u from the queue;
        			for each vertex w in the adjacency list of u {
        				if w is unvisited {
           					assign w to be visited;
           					assign the predecessor of w;
           					add w to the queue;
         				} 
       				}
       			}
    		}
    	}
	}
	*/

	public void bfs() {
		for (Vertex v : vertices) {
			v.setVisited(false);
		}
		Queue<Vertex> allVisited = new LinkedList<>();
		for (Vertex v : vertices) {
			if (!v.getVisited()) {
				v.setVisited(true);
				v.setPredecessor(v.getIndex());
				allVisited.add(v);
				while (!allVisited.isEmpty()) {
					Vertex u = allVisited.remove();
					for (AdjListNode z : u.getAdjList()) {
						Vertex current = vertices[z.getVertexIndex()];
						if (!current.visited) {
							current.setVisited(true);
							current.setPredecessor(u.getIndex());
							allVisited.add(current);
						}
					}
				}
			}
		}
	}

	/**
	 * Implements backtrack search for all the vertices in a graph to reach the destination vertex
	 * @param cP The current path used for adding vertices and comparing to the previous best path
	 * @param bP The best path, cloned from the current path if the current path is found to have a smaller distance
	 * @param dV The destination vertex
	 */
	public void backtrack(BTPath cP, BTPath bP, Vertex dV) {
		// for each vertex in that is adjacent to the last vertex added to the current path
		for (AdjListNode eachNode : cP.lastVertexAdded().getAdjList()) {
			Vertex eachUVertex = vertices[eachNode.getVertexIndex()];
			// if the vertex is not visited, add it to the path
			if (!eachUVertex.visited) {
				int eachUDistance = eachNode.getEdgeWeight();		//get the edge weight for the vertex
				cP.addToPath(eachUVertex, eachUDistance);

				// if the distance for the current path is found to be less than the distance for best path
				if (cP.getPathDistance() < bP.getPathDistance()) {
					// AND if the vertex recently added is infact the destination vertex
					if(eachUVertex == dV) {
						// update the best path
						int uDistance = cP.getPathDistance();
						@SuppressWarnings("unchecked")
						LinkedList<Vertex> uPathList = (LinkedList<Vertex>) cP.getPathList().clone();
						bP.updatePath(uPathList, uDistance);
					} else {
						// OTHERWISE call the function again for the next iteration
						backtrack(cP, bP, dV);
					}
				}

				cP.removeFromPath(eachUVertex, eachUDistance);
			}
		}
	}

}
