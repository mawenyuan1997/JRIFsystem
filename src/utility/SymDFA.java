package utility;

import SyKAT.BDD.BDD;
import SyKAT.BDD.BDDTree;
import SyKAT.BDD.Node;
import SyKAT.BDD.Operator;
import SyKAT.SyKATexpression;
import SyKAToperator.Delta;
import SyKAToperator.Epsilon;

import java.util.HashMap;
import java.util.HashSet;

public class SymDFA {

    public Util util;
    public HashMap<HashSet<SyKATexpression>, BDD<HashSet<SyKATexpression>>> transionBDD;
    public SymDFA(Util u, SyKATexpression expr) {
        HashSet<SyKATexpression> initial = new HashSet<>();
        initial.add(expr);
        buildFrom(initial);
        util = u;
    }

    public void buildFrom(HashSet<SyKATexpression> currState) {
        if (transionBDD.containsKey(currState)) return;
        BDD<HashSet<SyKATexpression>> total = null;
        Operator<HashSet<SyKATexpression>> op = new Operator<HashSet<SyKATexpression>>() {
            @Override
            public HashSet<SyKATexpression> operate(HashSet<SyKATexpression> x, HashSet<SyKATexpression> y) {
                HashSet<SyKATexpression> temp = new HashSet<>(x);
                temp.addAll(y);
                return temp;
            }
        };
        Delta del = new Delta(util.numOfTest, util.numOfAction);
        for(SyKATexpression expr : currState) {
            BDD<HashSet<SyKATexpression>> bdd = (BDD<HashSet<SyKATexpression>>) expr.accept(del);
            if (total == null)
                total = bdd;
            else {
                total = new BDD(op, total, bdd);
            }
        }
        transionBDD.put(currState, total);
        BDDTree<HashSet<SyKATexpression>> tree = total.getTree();
        for(Node<HashSet<SyKATexpression>> n : tree.nodes) {
            if (n.isTerminal()) {
                buildFrom(n.terminalValue);
            }
        }
    }
}
