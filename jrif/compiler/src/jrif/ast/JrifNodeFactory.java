package jrif.ast;

import jif.ast.*;
import polyglot.ast.*;
import polyglot.util.*;

import java.util.List;


/**
 * NodeFactory for jrif extension.
 */
public interface JrifNodeFactory extends JifNodeFactory {
    
	public ReclassifyExpr ReclassifyExpr(Position pos, Expr expr, Id name);
	public RifPolicyNode RifPolicyNode(Position pos, KatExprNode expr);
	public KatActionNode KatActionNode(Position pos, String s);
	public KatAndNode KatAndNode(Position pos, KatTestNode e1, KatTestNode e2);
	public KatAtomNode KatAtomNode(Position pos, List<PrincipalNode> l);
	public KatConcatNode KatConcatNode(Position pos, KatExprNode e1, KatExprNode e2);
	public KatNegateNode KatNegateNode(Position pos, KatTestNode e);
	public KatOneNode KatOneNode(Position pos);
	public KatOrNode KatOrNode(Position pos, KatTestNode e1, KatTestNode e2);
	public KatPlusNode KatPlusNode(Position pos, KatExprNode e1, KatExprNode e2);
	public KatStarNode KatStarNode(Position pos, KatExprNode e);
	public KatZeroNode KatZeroNode(Position pos);

	public RifiPolicyNode RifiPolicyNode(Position pos, KatExprNode expr);
	public AmbRifDynamicLabelNode AmbRifDynamicLabelNode(Position pos, Id name, LabelNode lbl); 
}
