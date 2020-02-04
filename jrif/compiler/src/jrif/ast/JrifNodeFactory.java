package jrif.ast;

import java.util.List;

import jif.ast.*;
import polyglot.ast.*;
import polyglot.util.*;


/**
 * NodeFactory for jrif extension.
 */
public interface JrifNodeFactory extends JifNodeFactory {
    
	public ReclassifyExpr ReclassifyExpr(Position pos, Expr expr, Id name);
	public RifTransitionNode RifTransitionNode(Position pos, Id name, Id lstate, Id rstate);
	public RifStateNode RifStateNode(Position pos, Id name, List<PrincipalNode> principals, String current);
	public RifPolicyNode RifPolicyNode(Position pos, List<RifComponentNode> components);
	public RifiPolicyNode RifiPolicyNode(Position pos, List<RifComponentNode> components);
	public AmbRifDynamicLabelNode AmbRifDynamicLabelNode(Position pos, Id name, LabelNode lbl); 
}
