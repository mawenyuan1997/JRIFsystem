package test;
import KAT.Action;
import KAT.ConcatExpression;
import KAT.KATexpression;
import KAT.PrimitiveTest;
import SyKAT.BDD.BDD;
import SyKAT.Concat;
import SyKAT.SyKATexpression;
import utility.Util;

import java.util.HashMap;
public class SykatUtilTest {
    HashMap<String, Boolean> atom = new HashMap<>();

    @org.junit.jupiter.api.Test
    void testE() {
        String[] primTests = new String[]{"A", "B", "C"};
        String[] primActions = new String[]{"p1","p2","p3"};
        Util util = new Util(primTests, primActions);
        String[] action1 = new String[]{"p1"};
        String[] action2 = new String[]{"p1", "p2"};
        KATexpression expr = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new Action(action1)
                ),
                new ConcatExpression(
                        new PrimitiveTest("B"),
                        new Action(action2)
                )
        );
        SyKATexpression sy = util.translate(expr);
        SyKATexpression testA = ((Concat)((Concat)sy).left).left;
        SyKATexpression action1expr = ((Concat)((Concat)sy).left).right;
        SyKATexpression action2expr = ((Concat)((Concat)sy).right).right;
        assert (testA instanceof BDD);
        assert (action1expr instanceof BDD);
        assert (action2expr instanceof BDD);
    }
}
