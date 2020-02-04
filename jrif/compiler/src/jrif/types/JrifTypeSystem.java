package jrif.types;

import polyglot.ast.Id;
import polyglot.types.*;
import polyglot.util.Position;

import java.util.Collection;

import jif.types.*;
import jif.types.label.ConfPolicy;
import jif.types.label.Label;
import jif.types.principal.Principal;
import jrif.types.label.RifConfPolicy;
import jrif.types.label.RifDynamicLabel;
import jrif.types.label.RifIntegPolicy;
import jrif.types.label.RifVarLabel;

public interface JrifTypeSystem extends JifTypeSystem {

	RifVarLabel freshRifLabelVariable(Position pos, String s, String description);
	RifConfPolicy rifreaderPolicy(Position pos, RifFSM fsm);
	RifIntegPolicy rifwriterPolicy(Position pos, RifFSM fsm);
	RifDynamicLabel rifDynamicLabel(Position pos, Id name, Label lbl);
	RifFSM topfsm(Position pos);
	RifFSM bottomfsm(Position pos);

}
