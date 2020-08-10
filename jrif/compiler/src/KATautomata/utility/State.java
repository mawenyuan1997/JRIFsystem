package KATautomata.utility;

import KATautomata.SyKAT.SyKatExpr;
import polyglot.util.SerialVersionUID;

import java.io.Serializable;
import java.util.HashSet;

/**
 * State class of symbolic KAT automata
 *
 * author: Wenyuan Ma   date:2020-06-17
 */
public class State implements Serializable {
    private static final long serialVersionUID = SerialVersionUID.generate();
    public HashSet<SyKatExpr> s;

    public State(HashSet<SyKatExpr> ss) {
        s = ss;
    }

    public State(SyKatExpr e) {
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
        HashSet<SyKatExpr> temp = new HashSet<>(s);
        temp.addAll(y.getSet());
        return new State(temp);
    }

    public HashSet<SyKatExpr> getSet() {
        return s;
    }

    public boolean has(SyKatExpr e) {
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
