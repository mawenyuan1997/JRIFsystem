package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Primitive implements SyKATexpression {
    public String id;

    public Primitive(String str) {
        id = str;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }
}
