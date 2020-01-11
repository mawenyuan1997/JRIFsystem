package test;

import KAT.*;
import KAToperator.*;
import SyKAT.SyKATexpression;
import utility.State;
import utility.StatePair;
import utility.Util;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

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
        assert (new State(s1)).equals(new State(s2));
        assert (new StatePair(new State(s1), new State(s2))).equals(new StatePair(new State(s3), new State(s4)));
        System.out.println((new State(s1)));
    }
}