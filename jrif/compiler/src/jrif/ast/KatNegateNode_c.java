package jrif.ast;

import KATautomata.KAT.KatExpr;
import KATautomata.KAT.KatFactory;
import KATautomata.KAT.NegateTest;
import KATautomata.KAT.TestExpr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

/**
 * AST node for KAT Negate operation
 *
 * author: Wenyuan Ma   date:2020-06-26
 */
public class KatNegateNode_c extends KatTestNode implements KatNegateNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatTestNode test;
    public KatNegateNode_c(Position pos, KatTestNode b) {
        super(pos);
        test = b;
    }

    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        KatExpr t = ((KatExprNode_c) test.disambiguate(ar)).getType();
        if (!(t instanceof TestExpr))
            throw new SemanticException();
        this.type = KatFactory.NegateTest((TestExpr) t);
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatTestNode tnew = visitChild(test, v);
        if (!test.equals(tnew)) {
            KatNegateNode_c n = (KatNegateNode_c) copy();
            n.test = tnew;
            return n;
        }
        return this;
    }
}
