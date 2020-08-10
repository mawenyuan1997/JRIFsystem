package jrif.ast;

import KATautomata.KAT.KatExpr;
import KATautomata.KAT.KatFactory;
import KATautomata.KAT.StarExpr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

/**
 * AST node for KAT Star operation
 *
 * author: Wenyuan Ma   date:2020-06-26
 */
public class KatStarNode_c extends KatExprNode_c implements KatStarNode {
    private static final long serialVersionUID = SerialVersionUID.generate();

    KatExprNode expr;
    public KatStarNode_c(Position pos, KatExprNode e) {
        super(pos);
        expr = e;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExpr t = ((KatExprNode_c) expr.disambiguate(ar)).getType();
        this.type = KatFactory.StarExpr(t);
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatExprNode tnew = visitChild(expr, v);
        if (!expr.equals(tnew)) {
            KatStarNode_c n = (KatStarNode_c) copy();
            n.expr = tnew;
            return n;
        }
        return this;
    }
}
