package jrif.ast;

import KATautomata.KAT.ConcatTest;
import KATautomata.KAT.PrimitiveTest;
import KATautomata.KAT.TestExpression;
import KATautomata.KAT.ZeroTest;
import jrif.types.KatExprType;
import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;

import java.util.List;

public class KatAtomNode extends KatTestNode{
    List<String> principalList;

    public KatAtomNode(Position pos, List l) {
        super(pos);
        principalList = l;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        TestExpression test;
        if (principalList.size() == 0) {
            test = new ZeroTest();
        } else if (principalList.size() == 1) {
            test = new PrimitiveTest(principalList.get(0));
        } else {
            PrimitiveTest p1 = new PrimitiveTest(principalList.get(0));
            PrimitiveTest p2 = new PrimitiveTest(principalList.get(1));
            test = new ConcatTest(p1, p2);
            for(int i=2; i < principalList.size(); i++) {
                test = new ConcatTest(test, new PrimitiveTest(principalList.get(i)));
            }
        }
        this.type = new KatExprType(test);
        return this;
    }
}
