package jrif.ast;

import KATautomata.KAT.KatExpr;
import KATautomata.utility.SymDFA;
import jif.ast.PolicyNode_c;
import jrif.types.JrifTypeSystem;
import jif.types.label.Policy;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.*;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

import static MetaData.Info.util;

public class RifiPolicyNode_c extends PolicyNode_c implements RifiPolicyNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExprNode child;
    private SymDFA automata;

    public RifiPolicyNode_c(Position pos, KatExprNode expr) {
        super(pos, (Policy) null); //this is not very principled!
        this.child = expr;
    }

    protected Policy producePolicy(JrifTypeSystem ts, KatExpr expr) {
        this.automata = new SymDFA(util, expr);
        return ts.rifwriterPolicy(position(), this.automata);
    }

    protected RifiPolicyNode_c reconstruct(KatExprNode kat) {
        if (!kat.equals(this.child)) {
            RifiPolicyNode_c n = (RifiPolicyNode_c) copy();
            n.child = kat;
            return n;
        }

        return this;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatExprNode kat = visitChild(child, v);
        return reconstruct(kat);
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        JrifTypeSystem ts = (JrifTypeSystem) ar.typeSystem();
        if (!child.isDisambiguated()) {
            ar.job().extensionInfo().scheduler().currentGoal()
                    .setUnreachableThisRun();
            return this;
        }
        this.policy = producePolicy(ts, child.getType());
        return this;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {

    }

}



