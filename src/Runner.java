import KAT.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<String> prin = new ArrayList<>();
        prin.add("A"); prin.add("B"); prin.add("C");
        List<String> actions = new ArrayList<>();
        actions.add("f"); actions.add("g");
        KATexpression expr = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new PrimitiveAction("f")
                ),
                new PrimitiveTest("B")
        );

        KATautomata auto = new KATautomata(prin, actions, expr);
        List<AtomAction> aps = new ArrayList<>();
        HashMap<String, Boolean> a1 = new HashMap<>();
        a1.put("A", true); a1.put("B", false); a1.put("C", true);
        HashMap<String, Boolean> a2 = new HashMap<>();
        a2.put("A", false); a2.put("B", true); a2.put("C", true);
        AtomAction ap1 = new AtomAction(a1, "g");
        aps.add(ap1);
        System.out.println(auto.check(aps, a2));

        KATprinter print = new KATprinter();
        System.out.println(auto.getStates().size());
        for(State s:auto.getStates()) {
            System.out.println((String) s.expr.accept(print));
        }
    }
}
