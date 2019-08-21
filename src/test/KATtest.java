package test;

import KAT.*;
import KAToperator.*;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class KATtest {

    HashMap<String, Boolean> atom = new HashMap<>();

    @org.junit.jupiter.api.Test
    void testE() {

        atom.put("A", true);
        atom.put("B", false);
        atom.put("C", false);
        Evaluation E = new Evaluation(atom);
        KATexpression trivial = new PrimitiveTest("A");
        assertEquals(true, trivial.accept(E));
        KATexpression expr = new ConcatExpression(
                                new PrimitiveTest("A"),
                                new PrimitiveAction("p")
        );
        assertEquals(false, expr.accept(E));
    }

    @org.junit.jupiter.api.Test
    void testDerivative() {
        atom.put("A", true);
        atom.put("B", false);
        atom.put("C", false);
        Evaluation E = new Evaluation(atom);
        KATexpression expr = new ConcatExpression(
                                new ConcatExpression(
                                        new PrimitiveTest("A"),
                                        new PrimitiveAction("p")
                                ),
                                new PrimitiveTest("B")
        );
        Derivative D = new Derivative(atom, "p");
        KATprinter print = new KATprinter();
        Simplify S = new Simplify();
        KATexpression d = (KATexpression) expr.accept(D);
        KATexpression sd = (KATexpression) d.accept(S);
        assertEquals("((0)(p)+(1)(1))(B)+(0)(0)", d.accept(print));
        assertEquals("B", sd.accept(print));
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        KATexpression expr1 = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new PrimitiveAction("p")
                ),
                new PrimitiveTest("B")
        );
        KATexpression expr2 = new ConcatExpression(
                new ConcatExpression(
                        new PrimitiveTest("A"),
                        new PrimitiveAction("p")
                ),
                new PrimitiveTest("B")
        );
        assert(expr1.equals(expr2));
        KATexpression e1 = new PlusExpression(
                new PrimitiveTest("A"),
                new PrimitiveTest("B")
        );
        KATexpression e2 = new PlusExpression(
                new PrimitiveTest("B"),
                new PrimitiveTest("A")
        );
        assert(e1.equals(e2));
    }

    @org.junit.jupiter.api.Test
    void testPartialDerivative() {
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
        System.out.println((HashSet<KATexpression>) expr.accept(D));
        HashSet<KATexpression> set = (HashSet<KATexpression>) expr.accept(D);

        assert(set.contains(new ConcatExpression(
                new OneTest(),
                new PrimitiveTest("B")
        )));
        assert(set.contains(new ConcatExpression(
                new OneTest(),
                new PrimitiveTest("C")
        )));
    }
}