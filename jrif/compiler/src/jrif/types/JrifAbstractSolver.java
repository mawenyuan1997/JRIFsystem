package jrif.types;




import jif.types.AbstractSolver;
import jif.types.JifTypeSystem;
import polyglot.frontend.Compiler;



public abstract class JrifAbstractSolver extends AbstractSolver {
	
    protected JrifAbstractSolver(JifTypeSystem ts, Compiler compiler,
            String solverName) {
        this(ts, compiler, solverName, false);
    }
	
	protected JrifAbstractSolver(JifTypeSystem ts, Compiler compiler,
            String solverName, boolean useSCC) {
        super(ts,compiler,solverName,useSCC);
        bounds = new JrifVarMap(ts, getDefaultLabelBound(),
                getDefaultPrincipalBound());
    }
	
	 protected JrifAbstractSolver(JrifAbstractSolver js) {
	        super(js);
	    }

}
