package KATautomata.KAT;

import KATautomata.KAToperator.KATexpressionVisitor;
import jif.types.principal.Principal;
import polyglot.types.Type;
import polyglot.types.TypeSystem;

import java.io.Serializable;
import java.util.List;

public interface KatExpr extends Serializable {
    Object accept(KATexpressionVisitor visitor);
    List<Principal> principals();
}
