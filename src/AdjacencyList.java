import java.util.ArrayList;

public class AdjacencyList<T> {
	private ArrayList<T> vertices; // gives indexes that vertices are located at
	private ArrayList<LinkedList<T>> list; // holds the list to edges
	
	AdjacencyList(){
		vertices = new ArrayList<T>();
		list = new ArrayList<LinkedList<T>>();
	}
	
	/**
	 * Add given vertex into the adjacency list
	 * Written for directional graphs - if graph is non-directional, method must be called twice
	 * @param vertex - vertex to be added
	 */
	public void addVertex(T vertex) {
		vertices.add(vertex);
		list.add(new LinkedList<T>()); // duplicate exception will be thrown if duplicate is entered
	}
	
	/**
	 * Removes the given vertex from adjacency list
	 * @param vertex - vertex to be removed
	 */
	public void removeVetex(T vertex) {
		int index = vertices.indexOf(vertex);
		// vertex does not exist
		if( index == -1 ) {
			throw new IllegalArgumentException("vertex not found");
		}
		vertices.remove(index);
		list.remove(index);
		// removes every edge to vertex within list
		for(int x = 0; x < list.size(); x++) {
			list.get(x).remove(vertex);
		}
		
	}
	
	/**
	 * Adds edge between source and destination
	 * Written for directional graphs - if graph is non-directional, method must be called twice
	 * @param source - beginning of edge
	 * @param destination - end of edge
	 */
	public void addEdge(T source, T destination) {
		int index = vertices.indexOf(source); // gets index of vertex in list
		// vertex does not exist
		if( index == -1 ) {
			throw new  IllegalArgumentException("Source not found");
		}
		// adds destination to list
		list.get(index).add(destination);
	}
	
	/**
	 * Removes edge between source and destination
	 * Written for directional graphs, if non-directional, method should be called twice
	 * @param source
	 * @param destination
	 */
	public void removeEdge(T source, T destination) {
		int index = vertices.indexOf(source);
		// source does not exist in vertices
		if( index == -1 ) {
			throw new IllegalArgumentException("Source not found");
		}
		// removes edge from given vertices
		list.get(index).remove(destination);
	}
}
