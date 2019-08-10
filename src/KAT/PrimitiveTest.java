package KAT;

public class PrimitiveTest extends TestExpression {

    String id;

    public PrimitiveTest(String str) {
        id = str;
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
}
