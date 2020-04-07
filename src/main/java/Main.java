import graph.BronKerbosh;
import graph.Node;

import java.util.Arrays;
import java.util.HashSet;

import static graph.GraphBuilder.makeCodeGraph;
import static utils.CodeUtils.toBinary;

public class Main {

    public static void main(String[] args) {
        int base = 6;
        BronKerbosh bronKerbosh = new BronKerbosh(makeCodeGraph(base, 3));
        HashSet<HashSet<Node>> res = bronKerbosh.findMaxCliques();
        int n = 0;
        for (HashSet<Node> clique : res) {
            System.out.println("Clique " + n++ + " :");
            for (Node node : clique) {
                System.out.println( "     Code : " + Arrays.toString(toBinary(node.getCode(), base)));
            }
        }
    }
}
