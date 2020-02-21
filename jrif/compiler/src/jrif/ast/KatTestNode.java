package jrif.ast;
import polyglot.util.Position;

public abstract class KatTestNode extends KatExprNode{
    public KatTestNode(Position pos) {
        super(pos);
    }

    public abstract void disambiguate();
}
