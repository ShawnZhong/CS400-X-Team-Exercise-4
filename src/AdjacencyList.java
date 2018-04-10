import java.util.ArrayList;

public class AdjacencyList<T> {
    private ArrayList<LinkedList<T>> list; // holds the list to edges

    AdjacencyList() {
        list = new ArrayList<LinkedList<T>>();
    }

    /**
     * Add given vertex into the adjacency list
     * Written for directional graphs - if graph is non-directional, method must be called twice
     *
     * @param vertex - vertex to be added
     */
    public void addVertex(T vertex) {
        list.add(new LinkedList<T>()); // duplicate exception will be thrown if duplicate is entered
    }

    /**
     * Removes the given vertex from adjacency list
     *
     * @param vertex - vertex to be removed
     * @param index  - index that vertex's edges are located
     */
    public void removeVetex(T vertex, int index) {
        list.remove(index);
        // removes every edge to vertex within list
        for (int x = 0; x < list.size(); x++) {
            // should have no duplicates within the LinkedList
            try {
                list.get(x).remove(vertex);
            } catch (RuntimeException e) {

            }
        }

    }

    /**
     * Adds edge between source and destination
     * Written for directional graphs - if graph is non-directional, method must be called twice
     *
     * @param source      - beginning of edge
     * @param destination - end of edge
     * @param index       - index of source's edges are located
     */
    public void addEdge(T source, T destination, int index) {
        // adds destination to list
        list.get(index).add(destination);
    }

    /**
     * returns the edges of a specified vertex
     *
     * @param index - index of the vertex's whose edges are stored
     * @return list of edges from vertex
     */
    public ArrayList<T> getEdges(int index) {
        return list.get(index).toArrayList();
    }

    /**
     * Removes edge between source and destination
     * Written for directional graphs, if non-directional, method should be called twice
     *
     * @param source      - beginning of the edge
     * @param destination - end of the edge
     * @param int         index - index of the source's edges are located
     */
    public void removeEdge(T source, T destination, int index) {
        // source does not exist in vertices
        if (index == -1) {
            throw new IllegalArgumentException("Source not found");
        }
        // removes edge from given vertices
        list.get(index).remove(destination);
    }

    /**
     * Checks if two vertices have an edge between each other
     *
     * @param source      - beginning of edge
     * @param destination - end of edge
     * @param index       - index of source's edges within list
     * @return true if an edge exists between two vertices
     */
    public boolean isConntected(T source, T destination, int index) {
        T value = list.get(index).search(destination);
        // value was not found
        if (value == null) {
            return false;
        }
        return true;
    }
}
