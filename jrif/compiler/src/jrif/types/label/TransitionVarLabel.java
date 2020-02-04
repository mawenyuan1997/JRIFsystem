package jrif.types.label;

import polyglot.ast.Id;

public interface TransitionVarLabel extends RifVarLabel {

    Id transition();

    RifVarLabel innerRifLabel();

}
