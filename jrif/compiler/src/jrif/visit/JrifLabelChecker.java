package jrif.visit;

import jif.visit.LabelChecker;
import jrif.types.JrifTypeSystem;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.TypeSystem;

public class JrifLabelChecker extends LabelChecker{


    final protected JrifTypeSystem rifts;

   
    public JrifLabelChecker(Job job, TypeSystem ts, NodeFactory nf,
            boolean warningsEnabled, boolean solvePerClassBody,
            boolean solvePerMethod, boolean doLabelSubst) {
    	
    	super(job,ts,nf,warningsEnabled,solvePerClassBody,solvePerMethod,doLabelSubst);
        this.rifts = (JrifTypeSystem) ts;
    }



    public JrifTypeSystem typeSystem() {
        return rifts;
    }

    
}
