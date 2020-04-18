package utils;

import graph.Edge;
import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static graph.GraphBuilder.makeCodeGraph;

public class GraphTests {
    private Node[] testNodes = new Node[] {new Node(0), new Node(1), new Node(2), new Node(3),
            new Node(4), new Node(5), new Node(6), new Node(7)};

    private void addTestNode(Graph g, int idx) {
        g.addNode(testNodes[idx]);
    }

    private void addTestEdge(Graph g, int from, int to) {
        g.addEdge(new Edge(testNodes[from], testNodes[to]));
    }

    private void assertContainsNode(Graph g, int idx) {
        Assertions.assertEquals(true, g.getNodes().contains(testNodes[idx]));
    }

    private void assertNotContainsNode(Graph g, int idx) {
        Assertions.assertEquals(false, g.getNodes().contains(testNodes[idx]));
    }

    private void assertContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(true, g.getEdges().contains(new Edge(testNodes[from], testNodes[to])));
    }

    private void assertNotContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(false, g.getEdges().contains(new Edge(testNodes[from], testNodes[to])));
    }

    private void assertContainsNodes(Graph g, int...inds) {
        for (int i = 0; i < inds.length; i++) {
            assertContainsNode(g, inds[i]);
        }
    }

    private void fillSimpleGraph(Graph g) {
        addTestNode(g, 0);
        addTestNode(g, 1);
        addTestNode(g, 2);

        addTestEdge(g, 0, 1);
        addTestEdge(g, 0, 2);
        addTestEdge(g, 1, 2);
    }

    private void simpleGraphTest(Graph g) {
        assertContainsNodes(g, 0, 1, 2);
        assertNotContainsNode(g, 3);

        assertContainsEdge(g, 0, 1);
        assertContainsEdge(g, 0, 2);
        assertContainsEdge(g, 1, 2);
        assertNotContainsEdge(g, 1, 3);
    }

    @Test
    public void makeGraphTest1() {
        Graph g = new Graph();
        fillSimpleGraph(g);
        simpleGraphTest(g);
    }

    @Test
    public void codeGraphBuildTest1() {
        Graph g = makeCodeGraph(3, 1);
        assertContainsNodes(g, 0, 1, 2, 3, 4, 5, 6, 7);
        assertContainsEdge(g, 0, 3);
        assertContainsEdge(g, 0, 5);
        assertContainsEdge(g, 0, 7);
        assertContainsEdge(g, 1, 2);
        assertContainsEdge(g, 1, 6);
        assertContainsEdge(g, 2, 5);
        assertNotContainsEdge(g, 7, 3);
    }

    @Test
    public void hashCodeTest() {
        HashSet<Node> singleton = new HashSet<>();
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        singleton.add(n1);
        singleton.add(n2);
        Assertions.assertEquals(1, singleton.size());
    }

    @Test
    public void serializationSetTest() throws IOException {
        HashSet<Node> set = new HashSet<>();
        set.add(new Node(1));
        set.add(new Node(2));
        File tmp = File.createTempFile("testSet", "tmp");
        tmp.deleteOnExit();
        Serializer.serializeSet(set, tmp.getPath());
        HashSet<Node> deserialize = Serializer.loadSet(tmp.getPath());
        Assertions.assertEquals(2, deserialize.size());
        Assertions.assertTrue(deserialize.contains(new Node(1)));
        Assertions.assertTrue(deserialize.contains(new Node(2)));
    }

    @Test
    public void serializationGraphTest() throws IOException {
        Graph g = new Graph();
        fillSimpleGraph(g);
        File tmp = File.createTempFile("testGraph", "tmp");
        tmp.deleteOnExit();
        Serializer.serializeGraph(g, tmp.getPath());
        Graph deserialize = Serializer.loadGraph(tmp.getPath());
        simpleGraphTest(deserialize);
    }
}
