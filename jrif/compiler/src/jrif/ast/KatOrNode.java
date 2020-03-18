package jrif.ast;

import KATautomata.KAT.PlusTest;
import KATautomata.KAT.TestExpression;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatOrNode extends KatTestNode {
    KatTestNode left, right;
    public KatOrNode(Position pos, KatTestNode e1, KatTestNode e2) {
        super(pos);
        left = e1;
        right = e2;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType l = ((KatExprNode) left.disambiguate(ar)).getType();
        KatExprType r = ((KatExprNode) right.disambiguate(ar)).getType();
        if (!(l.getExpr() instanceof TestExpression) || !(r.getExpr() instanceof TestExpression))
            throw new SemanticException();
        this.type = new KatExprType(new PlusTest((TestExpression) l.getExpr(), (TestExpression) r.getExpr()));
        return this;
    }
}
