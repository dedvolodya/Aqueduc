package program;

import algorithm.BronKerboshAlgorithm;
import algorithm.IntermediateResult;
import graph.GraphBuilder;
import graph.HemmingGraph;
import graph.Node;
import utils.Code;
import utils.Serializer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CodeResolver {
    private String codeName;
    boolean optimization = false;

    public CodeResolver(String codeName) {
        this.codeName = codeName;
    }

    //TODO: improvements
    public CodeResolver withOptimization() {
        this.optimization = true;
        return this;
    }

    public HashSet<Code> findNewCodes(int codeRadix, int codeDistance) {
        HemmingGraph g = GraphBuilder.makeHammingGraph(codeRadix, codeDistance);
        HashSet<Node> p = new HashSet<>();
        p.addAll(g.getNodes());
        return start(g, new HashSet<>(), p, new HashSet<>());
    }

    public HashSet<Code> findSavedCodes() {
        IntermediateResult intermediateResult = Serializer.loadIntermediateResult(codeName);
        return start((HemmingGraph) intermediateResult.getGraph(), intermediateResult.getRSet(), intermediateResult.getPSet(),
                intermediateResult.getXSet());

    }

    private HashSet<Code> start(HemmingGraph g, HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        BronKerboshAlgorithm algorithm = new BronKerboshAlgorithm(g);
        HashSet<HashSet<Node>> result = algorithm.findMaxCliques(r, p, x);
        System.out.print("FINISHED");
        if (algorithm.wasInterrupted()) {
            System.out.print("SERIALIZE");
            Serializer.serializeIntermediateResult(algorithm.getIntermediateResult(), codeName);
        }
        return convertToCode(result, g.getBitRate());
    }

    private HashSet<Code> convertToCode(HashSet<HashSet<Node>> cliques, int bitRate) {
        HashSet<Code> result = new HashSet<>();
        for (HashSet<Node> clique : cliques) {
            List<Integer> codes = new ArrayList<>();
            for (Node nd : clique) {
                codes.add(nd.getCode());
            }
            result.add(new Code(bitRate, codes));
        }
        return result;
    }
}
