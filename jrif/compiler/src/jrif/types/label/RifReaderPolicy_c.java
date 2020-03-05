package jrif.types.label;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jif.types.JifContext;
import jif.types.JifTypeSystem;
import jif.types.LabelSubstitution;
import jif.types.PathMap;
import jrif.types.JrifTypeSystem;
import jrif.types.RifFSM;
import jif.types.hierarchy.LabelEnv;
import jif.types.hierarchy.LabelEnv.SearchState;
import jif.types.label.ConfPolicy;
import jif.types.label.Label;
import jif.types.label.Policy;
import jif.types.label.Policy_c;
import jif.types.principal.Principal;
import jif.visit.LabelChecker;
import polyglot.ast.Id;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import utility.SymDFA;

/** An implementation of the <code>PolicyLabel</code> interface.
 */
public class RifReaderPolicy_c extends Policy_c implements RifConfPolicy{
    private static final long serialVersionUID = SerialVersionUID.generate();

    private SymDFA fsm;

    public RifReaderPolicy_c(SymDFA dfa, JifTypeSystem ts, Position pos) {
        super(ts, pos);
        this.fsm = dfa;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public SymDFA getFSM() {
        return this.fsm;
    }

    @Override
    public boolean isCanonical() {
        return this.fsm.isCanonical();
    }

    @Override
    public boolean isRuntimeRepresentable() {
        return this.fsm.isRuntimeRepresentable();
    }

    @Override
    public RifConfPolicy takeTransition(Id action) {
        SymDFA newfsm = this.fsm.takeTransition(action);
        return new RifReaderPolicy_c(newfsm, (JifTypeSystem) this.ts,
                this.position);
    }

    @Override
    public Set<RifFSM> getFSMs() {
        return null;
    }

    @Override
    protected Policy simplifyImpl() {
        return this;
    }

    @Override
    public boolean equalsImpl(TypeObject o) {
        List<String> visited = new LinkedList<String>();
        if (this == o) return true;
        if (o instanceof RifReaderPolicy_c) {
            return this.fsm
                    .equalsFSM(((RifReaderPolicy_c) o).getFSM(), visited);
        }
        if (o instanceof RifJoinConfPolicy_c) {
            throw new UnsupportedOperationException(
                    "Try to compare RifReaderPolicy with RifJoinConfPolicy.");
        }
        return false;
    }

    @Override
    public int hashCode() {
        // return (owner == null ? 0 : owner.hashCode()) ^ reader.hashCode()
        //         ^ 948234;
        return 948234;
    }

    @Override
    public boolean leq_(ConfPolicy p, LabelEnv env, SearchState state) {
        List<String> visited = new LinkedList<String>();
        if (this.isBottomConfidentiality() || p.isTopConfidentiality())
            return true;
        if (p instanceof RifReaderPolicy_c) {
            return this.fsm.leqFSM(((RifReaderPolicy_c) p).getFSM(), visited);
        }
        return false;
    }

    @Override
    public String toString(Set<Label> printedLabels) {
        return this.fsm.toString();
    }

    @Override
    public List<Type> throwTypes(TypeSystem ts) {
        return this.fsm.throwTypes(ts);
    }

    @Override
    public Policy subst(LabelSubstitution substitution)
            throws SemanticException {
        RifFSM newfsm;
        newfsm = this.fsm.subst(substitution);
        if (newfsm == null) {
            return substitution.substPolicy(this);
        }

        JrifTypeSystem ts = (JrifTypeSystem) typeSystem();
        RifConfPolicy newPolicy = ts.rifreaderPolicy(this.position(), newfsm);
        return substitution.substPolicy(newPolicy);
    }

    @Override
    public PathMap labelCheck(JifContext A, LabelChecker lc) {
        return this.fsm.labelCheck(A, lc);
    }

    @Override
    public boolean isBottomConfidentiality() {
        return this.fsm.isBottomConfidentiality();
    }

    @Override
    public boolean isTopConfidentiality() {
        return this.fsm.isTopConfidentiality();
    }

    @Override
    public boolean isTop() {
        return isTopConfidentiality();
    }

    @Override
    public boolean isBottom() {
        return isBottomConfidentiality();
    }

    @Override
    public ConfPolicy meet(ConfPolicy p) {
        JifTypeSystem ts = (JifTypeSystem) this.ts;
        return ts.meet(this, p);
    }

    @Override
    public ConfPolicy join(ConfPolicy p) {
        JifTypeSystem ts = (JifTypeSystem) this.ts;
        ConfPolicy newpol;
        if (p instanceof RifReaderPolicy_c) {
            newpol = ts.join(this, (RifReaderPolicy_c) p);
        } else if (p instanceof RifConfPolicy) {
            newpol = ts.join((RifConfPolicy) this, (RifConfPolicy) p);
        } else {
            newpol = ts.join(this, p);
        }
        return newpol;
    }

	@Override
	public Principal owner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal reader() {
		// TODO Auto-generated method stub
		return null;
	}
}
