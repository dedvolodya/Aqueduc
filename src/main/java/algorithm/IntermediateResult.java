package algorithm;

import graph.Graph;
import graph.Node;

import java.io.Serializable;
import java.util.HashSet;

public class IntermediateResult implements Serializable {
    private HashSet<Node> p;
    private HashSet<Node> r;
    private HashSet<Node> x;
    private Graph g;

    public IntermediateResult(Graph g, HashSet<Node> r, HashSet<Node> p, HashSet<Node> x) {
        this.g = g;
        this.p = p;
        this.r = r;
        this.x = x;
    }

    public Graph getGraph() {
        return this.g;
    }

    public HashSet<Node> getPSet() {
        return this.p;
    }

    public HashSet<Node> getRSet() {
        return this.r;
    }

    public HashSet<Node> getXSet() {
        return this.x;
    }
}
