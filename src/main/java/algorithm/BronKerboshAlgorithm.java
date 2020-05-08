package algorithm;

import graph.Graph;
import graph.Node;
import utils.KeyPressThread;

import java.util.HashSet;
import java.util.Iterator;

import static utils.Sets.*;

public class BronKerboshAlgorithm {

    private HashSet<HashSet<Node>> cliques = new HashSet<>();
    private Graph graph;
    private KeyPressThread stopThread = new KeyPressThread("Q");
    private IntermediateResult intermediateResult = null;

    public BronKerboshAlgorithm() {
    }

    public void withGraph(Graph g) {
        this.graph = g;
    }

    public BronKerboshAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public HashSet<HashSet<Node>> findMaxCliques(HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        if (graph == null) {
            throw new RuntimeException("Graph is not provided");
        }
        stopThread.start();
        findAllCliques(r, p, x);
        saveIntermediateResult(r, p, x);
        filterCliques();
        return cliques;
    }

    public IntermediateResult getIntermediateResult() {
        return this.intermediateResult;
    }

    public boolean wasInterrupted() {
        return intermediateResult != null;
    }

    private void saveIntermediateResult(HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        if (stopThread.isAlive()) {
            System.out.println("NOT SAVE");
            stopThread.exit();
        } else {
            System.out.println("SAVE RESULTS");
            intermediateResult = new IntermediateResult(graph, r, p, x);
        }
    }

    private void findAllCliques(HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        if (!stopThread.isAlive()) {
            return;
        }
        if (p.isEmpty() && x.isEmpty()) {
            cliques.add(r);
        }
        Iterator<Node> nodeIterator = p.iterator();
        while (!p.isEmpty() && nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            HashSet<Node> singleton = new HashSet<>();
            singleton.add(node);
            findAllCliques(union(r, singleton), intersection(p, graph.getChildren(node)), intersection(x, graph.getChildren(node)));
            p = difference(p, singleton);
            x = union(x, singleton);
            if (p.isEmpty()) {
                nodeIterator = p.iterator();
            }
        }
    }

    private void filterCliques() {
        Iterator<HashSet<Node>> cliqueIterator = cliques.iterator();
        if (!cliqueIterator.hasNext()) {
            return;
        }
        HashSet<Node> max = cliqueIterator.next();
        while (cliqueIterator.hasNext()) {
            HashSet<Node> next = cliqueIterator.next();
            if (next.size() > max.size()) {
                max = next;
            }
        }
        final int maxSize = max.size();
        cliques.removeIf(c -> c.size() != maxSize);
    }
}
