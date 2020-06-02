package jrif.ast;

import jif.ast.JifExtFactory;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

/**
 * Extension factory for jrif extension.
 */
public interface JrifExtFactory extends JifExtFactory {
    Ext extReclassifyExpr();
    
    Ext extKatActionNode();

    Ext extKatAndNode();

    Ext extKatAtomNode();

    Ext extKatConcatNode();

    Ext extKatNegateNode();

    Ext extKatOneNode();

    Ext extKatOrNode();

    Ext extKatPlusNode();

    Ext extKatStarNode();

    Ext extKatZeroNode();

    Ext extRifPolicyNode();

    Ext extRifiPolicyNode();
    
    Ext extAmbRifDynamicLabelNode();
/*
    Ext extRifLabelNode();

    Ext extRifiLabelNode();
    */
}
