package test;

import KAT.*;
import SyKAT.BDD.BDD;
import SyKAT.SyKATexpression;
import SyKAT.Plus;
import SyKAToperator.Delta;
import utility.State;
import utility.SymDFA;
import utility.Util;

import java.util.HashMap;
import java.util.HashSet;

import static SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SymDFAtest {
    String[] primTests = new String[]{"A", "B", "C"};
    String[] primActions = new String[]{"p1","p2","p3"};
    Util util = new Util(primTests, primActions);
    String[] action1 = new String[]{"p1"};
    String[] action2 = new String[]{"p1", "p2"};
    KATexpression expr = new PlusExpression(
            new ConcatExpression(
                    new PrimitiveTest("A"),
                    new Action(action1)
            ),
            new Action(action2)
    );
    SyKATexpression sy = util.translate(expr);
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);
    Delta del = new Delta(3,3);

    @org.junit.jupiter.api.Test
    void testBuild() {
        // sy = ap+q,  bdd = D(sy)
        SymDFA dfa = new SymDFA(util, sy);
        BDD<State> bdd = (BDD<State>) sy.accept(del);
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{false, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, false, false, true, false}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, true, false, false, true}));
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, true, false})).equals(trueBdd);
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, false, false})).equals(trueBdd);
        assert bdd.execute(new boolean[]{true, false, true, true, false, true}).isEmpty();
        assert bdd.execute(new boolean[]{false, false, true, true, false, false}).isEmpty();
    }

    @org.junit.jupiter.api.Test
    void testCompareBasic() {
        // ap+q = ap+q, q < ap+q, ap < ap+q
        SymDFA dfa = new SymDFA(util, sy);
        assert dfa.isSmallerThan(dfa);
        SymDFA dfa2 = new SymDFA(util, ((Plus) sy).right);
        assert dfa2.isSmallerThan(dfa);
        assert !dfa.isSmallerThan(dfa2);
        SymDFA dfa3 = new SymDFA(util, ((Plus) sy).left);
        assert dfa3.isSmallerThan(dfa);
        assert !dfa.isSmallerThan(dfa3);
        assert !dfa2.isSmallerThan(dfa3);
        assert !dfa3.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare1() {
        // (p+q)* = p*(qp*)* = ((1+p)(1+q))*
        KATexpression e1 = new StarExpression(new PlusExpression(new Action(action1), new Action(action2)));
        KATexpression e2 = new ConcatExpression(new StarExpression(new Action(action1)),
                new StarExpression(new ConcatExpression(new Action(action2), new StarExpression(new Action(action1)))));
        KATexpression e3 = new StarExpression(new ConcatExpression(new PlusExpression(new OneTest(), new Action(action1)),
                new PlusExpression(new OneTest(), new Action(action2))));
        SymDFA dfa1 = new SymDFA(util, util.translate(e1));
        SymDFA dfa2 = new SymDFA(util, util.translate(e2));
        SymDFA dfa3 = new SymDFA(util, util.translate(e3));
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa3);
        assert dfa3.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare2() {
        // (pp)*(ppp)* = (ppp)*(pp)*
        KATexpression pp = new ConcatExpression(new Action(action1), new Action(action1));
        KATexpression ppp = new ConcatExpression(new ConcatExpression(new Action(action1), new Action(action1)), new Action(action1));
        KATexpression e1 = new ConcatExpression(new StarExpression(pp), new StarExpression(ppp));
        KATexpression e2 = new ConcatExpression(new StarExpression(ppp), new StarExpression(pp));
        SymDFA dfa1 = new SymDFA(util, util.translate(e1));
        SymDFA dfa2 = new SymDFA(util, util.translate(e2));
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare3() {
        //  (pp)*+p* = (ppp)*+p*
        KATexpression pp = new ConcatExpression(new Action(action1), new Action(action1));
        KATexpression ppp = new ConcatExpression(new ConcatExpression(new Action(action1), new Action(action1)), new Action(action1));
        KATexpression e1 = new PlusExpression(new StarExpression(pp), new StarExpression(new Action(action1)));
        KATexpression e2 = new PlusExpression(new StarExpression(ppp), new StarExpression(new Action(action1)));
        SymDFA dfa1 = new SymDFA(util, util.translate(e1));
        SymDFA dfa2 = new SymDFA(util, util.translate(e2));
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
    }

    @org.junit.jupiter.api.Test
    void testCompare4() {
        // ap!b+(b!aq)* < (b!aq+ap!b)*
        KATexpression p = new Action(action1), q = new Action(action2);
        TestExpression a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        KATexpression e1 = new ConcatExpression(a, new ConcatExpression(p, new NegateTest(b)));
        KATexpression e2 = new ConcatExpression(b, new ConcatExpression(new NegateTest(a), q));
        KATexpression l = new PlusExpression(e1, new StarExpression(e2));
        KATexpression r = new StarExpression(new PlusExpression(e1, e2));
        SymDFA dfa1 = new SymDFA(util, util.translate(l));
        SymDFA dfa2 = new SymDFA(util, util.translate(r));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare5() {
        // (ar!a)* = 1+ar!a
        KATexpression r = new Action(action1);
        TestExpression a = new PrimitiveTest("A");
        KATexpression ara = new ConcatExpression(a, new ConcatExpression(r, new NegateTest(a)));
        KATexpression e1 = new StarExpression(ara);
        KATexpression e2 = new PlusExpression(new OneTest(), ara);
        SymDFA dfa1 = new SymDFA(util, util.translate(e1));
        SymDFA dfa2 = new SymDFA(util, util.translate(e2));
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare6() {
        // (ap)*!a(ap)*!a = (ap)*!a
        KATexpression a = new PrimitiveTest("A"), na = new NegateTest((TestExpression) a);
        KATexpression p = new Action(action1);
        KATexpression ap = new StarExpression(new ConcatExpression(a, p));
        KATexpression l = new ConcatExpression(ap, new ConcatExpression(na, new ConcatExpression(ap, na)));
        KATexpression r = new ConcatExpression(ap, na);
        SymDFA dfa1 = new SymDFA(util, util.translate(l));
        SymDFA dfa2 = new SymDFA(util, util.translate(r));
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare7() {
        // (pq)* < (p+q)*
        KATexpression p = new Action(action1), q = new Action(action2);
        KATexpression l = new StarExpression(new ConcatExpression(p, q));
        KATexpression r = new StarExpression(new PlusExpression(p, q));
        SymDFA dfa1 = new SymDFA(util, util.translate(l));
        SymDFA dfa2 = new SymDFA(util, util.translate(r));
        assert  dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare8() {
        // a(bpq+!bp)+!a(bpq+!bp) = b(cpq+!cpq)+!bp
        KATexpression p = new Action(action1), q = new Action(action2);
        TestExpression a = new PrimitiveTest("A"), b = new PrimitiveTest("B"), c = new PrimitiveTest("C");
        KATexpression bpq = new ConcatExpression(b, new ConcatExpression(p, q));
        KATexpression cpq = new ConcatExpression(c, new ConcatExpression(p, q));
        KATexpression nbp = new ConcatExpression(new NegateTest(b), p);
        KATexpression l = new PlusExpression(new ConcatExpression(a, new PlusExpression(bpq, nbp)),
                new ConcatExpression(new NegateTest(a), new PlusExpression(bpq, nbp)));
        KATexpression ncpq = new ConcatExpression(new NegateTest(c), new ConcatExpression(p, q));
        KATexpression r = new PlusExpression(new ConcatExpression(b, new PlusExpression(cpq, ncpq)), nbp);
        SymDFA dfa1 = new SymDFA(util, util.translate(l));
        SymDFA dfa2 = new SymDFA(util, util.translate(r));
        assert dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare9() {
        // p ≠ q, a < a+b, ab < a
        KATexpression p = new Action(action1), q = new Action(action2);
        SymDFA dfa1 = new SymDFA(util, util.translate(p));
        SymDFA dfa2 = new SymDFA(util, util.translate(q));
        assert !dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        TestExpression a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        dfa1 = new SymDFA(util, util.translate(a));
        dfa2 = new SymDFA(util, util.translate(new PlusTest(a, b)));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, util.translate(a));
        dfa2 = new SymDFA(util, util.translate(new ConcatTest(a, b)));
        assert !dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
    }

    @org.junit.jupiter.api.Test
    void testCompare10() {
        // a+b < 1, ab > 0, 0 < 1
        TestExpression a = new PrimitiveTest("A"), b = new PrimitiveTest("B");
        SymDFA dfa1 = new SymDFA(util, util.translate(new OneTest()));
        SymDFA dfa2 = new SymDFA(util, util.translate(new PlusTest(a, b)));
        assert !dfa1.isSmallerThan(dfa2);
        assert dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, util.translate(new ZeroTest()));
        dfa2 = new SymDFA(util, util.translate(new ConcatTest(a, b)));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
        dfa1 = new SymDFA(util, util.translate(new ZeroTest()));
        dfa2 = new SymDFA(util, util.translate(new OneTest()));
        assert dfa1.isSmallerThan(dfa2);
        assert !dfa2.isSmallerThan(dfa1);
    }
}
