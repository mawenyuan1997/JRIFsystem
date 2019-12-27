package SyKAToperator;

import SyKAT.BDD.*;
import SyKAT.Concat;
import SyKAT.Plus;
import SyKAT.Star;
import SyKAT.SyKATexpression;

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
            BDDTree<HashSet<SyKATexpression>> tree = new BDDTree<>(this.numInputs);
            HashSet<SyKATexpression> empty = new HashSet<SyKATexpression>();
            tree.addNode(new Node<HashSet<SyKATexpression>>(empty, this.numInputs));
            return new BDD<>(tree);
        }
        BDDTree<HashSet<SyKATexpression>> deriBDDtree = new BDDTree<>(this.numInputs);
        build(new boolean[this.numInputs], 0, bdd, deriBDDtree);
        return new BDD(deriBDDtree);
    }

    private int build(boolean[] input, int inputIndex, BDD<Boolean> bdd, BDDTree<HashSet<SyKATexpression>> deriBDDtree) {
        if (inputIndex == this.numInputs) {
            HashSet<SyKATexpression> set = new HashSet<>();
            if (bdd.execute(Arrays.copyOfRange(input, numAtom, numInputs))) {
                set.add(singleBooleanBDD(true, numAtom));
            }
            Node n = new Node<>(set, this.numInputs);
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
        BDD<HashSet<SyKATexpression>> dx = (BDD<HashSet<SyKATexpression>>) x.accept(this);
        BDD<HashSet<SyKATexpression>> dy = (BDD<HashSet<SyKATexpression>>) y.accept(this);
        Epsilon eps = new Epsilon(numAtom);
        BDD<Boolean> epsBDD = (BDD<Boolean>) x.accept(eps);
        BDD<HashSet<SyKATexpression>> left = squareDot(dx, y);
        BDD<HashSet<SyKATexpression>> right = squareCross(epsBDD, dy);
        return union(left, right);
    }

    @Override
    public Object visit(Plus expr) {
        BDD<HashSet<SyKATexpression>> l = (BDD<HashSet<SyKATexpression>>) expr.left.accept(this);
        BDD<HashSet<SyKATexpression>> r = (BDD<HashSet<SyKATexpression>>) expr.right.accept(this);
//        System.out.println(l.execute(new boolean[]{true, false, false, true, false, false}).contains(singleBooleanBDD(true, 3)));
        return union(l, r);
    }

    @Override
    public Object visit(Star expr) {
        BDD<HashSet<SyKATexpression>> dx = (BDD<HashSet<SyKATexpression>>) expr.p.accept(this);
        SyKATexpression xstar = new Star(expr.p);
        return squareDot(dx, xstar);
    }

    private BDD<HashSet<SyKATexpression>> squareDot(
                                          BDD<HashSet<SyKATexpression>> dx,
                                          SyKATexpression y
                                          ) {
        BDDTree<HashSet<SyKATexpression>> bddTree = dx.getTree();
        BDDTree<HashSet<SyKATexpression>> dxytree = new BDDTree<>(this.numInputs);
        for(Node<HashSet<SyKATexpression>> node : bddTree.nodes) {
            Node n;
            if (node.isTerminal()) {
                HashSet<SyKATexpression> set = new HashSet<>();
                for(SyKATexpression xp : node.terminalValue) {
                    set.add(new Concat(xp, y));
                }
                n = new Node<>(set, node.inputIndex);
            } else {
                n = new Node<>(node.low, node.high, node.inputIndex);
            }
            dxytree.addNode(n);
        }
        return new BDD<>(dxytree);
    }

    private BDD<HashSet<SyKATexpression>> squareCross(BDD<Boolean> epsx,
                                                      BDD<HashSet<SyKATexpression>> dy
                                                     ) {
        BDDTree<Boolean> eps = epsx.getTree();
        BDDTree<HashSet<SyKATexpression>> epsSetTree = new BDDTree<>();
        int sz = eps.nodes.size();
        epsSetTree.numInputs = dy.getTree().numInputs;
        epsSetTree.nodes = new ArrayList(sz);
        epsSetTree.nodesHash = new HashMap(sz);
        for (int i = 0; i < sz; i++)
        {
            Node<HashSet<SyKATexpression>> n;
            Node<Boolean> t = eps.nodes.get(i);
            if (t.isTerminal()) {
                HashSet<SyKATexpression> s = new HashSet<>();
                if (t.terminalValue) {
                    s.add(singleBooleanBDD(true, dy.getTree().numInputs));
                }
                n = new Node<>(s, dy.getTree().numInputs);
            } else {
                n = new Node<>(t.low, t.high, t.inputIndex);
            }
            epsSetTree.nodes.add(n);
            epsSetTree.nodesHash.put(n, i);
        }
        Operator<HashSet<SyKATexpression>> op = new Operator<HashSet<SyKATexpression>>() {
            @Override
            public HashSet<SyKATexpression> operate(HashSet<SyKATexpression> x, HashSet<SyKATexpression> y) {
                if (x.isEmpty()) return new HashSet<>();
                return y;
            }
        };
        return new BDD(op, new BDD<>(epsSetTree), dy);
    }


    private BDD<HashSet<SyKATexpression>> union(BDD<HashSet<SyKATexpression>> l,
                                                BDD<HashSet<SyKATexpression>> r
                                                ) {
        Operator<HashSet<SyKATexpression>> op = new Operator<HashSet<SyKATexpression>>() {
            @Override
            public HashSet<SyKATexpression> operate(HashSet<SyKATexpression> x, HashSet<SyKATexpression> y) {
                HashSet<SyKATexpression> temp = new HashSet<>(x);
                temp.addAll(y);
                return temp;
            }
        };
        return new BDD(op, l, r);
    }
}
