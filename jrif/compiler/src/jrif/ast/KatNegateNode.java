package jrif.ast;

import KATautomata.KAT.NegateTest;
import KATautomata.KAT.TestExpr;
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
        if (!(t.getExpr() instanceof TestExpr))
            throw new SemanticException();
        this.type = new KatExprType(new NegateTest((TestExpr) t.getExpr()));
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}
