package MetaData;

import KATautomata.utility.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Info {
    public static String[] primTests = new String[]{
            "Alice",
           "Bob"
    };
    public static String[] primActions = new String[]{"p1","p2","p3"};
    public static Util util = new Util(primTests, primActions);
    public static HashMap<String, boolean[]> actionToPrims = new HashMap<String, boolean[]>() {
        {
            put("f0", new boolean[]{true,true,false});
            put("f", new boolean[]{true,true,false});
            put("f1", new boolean[]{true,true,false});
        }
    };
}
