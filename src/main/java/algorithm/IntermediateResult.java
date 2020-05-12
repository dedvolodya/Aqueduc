package algorithm;

import graph.HemmingGraph;
import graph.Node;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Stack;

public class IntermediateResult implements Serializable {
    private Stack<HashSet<Node>> compsub;
    private Stack<HashSet<Node>> candidates;
    private Stack<HashSet<Node>> not;
    private Stack<HashSet<Node>> singleton;
    private HemmingGraph g;
    private HashSet<HashSet<Node>> cliques;

    public IntermediateResult(HemmingGraph g, Stack<HashSet<Node>> compsub, Stack<HashSet<Node>> candidates,
                              Stack<HashSet<Node>> not, Stack<HashSet<Node>> singleton, HashSet<HashSet<Node>> cliques) {
        this.g = g;
        this.compsub = compsub;
        this.candidates = candidates;
        this.not = not;
        this.singleton = singleton;
        this.cliques = cliques;
    }

    public HemmingGraph getGraph() {
        return this.g;
    }

    public Stack<HashSet<Node>> getCompsub() {
        return this.compsub;
    }

    public Stack<HashSet<Node>> getCandidates() {
        return this.candidates;
    }

    public Stack<HashSet<Node>> getNot() {
        return this.not;
    }

    public Stack<HashSet<Node>> getSingleton() {
        return this.singleton;
    }

    public HashSet<HashSet<Node>> getCliques() {
        return cliques;
    }
}
