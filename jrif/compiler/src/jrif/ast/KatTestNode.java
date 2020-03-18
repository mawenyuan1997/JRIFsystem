package jrif.ast;
import KATautomata.KAT.TestExpression;
import polyglot.util.Position;

public abstract class KatTestNode extends KatExprNode{

    private TestExpression testType;
    public KatTestNode(Position pos) {
        super(pos);
    }

}
