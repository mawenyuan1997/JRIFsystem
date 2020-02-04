package jrif.types.label;

import java.util.List;

import jif.types.label.Label;
import polyglot.ast.Id;

public interface RifDynamicLabel extends Label {

    Id getName();

    Label getLabel();

    List<Id> transToBeTaken(Label rdl, List<Id> list);

}
