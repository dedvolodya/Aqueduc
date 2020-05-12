package algorithm;

import graph.HemmingGraph;
import graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static graph.GraphBuilder.makeHammingGraph;

public class BronKerboshTests {
    @Test
    public void findClique() {
        HemmingGraph g = makeHammingGraph(3, 2);
        BronKerboshAlgorithm algo = new BronKerboshAlgorithm(g);
        algo.initStacks();
        HashSet<HashSet<Node>> result = algo.findMaxCliques();
        Assertions.assertEquals(1, result.size());
        int[] expectedNodes = new int[]{0, 3, 5, 6};
        for (HashSet<Node> set : result) {
            int i = 0;
            for (Node nd : set) {
                Assertions.assertEquals(expectedNodes[i++], nd.getCode());
            }
        }
    }
}
