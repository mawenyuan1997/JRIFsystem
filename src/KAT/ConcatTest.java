package KAT;

public class ConcatTest extends TestExpression {

    TestExpression left, right;

    public ConcatTest(TestExpression p, TestExpression q) {
        left = p;
        right = q;
    }

    public Object accept(KATexpressionVisitor visitor) {
       return  visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ConcatTest)) return false;
        ConcatTest e = (ConcatTest) o;
        return this.left.equals(e.left) && this.right.equals(e.right);
    }
}
