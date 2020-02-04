package jrif.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;

public class JrifLang_c extends JLang_c implements JrifLang {
    public static final JrifLang_c instance = new JrifLang_c();

    public static JrifLang lang(NodeOps n) {
        while (n != null) {
            Lang lang = n.lang();
            if (lang instanceof JrifLang) return (JrifLang) lang;
            if (n instanceof Ext)
                n = ((Ext) n).pred();
            else return null;
        }
        throw new InternalCompilerError("Impossible to reach");
    }

    protected JrifLang_c() {
    }

    protected static JrifExt jrifExt(Node n) {
        return JrifExt.ext(n);
    }

    @Override
    protected NodeOps NodeOps(Node n) {
        return jrifExt(n);
    }

    // TODO:  Implement dispatch methods for new AST operations.
    // TODO:  Override *Ops methods for AST nodes with new extension nodes.
}
