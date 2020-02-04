package jrif.visit;

import java.util.LinkedHashSet;
import java.util.Set;

import jif.ast.DowngradeExpr;
import jif.visit.NotNullChecker;
import jrif.visit.PreciseClassChecker.AccessPath;
import jrif.ast.ReclassifyExpr;
import polyglot.ast.ArrayInit;
import polyglot.ast.Binary;
import polyglot.ast.Cast;
import polyglot.ast.Conditional;
import polyglot.ast.Expr;
import polyglot.ast.Lit;
import polyglot.ast.New;
import polyglot.ast.NewArray;
import polyglot.ast.NullLit;
import polyglot.ast.Special;
import polyglot.frontend.Job;
import polyglot.visit.DataFlow;

public class JrifNotNullCkecker extends NotNullChecker {

	public JrifNotNullCkecker(Job job) {
		super(job);
		// TODO Auto-generated constructor stub
	}
	
    static class DataFlowItem extends DataFlow.Item {
        // contains objects of type VarInstance that are not null
        Set<AccessPath> notNullAccessPaths;

        // if the result of the expression is not null.
        boolean resultIsNotNull;

        DataFlowItem() {
            notNullAccessPaths = new LinkedHashSet<AccessPath>();
            resultIsNotNull = false;
        }

        DataFlowItem(Set<AccessPath> notNullAccessPaths,
                boolean resultIsNotNull) {
            this.notNullAccessPaths = notNullAccessPaths;
            this.resultIsNotNull = resultIsNotNull;
        }

        DataFlowItem(DataFlowItem d) {
            notNullAccessPaths =
                    new LinkedHashSet<AccessPath>(d.notNullAccessPaths);
        }

        static boolean exprIsNotNullStatic(Expr e) {
            // expression is not null if it is a "new" expression, "this"
            // or if it is a cast of a non-null expression.
            return (e instanceof New) || (e instanceof NewArray)
                    || (e instanceof ArrayInit) || (e instanceof Special)
                    || (e instanceof Lit && !(e instanceof NullLit))
                    || (e instanceof Binary && ((Binary) e).type().typeSystem()
                            .String().equals(((Binary) e).type()))
                    || (e instanceof Cast
                            && exprIsNotNullStatic(((Cast) e).expr()))
                    || (e instanceof DowngradeExpr
                            && exprIsNotNullStatic(((DowngradeExpr) e).expr()))
                    || (e instanceof ReclassifyExpr && exprIsNotNullStatic(((ReclassifyExpr) e)
                            .expr()))
                    || (e instanceof Conditional
                            && exprIsNotNullStatic(
                                    ((Conditional) e).consequent())
                            && exprIsNotNullStatic(
                                    ((Conditional) e).alternative()));
        }

        boolean exprIsNotNull(Expr e) {
            // expression is not null if it is a "new" expression,
            // or if it is an accesspath that is contained in
            // notNullVariables, or if it is a cast of a
            // non-null expression.
            AccessPath ap = PreciseClassChecker.findAccessPathForExpr(e);
            return exprIsNotNullStatic(e)
                    || (ap != null && notNullAccessPaths.contains(ap))
                    || (e instanceof Cast && exprIsNotNull(((Cast) e).expr()))
                    || (e instanceof DowngradeExpr
                            && exprIsNotNull(((DowngradeExpr) e).expr()))
                    || (e instanceof ReclassifyExpr && exprIsNotNull(((ReclassifyExpr) e)
                            .expr()))
                    || (e instanceof Conditional && (exprIsNotNullStatic(
                            ((Conditional) e).consequent())
                            && exprIsNotNullStatic(
                                    ((Conditional) e).alternative())));
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof DataFlowItem) {
                return this.notNullAccessPaths == ((DataFlowItem) o).notNullAccessPaths
                        || this.notNullAccessPaths
                                .equals(((DataFlowItem) o).notNullAccessPaths);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return notNullAccessPaths.hashCode();
        }

        @Override
        public String toString() {
            return "[nn access paths: " + notNullAccessPaths + "]";
        }
    }

}
