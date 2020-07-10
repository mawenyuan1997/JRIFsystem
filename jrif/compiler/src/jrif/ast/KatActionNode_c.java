package jrif.ast;
import KATautomata.KAT.Action;
import KATautomata.KAT.KatFactory;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import MetaData.Info;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class KatActionNode_c extends KatExprNode_c implements KatActionNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    String id;

    public KatActionNode_c(Position pos, String s) {
        super(pos);
        id = s;
        if (id.equals("P")) {
            this.type = Action.allAction;
        } else {
            this.type = KatFactory.Action(Info.actionToPrims.get(id));
        }
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return true;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        return this;
    }
}