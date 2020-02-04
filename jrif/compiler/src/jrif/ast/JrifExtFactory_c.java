package jrif.ast;

import jrif.extension.JrifReclassifyExprExt;
import jrif.translate.ReclassifyExprToJavaExt_c;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public final class JrifExtFactory_c extends JrifAbstractExtFactory_c {

    public JrifExtFactory_c() {
        super();
    }

    public JrifExtFactory_c(ExtFactory nextExtFactory) {
        super(nextExtFactory);
    }

    @Override
    protected JrifExt extNodeImpl() {
    	JrifExt ext = new JrifExt();
        return ext;
    }

    protected Ext extReclassifyExprImpl() {
        return new JrifReclassifyExprExt(new ReclassifyExprToJavaExt_c());
    }
}
