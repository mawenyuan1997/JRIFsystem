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
        SymDFA dfa = new SymDFA(util, sy);
        BDD<State> bdd = (BDD<State>) sy.accept(del);
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{false, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, false, false, true, false}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, true, false, false, true}));
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, true, false}));
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, false, false}));
        assert !dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, false, true}));
        assert !dfa.states.get(bdd.execute(new boolean[]{false, false, true, true, false, false}));
    }

    @org.junit.jupiter.api.Test
    void testCompareBasic() {
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
        KATexpression e1 = new StarExpression(new PlusExpression(new Action(action1), new Action(action2)));
        KATexpression e2 = new ConcatExpression(new StarExpression(new Action(action1)),
                new StarExpression(new ConcatExpression(new Action(action2), new StarExpression(new Action(action2)))));
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
        KATexpression pp = new ConcatExpression(new Action(action1), new Action(action1));
        KATexpression ppp = new ConcatExpression(new ConcatExpression(new Action(action1), new Action(action1)), new Action(action1));
        KATexpression e1 = new PlusExpression(new StarExpression(pp), new StarExpression(new Action(action1)));
        KATexpression e2 = new PlusExpression(new StarExpression(ppp), new StarExpression(new Action(action1)));
        SymDFA dfa1 = new SymDFA(util, util.translate(e1));
        SymDFA dfa2 = new SymDFA(util, util.translate(e2));
        assert dfa2.isSmallerThan(dfa1);
        assert dfa1.isSmallerThan(dfa2);
    }


}
