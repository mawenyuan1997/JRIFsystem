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
            BDDTree<HashSet<SyKATexpression>> tree = new BDDTree<>();
            tree.numInputs = this.numInputs;
            tree.nodes = new ArrayList();
            tree.nodesHash = new HashMap();
            HashSet<SyKATexpression> empty = new HashSet<SyKATexpression>();
            tree.addNode(new Node<HashSet<SyKATexpression>>(empty, this.numInputs));
            return new BDD<>(tree);
        }
        BDDTree<HashSet<SyKATexpression>> deriBDDtree = new BDDTree<>(this.numInputs);
        BDDTree<Boolean> bddTree = bdd.getTree();
        deriBDDtree.numInputs = this.numInputs;
        for(Node<Boolean> node : bddTree.nodes) {
            Node<HashSet<SyKATexpression>> n;
            if (node.isTerminal()) {
                HashSet<SyKATexpression> set = new HashSet<>();
                if (node.terminalValue) {
                    set.add(singleBooleanBDD(true, numAtom));
                }
                n = new Node<>(set, this.numInputs);
            } else {
                n = new Node<>(node.low, node.high,
                        this.numAtom + node.inputIndex); // new index in [atom, action] input
            }
            deriBDDtree.addNode(n);
        }
        return new BDD(deriBDDtree);
    }

    @Override
    public Object visit(Concat expr) {
        SyKATexpression x = expr.left;
        SyKATexpression y = expr.right;
        BDD<HashSet<SyKATexpression>> dx = (BDD<HashSet<SyKATexpression>>) x.accept(this);
        BDD<HashSet<SyKATexpression>> dy = (BDD<HashSet<SyKATexpression>>) y.accept(this);
        Epsilon eps = new Epsilon(numAtom);
        BDD<Boolean> epsBDD = (BDD<Boolean>) x.accept(eps);
        squareCross(epsBDD, dy, new boolean[dy.getNumInputs()], 0);
        BDD<HashSet<SyKATexpression>> left = squareDot(dx, y);
        BDD<HashSet<SyKATexpression>> right = dy;
        return union(left, right);
    }

    @Override
    public Object visit(Plus expr) {
        BDD<HashSet<SyKATexpression>> l = (BDD<HashSet<SyKATexpression>>) expr.left.accept(this);
        BDD<HashSet<SyKATexpression>> r = (BDD<HashSet<SyKATexpression>>) expr.right.accept(this);
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
        BDDTree<HashSet<SyKATexpression>> dxytree = new BDDTree<>();
        dxytree.numInputs = this.numInputs;
        ArrayList<Node<HashSet<SyKATexpression>>> deriNodes = new ArrayList<>();
        HashMap<Node<HashSet<SyKATexpression>>, Integer> deriNodesHash = new HashMap<>();
        for(Node<HashSet<SyKATexpression>> node : bddTree.nodes) {
            if (node.isTerminal()) {
                HashSet<SyKATexpression> set = new HashSet<>();
                for(SyKATexpression xp : node.terminalValue) {
                    set.add(new Concat(xp, y));
                }
                Node n = new Node<>(set, 0);
                deriNodes.add(n);
                deriNodesHash.put(n, bddTree.nodesHash.get(node));
            } else {
                Node n = new Node<>(node.low, node.high, node.inputIndex);
                deriNodes.add(n);
                deriNodesHash.put(n, bddTree.nodesHash.get(node));
            }
        }
        dxytree.nodes = deriNodes;
        dxytree.nodesHash = deriNodesHash;
        return new BDD<>(dxytree);
    }

    private void squareCross(BDD<Boolean> epsx,
                             BDD<HashSet<SyKATexpression>> dy,
                             boolean[] input,
                             int inputIndex
                             ) {
        if (inputIndex == this.numInputs) {
            Boolean f = epsx.execute(Arrays.copyOfRange(input,0, epsx.getNumInputs()));
            if (!f) {
                HashSet<SyKATexpression> ter = dy.execute(Arrays.copyOfRange(input,epsx.getNumInputs(),this.numInputs));
                ter.clear();
            }
        } else {
            input[inputIndex] = false;
            squareCross(epsx, dy, input, inputIndex+1);
            input[inputIndex] = true;
            squareCross(epsx, dy, input, inputIndex+1);
        }
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
