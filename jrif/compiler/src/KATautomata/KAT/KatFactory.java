package KATautomata.KAT;

import KATautomata.utility.Util;
import jif.ast.PrincipalNode;
import jif.types.principal.*;

import java.util.List;

public class KatFactory {
    public static OneTest one = new OneTest();
    public static ZeroTest zero = new ZeroTest();
    private Util util;

    public KatFactory(Util u) {
        util = u;
    }

    public static TestExpr getTest(List<Principal> principals) {
        if (principals.size() == 0)
            return KatFactory.ZeroTest();
        TestExpr t = KatFactory.PrimitiveTest(principals.get(0));
        for(int i=1; i<principals.size(); i++) {
            t = KatFactory.PlusTest(t, KatFactory.PrimitiveTest(principals.get(i)));
        }
        return t;
    }

    public static OneTest OneTest() { return one; }

    public static ZeroTest ZeroTest() { return zero; }

    public static TestExpr PrimitiveTest(Principal p) {
        if (p instanceof BottomPrincipal) return OneTest();
        else if (p instanceof TopPrincipal) return ZeroTest();
        return new PrimitiveTest(p);
    }

    public static Action Action(boolean[] truthValue) {
        return new Action(truthValue);
    }

    public static ConcatExpr ConcatExpr(KatExpr p, KatExpr q) {
        return new ConcatExpr(p, q);
    }

    public static ConcatTest ConcatTest(TestExpr p, TestExpr q) {
        return new ConcatTest(p, q);
    }

    public static PlusExpr PlusExpr(KatExpr p, KatExpr q) {
        return new PlusExpr(p, q);
    }

    public static PlusTest PlusTest(TestExpr p, TestExpr q) {
        return new PlusTest(p, q);
    }

    public static NegateTest NegateTest(TestExpr p) {
        return new NegateTest(p);
    }

    public static StarExpr StarExpr(KatExpr p) {
        return new StarExpr(p);
    }
}
