package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Star implements SyKATexpression {

    public SyKATexpression p;

    public Star(SyKATexpression expr) {p = expr;}

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }
}
