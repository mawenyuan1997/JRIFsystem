package KATautomata.KAT;

import jif.ast.PrincipalNode;
import jif.types.principal.DynamicPrincipal;
import jif.types.principal.ExternalPrincipal;
import jif.types.principal.Principal;

import java.util.List;

public class KatFactory {
    public static OneTest one = new OneTest();
    public static ZeroTest zero = new ZeroTest();

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

    public static PrimitiveTest PrimitiveTest(Principal name) {
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
