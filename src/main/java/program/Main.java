package program;

import utils.Code;

import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        CodeResolver cr = new CodeResolver("code1");
        HashSet<Code> res = cr.loadAndContinueSearch();
                 //cr.findNewCodes(8, 3);
        Iterator resI = res.iterator();
        System.out.print(resI.next());
        System.out.print("next");
        System.out.print(resI.next());
    }

}
