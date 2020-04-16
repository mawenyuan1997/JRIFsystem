package jrif.ast;
import KATautomata.KAT.TestExpr;
import polyglot.util.Position;

public abstract class KatTestNode extends KatExprNode{

    private TestExpr testType;
    public KatTestNode(Position pos) {
        super(pos);
    }

}
