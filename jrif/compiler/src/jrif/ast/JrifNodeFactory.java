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
//	public RifPolicyNode RifPolicyNode(Position pos, List<RifComponentNode> components);
//	public RifiPolicyNode RifiPolicyNode(Position pos, List<RifComponentNode> components);
	public AmbRifDynamicLabelNode AmbRifDynamicLabelNode(Position pos, Id name, LabelNode lbl); 
}
