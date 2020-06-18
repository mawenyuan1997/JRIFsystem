package test.KatAutomata;

import KATautomata.KAT.*;
import KATautomata.SyKAT.SyKatExpr;
import KATautomata.utility.State;
import KATautomata.utility.StatePair;
import KATautomata.utility.Util;

import java.util.Arrays;
import java.util.HashSet;

class KATtest {

    @org.junit.jupiter.api.Test
    void testEquals() {
        String[] primTests = new String[]{"A", "B", "C"};
        String[] primActions = new String[]{"p1","p2","p3"};
        Util util = new Util(primTests, primActions);
        String[] action1 = new String[]{"p1"};
        String[] action2 = new String[]{"p1", "p2"};
        KatExpr pp = new ConcatExpr(new Action(Arrays.asList(action1)), new Action(Arrays.asList(action1)));
        SyKatExpr sy1 = util.translate(pp);
        SyKatExpr sy2 = util.translate(pp);
        assert sy1.equals(sy2);
        HashSet<SyKatExpr> s = new HashSet<>();
        s.add(sy1);
        assert s.contains(sy2);
        System.out.println(sy1.toString());
        HashSet<SyKatExpr> s1 = new HashSet<>();
        HashSet<SyKatExpr> s2 = new HashSet<>();
        HashSet<SyKatExpr> s3 = new HashSet<>();
        HashSet<SyKatExpr> s4 = new HashSet<>();
        HashSet<SyKatExpr> [] q = new HashSet[2];
        q[0]=s1;
        q[1]=s2;
        HashSet<SyKatExpr> [] w = new HashSet[2];
        w[0]=s3;
        w[1]=s4;
        assert !q.equals(w);
        assert (new StatePair(new State(s1), new State(s2))).equals(new StatePair(new State(s3), new State(s4)));
        System.out.println((new State(s1)));
    }
}