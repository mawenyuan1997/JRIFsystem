package jrif.extension;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jif.extension.LabelTypeCheckUtil;
import jif.types.JifTypeSystem;
import jif.types.label.ConfPolicy;
import jif.types.label.ConfProjectionPolicy_c;
import jif.types.label.IntegPolicy;
import jif.types.label.IntegProjectionPolicy_c;
import jif.types.label.JoinPolicy_c;
import jif.types.label.MeetPolicy_c;
import jif.types.label.Policy;
import jif.types.label.ReaderPolicy;
import jif.types.label.WriterPolicy;
import jif.types.principal.Principal;
import jrif.types.RifFSM;
import jrif.types.label.RifConfPolicy;
import jrif.types.label.RifIntegPolicy;
import jrif.types.label.RifJoinConfPolicy_c;
import jrif.types.label.RifJoinIntegPolicy_c;
import polyglot.types.SemanticException;
import polyglot.util.InternalCompilerError;
import polyglot.visit.TypeChecker;

public class JrifLabelTypeCheckUtil extends LabelTypeCheckUtil{

	public JrifLabelTypeCheckUtil(JifTypeSystem ts) {
		super(ts);
		// TODO Auto-generated constructor stub
	}
	
	 public void typeCheckFSM(TypeChecker tc, Set<RifFSM> fsms)
	            throws SemanticException {

//	        for (RifFSM fsm : fsms) {
//	            Map<String, RifFSMstate> states = fsm.states();
//	            List<Principal> principals;
//	            for (Entry<String, RifFSMstate> pair : states.entrySet()) {
//	                principals = pair.getValue().principals();
//	                if (principals != null) {
//	                    for (Principal p : principals) {
//	                        typeCheckPrincipal(tc, p);
//	                    }
//	                }
//	            }
//	        }
	    }
	 
	 @Override
	 public void typeCheckPolicy(TypeChecker tc, Policy p)
	            throws SemanticException {
	        if (p instanceof ConfProjectionPolicy_c) {
	            ConfProjectionPolicy_c cpp = (ConfProjectionPolicy_c) p;
	            typeCheckLabel(tc, cpp.label());
	        } else if (p instanceof IntegProjectionPolicy_c) {
	            IntegProjectionPolicy_c ipp = (IntegProjectionPolicy_c) p;
	            typeCheckLabel(tc, ipp.label());
	        } else if (p instanceof JoinPolicy_c) {
	            @SuppressWarnings("unchecked")
	            JoinPolicy_c<Policy> jp = (JoinPolicy_c<Policy>) p;
	            Collection<Policy> joinComponents = jp.joinComponents();
	            for (Policy pol : joinComponents) {
	                typeCheckPolicy(tc, pol);
	            }
	        } else if (p instanceof RifJoinConfPolicy_c) {
	            RifJoinConfPolicy_c rjcp = (RifJoinConfPolicy_c) p;
	            Collection<ConfPolicy> joinComponents = rjcp.joinComponents();
	            for (Policy pol : joinComponents) {
	                typeCheckPolicy(tc, pol);
	            }
	        } else if (p instanceof RifJoinIntegPolicy_c) {
	            RifJoinIntegPolicy_c rjip = (RifJoinIntegPolicy_c) p;
	            Collection<IntegPolicy> joinComponents = rjip.joinComponents();
	            for (Policy pol : joinComponents) {
	                typeCheckPolicy(tc, pol);
	            }
	        } else if (p instanceof MeetPolicy_c) {
	            @SuppressWarnings("unchecked")
	            MeetPolicy_c<Policy> mp = (MeetPolicy_c<Policy>) p;
	            Collection<Policy> meetComponents = mp.meetComponents();
	            for (Policy pol : meetComponents) {
	                typeCheckPolicy(tc, pol);
	            }
	        } else if (p instanceof ReaderPolicy) {
	            ReaderPolicy pol = (ReaderPolicy) p;
	            typeCheckPrincipal(tc, pol.owner());
	            typeCheckPrincipal(tc, pol.reader());
	        } else if (p instanceof WriterPolicy) {
	            WriterPolicy pol = (WriterPolicy) p;
	            typeCheckPrincipal(tc, pol.owner());
	            typeCheckPrincipal(tc, pol.writer());
	        } else if (p instanceof RifConfPolicy) {
	            RifConfPolicy pol = (RifConfPolicy) p;
	            typeCheckFSM(tc, pol.getFSMs());
	        } else if (p instanceof RifIntegPolicy) {
	            RifIntegPolicy pol = (RifIntegPolicy) p;
	            typeCheckFSM(tc, pol.getFSMs());
	        } else {
	            System.out.println("HELLO " + p.getClass().getName());
	            throw new InternalCompilerError("Unexpected policy " + p);
	        }
	    }

}
