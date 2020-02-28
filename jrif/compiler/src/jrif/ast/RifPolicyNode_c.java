package jrif.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import utility.Util;

import KAT.KATexpression;
import SyKAT.SyKATexpression;
import jrif.types.JrifTypeSystem;
import jrif.types.RifComponent;
import jrif.types.RifFSM_c;
import jif.ast.PolicyNode_c;
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
import utility.SymDFA;

public class RifPolicyNode_c extends PolicyNode_c implements RifPolicyNode {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private KatExprNode child;
    private KATexpression policy;
    private SymDFA automata;
    private Util util;

    public RifPolicyNode_c(Position pos, KatExprNode expr) {
        super(pos, (Policy) null); //this is not very principled!
        this.child = expr;
    }


    protected Policy producePolicy(JrifTypeSystem ts, KATexpression expr) {
        SyKATexpression sy = util.translate(expr);
        this.automata = new SymDFA(util, sy);
        return ts.rifreaderPolicy(position(), this.automata);
    }

    @Override
    public Node disambiguate(AmbiguityRemover ar) throws SemanticException {
        JrifTypeSystem ts = (JrifTypeSystem) ar.typeSystem();
        KATexpression expr = child.disambiguate();
        this.policy = producePolicy(ts, expr);
        return this;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter tr) {


    }

}


