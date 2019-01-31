//import java.lang.reflect.Array;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Stack;
//import java.util.Set;
//import java.util.Collection;
import java.util.Collections;

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
	 * Implementation of the Dijkstra's Algorithm
	 * Finds the shortest path from the starting vertex to the destination vertex,
	 * prints out the results.
	 * @param beginIndex The index of the starting vertex
	 * @param endIndex The index of the destination vertex
	 */
	public void djikstra(int beginIndex, int endIndex) {

		/*
		Initialise Components
		*/
		Vertex start = vertices[beginIndex];
		Vertex finish = vertices[endIndex];

		HashSet<Vertex> allVisited = new HashSet<Vertex>();
		HashSet<Vertex> notVisited = new HashSet<Vertex>();

		HashMap<Vertex, Integer> distances = new HashMap<Vertex, Integer>();
		HashMap<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

		// add the starting vertex to the distances, necessary so that the loop first finds starting vertex to be the minimum
		distances.put(start, 0);

		// iniitialise all distance except for the starting vertex to MAX and set all predecessors to the starting vertex
		// then add them to the not visited set
		for (Vertex eachVertex : vertices) {
			if (eachVertex != start) {
				distances.put(eachVertex, Integer.MAX_VALUE);
				predecessors.put(eachVertex, start);
			}
			notVisited.add(eachVertex);
		}

		// while there are vertices that haven't been visited
		while(!notVisited.isEmpty()) {
			Vertex minVertex = getMinimumVertex(notVisited, distances);		// get the vertex with the smallest distance
			allVisited.add(minVertex);		// add this vertex to the visited set
			notVisited.remove(minVertex);	// remove this vertex from the not visited set
			relaxation(minVertex, notVisited, distances, predecessors);		// perform relaxation
		}

		// print out the results
		if(distances.get(finish) != Integer.MAX_VALUE) {
			shortestPath(start, finish, predecessors, distances);
		} else {
			System.out.println("Path does not exist.");
		}

	}

	/**
	 * Checks the distances of all unvisited vertices, and returns the vertex with the least distance
	 * @param nV Set of the non-visited vertices
	 * @param d Map of the distances
	 * @return The vertex with the minimum distance
	 */
	public Vertex getMinimumVertex(HashSet<Vertex> nV, HashMap<Vertex, Integer> d) {
		Vertex min = null;
		//int minDistance = Integer.MAX_VALUE;

		for (Vertex eachVertex : nV) {
			if (min == null) {
				min = eachVertex;
			}
			else {
				if (findDistance(eachVertex, d) <= findDistance(min, d)) {
					//minDistance = d.get(eachVertex);
					min = eachVertex;
				}
			}
		}

		return min;
	}

	/**
	 * Extracts the distance of a specific vertex from the map of distances
	 * @param v Vertex for which the distance needs to be extracted
	 * @param d Map of the distances
	 * @return The distance of the vertex v
	 */
	public int findDistance(Vertex v, HashMap<Vertex, Integer> d) {
		int distance = d.get(v);
		return distance;
	}

	/**
	 * Updates the distances and the predecessors of all the vertices
	 * that are not visited and are adjacent to the vertex recently visited
	 * @param v The Vertex recently visited
	 * @param nV The set of non-visited vertices
	 * @param d The Map of distances
	 * @param p The Map of predecessors
	 */
	public void relaxation(Vertex v, HashSet<Vertex> nV, HashMap<Vertex, Integer> d, HashMap<Vertex, Vertex> p) {
		for (Vertex eachVertex : nV) {				// for each vertex that isnt visited
			if (isAdjacent(v, eachVertex)) {		// if this vertex is adjacent to vertex v
				if (updateMin(v, eachVertex, d)) {	// update its distance in the map
					updatePred(eachVertex, v, p);	// update its predecessor if the distance was updated (smaller distance found)
				}
			}
		}
	}

	/**
	 * Finds if a vertex w is adjacent to a vertex v
	 * @param v The vertex for which we check its adjacency list
	 * @param w The vertex to be found in the adhacency list
	 * @return true if vertex w found in adjacency list, false otherwise
	 */
	public boolean isAdjacent(Vertex v, Vertex w) {
		for (AdjListNode eachVertexNode : v.getAdjList()) {
			if (eachVertexNode.getVertexIndex() == w.getIndex()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the distance of the vertex w according to its adjacency to vertex v
	 * @param v The vertex with which the vertex w is adjacent
	 * @param w The vertex that needs to have its distance updated
	 * @param d The map of distances
	 * @return true if the distance was changed for vertex w, false otherwise
	 */
	public boolean updateMin(Vertex v, Vertex w, HashMap<Vertex, Integer> d) {
		int checkIndex = w.getIndex();		// Index of the vertex w
		int checkDistanceVtoW = Integer.MAX_VALUE;		// intialise (will be used to store the edge weight between v and w)

		/*
		find the edge weight between v and w
		*/
		for (AdjListNode eachNode : v.getAdjList()) {
			if(eachNode.getVertexIndex() == checkIndex) {
				checkDistanceVtoW = eachNode.getEdgeWeight();
			}
		}
		
		/*
		if the sum of the edge weight (checkDistanceVtoW) and the current distance of vertex v
		is less than or equal to the current distance of vertex w
		update the distance of vertex w and return true

		Otherwise, don't update and return false
		*/
		if (checkDistanceVtoW + d.get(v) <= d.get(w)) {
			d.replace(w, checkDistanceVtoW + d.get(v) );
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Update the predecessor of a vertex
	 * @param v The vertex
	 * @param w The new predecessor
	 * @param p The predecessor map
	 */
	public void updatePred(Vertex v, Vertex w, HashMap<Vertex, Vertex> p) {
		p.replace(v, w);
	}

	/**
	 * Prints out the result of the algorithm
	 * @param s The starting vertex
	 * @param f The desitnation vertex
	 * @param p The predecessor map
	 * @param d The distances map
	 */
	public void shortestPath(Vertex s, Vertex f, HashMap<Vertex, Vertex> p, HashMap<Vertex, Integer> d) {
		
		System.out.println("Shortest distance from vertex " + s.getIndex() + " to " + f.getIndex() + " is " + d.get(f));

		// LINKEDLIST IMPLEMENTATION, BETTER AVERAGE RUNTIME COMPARED TO STACK
		if (p.get(f) == null) {
			System.out.println("Path does not exist.");
			return;
		}
		
		LinkedList<Vertex> flow = new LinkedList<Vertex>();
		/*
		Add predecessors in sequence to the flow
		*/
		while(f != null){
			flow.add(f);
			f = p.get(f);
		}

		Collections.reverse(flow);		//reverse the linkedlist
		
		for (Vertex eachVertex : flow) {
			System.out.print(eachVertex.getIndex() + " ");
		}
		
		// STACK IMPLEMENTATION, DOESN'T IMPROVE RUNTIME WITH ANY SIGNIFICANCE
		/*
		Stack<Vertex> flow = new Stack<Vertex>();

		while(f != null){
			flow.push(f);
			f = p.get(f);
		}

		int loopIndex = flow.size();
		while(loopIndex > 0) {
			System.out.print(flow.pop().getIndex() + " ");
			loopIndex--;
		}
		*/
	}

}
