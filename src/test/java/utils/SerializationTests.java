package utils;

import algorithm.IntermediateResult;
import graph.Graph;
import graph.HemmingGraph;
import graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static utils.GraphResources.fillSimpleGraph;
import static utils.GraphResources.simpleGraphTest;

public class SerializationTests {
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

    @Test
    public void serializationIntermediateResultTest() throws IOException {
        HemmingGraph g = new HemmingGraph(6,3);
        fillSimpleGraph(g);
        File tmp = File.createTempFile("testIResult", "tmp");
        tmp.deleteOnExit();
        HashSet<Node> set = new HashSet<>();
        set.add(new Node(1));
        IntermediateResult result = new IntermediateResult(g, set, set, new HashSet<>());
        Serializer.serializeIntermediateResult(result, tmp.getPath());
        IntermediateResult deserialize = Serializer.loadIntermediateResult(tmp.getPath());
        HemmingGraph resultGraph = (HemmingGraph) deserialize.getGraph();
        simpleGraphTest(resultGraph);
        Assertions.assertEquals(6, resultGraph.getBitRate());
        Assertions.assertEquals(3,  resultGraph.getDistance());
        Assertions.assertTrue(result.getRSet().contains(new Node(1)));
        Assertions.assertTrue(result.getPSet().contains(new Node(1)));
        Assertions.assertFalse(result.getXSet().contains(new Node(1)));
    }
}
