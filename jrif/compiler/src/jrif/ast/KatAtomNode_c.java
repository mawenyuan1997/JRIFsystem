package jrif.ast;

import KATautomata.KAT.*;
import jif.ast.AmbPrincipalNode;
import jif.ast.PrincipalNode;
import jif.types.principal.Principal;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CollectionUtil;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;

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
                    System.out.println(r.getClass().toString());
//                    ar.job().extensionInfo().scheduler().currentGoal()
//                            .setUnreachableThisRun();
//                    return this;
                }
                l.add(r.principal());
            }
        }
        this.type = KatFactory.getTest(l);
        return this;
    }

    @Override
    public boolean isDisambiguated() {
        return this.type != null;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
//        List<PrincipalNode> p = visitList(this.principals, v);
//        if (!CollectionUtil.equals(p, this.principals)) {
//            KatAtomNode_c n = (KatAtomNode_c) copy();
//            n.principals = ListUtil.copy(p, true);
//            return n;
//        }
        return this;
    }
}
