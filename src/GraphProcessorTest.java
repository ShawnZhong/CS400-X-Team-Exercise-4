import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class implements JUnit test to test whether GraphProcessor class is properly implemented
 * 
 * @author Jiazhi Yang (jyang436@wisc.edu)
 *
 */
public class GraphProcessorTest {
    @Rule
    public Timeout globalTimeout = new Timeout(60, TimeUnit.SECONDS);

    GraphProcessor gp = null;
    private static int sum = 0;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception { }

    @AfterClass
    public static void tearDownAfterClass() throws Exception { }

    @Before
    public void setUp() throws Exception {
        gp = new GraphProcessor();
        sum = gp.populateGraph("word_list.txt");
    }

    @After
    public void tearDown() throws Exception {
        gp = null;
    }

    /**
     * This test tests whether the populateGraph method whether properly executed.
     * It passes when it return correct numbers of vertices which are no duplicates, false otherwise.
     */
    @Test
    public void test01_populateGraph() {
        assertEquals("Number of vertices in the graph:", 427, sum);
    }
    
    /**
     * This test tests whether populateGraph method could handle the problem that the file it read is not existed.
     * Passes when it throws IOException, false otherwise.
     * @throws IOException
     */
    @Test
    (expected = IOException.class)
    public void test02_read_file_does_not_exist() throws IOException {
        int sum = gp.populateGraph("");
    }
    
    /**
     * 
     */
    @Test
    public void test03_get_shortest_distance() {
        int sd1 = gp.getShortestDistance("COMEDO", "CHARGE");
        assertEquals("Shortest distance from COMEDO to CHARGE is", 49, sd1);
        int sd2 = gp.getShortestDistance("CHARGE", "GIMLETS");
        assertEquals("Shortest distance from CHARGE to GIMLETS is", 78, sd2);
        int sd3 = gp.getShortestDistance("BELLIES", "JOLLIES");
        assertEquals("Shortest distance from BELLIES to JOLLIES is", 2, sd3);
        int sd4 = gp.getShortestDistance("DEFINE", "SHINY");
        assertEquals("Shortest distance from DEFINE to SHINY is", 26, sd4);
    }
    
    /**
     * This test tests whether the source or destination vertex is not stored in the graph.
     * Passes when it throws proper exception, false otherwise.
     */
    @Test
    public void test04_get_shortest_distance_from_vertex_does_not_exist() {
        gp.getShortestDistance("COMEDO", "A");
    }
    
    /**
     * This test tests whether the source or destination vertex is not stored in the same component.
     * Passes when it return infinity(Integer.MAX_VALUE), false otherwise.
     */
    @Test
    public void test05_get_shortest_distance_from_two_component() {
        int sd = gp.getShortestDistance("RAPINE", "ALIKE");
        assertEquals("Shortest distance from RAPINE to ALIKE is", Integer.MAX_VALUE, sd);
    }
    
    /**
     * This test tests whether the getShortestPath() method is whether properly executed.
     * Passes when it returns the correct list, false otherwise.
     */
    @Test
    public void test06_get_shortest_path() {
        List<String> actual = gp.getShortestPath("BELLIES", "JOLLIES");
        List<String> expected = new ArrayList<String>(); 
        expected.add("BELLIES");
        expected.add("JELLIES");
        expected.add("JOLLIES");
        assertEquals("Shortest path from BELLIES to JOLLIES is", expected, actual);
    }
    
    /**
     * 
     */
    @Test
    public void test07_get_shortest_path_from_vertex_does_not_exist() {
        gp.getShortestPath("COMEDO", "A");
    }
    
    /**
     * 
     */
    @Test
    public void test08_get_shortest_path_from_two_components() {
        List<String> actual = gp.getShortestPath("RAPINE", "ALIKE");
        assertEquals("Shortest distance from RAPINE to ALIKE is", null, actual);
    }
}
