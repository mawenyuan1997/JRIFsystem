package KATautomata.test;

import KAT.*;
import KAToperator.*;
import SyKAT.SyKATexpression;
import KATautomata.utility.State;
import KATautomata.utility.StatePair;
import KATautomata.utility.Util;

import java.util.HashSet;

class KATtest {

    @org.junit.jupiter.api.Test
    void testEquals() {
        String[] primTests = new String[]{"A", "B", "C"};
        String[] primActions = new String[]{"p1","p2","p3"};
        Util util = new Util(primTests, primActions);
        String[] action1 = new String[]{"p1"};
        String[] action2 = new String[]{"p1", "p2"};
        KATexpression pp = new ConcatExpression(new Action(action1), new Action(action1));
        SyKATexpression sy1 = util.translate(pp);
        SyKATexpression sy2 = util.translate(pp);
        assert sy1.equals(sy2);
        HashSet<SyKATexpression> s = new HashSet<>();
        s.add(sy1);
        assert s.contains(sy2);
        System.out.println(sy1.toString());
        HashSet<SyKATexpression> s1 = new HashSet<>();
        HashSet<SyKATexpression> s2 = new HashSet<>();
        HashSet<SyKATexpression> s3 = new HashSet<>();
        HashSet<SyKATexpression> s4 = new HashSet<>();
        HashSet<SyKATexpression> [] q = new HashSet[2];
        q[0]=s1;
        q[1]=s2;
        HashSet<SyKATexpression> [] w = new HashSet[2];
        w[0]=s3;
        w[1]=s4;
        assert !q.equals(w);
        assert (new StatePair(new State(s1), new State(s2))).equals(new StatePair(new State(s3), new State(s4)));
        System.out.println((new State(s1)));
    }
}