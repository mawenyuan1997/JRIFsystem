package jrif.ast;

import KAT.TestExpression;
import polyglot.ast.Expr;
import polyglot.util.Position;

import java.util.List;

public class KatAtomNode extends KatTestNode{
    Expr accesspath;
    List principalList;
    public KatAtomNode(Position pos, Expr e) {
        super(pos);
        accesspath = e;
    }

    public KatAtomNode(Position pos, List l) {
        super(pos);
        principalList = l;
    }

    public TestExpression disambiguate() {

    }
}
