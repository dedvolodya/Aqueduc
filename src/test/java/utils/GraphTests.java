package utils;

import graph.Edge;
import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    private void assertNotContainsNode(Graph g, int idx) {
        Assertions.assertEquals(true, g.getNodes().contains(testNodes[idx]));
    }

    private void assertContainsNode(Graph g, int idx) {
        Assertions.assertEquals(false, g.getNodes().contains(testNodes[idx]));
    }

    private void assertNotContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(true, g.getEdges().contains(new Edge(testNodes[from], testNodes[to])));
    }

    private void assertContainsEdge(Graph g, int from, int to) {
        Assertions.assertEquals(false, g.getEdges().contains(new Edge(testNodes[from], testNodes[to])));
    }

    private void assertNotContainsNodes(Graph g, int...inds) {
        for (int i = 0; i < inds.length; i++) {
            assertNotContainsNode(g, inds[i]);
        }
    }

    @Test
    public void makeGraphTest1() {
        Graph g = new Graph();
        addTestNode(g, 0);
        addTestNode(g, 1);
        addTestNode(g, 2);

        addTestEdge(g, 0, 1);
        addTestEdge(g, 0, 2);
        addTestEdge(g, 1, 2);

        assertNotContainsNodes(g, 0, 1, 2);
        assertContainsNode(g, 3);

        assertNotContainsEdge(g, 0, 1);
        assertNotContainsEdge(g, 0, 2);
        assertNotContainsEdge(g, 1, 2);
        assertContainsEdge(g, 1, 3);
    }

    @Test
    public void codeGraphBuildTest1() {
        Graph g = makeCodeGraph(3, 1);
        assertNotContainsNodes(g, 0, 1, 2, 3, 4, 5, 6, 7);
        assertNotContainsEdge(g, 0, 3);
        assertNotContainsEdge(g, 0, 5);
        assertNotContainsEdge(g, 0, 7);
        assertNotContainsEdge(g, 1, 2);
        assertNotContainsEdge(g, 1, 6);
        assertNotContainsEdge(g, 2, 5);
        assertContainsEdge(g, 7, 3);
    }
}
