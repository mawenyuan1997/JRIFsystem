package jrif.ast;
import KATautomata.KAT.Action;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import MetaData.Info;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;

import java.util.ArrayList;
import java.util.List;

public class KatActionNode_c extends KatExprNode_c implements KatActionNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    String id;

    public KatActionNode_c(Position pos, String s) {
        super(pos);
        id = s;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        int ascii = (int)id.charAt(0);
        List<String> prims = new ArrayList<>();
        for(int i=0; i<Info.util.getNumOfAction(); i++) {
            if (ascii % 2 == 1) prims.add(Info.util.getPrimActions()[i]);
            ascii = ascii / 2;
            if (ascii == 0) break;
        }
        this.type = new KatExprType(new Action(prims));
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}