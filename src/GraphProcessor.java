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

import java.util.*;

/**
 * This class adds additional functionality to the graph as a whole.
 * <p>
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 *
 * @author sapan (sapan@cs.wisc.edu)
 * @see #populateGraph(String)
 * - loads a dictionary of words as vertices in the graph.
 * - finds possible edges between all pairs of vertices and adds these edges in the graph.
 * - returns number of vertices added as Integer.
 * - every call to this method will add to the existing graph.
 * - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 * - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 * - the shortest path data structures are used later to
 * to quickly find the shortest path and distance between two vertices.
 * - this method is called after any call to populateGraph.
 * - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 * - returns a list of vertices that constitute the shortest path between two given vertices,
 * computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 * - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 * - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 * - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 * - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 */
public class GraphProcessor {

    private GraphADT<String> graph;// Graph which stores the dictionary words and their associated connections
    private List<String> wordList; // Store each word, similar to graph.getAllVertices() but with indices
    private int size; // number of words
    private int[][] distance; //Pre-computed data, distance[i][j] stores the distance between i and j
    private int[][] predecessor; // Stores the predecessor of each vertex with shortest path


    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        graph = new Graph<>();

    }

    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between
     * existing words.
     * <p>
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * <p>
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * <p>
     * Log any issues encountered (print the issue details)
     *
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added; return -1 if file not found or if encountering other exceptions
     */
    public Integer populateGraph(String filepath) {
        try { // read all data from filepath and add vertices to the graph
            WordProcessor.getWordStream(filepath).forEach(graph::addVertex);

            // Initialize wordList and put all the words in it
            wordList = new ArrayList<>();
            graph.getAllVertices().forEach(wordList::add);

            // Add edges to the graph
            for (String e1 : wordList)
                for (String e2 : wordList)
                    if (WordProcessor.isAdjacent(e1, e2))
                        graph.addEdge(e1, e2);

            return size = wordList.size();
        } catch (Exception e) { // Error handling
            System.out.println("Can not load word from file" + filepath);
            return -1;
        }
    }

    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     *
     * @throws IllegalStateException if populateGraph wasn't called before this method
     */
    public void shortestPathPrecomputation() {
        if (wordList == null) // error checking
            throw new IllegalStateException();

        predecessor = new int[size][size];
        distance = new int[size][size];
        for (int i = 0; i < size; i++) { // Initialize to -1
            Arrays.fill(predecessor[i], -1);
            Arrays.fill(distance[i], -1);
        }


        for (int src = 0; src < size; src++) { //BFS
            // initialization
            Queue<Integer> queue = new LinkedList<>();
            boolean visited[] = new boolean[size];
            Arrays.fill(visited, false);
            visited[src] = true;
            queue.add(src);
            distance[src][src] = 0;
            while (!queue.isEmpty()) {
                int cur = queue.remove();
                for (String e : graph.getNeighbors(wordList.get(cur))) {
                    int des = index(e);
                    if (!visited[des]) {// for each unvisited neighbor
                        visited[des] = true;
                        queue.add(des);
                        distance[src][des] = distance[src][cur] + 1; // update distance
                        predecessor[src][des] = cur; // set predecessor
                    }
                }
            }
        }


    }

    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * <p>
     * Example: Given a dictionary,
     * cat
     * rat
     * hat
     * neat
     * wheat
     * kit
     * shortest path between cat and wheat is the following list of words:
     * [cat, hat, heat, wheat]
     * <p>
     * If word1 = word2, List will be empty.
     * Both the arguments will always be present in the graph.
     *
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     * @throws IllegalStateException if shortestPathPrecomputation wasn't called before this method
     */
    public List<String> getShortestPath(String word1, String word2) {
        List<String> result = new ArrayList<>();

        if (getShortestDistance(word1, word2) <= 0) // return empty list if word1==word2 or no path
            return result;

        int src = index(word2); // reverse src and des so that we can get the list in right order
        for (int i = index(word1); i != -1; i = predecessor[src][i])
            result.add(wordList.get(i));

        return result;
    }

    /**
     * Gets the distance of the shortest path between word1 and word2
     * <p>
     * Example: Given a dictionary,
     * cat
     * rat
     * hat
     * neat
     * wheat
     * kit
     * distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     * = 3 (the number of edges in the shortest path)
     * <p>
     * Distance = -1 if no path found between words (true also for word1=word2)
     * Both the arguments will always be present in the graph.
     *
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     * @throws IllegalStateException if shortestPathPrecomputation wasn't called before this method
     */
    public Integer getShortestDistance(String word1, String word2) {
        if (distance == null) // error checking
            throw new IllegalStateException();

        // if one of the word is null, or they are equal, or they doesn't exists, then return -1
        if (word1 == null || word2 == null || word1.equals(word2) || index(word1) < 0 || index(word2) < 0)
            return -1;

        return distance[index(word1)][index(word2)];
    }

    /**
     * return the index of word in the wordList
     *
     * @param word the word we want to find the index
     * @return the index of word in the wordList
     */
    private int index(String word) {
        return wordList.indexOf(word.trim().toUpperCase());
    }
}
