package jrif.ast;

import KATautomata.KAT.KatExpr;
import KATautomata.SyKAT.SyKATexpression;
import KATautomata.utility.SymDFA;
import jif.ast.PolicyNode_c;
import jrif.types.JrifTypeSystem;
import jrif.types.KatExprType;
import jif.types.label.Policy;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.PrettyPrinter;

import static MetaData.Info.util;

public class RifiPolicyNode_c extends PolicyNode_c implements RifiPolicyNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExprNode child;
    private Policy policy;
    private SymDFA automata;

    public RifiPolicyNode_c(Position pos, KatExprNode expr) {
        super(pos, (Policy) null); //this is not very principled!
        this.child = expr;
    }

    protected Policy producePolicy(JrifTypeSystem ts, KatExpr expr) {
        SyKATexpression sy = util.translate(expr);
        this.automata = new SymDFA(util, sy);
        return ts.rifreaderPolicy(position(), this.automata);
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        JrifTypeSystem ts = (JrifTypeSystem) ar.typeSystem();
        KatExprType expr = ((KatExprNode) child.disambiguate(ar)).getType();
        this.policy = producePolicy(ts, expr.getExpr());
        return this;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {

    }

}



