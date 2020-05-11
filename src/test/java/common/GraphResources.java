package common;

import graph.Edge;
import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Assertions;

public class GraphResources {
    private static Node[] TEST_NODES = new Node[] {new Node(0), new Node(1), new Node(2), new Node(3),
            new Node(4), new Node(5), new Node(6), new Node(7)};

    public static void addTestNode(Graph g, int idx) {
        g.addNode(TEST_NODES[idx]);
    }

    public static void addTestEdge(Graph g, int from, int to) {
        g.addEdge(new Edge(TEST_NODES[from], TEST_NODES[to]));
    }

    public static void assertContainsNode(Graph g, int idx) {
        Assertions.assertEquals(true, g.getNodes().contains(TEST_NODES[idx]));
    }

    public static void assertNotContainsNode(Graph g, int idx) {
        Assertions.assertEquals(false, g.getNodes().contains(TEST_NODES[idx]));
    }

    public static void assertContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(true, g.getEdges().contains(new Edge(TEST_NODES[from], TEST_NODES[to])));
    }

    public static void assertNotContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(false, g.getEdges().contains(new Edge(TEST_NODES[from], TEST_NODES[to])));
    }

    public static void assertContainsNodes(Graph g, int...inds) {
        for (int i = 0; i < inds.length; i++) {
            assertContainsNode(g, inds[i]);
        }
    }

    public static void fillSimpleGraph(Graph g) {
        addTestNode(g, 0);
        addTestNode(g, 1);
        addTestNode(g, 2);

        addTestEdge(g, 0, 1);
        addTestEdge(g, 0, 2);
        addTestEdge(g, 1, 2);
    }

    public static void simpleGraphTest(Graph g) {
        assertContainsNodes(g, 0, 1, 2);
        assertNotContainsNode(g, 3);

        assertContainsEdge(g, 0, 1);
        assertContainsEdge(g, 0, 2);
        assertContainsEdge(g, 1, 2);
        assertNotContainsEdge(g, 1, 3);
    }
}
