package jrif.ast;

import KAT.ConcatExpression;
import KAT.ConcatTest;
import KAT.KATexpression;
import KAT.TestExpression;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatConcatNode extends KatExprNode {

    KatExprNode left, right;
    public KatConcatNode(Position pos, KatExprNode e1, KatExprNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType l = ((KatExprNode) left.disambiguate(ar)).getType();
        KatExprType r = ((KatExprNode) right.disambiguate(ar)).getType();
        this.type = new KatExprType(new ConcatTest((TestExpression) l.getExpr(), (TestExpression) r.getExpr()));
        return this;
    }
}
