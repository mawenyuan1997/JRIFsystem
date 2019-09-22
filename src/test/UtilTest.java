import KAT.ConcatExpression;
import KAT.KATexpression;
import KAT.PrimitiveAction;
import KAT.PrimitiveTest;
import SyKAT.BDD.BDD;
import SyKAT.Concat;
import SyKAT.SyKATexpression;
import utility.Util;

import java.util.HashMap;

class Utiltest {

    HashMap<String, Boolean> atom = new HashMap<>();

    @org.junit.jupiter.api.Test
    void testE() {
        KATexpression expr = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A",0),
                        new PrimitiveAction("p", 0)
                ),
                new PrimitiveTest("B",1)
        );
        SyKATexpression sy = Util.translate(2, expr);
        assert (((Concat)sy).right instanceof BDD);
        System.out.println(((BDD) ((Concat)sy).right).printTruthTable());
        SyKATexpression l = ((Concat)sy).left;
        assert (l instanceof Concat);
        SyKATexpression ll = ((Concat)l).left;
        assert (ll instanceof BDD);
        System.out.println(((BDD) ll).printTruthTable());
    }
}
