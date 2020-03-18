package KATautomata.utility;

import KATautomata.SyKAT.SyKATexpression;

import java.util.HashSet;

public class State {
    public HashSet<SyKATexpression> s;

    public State(HashSet<SyKATexpression> ss) {
        s = ss;
    }

    public State(SyKATexpression e) {
        s = new HashSet<>();
        s.add(e);
    }

    public State() {
        s = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof State)) return false;
        State e = (State) o;
        return e.s.equals(s);
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public State merge(State y) {
        HashSet<SyKATexpression> temp = new HashSet<>(s);
        temp.addAll(y.getSet());
        return new State(temp);
    }

    public HashSet<SyKATexpression> getSet() {
        return s;
    }

    public boolean has(SyKATexpression e) {
        return s.contains(e);
    }

    public int size() {
        return s.size();
    }
    @Override
    public String toString() {
        return s.toString();
    }
    @Override
    public int hashCode() {
        return s.hashCode();
    }
}
