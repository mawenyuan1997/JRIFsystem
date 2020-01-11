package utility;

import KAT.Action;
import KAT.ConcatExpression;
import KAT.KATexpression;
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
    public HashMap<State, BDD<State>> transition;
    public HashMap<State, Boolean> states;
    State initial;
    BDD<Boolean> trueBdd = singleBooleanBDD(true, 3);
    public SymDFA(Util u, SyKATexpression expr) {
        util = u;
        transition = new HashMap<>();
        states = new HashMap<>();
        HashSet<SyKATexpression> init = new HashSet<>();
        init.add(expr);
        initial = new State(init);
        if (expr.equals(trueBdd)) states.put(initial, true);
        else states.put(initial, false);
        buildFrom(initial);
    }

    private void buildFrom(State currState) {
        if (transition.containsKey(currState) || currState.isEmpty()) return;
        BDD<State> total = null;
        Operator<State> op = (x, y) -> x.merge(y);
        Delta del = new Delta(util.numOfTest, util.numOfAction);
        for(SyKATexpression expr : currState.getSet()) {
            BDD<State> bdd = (BDD<State>) expr.accept(del);
            if (total == null)
                total = bdd;
            else {
                total = new BDD(op, total, bdd);
            }
        }
        transition.put(currState, total);
        BDDTree<State> tree = total.getTree();
        for(Node<State> n : tree.nodes) {
            if (n.isTerminal()) {
                if (n.terminalValue.has(trueBdd)) states.put(n.terminalValue, true);
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
        HashSet<StatePair> r = new HashSet<>();
        Queue<StatePair> todo = new LinkedList<>();
        StatePair temp = new StatePair(initial, dfa.initial);
        todo.add(temp);
        while(!todo.isEmpty()) {
            StatePair cur = todo.poll();
            if (r.contains(cur)) continue;
            State s1 = cur.getFirst(), s2 = cur.getSecond();
            if (states.get(s1) && !dfa.states.get(s2)) return false;
            if (!s1.isEmpty() && !s2.isEmpty())
                addNext(todo, dfa, s1, s2, new boolean[util.numOfAction+util.numOfTest],0);
            r.add(cur);
        }
        return true;
//        HashMap<HashSet<SyKATexpression>[], Boolean> checked = new HashMap<>();
//        return check(initial, dfa.initial, dfa, checked);
    }

    private void addNext(Queue<StatePair> todo, SymDFA dfa, State x, State y
            , boolean[] input, int index) {
        if (index == util.numOfAction+util.numOfTest) {
            State xnext = transition.get(x).execute(input);
            State ynext = dfa.transition.get(y).execute(input);
            StatePair temp = new StatePair(xnext, ynext);
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

