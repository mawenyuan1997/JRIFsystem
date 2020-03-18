package KATautomata.SyKAT.BDD;

import java.util.ArrayList;
import java.util.HashMap;

public class BooleanBDDutil {
    /**
     *  Create a SyKAT.BDD for a constant boolean value (i.e. zero inputs).
     */
    public static BDD<Boolean> singleBooleanBDD(boolean value, int numInputs)
    {
        BDDTree<Boolean> tree = new BDDTree<Boolean>();
        tree.numInputs = numInputs;
        tree.nodes = new ArrayList();
        tree.nodesHash = new HashMap();
        tree.addNode(new Node<Boolean>(value, numInputs));
        return new BDD<>(tree);
    }
//    public enum Function { TRUE, FALSE, NOT, NAND, AND, OR, XOR, SHUNT, TEST1, TEST2, TEST3, TEST4, TEST5, XOR_POSTCAT2, XOR_PRECAT2 };
//    /**
//     *  Constructor for pre-defined functions
//     */
//
//    public static BDD<Boolean> BDDfromFunction(Function function)
//    {
//        BDDTree<Boolean> bddtree;
//        switch (function)
//        {
//            default:
//            case TRUE:
//                bddtree = singleBooleanBDDTree(true);
//                break;
//
//            case FALSE:
//                bddtree = singleBooleanBDDTree(false);
//                break;
//
//            case NOT:
//                bddtree = new BDDTree(1);
//                bddtree.addNode(new Node(1, 0, 0));
//                break;
//
//            case NAND:
//                bddtree = new BDDTree(2);
//                int highNodeNAND = bddtree.addNode(new Node(1, 0, 1));
//                bddtree.addNode(new Node(1, highNodeNAND, 0));
//                break;
//
//            case AND:
//                bddtree = new BDDTree(2);
//                int highNodeAND = bddtree.addNode(new Node(0, 1, 1));
//                bddtree.addNode(new Node(0, highNodeAND, 0));
//                break;
//
//            case OR:
//                bddtree = new BDDTree(2);
//                int lowNodeOR = bddtree.addNode(new Node(0, 1, 1));
//                bddtree.addNode(new Node(lowNodeOR, 1, 0));
//                break;
//
//            case XOR:
//                bddtree = new BDDTree(2);
//                int lowNodeXOR = bddtree.addNode(new Node(0, 1, 1));
//                int highNodeXOR = bddtree.addNode(new Node(1, 0, 1));
//                bddtree.addNode(new Node(lowNodeXOR, highNodeXOR, 0));
//                break;
//
//            case SHUNT:
//                bddtree = new BDDTree(1);
//                bddtree.addNode(new Node(0, 1, 0));
//                break;
//
//            case TEST1:
//                bddtree = new BDDTree(2);
//                bddtree.addNode(new Node(0,1, 1));
//                break;
//
//            case TEST2: // Corresponds to the example Anderson applies restrict() to
//                bddtree = new BDDTree(3);
//                int x3 = bddtree.addNode(new Node(0, 1, 2));
//                int x2a = bddtree.addNode(new Node(1, x3, 1));
//                int x2b = bddtree.addNode(new Node(x3, 1, 1));
//                bddtree.addNode(new Node(x2a, x2b, 0));
//                break;
//
//            case TEST3: // Corresponds to Anderson's application of restrict() to TEST2
//                bddtree = new BDDTree(3);
//                x3 = bddtree.addNode(new Node(0, 1, 2));
//                bddtree.addNode(new Node(1, x3, 0));
//                break;
//
//            case TEST4: // From Anderson's example of apply()
//                bddtree = new BDDTree(5);
//                int n2 = bddtree.addNode(new Node(1, 0, 4));
//                int n3 = bddtree.addNode(new Node(n2, 0, 3));
//                int n4 = bddtree.addNode(new Node(0, n2, 3));
//                int n5 = bddtree.addNode(new Node(n3, n4, 2));
//                int n6 = bddtree.addNode(new Node(n5, 0, 1));
//                int n7 = bddtree.addNode(new Node(0, n5, 1));
//                bddtree.addNode(new Node(n6, n7, 0));
//                break;
//
//            case TEST5: // From Anderson's example of apply()
//                bddtree = new BDDTree(5);
//                n2 = bddtree.addNode(new Node(1, 0, 4));
//                n3 = bddtree.addNode(new Node(n2, 0, 2));
//                n4 = bddtree.addNode(new Node(0, n2, 2));
//                bddtree.addNode(new Node(n3, n4, 0));
//                break;
//
//            case XOR_POSTCAT2:
//                bddtree = new BDDTree(4);
//                n2 = bddtree.addNode(new Node(0, 1, 1));
//                n3 = bddtree.addNode(new Node(1, 0, 1));
//                bddtree.addNode(new Node(n2, n3, 0));
//                break;
//
//            case XOR_PRECAT2:
//                bddtree = new BDDTree(4);
//                n2 = bddtree.addNode(new Node(0, 1, 3));
//                n3 = bddtree.addNode(new Node(1, 0, 3));
//                bddtree.addNode(new Node(n2, n3, 2));
//                break;
//        }
//        BDD<> bdd =  new BDD<Boolean>(bddtree);
//        bdd.num
//    }
}
