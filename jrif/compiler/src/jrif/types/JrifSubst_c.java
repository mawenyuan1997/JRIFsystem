package jrif.types;

import java.io.Serializable;
import java.util.Map;

import jif.types.JifSubst_c;
import jif.types.JifTypeSystem;
import jif.types.LabelSubstitution;
import jif.types.Param;
import jif.types.ParamInstance;
import jif.types.label.AccessPath;
import jif.types.label.AccessPathField;
import jif.types.label.CovariantParamLabel;
import jif.types.label.Label;
import jif.types.label.ParamLabel;
import jif.types.principal.ParamPrincipal;
import jif.types.principal.Principal;
import jrif.types.label.RifDynamicLabel;
import jrif.types.label.RifDynamicLabel_c;
import polyglot.ast.Id;
import polyglot.types.FieldInstance;
import polyglot.types.SemanticException;
import polyglot.util.SerialVersionUID;

public class JrifSubst_c extends JifSubst_c{
	private static final long serialVersionUID = SerialVersionUID.generate();

	public JrifSubst_c(JifTypeSystem ts, Map<ParamInstance, ? extends Param> subst) {
		super(ts, subst);
		// TODO Auto-generated constructor stub
	}

   
    
    /**
     * This class is a <code>LabelSubstitution</code> that performs
     * substitutions on <code>Label</code>s and <code>Principal</code>s.
     *
     */
    @SuppressWarnings("serial")
    protected class SubstLabelSubst extends LabelSubstitution
            implements Serializable {
        /**
         * @throws SemanticException
         */
        @Override
        public Label substLabel(Label L) throws SemanticException {
            if (L instanceof ParamLabel) {
                ParamLabel c = (ParamLabel) L;
                return subLabel(c, c.paramInstance());
            } else if (L instanceof CovariantParamLabel) {
                CovariantParamLabel c = (CovariantParamLabel) L;
                return subLabel(c, c.paramInstance());
            } else if (L instanceof RifDynamicLabel) {
                RifDynamicLabel c = (RifDynamicLabel) L;
                Label label = c.getLabel();
                Id name = c.getName();
                Label substlabel = substLabel(label);
                return new RifDynamicLabel_c(name, substlabel,
                        (JifTypeSystem) ts, c.position());
            }
            return L;
        }

        /**
         * @throws SemanticException
         */
        @Override
        public Principal substPrincipal(Principal p) throws SemanticException {
            if (p instanceof ParamPrincipal) {
                ParamPrincipal pp = (ParamPrincipal) p;
                return subPrincipal(pp, pp.paramInstance());
            }

            return p;
        }

        @Override
        public AccessPath substAccessPath(AccessPath ap)
                throws SemanticException {
            ap = super.substAccessPath(ap);
            if (ap instanceof AccessPathField) {
                // Also perform substitution within the access path's field
                // instance.
                AccessPathField apf = (AccessPathField) ap;
                FieldInstance substFI = substField(apf.fieldInstance());
                return apf.fieldInstance(substFI);
            }

            return ap;
        }
    }
	
}
