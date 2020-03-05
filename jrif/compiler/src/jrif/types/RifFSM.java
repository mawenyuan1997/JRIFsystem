package jrif.types;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jif.types.JifContext;
import jif.types.LabelSubstitution;
import jif.types.PathMap;
import jif.visit.LabelChecker;
import polyglot.ast.Id;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import utility.SymDFA;

public interface RifFSM extends Serializable {

    //RifFSMstate currentState();

    SymDFA takeTransition(Id action);

    boolean equalsFSM(RifFSM other, List<String> visited);

    boolean leqFSM(SymDFA other, List<String> visited);

    boolean isCanonical();

    boolean isRuntimeRepresentable();

    List<Type> throwTypes(TypeSystem ts);

    boolean isBottomConfidentiality();

    boolean isTopConfidentiality();

    @Override
    String toString();

    RifFSM subst(LabelSubstitution substitution) throws SemanticException;

    PathMap labelCheck(JifContext A, LabelChecker lc);

    //Map<String, RifFSMstate> states();

    boolean isBottom(List<String> newvisited);

    boolean isTop(List<String> visited);

}
