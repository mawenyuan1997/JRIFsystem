package jrif.types.label;

import jif.types.label.VarLabel;
import polyglot.ast.Id;

public interface RifVarLabel extends VarLabel {

    RifVarLabel takeTransition(Id transition);

}
