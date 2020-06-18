package test.KatAutomata;

import KATautomata.KAT.*;
import KATautomata.SyKAT.BDD.BDD;
import KATautomata.SyKAT.SyKatExpr;
import KATautomata.SyKAT.Plus;
import KATautomata.SyKAToperator.Delta;
import KATautomata.utility.State;
import KATautomata.utility.SymDFA;
import KATautomata.utility.Util;

import java.util.Arrays;

import static KATautomata.SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SymDFAtest {
    String[] primTests = new String[]{"A", "B", "C"};
    String[] primActions = new String[]{"p1","p2","p3"};
    Util util = new Util(primTests, primActions);
    String[] action1 = new String[]{"p1"};
    String[] action2 = new String[]{"p1", "p2"};
    KatExpr a1 = KatFactory.Action(Arrays.asList(action1));
    KatExpr a2 = KatFactory.Action(Arrays.asList(action2));
    KatExpr expr = KatFactory.PlusExpr(
            KatFactory.ConcatExpr(
                    new PrimitiveTest("A"),
                    KatFactory.Action(Arrays.asList(action1))
            ),
            new Action(Arrays.asList(action2))
    );
    SyKatExpr sy = util.translate(expr);
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);
    Delta del = new Delta(3,3);

    @org.junit.jupiter.api.Test
    void testBuild() {
        // sy = ap+q,  bdd = D(sy)
        SymDFA dfa = new SymDFA(util, expr);
        BDD<State> bdd = (BDD<State>) sy.accept(del);
        assert dfa.getStates().containsKey(bdd.execute(new boolean[]{true, true, false, false, true, true}));
        assert dfa.getStates().containsKey(bdd.execute(new boolean[]{false, true, false, false, true, true}));
        assert dfa.getStates().containsKey(bdd.execute(new boolean[]{true, false, false, false, true, false}));
        assert dfa.getStates().containsKey(bdd.execute(new boolean[]{true, false, true, false, false, true}));
        assert dfa.getStates().get(bdd.execute(new boolean[]{true, false, true, true, true, false})).equals(trueBdd);
        assert dfa.getStates().get(bdd.execute(new boolean[]{true, false, true, true, false, false})).equals(trueBdd);
        assert bdd.execute(new boolean[]{true, false, true, true, false, true}).isEmpty();
        assert bdd.execute(new boolean[]{false, false, true, true, false, false}).isEmpty();
    }

    @org.junit.jupiter.api.Test
    void testCompareBasic() {
        // ap+q = ap+q, q < ap+q, ap < ap+q
        SymDFA dfa = new SymDFA(util, expr);
        assert dfa.isSmallerThan(dfa);
        SymDFA dfa2 = new SymDFA(util, ((PlusExpr) expr).right);
        assert dfa2.isSmallerThan(dfa);
        assert !dfa.isSmallerThan(dfa2);
        SymDFA dfa3 = new SymDFA(util, ((PlusExpr) expr).left);
        assert dfa3.isSmallerThan(dfa);
        assert !dfa.isSmallerThan(dfa3);
        assert !dfa2.isSmallerThan(dfa3);
        assert !dfa3.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare1() {
        // (p+q)* = p*(qp*)* = ((1+p)(1+q))*
        KatExpr e1 = KatFactory.StarExpr(KatFactory.PlusExpr(a1, a2));
        KatExpr e2 = KatFactory.ConcatExpr(KatFactory.StarExpr(a1),
                KatFactory.StarExpr(KatFactory.ConcatExpr(a2, KatFactory.StarExpr(a1))));
        KatExpr e3 = KatFactory.StarExpr(KatFactory.ConcatExpr(KatFactory.PlusExpr(KatFactory.OneTest(), a1),
                KatFactory.PlusExpr(KatFactory.OneTest(), a2)));
        SymDFA dfa1 = new SymDFA(util, e1);
        SymDFA dfa2 = new SymDFA(util, e2);
        SymDFA dfa3 = new SymDFA(util, e3);
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa3);
        assert dfa3.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare2() {
        // (pp)*(ppp)* = (ppp)*(pp)*
        KatExpr pp = new ConcatExpr(a1, a1);
        KatExpr ppp = new ConcatExpr(new ConcatExpr(a1, a1), a1);
        KatExpr e1 = new ConcatExpr(new StarExpr(pp), new StarExpr(ppp));
        KatExpr e2 = new ConcatExpr(new StarExpr(ppp), new StarExpr(pp));
        SymDFA dfa1 = new SymDFA(util, e1);
        SymDFA dfa2 = new SymDFA(util, e2);
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare3() {
        //  (pp)*+p* = (ppp)*+p*
        KatExpr pp = new ConcatExpr(a1, a1);
        KatExpr ppp = new ConcatExpr(new ConcatExpr(a1, a1), a1);
        KatExpr e1 = new PlusExpr(new StarExpr(pp), new StarExpr(a1));
        KatExpr e2 = new PlusExpr(new StarExpr(ppp), new StarExpr(a1));
        SymDFA dfa1 = new SymDFA(util, e1);
        SymDFA dfa2 = new SymDFA(util, e2);
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare4() {
        // ap!b+(b!aq)* < (b!aq+ap!b)*
        KatExpr p = a1, q = a2;
        TestExpr a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        KatExpr e1 = new ConcatExpr(a, new ConcatExpr(p, new NegateTest(b)));
        KatExpr e2 = new ConcatExpr(b, new ConcatExpr(new NegateTest(a), q));
        KatExpr l = new PlusExpr(e1, new StarExpr(e2));
        KatExpr r = new StarExpr(new PlusExpr(e1, e2));
        SymDFA dfa1 = new SymDFA(util, l);
        SymDFA dfa2 = new SymDFA(util, r);
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare5() {
        // (ar!a)* = 1+ar!a
        KatExpr r = a1;
        TestExpr a = new PrimitiveTest("A");
        KatExpr ara = new ConcatExpr(a, new ConcatExpr(r, new NegateTest(a)));
        KatExpr e1 = new StarExpr(ara);
        KatExpr e2 = new PlusExpr(new OneTest(), ara);
        SymDFA dfa1 = new SymDFA(util, e1);
        SymDFA dfa2 = new SymDFA(util, e2);
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare6() {
        // (ap)*!a(ap)*!a = (ap)*!a
        KatExpr a = new PrimitiveTest("A"), na = new NegateTest((TestExpr) a);
        KatExpr p = a1;
        KatExpr ap = new StarExpr(new ConcatExpr(a, p));
        KatExpr l = new ConcatExpr(ap, new ConcatExpr(na, new ConcatExpr(ap, na)));
        KatExpr r = new ConcatExpr(ap, na);
        SymDFA dfa1 = new SymDFA(util, l);
        SymDFA dfa2 = new SymDFA(util, r);
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare7() {
        // (pq)* < (p+q)*
        KatExpr p = a1, q = a2;
        KatExpr l = new StarExpr(new ConcatExpr(p, q));
        KatExpr r = new StarExpr(new PlusExpr(p, q));
        SymDFA dfa1 = new SymDFA(util, l);
        SymDFA dfa2 = new SymDFA(util, r);
        assert  dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare8() {
        // a(bpq+!bp)+!a(bpq+!bp) = b(cpq+!cpq)+!bp
        KatExpr p = a1, q = a2;
        TestExpr a = new PrimitiveTest("A"), b = new PrimitiveTest("B"), c = new PrimitiveTest("C");
        KatExpr bpq = new ConcatExpr(b, new ConcatExpr(p, q));
        KatExpr cpq = new ConcatExpr(c, new ConcatExpr(p, q));
        KatExpr nbp = new ConcatExpr(new NegateTest(b), p);
        KatExpr l = new PlusExpr(new ConcatExpr(a, new PlusExpr(bpq, nbp)),
                new ConcatExpr(new NegateTest(a), new PlusExpr(bpq, nbp)));
        KatExpr ncpq = new ConcatExpr(new NegateTest(c), new ConcatExpr(p, q));
        KatExpr r = new PlusExpr(new ConcatExpr(b, new PlusExpr(cpq, ncpq)), nbp);
        SymDFA dfa1 = new SymDFA(util, l);
        SymDFA dfa2 = new SymDFA(util, r);
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare9() {
        // p ≠ q, a < a+b, ab < a
        KatExpr p = a1, q = a2;
        SymDFA dfa1 = new SymDFA(util, p);
        SymDFA dfa2 = new SymDFA(util, q);
        assert !dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        TestExpr a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        dfa1 = new SymDFA(util, a);
        dfa2 = new SymDFA(util, new PlusTest(a, b));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, a);
        dfa2 = new SymDFA(util, new ConcatTest(a, b));
        assert !dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare10() {
        // a+b < 1, ab > 0, 0 < 1
        TestExpr a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        SymDFA dfa1 = new SymDFA(util, KatFactory.OneTest());
        SymDFA dfa2 = new SymDFA(util, KatFactory.PlusTest(a, b));
        assert !dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, KatFactory.ZeroTest());
        dfa2 = new SymDFA(util, KatFactory.ConcatTest(a, b));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, KatFactory.ZeroTest());
        dfa2 = new SymDFA(util, KatFactory.OneTest());
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }
}
