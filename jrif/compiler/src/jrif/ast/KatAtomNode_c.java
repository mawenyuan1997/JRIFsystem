package jrif.ast;

import KATautomata.KAT.*;
import jif.ast.PrincipalNode;
import jif.types.principal.Principal;
import jrif.types.KatExprType;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;

import java.util.LinkedList;
import java.util.List;

public class KatAtomNode_c extends KatTestNode implements KatAtomNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    List<PrincipalNode> principals;

    public KatAtomNode_c(Position pos, List<PrincipalNode> l) {
        super(pos);
        principals = l;
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        List<Principal> l = new LinkedList<Principal>();

        if (this.principals == null)
            l = null;
        else {
            for (PrincipalNode r : this.principals) {
                if (!r.isDisambiguated()) {
                    ar.job().extensionInfo().scheduler().currentGoal()
                            .setUnreachableThisRun();
                    return this;
                }
                l.add(r.principal());
            }
        }
        this.type = new KatExprType(KatFactory.getTest(l));
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

}
