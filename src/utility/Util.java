package utility;

import KAT.*;
import SyKAT.*;
import SyKAT.BDD.BDD;
import SyKAT.BDD.BooleanFunction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Util {
    public String[] primTests;
    public String[] primActions;
    // mapping from primitive test to its index in primTest
    public HashMap<String, Integer> primTestMapping;
    // mapping from primitive action to its index in primActions
    public HashMap<String, Integer> primActionMapping;
    public int numOfTest;   // length of primTests
    public int numOfAction; // length of primActions

    public Util(String[] tests, String[] actions) {
        primTests = tests.clone();
        primActions = actions.clone();
        primTestMapping = new HashMap<>();
        primActionMapping = new HashMap<>();
        for(int i=0; i<primTests.length; i++) {
            primTestMapping.put(primTests[i], i);
        }
        for(int i=0; i<primActions.length; i++) {
            primActionMapping.put(primActions[i], i);
        }
        numOfAction = primActions.length;
        numOfTest = primTests.length;
    }

    /**
     * return a boolean function from a KAT test
     * input of the function is a boolean array with values both
     * for primTest and primAction (primTest first)
     * @param test
     * @return
     */
    public BooleanFunction getFunction(TestExpression test) {
        int n = numOfTest + numOfAction;
        if (test instanceof OneTest) {
            return new BooleanFunction("one", n, 1) {
                public Boolean execute(boolean[] input) {
                    return true;
                }
            };
        }
        if (test instanceof ZeroTest) {
            return new BooleanFunction("zero", n, 1) {
                public Boolean execute(boolean[] input) {
                    return false;
                }
            };
        }
        if (test instanceof NegateTest) {
            return new BooleanFunction("negate", n, 1) {
                final BooleanFunction l = getFunction(((NegateTest) test).test);
                public Boolean execute(boolean[] input) {
                    boolean res = l.execute(input);
                    return !res;
                }
            };
        }
        if (test instanceof PrimitiveTest) {
            return new BooleanFunction("prim", n, 1) {
                public Boolean execute(boolean[] input) {
                    String testId = ((PrimitiveTest) test).id;
                    return input[primTestMapping.get(testId)];
                }
            };
        }
        if (test instanceof ConcatTest) {
            final BooleanFunction l = getFunction(((ConcatTest) test).left);
            final BooleanFunction r = getFunction(((ConcatTest) test).right);
            return new BooleanFunction("cancat", n, 1) {
                public Boolean execute(boolean[] input) {
                    boolean lres = l.execute(input);
                    boolean rres = r.execute(input);
                    return lres && rres;
                }
            };
        }
        else  {// if (test instanceof PlusTest)
            final BooleanFunction l = getFunction(((PlusTest) test).left);
            final BooleanFunction r = getFunction(((PlusTest) test).right);
            return new BooleanFunction("plus", n, 1) {
                public Boolean execute(boolean[] input) {
                    boolean lres = l.execute(input);
                    boolean rres = r.execute(input);
                    return lres || rres;
                }
            };
        }

    }

    /**
     * translate a KAT expression to Symbolic KAT expression
     * @param expr
     * @return
     */
    public SyKATexpression translate(KATexpression expr) {
        if (expr instanceof ConcatExpression) {
            SyKATexpression l = translate(((ConcatExpression) expr).left);
            SyKATexpression r = translate(((ConcatExpression) expr).right);
            return new Concat(l, r);
        }
        if (expr instanceof PlusExpression) {
            SyKATexpression l = translate(((PlusExpression) expr).left);
            SyKATexpression r = translate(((PlusExpression) expr).right);
            return new Plus(l, r);
        }
        if (expr instanceof StarExpression) {
            SyKATexpression p = translate(((StarExpression) expr).p);
            return new Star(p);
        }
        if (expr instanceof Action) {
            int n = numOfTest + numOfAction;
            // input of the function is a boolean array with values both
            // for primTest and primAction (primTest first)
            BooleanFunction f = new BooleanFunction("action", n, 1) {
                public Boolean execute(boolean[] input) {
                    HashSet<String> primSet = ((Action) expr).primActions;
                    boolean[] actionInput = Arrays.copyOfRange(input, numOfTest, n);
                    for(int i=0; i<primActions.length; i++) {
                        if (primSet.contains(primActions[i]) != actionInput[i])
                            return false;
                    }
                    return true;
                }
            };
            return new BDD(f, true);
        }
        // if expr instanceof TestExpression
        final BooleanFunction f = getFunction((TestExpression) expr);
        return new BDD(f, false);
    }
}
