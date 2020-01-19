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
    public HashMap<State, BDD<State>> transition; // map a state to its delta tree
    public HashMap<State, BDD<Boolean>> states;   // map a state to its epsilon bdd tree
    State initial;
    public SymDFA(Util u, SyKATexpression expr) {
        util = u;
        transition = new HashMap<>();
        states = new HashMap<>();
        initial = new State(expr);
        addState(initial);
        buildFrom(initial);
    }

    /**
     * add a new state s to states
     * @param s
     */
    private void addState(State s) {
        Epsilon eps = new Epsilon(util.numOfTest);
        BDD<Boolean> total = null;
        Operator<Boolean> op = (x, y) -> x || y;
        for(SyKATexpression expr : s.getSet()) {
            BDD<Boolean> bdd = (BDD<Boolean>) expr.accept(eps);
            if (total == null)
                total = bdd;
            else {
                total = new BDD(op, total, bdd);
            }
        }
        states.put(s, total);
    }

    /**
     * build transitions from currState and all states that can be arrived
     * @param currState
     */
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
                addState(n.terminalValue);
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
        // simple algorithm
//        HashSet<StatePair> r = new HashSet<>();
//        Queue<StatePair> todo = new LinkedList<>();
//        StatePair temp = new StatePair(initial, dfa.initial);
//        todo.add(temp);
//        while(!todo.isEmpty()) {
//            StatePair cur = todo.poll();
//            if (r.contains(cur)) continue;
//            System.out.println(cur);
//            State s1 = cur.getFirst(), s2 = cur.getSecond();
//            if (!check(cur, dfa)) return false;
//            addNext(todo, dfa, s1, s2, new boolean[util.numOfAction+util.numOfTest],0);
//            r.add(cur);
//        }
//        return true;

        // bdd algorithm
        HashSet<StatePair> r = new HashSet<>();
        Queue<StatePair> todo = new LinkedList<>();
        StatePair temp = new StatePair(initial, dfa.initial);
        todo.add(temp);
        while(!todo.isEmpty()) {
            StatePair cur = todo.poll();
            if (r.contains(cur)) continue;
            State s1 = cur.getFirst(), s2 = cur.getSecond();
            if (!check(cur, dfa)) return false;
            if (!s1.isEmpty() || !s2.isEmpty()) {
                BDDTree xTree = null, yTree = null;
                if (!s1.isEmpty()) xTree = transition.get(s1).getTree();
                if (!s2.isEmpty()) yTree = dfa.transition.get(s2).getTree();

                if (s1.isEmpty()) product(xTree, yTree, 0, yTree.getRootIndex(), todo);
                else if (s2.isEmpty()) product(xTree, yTree, xTree.getRootIndex(), 0, todo);
                else product(xTree, yTree, xTree.getRootIndex(), yTree.getRootIndex(), todo);
            }
            r.add(cur);
        }
        return true;
    }

    /**
     * check if state pair sp satisfy eps_a(sp.getFirst()) => eps_a(sp.getSecond())
     * @param sp
     * @param dfa dfa of second state
     * @return
     */
    private boolean check(StatePair sp, SymDFA dfa) {
        if (sp.getFirst().isEmpty()) return true;
        if (sp.getSecond().isEmpty()) {
            BDD<Boolean> b1 = states.get(sp.getFirst());
            BDDTree<Boolean> b1t = b1.getTree();
            for(Node<Boolean> n : b1t.nodes) {
                if (n.isTerminal()) {
                    if (n.terminalValue) return false;
                }
            }
            return true;
        }
        BDD<Boolean> b1 = states.get(sp.getFirst());
        BDD<Boolean> b2 = dfa.states.get(sp.getSecond());
        Operator<Boolean> op = (x, y) -> (x && !y);
        BDDTree<Boolean> b3t = new BDD(op, b1, b2).getTree();
        for(Node<Boolean> n : b3t.nodes) {
            if (n.isTerminal()) {
                if (n.terminalValue) return false;
            }
        }
        return true;
    }

    /**
     * add next state pair to the queue (simple method)
     * @param todo the queue
     * @param dfa dfa of second y
     * @param x
     * @param y
     * @param input
     * @param index
     */
    private void addNext(Queue<StatePair> todo, SymDFA dfa, State x, State y
            , boolean[] input, int index) {
        if (index == util.numOfAction+util.numOfTest) {
            State xnext, ynext;
            if (x.isEmpty())
                xnext = new State();
            else
                xnext = transition.get(x).execute(input);
            if (y.isEmpty())
                ynext = new State();
            else
                ynext = dfa.transition.get(y).execute(input);
            StatePair temp = new StatePair(xnext, ynext);
            todo.add(temp);
        } else {
            input[index] = true;
            addNext(todo, dfa, x, y, input,index+1);
            input[index] = false;
            addNext(todo, dfa, x, y, input,index+1);
        }
    }

    /**
     * add next state pair to the queue (bdd method)
     * @param xTree
     * @param yTree
     * @param xIndex
     * @param yIndex
     * @param queue
     */
    private void product(BDDTree xTree, BDDTree yTree, int xIndex, int yIndex, Queue<StatePair> queue) {
        if (xTree == null) {
            Node y = yTree.getNode(yIndex);
            if (y.isTerminal()) {
                StatePair pair = new StatePair(new State(), (State) y.terminalValue);
                queue.add(pair);
            } else {
                product(xTree, yTree, xIndex, y.low, queue);
                product(xTree, yTree, xIndex, y.high, queue);
            }
            return;
        }
        if (yTree == null) {
            Node x = xTree.getNode(xIndex);
            if (x.isTerminal()) {
                StatePair pair = new StatePair((State) x.terminalValue, new State());
                queue.add(pair);
            } else {
                product(xTree, yTree, x.low, yIndex, queue);
                product(xTree, yTree, x.high, yIndex, queue);
            }
            return;
        }
        Node x = xTree.getNode(xIndex);
        Node y = yTree.getNode(yIndex);
        if (x.isTerminal() && y.isTerminal()) {
            StatePair pair = new StatePair((State) x.terminalValue, (State) y.terminalValue);
            queue.add(pair);
        } else if (x.isTerminal()) {
            product(xTree, yTree, xIndex, y.low, queue);
            product(xTree, yTree, xIndex, y.high, queue);
        } else if (y.isTerminal()) {
            product(xTree, yTree, x.low, yIndex, queue);
            product(xTree, yTree, x.high, yIndex, queue);
        } else if (x.inputIndex == y.inputIndex) {
            product(xTree, yTree, x.low, y.low, queue);
            product(xTree, yTree, x.high, y.high, queue);
        } else if (x.inputIndex < y.inputIndex) {
            product(xTree, yTree, x.low, yIndex, queue);
            product(xTree, yTree, x.high, yIndex, queue);
        } else {//(x.inputIndex > y.inputIndex)
            product(xTree, yTree, xIndex, y.low, queue);
            product(xTree, yTree, xIndex, y.high, queue);
        }
    }
}

