package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.principal.Principal;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.SerialVersionUID;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Action implements KatExpr {
    private static final long serialVersionUID = SerialVersionUID.generate();
    private HashSet<String> primActions;
    public static Action allAction = new Action();

    private Action() {}

    public Action(List<String> primActionlist) {
        this.primActions = new HashSet<>(primActionlist);
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Principal> principals() {
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Action)) return false;
        Action e = (Action) o;
        if (e == allAction) return this == allAction;
        return this.primActions.equals(e.primActions);
    }

    @Override
    public int hashCode() {
        if (this == allAction) return "all".hashCode();
        return primActions.hashCode();
    }


    public HashSet<String> getPrimActions() {
        return primActions;
    }
}
