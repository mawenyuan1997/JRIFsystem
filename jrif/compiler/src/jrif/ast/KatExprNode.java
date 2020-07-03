package jrif.ast;
import KATautomata.KAT.KatExpr;
import polyglot.ast.Node;

public interface KatExprNode extends Node {
    KatExpr getType();
}
