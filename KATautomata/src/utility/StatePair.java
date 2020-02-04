package utility;

public class StatePair {
    public State e1, e2;
    public StatePair(State s1, State s2) {
        e1 = s1;
        e2 = s2;
    }

    @Override
    public int hashCode() {
        return e1.hashCode()*37 + e2.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StatePair)) return false;
        StatePair sp = (StatePair) o;
        return sp.e1.equals(e1) && sp.e2.equals(e2);
    }

    @Override
    public String toString() {
        return "("+e1.toString()+","+e2.toString()+")";
    }

    public State getFirst() {return e1;}

    public State getSecond() {return e2;}
}
