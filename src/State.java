import KAT.KATexpression;

import java.util.HashMap;

public class State {

    KATexpression expr;
    public State(KATexpression e) {
        expr = e;
    }

    @Override
    public int hashCode() {
        return expr.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof State)) {
            return false;
        }
        State s = (State) o;
        return this.expr.equals(s.expr);
    }
}
