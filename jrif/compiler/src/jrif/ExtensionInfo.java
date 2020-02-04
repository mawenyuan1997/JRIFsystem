package jrif;

import polyglot.lex.Lexer;
import jrif.parse.Lexer_c;
import jrif.parse.Grm;
import jrif.ast.*;
import jrif.types.*;
import jrif.visit.JrifLabelChecker;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.main.*;
import polyglot.types.*;
import polyglot.util.*;

import java.io.*;
import java.util.Set;

import jif.JifScheduler;
import jif.OutputExtensionInfo;
import jif.ast.JifNodeFactory;
import jif.ast.JifNodeFactory_c;
import jif.types.JifTypeSystem;
import jif.types.JifTypeSystem_c;
import jif.visit.LabelChecker;

/**
 * Extension information for jrif extension.
 */
public class ExtensionInfo extends jif.ExtensionInfo {
	

    @Override
    public String defaultFileExtension() {
        return "jrif";
    }

    @Override
    public String compilerName() {
        return "jrifc";
    }

    
    @Override
    public Parser parser(Reader reader, Source source, ErrorQueue eq) {

        polyglot.lex.Lexer lexer = new jrif.parse.Lexer_c(reader, source, eq);
        polyglot.parse.BaseParser grm = new jrif.parse.Grm(lexer,
                (JrifTypeSystem) ts, (JrifNodeFactory) nf, eq);

        return new CupParser(grm, source, eq);
    }

    @Override
    public Set<String> keywords() {
        return new jrif.parse.Lexer_c(null).keywords();
    }

    
    @Override
    protected NodeFactory createNodeFactory() {
        return new JrifNodeFactory_c();
    }

    @Override
    protected TypeSystem createTypeSystem() {
        // Need to pass it the jlTypeSystem() so that
        // it can look up jif.lang.Principal.
        return new JrifTypeSystem_c(jlTypeSystem());
    }
    
    @Override
    public JrifLabelChecker createLabelChecker(Job job, boolean warningsEnabled,
            boolean solvePerClassBody, boolean solvePerMethod,
            boolean doLabelSubst) {
        return new JrifLabelChecker(job, typeSystem(), nodeFactory(),
                warningsEnabled, solvePerClassBody, solvePerMethod,
                doLabelSubst);
    }
    
    @Override
    protected Scheduler createScheduler() {
        return new JrifScheduler(this, jlext);
    }

}
