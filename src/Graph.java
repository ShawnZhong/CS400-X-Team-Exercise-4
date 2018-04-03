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

    /**
     * Add new vertex to the graph
     * <p>
     * Valid argument conditions:
     * 1. vertex should be non-null
     * 2. vertex should not already exist in the graph
     *
     * @param vertex the vertex to be added
     * @return vertex if vertex added, else return null if vertex can not be added (also if valid conditions are violated)
     */
    @Override
    public E addVertex(E vertex) {
        return vertex;
    }

    /**
     * Remove the vertex and associated edge associations from the graph
     * <p>
     * Valid argument conditions:
     * 1. vertex should be non-null
     * 2. vertex should exist in the graph
     *
     * @param vertex the vertex to be removed
     * @return vertex if vertex removed, else return null if vertex and associated edges can not be removed (also if valid conditions are violated)
     */
    @Override
    public E removeVertex(E vertex) {
        return vertex;
    }

    /**
     * Add an edge between two vertices (edge is undirected and unweighted)
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if edge added, else return false if edge can not be added (also if valid conditions are violated)
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        return true;
    }

    /**
     * Remove the edge between two vertices (edge is undirected and unweighted)
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if edge removed, else return false if edge can not be removed (also if valid conditions are violated)
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        return true;
    }

    /**
     * Check whether the two vertices are adjacent
     * <p>
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if both the vertices have an edge with each other, else return false if vertex1 and vertex2 are not connected (also if valid conditions are violated)
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        return true;
    }

    /**
     * Get all the neighbor vertices of a vertex
     *
     * @param vertex the vertex
     * @return an iterable for all the immediate connected neighbor vertices
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        return null;
    }

    /**
     * Get all the vertices in the graph
     *
     * @return an iterable for all the vertices
     */
    @Override
    public Iterable<E> getAllVertices() {
        return null;
    }

}
