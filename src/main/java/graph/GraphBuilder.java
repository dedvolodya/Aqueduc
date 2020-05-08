package graph;

import static utils.CodeUtils.hemmingDistance;

public class GraphBuilder {

    public static HemmingGraph makeHammingGraph(int digits, int minDigits) {
        int maxValue = (int) Math.pow(2, digits);
        HemmingGraph g = new HemmingGraph(digits, minDigits);
        for (int i = 0; i < maxValue; i++) {
            Node node = new Node(i);
            g.getNodes().forEach(nd -> {
                if (hemmingDistance(node.getCode(), nd.getCode(), digits) >= minDigits)
                    g.addEdge(new Edge(node, nd));
            });
            g.addNode(node);
        }
        return g;
    }
}
