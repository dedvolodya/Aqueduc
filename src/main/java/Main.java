import graph.BronKerbosh;
import graph.Node;

import java.util.Arrays;
import java.util.HashSet;

import static graph.GraphBuilder.makeCodeGraph;
import static utils.CodeUtils.toBinary;

public class Main {

    public static void main(String[] args) {
        int base = 8;
        String graphFile = "graph.file";
        String setFile1 = "p.set";
        String setFile2 = "r.set";
        String setFile3 = "x.set";
        boolean continueWork = true;
        HashSet<HashSet<Node>> res;
        BronKerbosh bronKerbosh = new BronKerbosh(graphFile, setFile1, setFile2, setFile3);
        if (continueWork) {
            bronKerbosh.withLoadGraph();
            res = bronKerbosh.continueFindMaxCliques();
        } else {
            bronKerbosh.withNewGraph(makeCodeGraph(base, 3));
            res = bronKerbosh.startFindMaxCliques();
        }

        int n = 0;
        for (HashSet<Node> clique : res) {
            System.out.println("Clique " + n++ + " :");
            for (Node node : clique) {
                System.out.println( "     Code : " + Arrays.toString(toBinary(node.getCode(), base)));
            }
        }

    }

}
