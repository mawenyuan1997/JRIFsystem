package jrif.ast;
import KAT.KATexpression;
import polyglot.util.Position;

public class KatActionNode extends KatExprNode {
    String id;

    public KatActionNode(Position pos, String s) {
        super(pos);
        id = s;
    }

    public void disambiguate() {
        // TODO: convert string to primAction array
    }
}