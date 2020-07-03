package jrif.ast;

import KATautomata.KAT.KatFactory;
import KATautomata.KAT.OneTest;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

public class KatOneNode_c extends KatTestNode implements KatOneNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public KatOneNode_c(Position pos) {
        super(pos);
    }

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        this.type = KatFactory.OneTest();
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        return this;
    }
}
