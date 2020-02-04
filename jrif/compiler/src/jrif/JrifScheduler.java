package jrif;

import jif.ExtensionInfo;
import jif.JifScheduler;
import jrif.visit.PreciseClassChecker;
import jrif.visit.JrifIntegerBoundsChecker;
import polyglot.frontend.CyclicDependencyException;
import polyglot.frontend.JLExtensionInfo;
import polyglot.frontend.Job;
import polyglot.frontend.goals.Goal;
import polyglot.frontend.goals.VisitorGoal;
import polyglot.util.InternalCompilerError;

public class JrifScheduler extends JifScheduler{

	public JrifScheduler(ExtensionInfo extInfo, JLExtensionInfo jlext) {
		super(extInfo, jlext);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public Goal IntegerBoundsChecker(Job job) {
        Goal g = internGoal(
                new VisitorGoal(job, new JrifIntegerBoundsChecker(job)));

        try {
            addPrerequisiteDependency(g, this.ReachabilityChecked(job));
        } catch (CyclicDependencyException e) {
            throw new InternalCompilerError(e);
        }
        return g;

    }
	
	@Override
    public Goal PreciseClassChecker(Job job) {
        Goal g = internGoal(new VisitorGoal(job, new PreciseClassChecker(job)));

        try {
            addPrerequisiteDependency(g, this.ReachabilityChecked(job));
        } catch (CyclicDependencyException e) {
            throw new InternalCompilerError(e);
        }
        return g;

    }

}
