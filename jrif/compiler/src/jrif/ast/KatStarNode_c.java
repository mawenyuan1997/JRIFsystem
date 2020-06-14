package jrif.ast;

import KATautomata.KAT.StarExpr;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;

public class KatStarNode_c extends KatExprNode_c implements KatStarNode {
    private static final long serialVersionUID = SerialVersionUID.generate();

    KatExprNode expr;
    public KatStarNode_c(Position pos, KatExprNode e) {
        super(pos);
        expr = e;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType t = ((KatExprNode_c) expr.disambiguate(ar)).getType();
        this.type = new KatExprType(new StarExpr(t.getExpr()));
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}
