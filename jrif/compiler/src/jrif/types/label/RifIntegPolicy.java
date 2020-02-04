package jrif.types.label;

import java.util.Set;

import jrif.types.RifFSM;
import jif.types.label.IntegPolicy;
import jif.types.label.WriterPolicy;
import polyglot.ast.Id;

/** The policy label of the form <code>owner -> r1,&period;&period;&period;,rn</code>
 */
public interface RifIntegPolicy extends WriterPolicy {

    RifIntegPolicy takeTransition(Id action);

    Set<RifFSM> getFSMs();
}
