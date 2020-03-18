package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Action implements KATexpression{

    public HashSet<String> primActions;

    public Action(List<String> primActionlist) { this.primActions = new HashSet<>(primActionlist); }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Action)) return false;
        Action e = (Action) o;
        return this.primActions.equals(e.primActions);
    }

    @Override
    public int hashCode() {
        return primActions.hashCode();
    }
}
