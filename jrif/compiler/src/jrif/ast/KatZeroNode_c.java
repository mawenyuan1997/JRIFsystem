package jrif.ast;

import KATautomata.KAT.KatFactory;
import KATautomata.KAT.ZeroTest;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

/**
 * AST node for KAT Zero
 *
 * author: Wenyuan Ma   date:2020-06-26
 */
public class KatZeroNode_c extends KatTestNode implements KatZeroNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public KatZeroNode_c(Position pos) {
        super(pos);
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        this.type = KatFactory.ZeroTest();
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
