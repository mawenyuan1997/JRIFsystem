package jrif.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jif.ast.PolicyNode_c;
import jif.types.JifTypeSystem;
import jrif.types.JrifTypeSystem;
import jrif.types.RifComponent;
import jrif.types.RifFSM_c;
import jif.types.label.Policy;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

public class RifiPolicyNode_c extends PolicyNode_c implements RifiPolicyNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExprNode katExprNode;

    public RifiPolicyNode_c(Position pos, KatExprNode expr) {
        super(pos, (Policy) null); //this is not very principled!
        this.katExprNode = expr;
    }

    protected Policy producePolicy(JrifTypeSystem ts,
            List<RifComponent> components) {
        return ts.rifwriterPolicy(position(), new RifFSM_c(components));
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        JrifTypeSystem ts = (JrifTypeSystem) ar.typeSystem();
        List<RifComponent> l = new LinkedList<RifComponent>();


        this.policy = producePolicy(ts, l);
        return this;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {

    }

}



