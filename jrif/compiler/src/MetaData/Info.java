package MetaData;

import KATautomata.utility.Util;

public class Info {
    public static String[] primTests = new String[]{
            "jif.types.principal.DynamicPrincipal_c",
            "jif.types.principal.ConjunctivePrincipal_c",
            "jif.types.principal.DisjunctivePrincipal_c",
            "jif.types.principal.ExternalPrincipal_c",
            "jif.types.principal.ParamPrincipal_c",
            "jif.types.principal.UnknownPrincipal_c",
            "jif.types.principal.VarPrincipal_c"
    };
    public static String[] primActions = new String[]{"p1","p2","p3"};
    public static Util util = new Util(primTests, primActions);
}
