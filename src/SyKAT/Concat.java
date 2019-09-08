package SyKAT;

import SyKAToperator.SyKATexpressionVisitor;

public class Concat implements SyKATexpression {

    public SyKATexpression left,right;

    public Concat(SyKATexpression l, SyKATexpression r) {
        left = l;
        right = r;
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }
}
