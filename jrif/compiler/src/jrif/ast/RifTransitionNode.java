package jrif.ast;

import jrif.types.RifTransition;
import polyglot.ast.Id;

public interface RifTransitionNode extends RifComponentNode {

    public Id name();

    public Id lstate();

    public Id rstate();

    public RifTransition transition();

}
