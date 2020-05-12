package algorithm;

import graph.HemmingGraph;
import graph.Node;
import utils.CodeUtils;

import java.util.*;

import static utils.Sets.*;

public class BronKerboshAlgorithm {

    private HashSet<HashSet<Node>> cliques = new LinkedHashSet<>();
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
        HashSet<Node> nodes = new LinkedHashSet<>();
        Node zero = makeNode(0);
        Node second = makeNode(graph.getDistance());

        nodes.add(zero);
        nodes.add(second);
        for (Node nd : graph.getNodes()) {
            if (nd.getCode() != zero.getCode() && nd.getCode() != second.getCode()) {
                nodes.add(nd);
            }
        }
        candidateStack.add(nodes);
        notStack.add(new HashSet<>());
        return this;
    }

    public BronKerboshAlgorithm withIntermediateResults(IntermediateResult results) {
        this.compsubStack = results.getCompsub();
        this.candidateStack = results.getCandidates();
        this.notStack = results.getNot();
        this.singletonStack = results.getSingleton();
        this.cliques = results.getCliques();
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
        intermediateResult = new IntermediateResult(graph, compsubStack, candidateStack, notStack, singletonStack, cliques);
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
        int depth = compsubStack.size();
        while (!candidates.isEmpty() || !compsub.isEmpty()) {
            if (stop) {
                saveIntermediateResult();
                return;
            }
            HashSet<Node> singleton = new HashSet<>();
            if (!candidates.isEmpty()) {
                depth++;
                Node node = candidates.iterator().next();
                if (depth == 2) {
                    if (CodeUtils.numberWeight(node.getCode(), graph.getBitRate()) != graph.getDistance()) {
                        break;
                    }
                }
                singleton.add(node);

                compsubStack.push((HashSet<Node>) compsub.clone());
                candidateStack.push(candidates);
                singletonStack.push(singleton);
                notStack.push(not);

                compsub.addAll(singleton);

                HashSet<Node> notConnected = difference(toSet(graph.getNodes()), graph.getChildren(node));
                candidates = differenceLinkedSet(candidates, notConnected);
                candidates = differenceLinkedSet(candidates, singleton);
                not = difference(not, notConnected);
            } else {
                depth--;
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

    private Node makeNode(int weight) {
        return new Node(CodeUtils.makeIntWithWeight(weight, graph.getBitRate()));
    }

    public void stop() {
        stop = true;
    }

    public HemmingGraph getGraph() {
        return graph;
    }
}
