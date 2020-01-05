package utility;

import SyKAT.BDD.BDD;
import SyKAT.BDD.BDDTree;
import SyKAT.BDD.Node;
import SyKAT.BDD.Operator;
import SyKAT.SyKATexpression;
import SyKAToperator.Delta;
import SyKAToperator.Epsilon;

import java.util.*;

import static SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class SymDFA {

    public Util util;
    public HashMap<HashSet<SyKATexpression>, BDD<HashSet<SyKATexpression>>> transition;
    public HashMap<HashSet<SyKATexpression>, Boolean> states;
    HashSet<SyKATexpression> initial;
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);
    public SymDFA(Util u, SyKATexpression expr) {
        util = u;
        transition = new HashMap<>();
        states = new HashMap<>();
        initial = new HashSet<>();
        initial.add(expr);
        if (expr.equals(trueBdd)) states.put(initial, true);
        else states.put(initial, false);
        buildFrom(initial);
    }

    private void buildFrom(HashSet<SyKATexpression> currState) {
        if (transition.containsKey(currState) || currState.isEmpty()) return;
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
        transition.put(currState, total);
        BDDTree<HashSet<SyKATexpression>> tree = total.getTree();
        for(Node<HashSet<SyKATexpression>> n : tree.nodes) {
            if (n.isTerminal()) {
                if (n.terminalValue.contains(trueBdd)) states.put(n.terminalValue, true);
                else states.put(n.terminalValue, false);
                buildFrom(n.terminalValue);
            }
        }
    }

    /**
     * check if L(this) ≤ L(dfa)
     * @param dfa
     * @return
     */
    public boolean isSmallerThan(SymDFA dfa) {
//        HashSet<HashSet<SyKATexpression>[]> r = new HashSet<>();
//        Queue<HashSet<SyKATexpression>[]> todo = new LinkedList<>();
//        HashSet<SyKATexpression>[] temp = new HashSet[2];
//        temp[0] = initial;
//        temp[1] = dfa.initial;
//        todo.add(temp);
//        while(!todo.isEmpty()) {
//            System.out.println(r.size());
//            HashSet<SyKATexpression>[] cur = todo.poll();
//            if (r.contains(cur)) continue;
//            if (states.get(cur[0]) && !dfa.states.get(cur[1])) return false;
//            if (!cur[0].isEmpty() && !cur[1].isEmpty())
//                addNext(todo, dfa, cur[0], cur[1], new boolean[util.numOfAction+util.numOfTest],0);
//            r.add(cur);
//        }
//        return true;
        HashMap<HashSet<SyKATexpression>[], Boolean> checked = new HashMap<>();
        return check(initial, dfa.initial, dfa, checked);
    }

    private void addNext(Queue<HashSet<SyKATexpression>[]> todo, SymDFA dfa, HashSet<SyKATexpression> x, HashSet<SyKATexpression> y
            , boolean[] input, int index) {
        if (index == util.numOfAction+util.numOfTest) {
            HashSet<SyKATexpression> xnext = transition.get(x).execute(input);
            HashSet<SyKATexpression> ynext = dfa.transition.get(y).execute(input);
            HashSet<SyKATexpression>[] temp = new HashSet[2];
            temp[0] = xnext;
            temp[1] = ynext;
            todo.add(temp);
        } else {
            input[index] = true;
            addNext(todo, dfa, x, y, input,index+1);
            input[index] = false;
            addNext(todo, dfa, x, y, input,index+1);
        }
    }



    private boolean check(HashSet<SyKATexpression> x, HashSet<SyKATexpression> y, SymDFA dfa, HashMap<HashSet<SyKATexpression>[], Boolean> checked) {
        BDDTree xTree = transition.get(x).getTree();
        BDDTree yTree = dfa.transition.get(y).getTree();
        HashSet<SyKATexpression>[] key = new HashSet[] {x, y};
        if (checked.containsKey(key)) return checked.get(key);
        HashSet<HashSet<SyKATexpression>[]> next = new HashSet<>();
        if (!product(xTree, yTree, xTree.getRootIndex(), yTree.getRootIndex(), next, dfa)) {
            checked.put(key, false);
            return false;
        }

        else {
            for(HashSet<SyKATexpression>[] pair : next) {
                if (!check(pair[0], pair[1], dfa, checked)) return false;
            }
            checked.put(key, true);
            return true;
        }
    }

    private boolean product(BDDTree xTree, BDDTree yTree, int xIndex, int yIndex, HashSet<HashSet<SyKATexpression>[]> next, SymDFA dfa) {
        Node x = xTree.getNode(xIndex);
        Node y = yTree.getNode(yIndex);
        int output;
        if (x.isTerminal() && y.isTerminal()) {
            HashSet<SyKATexpression>[] pair = new HashSet[2];
            pair[0] = (HashSet<SyKATexpression>) x.terminalValue;
            pair[1] = (HashSet<SyKATexpression>) y.terminalValue;
            if (!pair[0].isEmpty() && !pair[1].isEmpty())
                next.add(pair);
            if (states.get(x.terminalValue) && !dfa.states.get(y.terminalValue))
                return false;
            return true;
        } else if (x.isTerminal())
            return product(xTree, yTree, xIndex, y.low, next, dfa) && product(xTree, yTree, xIndex, y.high, next, dfa);
        else if (y.isTerminal())
            return product(xTree, yTree, x.low, yIndex, next, dfa) && product(xTree, yTree, x.high, yIndex, next, dfa);
        else if (x.inputIndex == y.inputIndex)
            return product(xTree, yTree, x.low, y.low, next, dfa) && product(xTree, yTree, x.high, y.high, next, dfa);
        else if (x.inputIndex < y.inputIndex)
            return product(xTree, yTree, x.low, yIndex, next, dfa) && product(xTree, yTree, x.high, yIndex, next, dfa);
        else //(x.inputIndex > y.inputIndex)
            return product(xTree, yTree, xIndex, y.low, next, dfa) && product(xTree, yTree, xIndex, y.high, next, dfa);
    }
}

