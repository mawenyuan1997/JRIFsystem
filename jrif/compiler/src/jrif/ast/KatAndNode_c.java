package jrif.ast;

import KATautomata.KAT.ConcatTest;
import KATautomata.KAT.KatExpr;
import KATautomata.KAT.KatFactory;
import KATautomata.KAT.TestExpr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

/**
 * AST node for AND operation
 *
 * author: Wenyuan Ma   date:2020-06-26
 */
public class KatAndNode_c extends KatTestNode implements KatAndNode{
    private static final long serialVersionUID = SerialVersionUID.generate();
    KatTestNode left, right;
    public KatAndNode_c(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExpr l = ((KatExprNode_c) left.disambiguate(ar)).getType();
        KatExpr r = ((KatExprNode_c) right.disambiguate(ar)).getType();
        if (!(l instanceof TestExpr) || !(r instanceof TestExpr))
            throw new SemanticException();
        this.type = KatFactory.ConcatTest((TestExpr) l, (TestExpr) r);
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatTestNode lnew = visitChild(left, v);
        KatTestNode rnew = visitChild(right, v);
        if (!left.equals(lnew) || !right.equals(rnew)) {
            KatAndNode_c n = (KatAndNode_c) copy();
            n.left = lnew;
            n.right = rnew;
            return n;
        }
        return this;
    }
}
