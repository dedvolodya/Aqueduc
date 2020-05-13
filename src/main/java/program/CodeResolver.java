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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.Callable;

import static utils.CodeUtils.convertToCode;

public class CodeResolver implements Callable<HashSet<Code>> {
    private String codeName;
    private BronKerboshAlgorithm algorithm;
    private int codeRadix;

    public CodeResolver(String codeName) {
        this.codeName = codeName;
    }

    public CodeResolver findNewCodes(int codeRadix, int codeDistance) {
        HemmingGraph g = GraphBuilder.makeHammingGraph(codeRadix, codeDistance);
        algorithm = new BronKerboshAlgorithm(g).initStacks();
        this.codeRadix = codeRadix;
        return this;
    }

    public CodeResolver loadAndContinueSearch() {
        IntermediateResult intermediateResult = Serializer.loadIntermediateResult(codeName);
        algorithm = new BronKerboshAlgorithm(intermediateResult.getGraph()).withIntermediateResults(intermediateResult);
        codeRadix = intermediateResult.getGraph().getBitRate();
        return this;
    }

    private HashSet<Code> processResult(HashSet<HashSet<Node>> result, int bitRate) {
        if (algorithm.wasInterrupted()) {
            Serializer.serializeIntermediateResult(algorithm.getIntermediateResult(), codeName);
        }
        return convertToCode(result, bitRate);
    }

    public void stopExecuting() {
        algorithm.stop();
    }

    public boolean wasInterrupted() {
        return algorithm.wasInterrupted();
    }

    @Override
    public HashSet<Code> call() throws Exception {
        HashSet<HashSet<Node>> result = algorithm.findMaxCliques();
        return processResult(result, codeRadix);
    }

    public HemmingGraph getGraph() {
        return algorithm.getGraph();
    }
}
