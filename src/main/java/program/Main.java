package program;

import utils.Code;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        CodeResolver cr = new CodeResolver("code1");
        HashSet<Code> res = cr.findSavedCodes();
                 //cr.findNewCodes(8, 3);
        System.out.print(res.iterator().next());
        System.out.print("next");
        System.out.print(res.iterator().next());
    }

}
