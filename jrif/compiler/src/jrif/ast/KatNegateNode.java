package jrif.ast;

import KAT.NegateTest;
import KAT.TestExpression;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatNegateNode extends KatTestNode {
    KatTestNode test;
    public KatNegateNode(Position pos, KatTestNode b) {
        super(pos);
        test = b;
    }

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExprType t = ((KatExprNode) test.disambiguate(ar)).getType();
        if (!(t.getExpr() instanceof TestExpression))
            throw new SemanticException();
        this.type = new KatExprType(new NegateTest((TestExpression) t.getExpr()));
        return this;
    }
}
