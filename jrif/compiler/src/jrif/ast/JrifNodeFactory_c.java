package jrif.ast;

import jif.ast.*;
import polyglot.ast.*;
import polyglot.util.*;

import java.util.List;

/**
 * NodeFactory for jrif extension.
 */
public class JrifNodeFactory_c extends JifNodeFactory_c implements JrifNodeFactory {

	public JrifNodeFactory_c() {
		super(new JrifExtFactory_c(new JifExtFactory_c()));
    }

    @Override
    public JrifExtFactory extFactory() {
        return (JrifExtFactory) super.extFactory();
    }
    
    protected JrifExtFactory jrifExtFactory() {
        return (JrifExtFactory) this.extFactory();
    }

    @Override
    public RifPolicyNode RifPolicyNode(Position pos,
                                       KatExprNode expr) {
        RifPolicyNode n = new RifPolicyNode_c(pos, expr);
        n = ext(n, jrifExtFactory().extRifPolicyNode());
        n = del(n, delFactory().delExpr());
        return n;
    }


    @Override
    public RifiPolicyNode RifiPolicyNode(Position pos,
                                         KatExprNode expr) {
        RifiPolicyNode n = new RifiPolicyNode_c(pos, expr);
        n = ext(n, jrifExtFactory().extRifiPolicyNode());
        n = del(n, delFactory().delExpr());
        return n;
    }


    public ReclassifyExpr ReclassifyExpr(Position pos, Expr expr, Id name) {
        ReclassifyExpr n = new ReclassifyExpr_c(pos, expr, name,null);
        n = ext(n, jrifExtFactory().extReclassifyExpr());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatActionNode KatActionNode(Position pos, String s) {
        KatActionNode n = new KatActionNode_c(pos, s);
        n = ext(n, jrifExtFactory().extKatActionNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatAndNode KatAndNode(Position pos, KatTestNode e1, KatTestNode e2) {
        KatAndNode n = new KatAndNode_c(pos, e1, e2);
        n = ext(n, jrifExtFactory().extKatAndNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatAtomNode KatAtomNode(Position pos, List<PrincipalNode> l) {
        KatAtomNode n = new KatAtomNode_c(pos, l);
        n = ext(n, jrifExtFactory().extKatAtomNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatConcatNode KatConcatNode(Position pos, KatExprNode e1, KatExprNode e2) {
        KatConcatNode n = new KatConcatNode_c(pos, e1, e2);
        n = ext(n, jrifExtFactory().extKatConcatNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatNegateNode KatNegateNode(Position pos, KatTestNode e) {
        KatNegateNode n = new KatNegateNode_c(pos, e);
        n = ext(n, jrifExtFactory().extKatNegateNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatOneNode KatOneNode(Position pos) {
        KatOneNode n = new KatOneNode_c(pos);
        n = ext(n, jrifExtFactory().extKatOneNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatOrNode KatOrNode(Position pos, KatTestNode e1, KatTestNode e2) {
        KatOrNode n = new KatOrNode_c(pos, e1, e2);
        n = ext(n, jrifExtFactory().extKatOrNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatPlusNode KatPlusNode(Position pos, KatExprNode e1, KatExprNode e2) {
        KatPlusNode n = new KatPlusNode_c(pos, e1, e2);
        n = ext(n, jrifExtFactory().extKatPlusNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatStarNode KatStarNode(Position pos, KatExprNode e) {
        KatStarNode n = new KatStarNode_c(pos, e);
        n = ext(n, jrifExtFactory().extKatStarNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public KatZeroNode KatZeroNode(Position pos) {
        KatZeroNode n = new KatZeroNode_c(pos);
        n = ext(n, jrifExtFactory().extKatZeroNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    @Override
    public AmbRifDynamicLabelNode AmbRifDynamicLabelNode(Position pos, Id name,
            LabelNode lbl) {
        AmbRifDynamicLabelNode n = new AmbRifDynamicLabelNode_c(pos, name, lbl);
        n = ext(n, jrifExtFactory().extAmbRifDynamicLabelNode());
        n = del(n, delFactory().delExpr());
        return n;
    }

    
    /*
    @Override
    public RifLabelNode RifLabelNode(Position pos, List<RifPolicyNode> policies) {
        RifLabelNode n = new AmbRifLabelNode_c(pos, policies);
        n = (RifLabelNode) n.ext(jifExtFactory().extRifLabelNode());
        n = (RifLabelNode) n.del(delFactory().delNode());
        return n;
    }

    @Override
    public RifiLabelNode RifiLabelNode(Position pos,
            List<RifiPolicyNode> policies) {
        RifiLabelNode n = new AmbRifiLabelNode_c(pos, policies);
        n = (RifiLabelNode) n.ext(jifExtFactory().extRifiLabelNode());
        n = (RifiLabelNode) n.del(delFactory().delNode());
        return n;
    }

    @Override
    public RifLabelNode RifTransLabelNode(Position pos, LabelNode pol, Id name) {
        RifLabelNode n = new AmbRifTransLabelNode_c(pos, pol, name);
        n = (RifLabelNode) n.ext(jifExtFactory().extRifLabelNode());
        n = (RifLabelNode) n.del(delFactory().delNode());
        return n;
    }
*/
}
