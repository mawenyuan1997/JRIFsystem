package jrif.ast;

import jif.ast.AbstractJifExtFactory_c;
import polyglot.ast.AbstractExtFactory_c;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public abstract class JrifAbstractExtFactory_c extends AbstractJifExtFactory_c
        implements JrifExtFactory {

    public JrifAbstractExtFactory_c() {
        super();
    }

    public JrifAbstractExtFactory_c(ExtFactory nextExtFactory) {
        super(nextExtFactory);
    }

    @Override
    public Ext extReclassifyExpr() {
        Ext e = extReclassifyExprImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extReclassifyExpr();
            e = composeExts(e, e2);
        }
        return postExtDeclassifyExpr(e);
    }
    
    @Override
    public final Ext extRifTransitionNode() {
        Ext e = extRifTransitionNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifTransitionNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }

    @Override
    public final Ext extRifStateNode() {
        Ext e = extRifStateNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifStateNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }

    @Override
    public final Ext extRifPolicyNode() {
        Ext e = extRifPolicyNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifPolicyNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }

    @Override
    public final Ext extRifiPolicyNode() {
        Ext e = extRifiPolicyNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifiPolicyNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }
    
    @Override
    public final Ext extAmbRifDynamicLabelNode() {
        Ext e = extAmbRifDynamicLabelNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extAmbRifDynamicLabelNode();
            e = composeExts(e, e2);
        }
        return postExtAmbRifDynamicLabelNode(e);
    }

/*    @Override
    public final Ext extRifLabelNode() {
        Ext e = extRifLabelNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifLabelNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }

    @Override
    public final Ext extRifiLabelNode() {
        Ext e = extRifiLabelNodeImpl();
        if (nextExtFactory() != null
                && nextExtFactory() instanceof JrifExtFactory) {
            JrifExtFactory nextFac = (JrifExtFactory) nextExtFactory();
            Ext e2 = nextFac.extRifiLabelNode();
            e = composeExts(e, e2);
        }
        return postExtPolicyNode(e);
    }
*/    
    protected Ext extReclassifyExprImpl() {
        return extExprImpl();
    }
    
    protected Ext extRifTransitionNodeImpl() {
        return extNode();
    }

    protected Ext extRifStateNodeImpl() {
        return extNode();
    }

    protected Ext extRifPolicyNodeImpl() {
        return extNode();
    }

    protected Ext extRifiPolicyNodeImpl() {
        return extNode();
    }
    
    protected Ext extAmbRifDynamicLabelNodeImpl() {
        return extLabelNode();
    }
    
    protected Ext postExtAmbRifDynamicLabelNode(Ext e) {
        return postExtLabelNode(e);
    }
/*
    protected Ext extRifLabelNodeImpl() {
        return extNode();
    }

    protected Ext extRifiLabelNodeImpl() {
        return extNode();
    }
    */
}
