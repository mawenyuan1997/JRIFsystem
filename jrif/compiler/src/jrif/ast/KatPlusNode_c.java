package jrif.ast;

import KATautomata.KAT.KatExpr;
import KATautomata.KAT.KatFactory;
import KATautomata.KAT.PlusExpr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

public class KatPlusNode_c extends KatExprNode_c implements KatPlusNode {
    private static final long serialVersionUID = SerialVersionUID.generate();

    KatExprNode left, right;
    public KatPlusNode_c(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExpr l = ((KatExprNode_c) left.disambiguate(ar)).getType();
        KatExpr r = ((KatExprNode_c) right.disambiguate(ar)).getType();
        this.type = KatFactory.PlusExpr(l, r);
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatExprNode lnew = visitChild(left, v);
        KatExprNode rnew = visitChild(right, v);
        if (!left.equals(lnew) || !right.equals(rnew)) {
            KatPlusNode_c n = (KatPlusNode_c) copy();
            n.left = lnew;
            n.right = rnew;
            return n;
        }
        return this;
    }
}
