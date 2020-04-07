package graph;

import java.util.HashSet;
import java.util.Iterator;

import static utils.Sets.*;

public class BronKerbosh {

    private HashSet<HashSet<Node>> cliques = new HashSet<>();
    private Graph graph;

    public BronKerbosh(Graph graph) {
        this.graph = graph;
    }

    public HashSet<HashSet<Node>> findMaxCliques() {
        HashSet<Node> p = new HashSet<>();
        HashSet<Node> r = new HashSet<>();
        HashSet<Node> x = new HashSet<>();
        p.addAll(graph.getNodes());
        findAllCliques(r, p, x);
        filterCliques();
        return cliques;
    }

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
