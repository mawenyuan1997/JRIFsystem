package jrif.ast;

import jif.ast.JifExt_c;
import jif.translate.ToJavaExt;
import polyglot.ast.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.SerialVersionUID;

public class JrifExt extends Ext_c {
	
	private static final long serialVersionUID = SerialVersionUID.generate();

    public static JrifExt ext(Node n) {
        Ext e = n.ext();
        while (e != null && !(e instanceof JrifExt)) {
            e = e.ext();
        }
        if (e == null) {
            throw new InternalCompilerError("No Jrif extension object for node "
                    + n + " (" + n.getClass() + ")", n.position());
        }
        return (JrifExt) e;
    }

    @Override
    public final JrifLang lang() {
        return JrifLang_c.instance;
    }

    // TODO:  Override operation methods for overridden AST operations.
}
