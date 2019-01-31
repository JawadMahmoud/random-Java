
/**
 class to represent an entry in the adjacency list of a vertex
 in a graph
 */
public class AdjListNode {

	private int vertexIndex;	//Index of the vertex
	private int edgeWeight;		//Weight of the edge between the two vetices
	// could be other fields, for example representing
	// properties of the edge - weight, capacity, ...
	
    /* creates a new instance */
	public AdjListNode(int n, int w){
		vertexIndex = n;
		edgeWeight = w;
	}
	
	public int getEdgeWeight(){
		return edgeWeight;
	}

	public void setVertexWeight(int w){
		edgeWeight = w;
	}

	public int getVertexIndex(){
		return vertexIndex;
	}
	
	public void setVertexIndex(int n){
		vertexIndex = n;
	}
	
}
