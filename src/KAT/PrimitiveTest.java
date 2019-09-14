package KAT;

import KAToperator.KATexpressionVisitor;

public class PrimitiveTest extends TestExpression {

    public String id;
    public int index;

    public PrimitiveTest(String str,int i) {
        id = str;
        index = i;
    }

    public Object accept(KATexpressionVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PrimitiveTest)) return false;
        PrimitiveTest e = (PrimitiveTest) o;
        return this.id.equals(e.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
