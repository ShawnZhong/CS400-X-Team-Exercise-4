/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          P4 Dictionary Graph
// FILES:            Graph.java
//                   GraphTest.java
//                   BalancedSearchTree.java
//                   DuplicateKeyException.java
//
// USER:             Shawn Zhong (shawn.zhong@wisc.edu)
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
//
// Bugs:             N/A
// Source Credits:   https://pages.cs.wisc.edu/~deppeler/cs400/readings/AVL-Trees/index.html
//                   I looked up the rotateLeft() method on this page for reference
// Due date:         Monday, February 5th
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private Map<E, Set<E>> edges;

    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
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

        edges.put(vertex, new HashSet<>()); // adds index in edges list to vertex
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
        if (vertex == null || edges.get(vertex) == null)  // vertex is null or not within graph
            return null;

        // removes every edge to vertex
        for (E key : edges.keySet())
            edges.get(key).remove(vertex);

        edges.remove(vertex);// removes vertex

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
        if (notValid(vertex1, vertex2))
            return false;

        // added twice, edges is written for directed graph
        edges.get(vertex1).add(vertex2);
        edges.get(vertex2).add(vertex1);
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
        if (notValid(vertex1, vertex2))
            return false;

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
        if (notValid(vertex1, vertex2))
            return false;

        // since graph is undirected, only needs to be called once
        return edges.get(vertex1).contains(vertex2);
    }

    /**
     * Get all the neighbor vertices of a vertex
     *
     * @param vertex - the vertex
     * @return an iterable for all the immediate connected neighbor vertices
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        return edges.get(vertex);
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

    /**
     * check whether the vertex is valid
     *
     * @param v1 - the first vertex
     * @param v2 - the second vertex
     * @return true if v1/v2 is null, or v1/v2 is not within graph or they are equal
     */
    private boolean notValid(E v1, E v2) {
        return v1 == null || v2 == null || edges.get(v1) == null || edges.get(v2) == null || v1.equals(v2);
    }
}
