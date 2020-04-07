package graph;

public class Node {
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
}
