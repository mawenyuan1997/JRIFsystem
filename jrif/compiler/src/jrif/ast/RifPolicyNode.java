package jrif.ast;

import java.util.List;

import jif.ast.PolicyNode;

public interface RifPolicyNode extends PolicyNode {

    public List<RifComponentNode> components();

}
