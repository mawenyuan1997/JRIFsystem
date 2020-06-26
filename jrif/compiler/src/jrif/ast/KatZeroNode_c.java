package jrif.ast;

import KATautomata.KAT.ZeroTest;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

public class KatZeroNode_c extends KatTestNode implements KatZeroNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public KatZeroNode_c(Position pos) {
        super(pos);
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        this.type = new KatExprType(new ZeroTest());
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
