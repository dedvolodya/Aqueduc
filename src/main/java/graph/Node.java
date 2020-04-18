package graph;

import java.io.Serializable;

public class Node implements Serializable {
    private int code;

    public Node(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return this.code == ((Node) obj).code;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return code;
    }
}
