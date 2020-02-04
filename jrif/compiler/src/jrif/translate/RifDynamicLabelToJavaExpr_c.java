package jrif.translate;

import jif.translate.JifToJavaRewriter;
import jif.translate.LabelToJavaExpr_c;
import jif.types.label.Label;
import jrif.types.label.RifDynamicLabel;
import polyglot.ast.Expr;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class RifDynamicLabelToJavaExpr_c extends LabelToJavaExpr_c {
    private static final long serialVersionUID = SerialVersionUID.generate();

    @Override
    public Expr toJava(Label label, JifToJavaRewriter rw, Expr thisQualifier,
            boolean simplify)
            throws SemanticException {
        RifDynamicLabel L = (RifDynamicLabel) label;
        Expr e = L.getLabel().toJava(rw, thisQualifier);
        return (Expr) rw
                .qq()
                .parseExpr(
                        rw.runtimeLabelUtil() + ".taketransition(" + "\""
                                + L.getName().id() + "\"" + ",%E)", e)
                .position(Position.compilerGenerated());
    }
}
