package graph;

import java.io.Serializable;

public class Edge implements Serializable {
    private Node x, y;

    public Edge(Node x, Node y) {
        this.x = x;
        this.y = y;
    }

    public Node getX() {
        return this.x;
    }

    public Node getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            return this.x.equals(((Edge) obj).x) && this.y.equals(((Edge) obj).y) ||
                    this.x.equals(((Edge) obj).y) && this.y.equals(((Edge) obj).x);
        }
        return false;
    }
}
