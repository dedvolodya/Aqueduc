package graph;

import utils.KeyPressThread;
import utils.Serializer;

import java.util.HashSet;
import java.util.Iterator;

import static utils.Sets.*;

public class BronKerbosh {

    private HashSet<HashSet<Node>> cliques = new HashSet<>();
    private Graph graph;
    private KeyPressThread stopThread = new KeyPressThread("Q");
    private String graphFile;
    private String rFile;
    private String pFile;
    private String xFile;

    public BronKerbosh(String graphFile, String rFile, String pFile, String xFile) {
        this.graphFile = graphFile;
        this.rFile = rFile;
        this.pFile = pFile;
        this.xFile = xFile;
    }

    public BronKerbosh withNewGraph(Graph graph) {
        this.graph = graph;
        return this;
    }

    public BronKerbosh withLoadGraph() {
        this.graph = Serializer.loadGraph(this.graphFile);
        return this;
    }

    public HashSet<HashSet<Node>> startFindMaxCliques() {
        stopThread.start();
        HashSet<Node> r = new HashSet<>();
        HashSet<Node> p = new HashSet<>();
        HashSet<Node> x = new HashSet<>();
        p.addAll(graph.getNodes());
        findAllCliques(r, p, x);
        saveIntermediateResult(r, p, x);
        filterCliques();
        return cliques;
    }

    public HashSet<HashSet<Node>> continueFindMaxCliques() {
        stopThread.start();
        HashSet<Node> r = Serializer.loadSet(rFile);
        HashSet<Node> p = Serializer.loadSet(pFile);
        HashSet<Node> x = Serializer.loadSet(xFile);
        findAllCliques(r, p, x);
        saveIntermediateResult(r, p, x);
        filterCliques();
        return cliques;
    }

    private void saveIntermediateResult(HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        if (stopThread.isAlive()) {
            stopThread.exit();
        } else {
            Serializer.serializeGraph(graph, graphFile);
            Serializer.serializeSet(r, rFile);
            Serializer.serializeSet(p, pFile);
            Serializer.serializeSet(x, xFile);
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
