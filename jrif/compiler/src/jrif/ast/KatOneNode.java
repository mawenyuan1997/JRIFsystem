package jrif.ast;

import KAT.NegateTest;
import KAT.OneTest;
import KAT.TestExpression;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

public class KatOneNode extends KatTestNode {
    public KatOneNode(Position pos) {
        super(pos);
    }

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        this.type = new KatExprType(new OneTest());
        return this;
    }
}
