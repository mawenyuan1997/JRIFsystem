import KAT.*;
import KAToperator.Evaluation;
import KAToperator.KATprinter;
import KAToperator.PartialDerivative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Runner {
    /*
    public static void main(String[] args) {
        HashMap<String, Boolean> atom = new HashMap<>();
        atom.put("A", true);
        atom.put("B", false);
        atom.put("C", false);
        Evaluation E = new Evaluation(atom);
        KATexpression e1 = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new PrimitiveAction("p")
                ),
                new PrimitiveTest("B")
        );
        KATexpression e2 = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new PrimitiveAction("p")
                ),
                new PrimitiveTest("C")
        );
        KATexpression expr = new PlusExpression(e1, e2);
        PartialDerivative D = new PartialDerivative(atom, "p");
        System.out.println(expr.accept(D));
    }
    */
}
