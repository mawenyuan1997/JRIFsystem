package jrif.ast;

import jrif.types.KatExprType;

import KATautomata.KAT.KatExpr;
import KATautomata.SyKAT.SyKATexpression;
import jrif.types.JrifTypeSystem;
import jif.ast.PolicyNode_c;
import jif.types.label.Policy;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import KATautomata.utility.SymDFA;

import static MetaData.Info.util;

public class RifPolicyNode_c extends PolicyNode_c implements RifPolicyNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExprNode child;
    private Policy policy;
    private SymDFA automata;

    public RifPolicyNode_c(Position pos, KatExprNode expr) {
        super(pos, (Policy) null); //this is not very principled!
        this.child = expr;
    }


    protected Policy producePolicy(JrifTypeSystem ts, KatExpr expr) {
        this.automata = new SymDFA(util, expr);
        return ts.rifreaderPolicy(position(), this.automata);
    }

    protected RifPolicyNode_c reconstruct(KatExprNode expr) {
        if (!expr.equals(this.child)) {
            RifPolicyNode_c n = (RifPolicyNode_c) copy();
            n.child = expr;
            return n;
        }

        return this;
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        KatExprNode kat = visitChild(child, v);
        return reconstruct(kat);
    }

    protected Policy producePolicy(JrifTypeSystem ts,
                                   KatExprNode_c expr) {
        return ts.rifreaderPolicy(position(), new SymDFA(util, expr.getType().getExpr()));
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        JrifTypeSystem ts = (JrifTypeSystem) ar.typeSystem();
        if (!child.isDisambiguated()) {
            ar.job().extensionInfo().scheduler().currentGoal()
                    .setUnreachableThisRun();
            return this;
        }

        KatExprType expr = ((KatExprNode_c) child.disambiguate(ar)).getType();
        this.policy = producePolicy(ts, expr.getExpr());
        return this;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {


    }

}


