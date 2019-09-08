package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Plus implements SyKATexpression {

    public SyKATexpression left,right;

    public Plus(SyKATexpression l, SyKATexpression r) {
        left = l;
        right = r;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }
}
