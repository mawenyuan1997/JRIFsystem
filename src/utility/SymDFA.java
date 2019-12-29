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
import java.util.List;

import static SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SymDFA {

    public Util util;
    public HashMap<HashSet<SyKATexpression>, BDD<HashSet<SyKATexpression>>> transition;
    public HashSet<State> states;
    State initial;
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);

    public SymDFA(Util u, SyKATexpression expr) {
        util = u;
        transition = new HashMap<>();
        states = new HashSet<>();
        HashSet<SyKATexpression> init = new HashSet<>();
        init.add(expr);
        if (expr.equals(trueBdd)) initial = new State(init, true);
        else initial = new State(init, false);
        states.add(initial);
        buildFrom(initial);
    }

    public void buildFrom(State currState) {
        if (transition.containsKey(currState.set) || currState.set.isEmpty()) return;
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
        for(SyKATexpression expr : currState.set) {
            BDD<HashSet<SyKATexpression>> bdd = (BDD<HashSet<SyKATexpression>>) expr.accept(del);
            if (total == null)
                total = bdd;
            else {
                total = new BDD(op, total, bdd);
            }
        }
        transition.put(currState.set, total);
        BDDTree<HashSet<SyKATexpression>> tree = total.getTree();
        for(Node<HashSet<SyKATexpression>> n : tree.nodes) {
            if (n.isTerminal()) {
                State s;
                if (n.terminalValue.contains(trueBdd)) s = new State(n.terminalValue, true);
                else s = new State(n.terminalValue, false);
                states.add(s);
                buildFrom(s);
            }
        }
    }

    public boolean isSmallerThan(SymDFA dfa) {
        BDDTree xTree = transition.get(initial.set).getTree();
        BDDTree yTree = transition.get(dfa.initial.set).getTree();

        return check(initial, dfa.initial);
    }

    private boolean check(State x, State y) {

        List<State[]> l =
    }

    private List<HashSet<SyKATexpression>[]> product(BDDTree xTree, BDDTree yTree, int xIndex, int yIndex) {
        Node x = xTree.getNode(xIndex);
        Node y = yTree.getNode(yIndex);
        int output;
        if (x.isTerminal() && y.isTerminal()) {
            if
        }

        else if (x.isTerminal())
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.low),applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.high), y.inputIndex));
        else if (y.isTerminal())
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, yIndex),applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, yIndex), x.inputIndex));
        else if (x.inputIndex == y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, y.low), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, y.high), x.inputIndex));
        else if (x.inputIndex < y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.low, yIndex), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, x.high, yIndex), x.inputIndex));
        else //(x.inputIndex > y.inputIndex)
            output = mk(new Node(applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.low), applyLoop(dynamicProgrammingMemory, op, xTree, yTree, xIndex, y.high), y.inputIndex));
        dynamicProgrammingMemory.put(key, output);
        return output;
    }
}

