package test.KatAutomata;
import KATautomata.KAT.*;
import KATautomata.SyKAT.BDD.BDD;
import KATautomata.SyKAT.Concat;
import KATautomata.SyKAT.Star;
import KATautomata.SyKAT.SyKatExpr;
import KATautomata.SyKAToperator.Delta;
import KATautomata.SyKAToperator.Epsilon;
import KATautomata.utility.State;
import KATautomata.utility.Util;

import java.util.Arrays;
import java.util.HashMap;

import static KATautomata.SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SykatTest {
    HashMap<String, Boolean> atom = new HashMap<>();
    String[] primTests = new String[]{"A", "B", "C"};
    String[] primActions = new String[]{"p1","p2","p3"};
    Util util = new Util(primTests, primActions);
    boolean[] action1 = new boolean[]{true, false, false};
    boolean[] action2 = new boolean[]{true, true, false};
    KatExpr a1 = new Action(action1);
    KatExpr a2 = new Action(action2);
    KatExpr expr = new ConcatExpr(
            new ConcatExpr(
                    new PrimitiveTest("A"),
                    a1
            ),
            new ConcatExpr(
                    new PrimitiveTest("B"),
                    a2
            )
    );
    SyKatExpr sy = util.translate(expr);
    SyKatExpr testA = ((Concat)((Concat)sy).left).left;
    SyKatExpr action1expr = ((Concat)((Concat)sy).left).right;
    SyKatExpr action2expr = ((Concat)((Concat)sy).right).right;
    SyKatExpr ap1 = (Concat)((Concat)sy).left;
    SyKatExpr bp2 = (Concat)((Concat)sy).right;
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);

    @org.junit.jupiter.api.Test
    void testSyKatTranslate() {
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
    void testEpsilon() {
        Epsilon eps = new Epsilon(3);
        assert (testA instanceof BDD<?>);
        BDD<Boolean> epsA = (BDD<Boolean>) testA.accept(eps);
        assert (epsA.getNumInputs() == 3);
        assert (epsA.execute(new boolean[]{true, true, true}));
        assert (epsA.execute(new boolean[]{true, false, true}));
        assert (!epsA.execute(new boolean[]{false, true, false}));

        assert (action1expr instanceof BDD<?>);
        BDD<Boolean> epsa1 = (BDD<Boolean>) action1expr.accept(eps);
        assert (epsa1.getNumInputs() == 3);
        assert (!epsa1.execute(new boolean[]{true, false, true}));
        assert (!epsa1.execute(new boolean[]{true, true, true}));

        BDD<Boolean> epsap1 =  (BDD<Boolean>) ap1.accept(eps);
        assert (epsap1.getNumInputs() == 3);
        assert (!epsap1.execute(new boolean[]{true, false, true}));
        assert (!epsap1.execute(new boolean[]{false, false, true}));

        BDD<Boolean> epssy =  (BDD<Boolean>) sy.accept(eps);
        assert (epssy.getNumInputs() == 3);
        assert (!epssy.execute(new boolean[]{true, false, false}));
        assert (!epssy.execute(new boolean[]{false, true, true}));

        KatExpr b = new StarExpr(a1);
        SyKatExpr syb = util.translate(b);
        BDD<Boolean> s =  (BDD<Boolean>) syb.accept(eps);
        assert (s.execute(new boolean[]{true, false, false}));
        assert (s.execute(new boolean[]{false, true, true}));
    }

    @org.junit.jupiter.api.Test
    void testDeltaSimple() {
        Delta del = new Delta(3,3);

        KatExpr b = new PrimitiveTest("B");
        SyKatExpr syb = util.translate(b);
        assert syb instanceof BDD<?>;
        BDD<State> dsyb = (BDD<State>) syb.accept(del);
        assert (dsyb.getNumInputs() == 6);
        assert dsyb.execute(new boolean[]{true, true, false, true, true, false}).isEmpty();

        b = a1;
        syb = util.translate(b);
        assert syb instanceof BDD<?>;
        dsyb = (BDD<State>) syb.accept(del);
        assert (dsyb.getNumInputs() == 6);
        State res = dsyb.execute(new boolean[]{true, false, false, true, false, false});
        assert res.size() == 1;
        assert res.has(trueBdd);

        b = new ConcatExpr(new PrimitiveTest("A"), a1);
        syb = util.translate(b);
        dsyb = (BDD<State>) syb.accept(del);
        res = dsyb.execute(new boolean[]{true, false, false, true, false, false});
        assert res.size() == 1;
        assert res.has(trueBdd);
        res = dsyb.execute(new boolean[]{false, true, true, true, false, false});
        assert res.isEmpty();

        b = new PlusExpr(new ConcatExpr(new PrimitiveTest("A"), a1),
                               new ConcatExpr(new PrimitiveTest("B"), a2));
        syb = util.translate(b);
        dsyb = (BDD<State>) syb.accept(del);
        res = dsyb.execute(new boolean[]{true, false, true, true, false, false});
        assert res.size() == 1;
        assert res.has(trueBdd);
        dsyb = (BDD<State>) syb.accept(del);
        res = dsyb.execute(new boolean[]{true, true, false, true, true, false});
        assert res.size() == 1;
        assert res.has(trueBdd);

        b = new StarExpr(a1);
        syb = util.translate(b);
        assert syb instanceof Star;
        dsyb = (BDD<State>) syb.accept(del);
        res = dsyb.execute(new boolean[]{true, false, false, true, false, false});
        assert res.size() == 1;
        for(SyKatExpr e : res.getSet()) {
            assert e instanceof Concat;
            assert ((Concat) e).left instanceof BDD;
            assert ((Concat) e).right instanceof Star;
            assert ((Star)((Concat) e).right).p instanceof BDD;
            assert (Boolean)((BDD)((Star)((Concat) e).right).p).execute(new boolean[]{true, false, false});
        }
    }

    @org.junit.jupiter.api.Test
    void testDeltaComplex() {
        Delta del = new Delta(3,3);
        BDD<State> dsyb = (BDD<State>) sy.accept(del);
        State res = dsyb.execute(new boolean[]{true, false, false, true, false, false});
        assert res.size() == 1;
        assert res.has(new Concat(trueBdd,bp2));

        KatExpr b = new ConcatExpr(new NegateTest(new PrimitiveTest("A")),
                                               new ConcatExpr(a1,
                                                                    new PrimitiveTest("A")
                                                                   )
                                              )
                ;
        SyKatExpr syb = util.translate(b);
        dsyb = (BDD<State>) syb.accept(del);
        assert dsyb.execute(new boolean[]{true, true, false, true, true, false}).isEmpty();
        assert dsyb.execute(new boolean[]{false, true, false, true, true, false}).isEmpty();
        res = dsyb.execute(new boolean[]{false, false, false, true, false, false});
        assert res.size() == 1;
        for(SyKatExpr e : res.getSet()) {
            assert e instanceof Concat;
            assert ((Concat) e).left.equals(trueBdd);
            assert ((Concat) e).right instanceof BDD;
            assert ((BDD<Boolean>) ((Concat) e).right).execute(new boolean[]{true, false, false});
            assert !((BDD<Boolean>) ((Concat) e).right).execute(new boolean[]{false, false, false});
        }
    }
}
