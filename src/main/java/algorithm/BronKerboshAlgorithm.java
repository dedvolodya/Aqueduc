package algorithm;

import graph.HemmingGraph;
import graph.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import static utils.Sets.*;

public class BronKerboshAlgorithm {

    private HashSet<HashSet<Node>> cliques = new HashSet<>();
    private HemmingGraph graph;
    private IntermediateResult intermediateResult = null;
    private volatile boolean stop = false;


    private Stack<HashSet<Node>> candidateStack = new Stack<>();
    private Stack<HashSet<Node>> notStack = new Stack<>();
    private Stack<HashSet<Node>> compsubStack = new Stack<>();
    private Stack<HashSet<Node>> singletonStack = new Stack<>();


    public BronKerboshAlgorithm(HemmingGraph graph) {
        this.graph = graph;
    }

    public BronKerboshAlgorithm initStacks() {
        compsubStack.add(new HashSet<>());
        HashSet<Node> nodes = new HashSet<>();
        nodes.addAll(graph.getNodes());
        candidateStack.add(nodes);
        notStack.add(new HashSet<>());
        return this;
    }

    public BronKerboshAlgorithm withIntermediateResults(IntermediateResult results) {
        this.compsubStack = results.getCompsub();
        this.candidateStack = results.getCandidates();
        this.notStack = results.getNot();
        this.singletonStack = results.getSingleton();
        return this;
    }

    public HashSet<HashSet<Node>> findMaxCliques() {
        findAllCliques();
        filterCliques();
        return cliques;
    }

    public IntermediateResult getIntermediateResult() {
        return this.intermediateResult;
    }

    public boolean wasInterrupted() {
        return intermediateResult != null;
    }

    private void saveIntermediateResult() {
        intermediateResult = new IntermediateResult(graph, compsubStack, candidateStack, notStack, singletonStack);
    }

    //recursive implementation
    private void findAllCliques(HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
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

    private void findAllCliques() {
        HashSet<Node> compsub = compsubStack.pop();
        HashSet<Node> candidates = candidateStack.pop();
        HashSet<Node> not = notStack.pop();
        while (!candidates.isEmpty() || !compsub.isEmpty()) {
            if (stop) {
                saveIntermediateResult();
                return;
            }
            HashSet<Node> singleton = new HashSet<>();
            if (!candidates.isEmpty()) {
                Node node = candidates.iterator().next();
                singleton.add(node);

                compsubStack.push((HashSet<Node>) compsub.clone());
                candidateStack.push(candidates);
                singletonStack.push(singleton);
                notStack.push(not);

                compsub.addAll(singleton);
                HashSet<Node> all = new HashSet<>();
                all.addAll(graph.getNodes());

                HashSet<Node> notConnected = difference(all, graph.getChildren(node));
                candidates = difference(candidates, notConnected);
                candidates = difference(candidates, singleton);
                not = difference(not, notConnected);
            } else {
                if (not.isEmpty()) {
                    cliques.add(compsub);
                }
                compsub = compsubStack.pop();
                candidates = candidateStack.pop();
                singleton = singletonStack.pop();
                not = notStack.pop();
                candidates = difference(candidates, singleton);
                not.addAll(singleton);
            }
        }
        stop = true;
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

    public void stop() {
        stop = true;
    }
}
