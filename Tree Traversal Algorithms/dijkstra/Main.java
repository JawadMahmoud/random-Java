import java.io.*;
import java.util.*;

/**
 program to find shortest path using Dijkstra's algorithm
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // input file name
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
		Scanner lineScanner = new Scanner(line);

		int numVertices = lineScanner.nextInt();

		Graph inputGraph = new Graph(numVertices);		//Initialise Graph using number of vertices

		/*
		Create all Vertex objects, and also their Adjacency Lists
		*/
		for (int n = 0; n<numVertices; n++) {
			//System.out.println(lineScanner.next());
			line = in.nextLine();
			String[] singleLine = line.split(" ");
			//System.out.println(Arrays.toString(singleLine));
			//Vertex newVertex = new Vertex(n);

			inputGraph.setVertex(n);
			//System.out.println("Added to Graph: (Index)" + n);
			for (int m = 0; m<singleLine.length; m++) {
				if (!singleLine[m].equals("0")) {
					inputGraph.getVertex(n).addToAdjList(m, Integer.parseInt(String.valueOf(singleLine[m])));
				}
			}
		}

		/*
		Get the start and end vertices from the last line of the input file
		*/
		line = in.nextLine();
		String[] direction = line.split(" ");
		int begin = Integer.parseInt(String.valueOf(direction[0]));
		int ending = Integer.parseInt(String.valueOf(direction[1]));


		reader.close();
		in.close();
		lineScanner.close();
		
		// do the work here

		/*
		Perform the dijkstra's algorithm
		*/
		inputGraph.djikstra(begin, ending);


		
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
