package jrif.types.label;

import java.util.Set;

import jrif.types.RifFSM;
import jif.types.label.ConfPolicy;
import jif.types.label.ReaderPolicy;
import polyglot.ast.Id;

/** The policy label of the form <code>owner -> r1,&period;&period;&period;,rn</code>
 */
public interface RifConfPolicy extends ReaderPolicy {

    RifConfPolicy takeTransition(Id action);

    Set<RifFSM> getFSMs();
}
