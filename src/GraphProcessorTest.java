import org.junit.*;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

public class GraphProcessorTest {
    @Rule
    public Timeout globalTimeout = new Timeout(60, TimeUnit.SECONDS);

    GraphProcessor gp = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception { }

    @AfterClass
    public static void tearDownAfterClass() throws Exception { }

    @Before
    public void setUp() throws Exception {
        gp = new GraphProcessor();
    }

    @After
    public void tearDown() throws Exception {
        gp = null;
    }

    @Test
    public void test01_tese() {

    }
}
