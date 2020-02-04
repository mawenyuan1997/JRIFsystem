package SyKAToperator;

import SyKAT.BDD.*;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;
import SyKAT.SyKATexpression;
import utility.State;

import java.util.*;

import static SyKAT.BDD.BooleanBDDutil.singleBooleanBDD;

public class Delta implements SyKATexpressionVisitor {
    int numAtom;
    int numAction;
    int numInputs;
    public Delta(int n1, int n2) {
        numAtom = n1;
        numAction = n2;
        numInputs = n1 + n2;
    }

    @Override
    public Object visit(BDD bdd) {
        if (!bdd.isAction()) {
            BDDTree<State> tree = new BDDTree<>(this.numInputs);
            HashSet<SyKATexpression> empty = new HashSet<SyKATexpression>();
            tree.addNode(new Node<>(new State(empty), this.numInputs));
            return new BDD<>(tree);
        }
        BDDTree<State> deriBDDtree = new BDDTree<>(this.numInputs);
        build(new boolean[this.numInputs], 0, bdd, deriBDDtree);
        return new BDD<>(deriBDDtree);
    }

    private int build(boolean[] input, int inputIndex, BDD<Boolean> bdd, BDDTree<State> deriBDDtree) {
        if (inputIndex == this.numInputs) {
            HashSet<SyKATexpression> set = new HashSet<>();
            if (bdd.execute(Arrays.copyOfRange(input, numAtom, numInputs))) {
                set.add(singleBooleanBDD(true, numAtom));
            }
            Node<State> n = new Node<>(new State(set), this.numInputs);
            return deriBDDtree.addNode(n);
        }
        else
        {
            input[inputIndex] = false;
            int lowChild = build(input, inputIndex + 1, bdd, deriBDDtree);
            input[inputIndex] = true;
            int highChild = build(input, inputIndex + 1, bdd, deriBDDtree);
            Node n = new Node<>(lowChild, highChild, inputIndex);
            return deriBDDtree.addNode(n);
        }
    }

    @Override
    public Object visit(Concat expr) {
        SyKATexpression x = expr.left;
        SyKATexpression y = expr.right;
        BDD<State> dx = (BDD<State>) x.accept(this);
        BDD<State> dy = (BDD<State>) y.accept(this);
        Epsilon eps = new Epsilon(numAtom);
        BDD<Boolean> epsBDD = (BDD<Boolean>) x.accept(eps);
        BDD<State> left = squareDot(dx, y);
        BDD<State> right = squareCross(epsBDD, dy);
        return union(left, right);
    }

    @Override
    public Object visit(Plus expr) {
        BDD<State> l = (BDD<State>) expr.left.accept(this);
        BDD<State> r = (BDD<State>) expr.right.accept(this);
//        System.out.println(l.execute(new boolean[]{true, false, false, true, false, false}).contains(singleBooleanBDD(true, 3)));
        return union(l, r);
    }

    @Override
    public Object visit(Star expr) {
        BDD<State> dx = (BDD<State>) expr.p.accept(this);
        SyKATexpression xstar = new Star(expr.p);
        return squareDot(dx, xstar);
    }

    private BDD<State> squareDot(
                                          BDD<State> dx,
                                          SyKATexpression y
                                          ) {
        BDDTree<State> bddTree = dx.getTree();
        BDDTree<State> dxytree = new BDDTree<>(this.numInputs);
        for(Node<State> node : bddTree.nodes) {
            Node n;
            if (node.isTerminal()) {
                HashSet<SyKATexpression> set = new HashSet<>();
                for(SyKATexpression xp : node.terminalValue.getSet()) {
                    set.add(new Concat(xp, y));
                }
                n = new Node<>(new State(set), node.inputIndex);
            } else {
                n = new Node<>(node.low, node.high, node.inputIndex);
            }
            dxytree.addNode(n);
        }
        return new BDD<>(dxytree);
    }

    private BDD<State> squareCross(BDD<Boolean> epsx,
                                                      BDD<State> dy
                                                     ) {
        BDDTree<Boolean> eps = epsx.getTree();
        BDDTree<State> epsSetTree = new BDDTree<>();
        int sz = eps.nodes.size();
        epsSetTree.numInputs = dy.getTree().numInputs;
        epsSetTree.nodes = new ArrayList(sz);
        epsSetTree.nodesHash = new HashMap(sz);
        for (int i = 0; i < sz; i++)
        {
            Node<State> n;
            Node<Boolean> t = eps.nodes.get(i);
            if (t.isTerminal()) {
                HashSet<SyKATexpression> s = new HashSet<>();
                if (t.terminalValue) {
                    s.add(singleBooleanBDD(true, dy.getTree().numInputs));
                }
                n = new Node<>(new State(s), dy.getTree().numInputs);
            } else {
                n = new Node<>(t.low, t.high, t.inputIndex);
            }
            epsSetTree.nodes.add(n);
            epsSetTree.nodesHash.put(n, i);
        }
        Operator<State> op = (x, y) -> {
            if (x.isEmpty()) return new State(new HashSet<>());
            return y;
        };
        return new BDD(op, new BDD<>(epsSetTree), dy);
    }

    private BDD<State> union(BDD<State> l, BDD<State> r) {
        Operator<State> op = (x, y) -> {
            HashSet<SyKATexpression> temp = new HashSet<>(x.getSet());
            temp.addAll(y.getSet());
            return new State(temp);
        };
        return new BDD(op, l, r);
    }
}
