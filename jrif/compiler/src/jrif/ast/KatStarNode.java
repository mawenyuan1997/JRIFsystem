package jrif.ast;

import KATautomata.KAT.KATexpression;
import KATautomata.KAT.StarExpression;
import KATautomata.KAT.TestExpression;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatStarNode extends KatExprNode {

    KatExprNode expr;
    public KatStarNode(Position pos, KatExprNode e) {
        super(pos);
        expr = e;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType t = ((KatExprNode) expr.disambiguate(ar)).getType();
        this.type = new KatExprType(new StarExpression(t.getExpr()));
        return this;
    }
}
