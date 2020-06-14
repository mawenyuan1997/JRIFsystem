package jrif.ast;

import KATautomata.KAT.ConcatTest;
import KATautomata.KAT.TestExpr;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;

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
        KatExprType l = ((KatExprNode_c) left.disambiguate(ar)).getType();
        KatExprType r = ((KatExprNode_c) right.disambiguate(ar)).getType();
        if (!(l.getExpr() instanceof TestExpr) || !(r.getExpr() instanceof TestExpr))
            throw new SemanticException();
        this.type = new KatExprType(new ConcatTest((TestExpr) l.getExpr(), (TestExpr) r.getExpr()));
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}
