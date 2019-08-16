package BDD;

import java.util.HashMap;

/*
  A is an ordered type
 */
public class BDDnode <A, B> {
    A a;
    BDDnode <A, B> l, r;
    B b = null;

    // decision node
    public BDDnode(A a, BDDnode l, BDDnode r) {
        this.a = a;
        this.l = l;
        this.r = r;
    }

    // leaf node
    public BDDnode(B b) {
        this.b = b;
    }

    public B evaluate(HashMap<A, Boolean> alpha) {
        if (b != null) return b;
        if (alpha.get(a))
            return r.evaluate(alpha);
        else
            return l.evaluate(alpha);
    }
}
