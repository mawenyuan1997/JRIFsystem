package jrif.ast;

import java.util.List;

import jif.ast.PrincipalNode;
import jrif.types.RifState;
import polyglot.ast.Id;

public interface RifStateNode extends RifComponentNode {

    public Id name();

    public List<PrincipalNode> principals();

    public RifState state();

    public boolean isCurrent();

}
