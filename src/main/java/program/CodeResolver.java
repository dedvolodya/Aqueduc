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

    public HashSet<Code> findNewCodes(int codeRadix, int codeDistance) {
        HemmingGraph g = GraphBuilder.makeHammingGraph(codeRadix, codeDistance);
        HashSet<Node> p = new HashSet<>();
        p.addAll(g.getNodes());
        BronKerboshAlgorithm algorithm = new BronKerboshAlgorithm(g);
        HashSet<HashSet<Node>> result = algorithm.initStacks().findMaxCliques();
        return processResult(algorithm, result, codeRadix);
    }

    public HashSet<Code> loadAndContinueSearch() {
        IntermediateResult intermediateResult = Serializer.loadIntermediateResult(codeName);
        BronKerboshAlgorithm algorithm = new BronKerboshAlgorithm(intermediateResult.getGraph());
        HashSet<HashSet<Node>> result = algorithm.withIntermediateResults(intermediateResult).findMaxCliques();
        return processResult(algorithm, result, intermediateResult.getGraph().getBitRate());
    }

    private HashSet<Code> processResult(BronKerboshAlgorithm algorithm, HashSet<HashSet<Node>> result, int bitRate) {
        if (algorithm.wasInterrupted()) {
            Serializer.serializeIntermediateResult(algorithm.getIntermediateResult(), codeName);
        }
        return convertToCode(result, bitRate);
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
