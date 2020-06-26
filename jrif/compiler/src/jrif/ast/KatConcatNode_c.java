package jrif.ast;

import KATautomata.KAT.ConcatExpr;
import KATautomata.KAT.ConcatTest;
import KATautomata.KAT.TestExpr;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

public class KatConcatNode_c extends KatExprNode_c implements KatConcatNode {
    private static final long serialVersionUID = SerialVersionUID.generate();

    KatExprNode left, right;
    public KatConcatNode_c(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType l = ((KatExprNode_c) left.disambiguate(ar)).getType();
        KatExprType r = ((KatExprNode_c) right.disambiguate(ar)).getType();
        this.type = new KatExprType(new ConcatExpr(l.getExpr(), r.getExpr()));
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
            KatConcatNode_c n = (KatConcatNode_c) copy();
            n.left = lnew;
            n.right = rnew;
            return n;
        }
        return this;
    }
}
