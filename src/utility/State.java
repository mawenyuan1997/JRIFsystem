package utility;

import SyKAT.SyKATexpression;

import java.util.HashSet;

public class State {
    HashSet<SyKATexpression> set;
    boolean output;
    public State(HashSet<SyKATexpression> s, boolean f) {
        set = s;
        output = f;
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof State)) return false;
        State e = (State) o;
        return this.set.equals(e.set) && this.output == e.output;
    }
}
