package test.KatAutomata;

import KATautomata.SyKAT.BDD.BDD;
import KATautomata.SyKAT.BDD.BooleanFunction;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class BDDtest {

    @org.junit.jupiter.api.Test
    void testE() {
        final BooleanFunction myFunction = new BooleanFunction("myFunc", 3, 1) {
            public Boolean execute(boolean[] input)
            {
                assert(input.length == 3);
                return input[0] || input[1] && input[2];
            }
        };

        System.out.println("Building SyKAT.BDD...");
        final BDD<Boolean> myBDD = new BDD(myFunction);
        boolean[] test1 = new boolean[]{true, false, false};
        assert myBDD.execute(test1) == true;
        boolean[] test2 = new boolean[]{false, true, false};
        assert myBDD.execute(test2) == false;
        boolean[] test3 = new boolean[]{true, true, false};
        assert myBDD.execute(test3) == true;
    }

}
