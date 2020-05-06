package jrif.ast;

import KATautomata.KAT.OneTest;
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

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}
