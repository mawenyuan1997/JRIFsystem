package MetaData;

import KATautomata.utility.Util;

public class Info {
    public static String[] primTests = new String[]{
            "Alice",
           "Bob"
    };
    public static String[] primActions = new String[]{"p1","p2","p3"};
    public static Util util = new Util(primTests, primActions);
}
