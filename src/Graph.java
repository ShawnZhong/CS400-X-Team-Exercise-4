import java.util.HashMap;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 *
 * @param <E> type of a vertex
 * @author sapan (sapan@cs.wisc.edu)
 */


public class Graph<E> implements GraphADT<E> {
    /**
     * Instance variables and constructors
     */
    private HashMap<E, HashMap<E, Integer>> edges;

    Graph() {
        edges = new HashMap<>();
    }

    /**
     * Add new vertex to the graph
     * <p>
     * Valid argument conditions:
     * 1. vertex should be non-null
     * 2. vertex should not already exist in the graph
     *
     * @param vertex - the vertex to be added
     * @return vertex if vertex added, else return null if vertex can not be added
     * (also if valid conditions are violated)
     */
    @Override
    public E addVertex(E vertex) {
        if (vertex == null || edges.containsKey(vertex))  // not null or duplicates
            return null;

        edges.put(vertex, new HashMap<>()); // adds index in edges list to vertex
        return vertex;
    }

    /**
     * Remove the vertex and associated edge associations from the graph
     * <p>
     * Valid argument conditions:
     * 1. vertex should be non-null
     * 2. vertex should exist in the graph
     *
     * @param vertex - the vertex to be removed
     * @return vertex if vertex removed, else return null if vertex and associated
     * edges can not be removed (also if valid conditions are violated)
     */
    @Override
    public E removeVertex(E vertex) {
        // non-null
        if (vertex == null) {
            return null;
        }
        if (edges.get(vertex) == null) {
            // vertex is not within graph
            return null;
        }
        // removes vertex
        edges.remove(vertex);
        // removes every edge to vertex
        Set<E> set = edges.keySet();
        for (E key : set) {
            HashMap<E, Integer> search = edges.get(key);
            search.remove(vertex);
        }
        return vertex;
    }

    /**
     * Add an edge between two vertices (edge is undirected and unweighted)
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 - the first vertex
     * @param vertex2 - the second vertex
     * @return true if edge added, else return false if edge can not be added (also
     * if valid conditions are violated)
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        // vertex1 or vertex2 is null
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        if (edges.get(vertex1) == null || edges.get(vertex2) == null || vertex1.equals(vertex2)) {
            //vertex1 or vertex2 is not within graph or vertex1 == vertex2
            return false;
        }
        // added twice, edges is written for directed graph
        edges.get(vertex1).put(vertex2, 0);
        edges.get(vertex2).put(vertex1, 0);
        return true;
    }

    /**
     * Remove the edge between two vertices (edge is undirected and unweighted)
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 - the first vertex
     * @param vertex2 - the second vertex
     * @return true if edge removed, else return false if edge can not be removed
     * (also if valid conditions are violated)
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        // vertex1 or vertex2 is null
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        if (edges.get(vertex1) == null || edges.get(vertex2) == null || vertex1.equals(vertex2)) {
            //vertex1 or vertex2 is not within graph or vertex1 == vertex2
            return false;
        }
        // removes edges between two vertices twice
        // edge.removeEdge() is written for directed graphs
        edges.get(vertex1).remove(vertex2);
        edges.get(vertex2).remove(vertex1);
        return true;
    }

    /**
     * Check whether the two vertices are adjacent
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 - the first vertex
     * @param vertex2 - the second vertex
     * @return true if both the vertices have an edge with each other, else return
     * false if vertex1 and vertex2 are not connected (also if valid
     * conditions are violated)
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        // vertex1 or vertex2 is null
        if (vertex1 == null || vertex2 == null)
            return false;

        if (edges.get(vertex1) == null || edges.get(vertex2) == null || vertex1.equals(vertex2)) {
            // vertex1 or vertex2 is not within graph or vertex1 == vertex2
            return false;
        }
        // since graph is undirected, only needs to be called once
        return edges.get(vertex1).containsKey(vertex2);
    }

    /**
     * Get all the neighbor vertices of a vertex
     *
     * @param vertex - the vertex
     * @return an iterable for all the immediate connected neighbor vertices
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        return edges.get(vertex).keySet();
    }

    /**
     * Get all the vertices in the graph
     *
     * @return an iterable for all the vertices
     */
    @Override
    public Iterable<E> getAllVertices() {
        return edges.keySet();
    }
}
