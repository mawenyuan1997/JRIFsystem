package SyKAT;

import SyKAT.BDD.BDD;
import SyKAToperator.SyKATexpressionVisitor;
import utility.Util;

public class Primitive implements SyKATexpression {
    public String id;
    public int index;
    public BDD bdd;

    public Primitive(String str, int i) {
        id = str;
        index = i;
        bdd = Util.actionToBDD(i);
    }

    @Override
    public Object accept(SyKATexpressionVisitor visitor) { return visitor.visit(this); }


}
