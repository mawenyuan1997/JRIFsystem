package jrif.types;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import jif.types.ActsForParam;
import jif.types.Equation;
import jif.types.JifTypeSystem;
import jif.types.LabelSubstitution;
import jif.types.VarMap;
import jif.types.label.IntegPolicy;
import jif.types.label.JoinLabel;
import jif.types.label.Label;
import jif.types.label.Policy;
import jif.types.label.VarLabel;
import jif.types.label.Variable;
import jif.types.principal.Principal;
import jif.types.principal.VarPrincipal;
import jrif.types.label.RifConfPolicy;
import jrif.types.label.RifDynamicLabel;
import jrif.types.label.RifDynamicLabel_c;
import jrif.types.label.RifIntegPolicy;
import jrif.types.label.TransitionVarLabel;
import polyglot.ast.Id;
import polyglot.types.SemanticException;
import polyglot.util.InternalCompilerError;

public class JrifVarMap extends VarMap{

	public JrifVarMap(JifTypeSystem ts, Label defaultLabelBound, Principal defaultPrincipalBound) {
		super(ts, defaultLabelBound, defaultPrincipalBound);
		// TODO Auto-generated constructor stub
	}
	
	

    
	@Override
	public Label boundOf(VarLabel v) {
        Label bound = (Label) bounds.get(v);
        if (v instanceof TransitionVarLabel) {
            TransitionVarLabel reclassbound = (TransitionVarLabel) v;
            Label innerlabel = reclassbound.innerRifLabel();
            Label innerbound = (Label) bounds.get(innerlabel);

            if (innerbound != null) {
                JifTypeSystem ts = innerbound.typeSystem();
                Collection<Label> components;
                Collection<Label> newcomponents = new LinkedHashSet<Label>();
                if (innerbound instanceof JoinLabel) {
                    JoinLabel inbound = (JoinLabel) innerbound;
                    // the components are already flattened
                    components = inbound.joinComponents();
                } else {
                    components = new LinkedHashSet<Label>();
                    components.add(innerbound);
                }
                for (Label ci : components) {
                    Label boundci;
                    if (!(ci.confProjection() instanceof RifConfPolicy)) {
                        boundci =
                                new RifDynamicLabel_c(
                                        reclassbound.transition(), ci, ts,
                                        innerbound.position());
                    } else {
                        RifConfPolicy innerconf =
                                (RifConfPolicy) ci.confProjection();
                        RifIntegPolicy innerinteg =
                                (RifIntegPolicy) ci.integProjection();
                        Id action = reclassbound.transition();
                        boundci =
                                ts.pairLabel(ci.position(),
                                        innerconf.takeTransition(action),
                                        innerinteg.takeTransition(action));
                    }
                    newcomponents.add(boundci);
                }
                if (newcomponents.size() == 1) {
                    bound = newcomponents.iterator().next();
                } else {
                    bound =
                            ts.joinLabel(innerbound.position(),
                                    (Set<Label>) newcomponents);
                }
                this.setBound(v, bound);
            } else {
                bound = defaultLabelBound;
                this.setBound(v, bound);
            }

        } else if (bound == null) {
            // The variable has no bound: assume the default label.
            // insert the default label into the map.
            bound = defaultLabelBound;
            this.setBound(v, bound);
        } else if (bound instanceof RifDynamicLabel) {
            Label innerlabel = ((RifDynamicLabel) bound).getLabel();
            Label reslabel = (Label) bounds.get(innerlabel);
            if (reslabel != null) {
                RifConfPolicy cp =
                        ((RifConfPolicy) reslabel.confProjection())
                                .takeTransition(((RifDynamicLabel) bound)
                                        .getName());
                IntegPolicy ip =
                        ((RifIntegPolicy) reslabel.integProjection())
                                .takeTransition(((RifDynamicLabel) bound)
                                        .getName());
                bound = ts.pairLabel(bound.position(), cp, ip);
                this.setBound(v, bound);
            }
        }

        return bound;
    }
	

	private class JrifVarMapLabelSubstitution extends LabelSubstitution {
        @Override
        public Label substLabel(Label L) throws SemanticException {
            if (L instanceof VarLabel) {
                VarLabel v = (VarLabel) L;
                return JrifVarMap.this.boundOf(v);
            } else if (L instanceof RifDynamicLabel) {
                Label innerlabel = ((RifDynamicLabel) L).getLabel();
                Label reslabel = (Label) bounds.get(innerlabel);
                if (reslabel != null && reslabel instanceof RifDynamicLabel) {
                    return ((JrifTypeSystem) ts).rifDynamicLabel(L.position(),
                            ((RifDynamicLabel) L).getName(), reslabel);
                }
                if (reslabel != null) {
                    RifConfPolicy cp =
                            ((RifConfPolicy) reslabel.confProjection())
                                    .takeTransition(((RifDynamicLabel) L)
                                            .getName());
                    IntegPolicy ip =
                            ((RifIntegPolicy) reslabel.integProjection())
                                    .takeTransition(((RifDynamicLabel) L)
                                            .getName());
                    return ts.pairLabel(L.position(), cp, ip);
                }
            }
            return L;
        }

        @Override
        public Principal substPrincipal(Principal p) throws SemanticException {
            if (p instanceof VarPrincipal) {
                VarPrincipal v = (VarPrincipal) p;
                return JrifVarMap.this.boundOf(v);
            }
            return p;
        }
    }
	
	@Override
    public Policy applyTo(Policy p) {
        LabelSubstitution s = new JrifVarMapLabelSubstitution();
        try {
            return p.subst(s);
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unexpected SemanticException", e);
        }

    }
    
	@Override
    public Label applyTo(Label c) {
        LabelSubstitution s = new JrifVarMapLabelSubstitution();
        try {
            return c.subst(s);
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unexpected SemanticException", e);
        }
    }

	@Override
    public void applyTo(Equation eqn) {
        // TODO: this imperatively updates eqn.
        LabelSubstitution s = new JrifVarMapLabelSubstitution();
        try {
            eqn.subst(s);
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unexpected SemanticException", e);
        }
    }

	@Override
    public Principal applyTo(Principal p) {
        LabelSubstitution s = new JrifVarMapLabelSubstitution();
        try {
            return p.subst(s);
        } catch (SemanticException e) {
            throw new InternalCompilerError("Unexpected SemanticException", e);
        }
    }
	
	@Override
    public VarMap copy() {
		JrifVarMap vp = new JrifVarMap(ts,  defaultLabelBound, defaultPrincipalBound);
		vp.setInitialBounds(bounds);
        return vp;
    }
	
	private void setInitialBounds(Map<Variable, ActsForParam> bounds){
	
		this.bounds = new LinkedHashMap<Variable, ActsForParam>(bounds);
	}
}
