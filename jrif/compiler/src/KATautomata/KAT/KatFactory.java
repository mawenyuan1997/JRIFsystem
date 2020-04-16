package KATautomata.KAT;

import jif.types.principal.DynamicPrincipal;
import jif.types.principal.ExternalPrincipal;
import jif.types.principal.Principal;

import java.util.List;

public class KatFactory {
    public static OneTest one = new OneTest();
    public static ZeroTest zero = new ZeroTest();

    public static TestExpr getPrimTest(Principal p, boolean isReader) {
        if (p.isBottomPrincipal()) {
            return isReader?OneTest() : ZeroTest();
        } else if (p.isTopPrincipal()) {
            return isReader?ZeroTest() : OneTest();
        } else {
            return KatFactory.PrimitiveTest(p.getClass().getName());
        }
    }

    public static OneTest OneTest() { return one; }

    public static ZeroTest ZeroTest() { return zero; }

    public static PrimitiveTest PrimitiveTest(String name) {
        return new PrimitiveTest(name);
    }

    public static Action Action(List<String> primActionlist) {
        return new Action(primActionlist);
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
