package utils;

import graph.Edge;
import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static graph.GraphBuilder.makeHammingGraph;
import static utils.GraphResources.*;

public class GraphTests {

    @Test
    public void makeGraphTest1() {
        Graph g = new Graph();
        fillSimpleGraph(g);
        simpleGraphTest(g);
    }

    @Test
    public void codeGraphBuildTest1() {
        Graph g = makeHammingGraph(3, 2);
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
}
