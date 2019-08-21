package KAT;

import KAToperator.KATexpressionVisitor;

public class PrimitiveAction implements KATexpression{

    public String id;

    public PrimitiveAction(String str) {
        id = str;
    }

    @Override
    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PrimitiveAction)) return false;
        PrimitiveAction e = (PrimitiveAction) o;
        return this.id.equals(e.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
