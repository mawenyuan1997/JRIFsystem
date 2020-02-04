package jrif.visit;

import jif.ast.DowngradeExpr;
import jif.visit.IntegerBoundsChecker;
import jrif.ast.ReclassifyExpr;
import polyglot.ast.Assign;
import polyglot.ast.Binary;
import polyglot.ast.Conditional;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Local;
import polyglot.ast.Unary;
import polyglot.frontend.Job;
import polyglot.types.LocalInstance;

public class JrifIntegerBoundsChecker extends IntegerBoundsChecker{

	public JrifIntegerBoundsChecker(Job job) {
		super(job);
		// TODO Auto-generated constructor stub
	}
	
	
	 /**
     * Finds the tightest numeric range for expr, given dataflow information
     * available immediately before evaluation of this expression (but after any
     * sub-expressions).
     */
	@Override
    protected Interval findNumericRange(Expr expr, DataFlowItem df) {
        if (!expr.type().isNumeric()) {
            throw new IllegalArgumentException();
        }

        if (expr.isConstant() && expr.constantValue() instanceof Number) {
            long n = ((Number) expr.constantValue()).longValue();
            return Interval.singleton(n);
        }

        Interval best = Interval.FULL;

        if (expr instanceof Field) {
            Field f = (Field) expr;
            if (f.name().equals("length") && f.target().type().isArray()) {
                // it's an expression x.length, so it must be non negative
                best = best.intersect(Interval.POS);

            }
        }
        if (df == null) {
            return best;
        }

        if (expr instanceof Local) {
            LocalInstance li = ((Local) expr).localInstance();
            Long low = findNumericBound(li, df, Bound.lower(false));
            Long high = findNumericBound(li, df, Bound.upper(false));
            best = best.intersect(new Interval(low, high));
        } else if (expr.isConstant() && expr.constantValue() instanceof Number) {
            long n = ((Number) expr.constantValue()).longValue();
            best = best.intersect(Interval.singleton(n));
        } else if (expr instanceof Unary) {
            Unary u = (Unary) expr;
            Interval rng = getExprBounds(u.expr());

            if (u.operator() == Unary.POST_INC
                    || u.operator() == Unary.POST_DEC) {
                best = best.intersect(rng);
            } else if (u.operator() == Unary.PRE_INC) {
                best = best.intersect(rng.shift(1));
            } else if (u.operator() == Unary.PRE_DEC) {
                best = best.intersect(rng.shift(-1));
            }
        } else if (expr instanceof Conditional) {
            Conditional c = (Conditional) expr;
            Interval con = getExprBounds(c.consequent());
            Interval alt = getExprBounds(c.alternative());
            best = best.intersect(con.union(alt));
        } else if (expr instanceof DowngradeExpr) {
            DowngradeExpr e = (DowngradeExpr) expr;
            best = best.intersect(getExprBounds(e.expr()));
        }
        else if (expr instanceof ReclassifyExpr) {
            ReclassifyExpr e = (ReclassifyExpr) expr;
            best = best.intersect(getExprBounds(e.expr()));
        }
        else if (expr instanceof Binary) {
            Binary b = (Binary) expr;
            Interval left = getExprBounds(b.left());
            Interval right = getExprBounds(b.right());

            if (b.operator() == Binary.ADD) {
                best = best.intersect(left.add(right));
            } else if (b.operator() == Binary.SUB) {
                best = best.intersect(left.subtract(right));
            } else if (b.operator() == Binary.MUL) {
                best = best.intersect(left.multiply(right));
            }
        } else if (expr instanceof Assign) {
            Assign a = (Assign) expr;
            Interval left = getExprBounds(a.left());
            Interval right = getExprBounds(a.right());

            if (a.operator() == Assign.ASSIGN) {
                best = best.intersect(right);
            } else if (a.operator() == Assign.ADD_ASSIGN) {
                best = best.intersect(left.add(right));
            } else if (a.operator() == Assign.SUB_ASSIGN) {
                best = best.intersect(left.subtract(right));
            } else if (a.operator() == Assign.MUL_ASSIGN) {
                best = best.intersect(left.multiply(right));
            }
        } else if (expr instanceof Field) {
            Field f = (Field) expr;

            if ("length".equals(f.name()) && f.type().isInt()
                    && f.target().type().isArray()) {
                // it's an array length, e.g., x.length.
                // thus it is of non-negative length
                best = best.intersect(Interval.POS);
            }
        }

        return best;
    }

}
