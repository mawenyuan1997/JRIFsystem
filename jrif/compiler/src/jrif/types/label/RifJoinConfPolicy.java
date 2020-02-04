package jrif.types.label;

import java.util.Collection;

import jif.types.hierarchy.LabelEnv;
import jif.types.hierarchy.LabelEnv.SearchState;
import jif.types.label.ConfPolicy;
import jif.types.label.Policy;

public interface RifJoinConfPolicy extends RifConfPolicy {
    Collection<ConfPolicy> joinComponents();

    boolean leq_(Policy p, LabelEnv env, SearchState state);

    ConfPolicy flatten();

}
