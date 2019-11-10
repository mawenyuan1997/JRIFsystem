package test;
import KAT.Action;
import KAT.ConcatExpression;
import KAT.KATexpression;
import KAT.PrimitiveTest;
import SyKAT.BDD.BDD;
import SyKAT.Concat;
import SyKAT.SyKATexpression;
import SyKAToperator.Delta;
import utility.Util;

import java.util.HashMap;
import java.util.HashSet;

public class SykatUtilTest {
    HashMap<String, Boolean> atom = new HashMap<>();
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

    @org.junit.jupiter.api.Test
    void testE() {
        SyKATexpression testA = ((Concat)((Concat)sy).left).left;
        SyKATexpression action1expr = ((Concat)((Concat)sy).left).right;
        SyKATexpression action2expr = ((Concat)((Concat)sy).right).right;
        assert (testA instanceof BDD<?>);
        BDD<Boolean> bddA = (BDD<Boolean>) testA;
        assert (bddA.getNumInputs() == 3);
        assert (bddA.execute(new boolean[]{true, true, true}));
        assert (!bddA.execute(new boolean[]{false, true, false}));

        assert (action1expr instanceof BDD<?>);
        BDD<Boolean> bdda1 = (BDD<Boolean>) action1expr;
        assert (bdda1.getNumInputs() == 3);
        assert (!bdda1.execute(new boolean[]{true, true, true}));
        assert (!bdda1.execute(new boolean[]{false, true, false}));
        assert (bdda1.execute(new boolean[]{true, false, false}));

        assert (action2expr instanceof BDD<?>);
        BDD<Boolean> bdda2 = (BDD<Boolean>) action2expr;
        assert (bdda2.getNumInputs() == 3);
        assert (!bdda2.execute(new boolean[]{true, false, true}));
        assert (!bdda2.execute(new boolean[]{true, true, true}));
        assert (bdda2.execute(new boolean[]{true, true, false}));
    }

    @org.junit.jupiter.api.Test
    void testDelta() {
        Delta del = new Delta();
//        BDD<HashSet<SyKATexpression>> dsy = (BDD<HashSet<SyKATexpression>>) sy.accept(del);
//        assert (dsy.getNumInputs() == 6);
//        KATexpression b2 = new ConcatExpression(
//                        new PrimitiveTest("B"),
//                        new Action(action2)
//                );
//        SyKATexpression syb2 = util.translate(expr);
//        boolean[] test = new boolean[]{true, false, false, true, false, false};
//        dsy.execute(test);
    }
}
