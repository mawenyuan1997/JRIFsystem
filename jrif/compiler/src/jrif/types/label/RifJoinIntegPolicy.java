package jrif.types.label;

import java.util.Collection;

import jif.types.hierarchy.LabelEnv;
import jif.types.hierarchy.LabelEnv.SearchState;
import jif.types.label.IntegPolicy;
import jif.types.label.Policy;

public interface RifJoinIntegPolicy extends RifIntegPolicy {
    Collection<IntegPolicy> joinComponents();

    boolean leq_(Policy p, LabelEnv env, SearchState state);

    IntegPolicy flatten();

}
