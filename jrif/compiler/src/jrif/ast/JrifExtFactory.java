package jrif.ast;

import jif.ast.JifExtFactory;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

/**
 * Extension factory for jrif extension.
 */
public interface JrifExtFactory extends JifExtFactory {
    Ext extReclassifyExpr();
    
    Ext extRifTransitionNode();

    Ext extRifStateNode();

    Ext extRifPolicyNode();

    Ext extRifiPolicyNode();
    
    Ext extAmbRifDynamicLabelNode();
/*
    Ext extRifLabelNode();

    Ext extRifiLabelNode();
    */
}
