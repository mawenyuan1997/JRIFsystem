package test;

import KAT.*;
import SyKAT.BDD.BDD;
import SyKAT.SyKATexpression;
import SyKAToperator.Delta;
import utility.SymDFA;
import utility.Util;

import java.util.HashMap;
import java.util.HashSet;

import static SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SymDFAtest {
    HashMap<String, Boolean> atom = new HashMap<>();
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
        BDD<HashSet<SyKATexpression>> bdd = (BDD<HashSet<SyKATexpression>>) sy.accept(del);
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{false, true, false, false, true, true}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, false, false, true, false}));
        assert dfa.states.containsKey(bdd.execute(new boolean[]{true, false, true, false, false, true}));
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, true, false}));
        assert dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, false, false}));
        assert !dfa.states.get(bdd.execute(new boolean[]{true, false, true, true, false, true}));
        assert !dfa.states.get(bdd.execute(new boolean[]{false, false, true, true, false, false}));
    }
}
