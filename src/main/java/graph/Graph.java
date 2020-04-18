package graph;

import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {
    List<Edge> edges = new ArrayList<>();
    List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public HashSet<Node> getChildren(Node node) {
        HashSet<Node> children = new HashSet<>();
        edges.forEach(edge -> { if (edge.getX().getCode() == node.getCode()) { children.add(edge.getY()); }
                           else if (edge.getY().getCode() == node.getCode()) children.add(edge.getX()); });
        return children;
    }

    public Collection<Node> getNodes() {
        return this.nodes;
    }

    public Collection<Edge> getEdges() {
        return this.edges;
    }
}
