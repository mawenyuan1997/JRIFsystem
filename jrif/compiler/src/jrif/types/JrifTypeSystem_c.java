package jrif.types;

import KATautomata.KAT.*;
import KATautomata.SyKAT.Concat;
import KATautomata.SyKAT.Plus;
import KATautomata.SyKAT.SyKATexpression;
import MetaData.Info;
import jif.types.JifTypeSystem_c;
import polyglot.types.TypeSystem;
import KATautomata.utility.SymDFA;

import polyglot.ast.Cast;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.Id;
import polyglot.ast.Local;
import polyglot.ast.NullLit;
import polyglot.ast.Receiver;
import polyglot.ast.Special;
import polyglot.ast.TypeNode;
import polyglot.ext.param.types.Subst;
import polyglot.types.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import jif.ast.DowngradeExpr;
import jif.ast.LabelExpr;
import jif.ast.PrincipalExpr;
import jif.ast.PrincipalNode;
import jif.translate.LabelToJavaExpr;
import jif.types.*;
import jif.types.hierarchy.LabelEnv;
import jrif.ast.ReclassifyExpr;
import jrif.extension.JrifLabelTypeCheckUtil;
import jrif.translate.JrifPairLabelToJavaExpr_c;
import jrif.types.hierarchy.JrifLabelEnv_c;
import jrif.types.label.RifConfPolicy;
import jrif.types.label.RifDynamicLabel;
import jrif.types.label.RifDynamicLabel_c;
import jrif.types.label.RifIntegPolicy;
import jrif.types.label.RifJoinConfPolicy_c;
import jrif.types.label.RifJoinIntegPolicy_c;
import jrif.types.label.RifReaderPolicy_c;
import jrif.types.label.RifVarLabel;
import jrif.types.label.RifVarLabel_c;
import jrif.types.label.RifWriterPolicy_c;
import jif.types.label.AccessPath;
import jif.types.label.AccessPathClass;
import jif.types.label.AccessPathConstant;
import jif.types.label.AccessPathField;
import jif.types.label.AccessPathLocal;
import jif.types.label.AccessPathThis;
import jif.types.label.ConfPolicy;
import jif.types.label.IntegPolicy;
import jif.types.label.Label;
import jif.types.label.ReaderPolicy;
import jif.types.label.WriterPolicy;
import jif.types.principal.Principal;
//
public class JrifTypeSystem_c extends JifTypeSystem_c implements JrifTypeSystem {
//
	public JrifTypeSystem_c(TypeSystem jlts) {
		super(jlts);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected LabelToJavaExpr pairLabelTranslator() {
        return new JrifPairLabelToJavaExpr_c();
    }

    @Override
    public RifVarLabel freshRifLabelVariable(Position pos, String s,
            String description) {
        RifVarLabel t = new RifVarLabel_c(s, description, this, pos);
        return t;
    }

    @Override
    public RifConfPolicy rifreaderPolicy(Position pos, RifFSM fsm) {
        RifConfPolicy t = new RifReaderPolicy_c(fsm, this, pos);
        return t;
    }
//
    @Override
    public RifIntegPolicy rifwriterPolicy(Position pos, RifFSM fsm) {
        RifIntegPolicy t = new RifWriterPolicy_c(fsm, this, pos);
        return t;
    }
//
    @Override
    public RifDynamicLabel rifDynamicLabel(Position pos, Id name, Label lbl) {
        RifDynamicLabel t = new RifDynamicLabel_c(name, lbl, this, pos);
        return t;
    }

    protected JrifLabelTypeCheckUtil jrifltcu = null;

    public JrifLabelTypeCheckUtil labelTypeCheckUtil() {
        if (jrifltcu == null) jrifltcu = new JrifLabelTypeCheckUtil(this);
        return jrifltcu;
    }

    @Override
    protected LabelEnv createEmptyLabelEnv() {
        return new JrifLabelEnv_c(this, false);
    }

    @Override
    public LabelEnv createLabelEnv() {
        return new JrifLabelEnv_c(this, true);
    }

    @Override
    public Solver createSolver(String solverName) {
        return new JrifSolverGLB(this, extInfo.compiler(), solverName);
    }

    @Override
    protected Subst<ParamInstance, Param> substImpl(
            Map<ParamInstance, ? extends Param> substMap) {
        return new JrifSubst_c(this, substMap);
    }

    @Override
    public RifFSM topfsm(Position pos) {
//        System.out.println("top");
        SyKATexpression e = Info.util.translate(new ZeroTest());
        return new SymDFA(Info.util, e);
    }
//
    @Override
    public RifFSM bottomfsm(Position pos) {
//        System.out.println("bottom");
        SyKATexpression e = Info.util.translate(new OneTest());
        return new SymDFA(Info.util, e);
    }


    @Override
    public ReaderPolicy readerPolicy(Position pos, Principal owner,
            Principal reader) {
        System.out.println("readPolicy");
        return new RifReaderPolicy_c(bottomfsm(pos), this, pos);
    }

    @Override
    public ReaderPolicy readerPolicy(Position pos, Principal owner,
            Collection<Principal> readers) {
        System.out.println("readPolicy");
        return new RifReaderPolicy_c(bottomfsm(pos), this, pos);
    }

    @Override
    public WriterPolicy writerPolicy(Position pos, Principal owner,
            Principal writer) {
        System.out.println("writePolicy");
        return new RifWriterPolicy_c(topfsm(pos), this, pos);
    }

    @Override
    public WriterPolicy writerPolicy(Position pos, Principal owner,
            Collection<Principal> writers) {
        System.out.println("writePolicy" + owner + writers);
        return new RifWriterPolicy_c(topfsm(pos), this, pos);
    }

    @Override
    public ConfPolicy bottomConfPolicy(Position pos) {
        Set<ConfPolicy> s = new LinkedHashSet<ConfPolicy>();
        s.add(rifreaderPolicy(pos, bottomfsm(pos)));
        return rifjoinConfPolicy(pos, s);
    }

    @Override
    public IntegPolicy bottomIntegPolicy(Position pos) {
        Set<IntegPolicy> s = new LinkedHashSet<IntegPolicy>();
        s.add(rifwriterPolicy(pos, topfsm(pos)));
        return rifjoinIntegPolicy(pos, s);
    }

    @Override
    public ConfPolicy topConfPolicy(Position pos) {
        Set<ConfPolicy> s = new LinkedHashSet<ConfPolicy>();
        s.add(rifreaderPolicy(pos, topfsm(pos)));
        return rifjoinConfPolicy(pos, s);
    }

    @Override
    public IntegPolicy topIntegPolicy(Position pos) {
        Set<IntegPolicy> s = new LinkedHashSet<IntegPolicy>();
        s.add(rifwriterPolicy(pos, bottomfsm(pos)));
        return rifjoinIntegPolicy(pos, s);
    }

    @Override
    public ConfPolicy joinConfPolicy(Position pos, Set<ConfPolicy> components) {

        return rifjoinConfPolicy(pos, components);
    }

    @Override
    public IntegPolicy joinIntegPolicy(Position pos, Set<IntegPolicy> components) {

        return rifjoinIntegPolicy(pos, components);
    }


    public RifFSM fsmConjunction(RifFSM dfa1, RifFSM dfa2) {
        System.out.println("fsmConjunction");
//        HashMap<String, RifFSMstate> states;
//        RifFSMstate newcurrentstate = null;
//
//        LinkedList<Id> allPossibleActions = new LinkedList<Id>();
//        int j;
//        for (j = 0; j < 5; j++) {
//            allPossibleActions.add(new Id_c(null, "f" + Integer.toString(j)));
//        }
//
//        states = new HashMap<String, RifFSMstate>();
//
//        Iterator<Entry<String, RifFSMstate>> it1 =
//                fsm1.states().entrySet().iterator();
//        while (it1.hasNext()) {
//            Entry<String, RifFSMstate> pairs1 = it1.next();
//            Iterator<Entry<String, RifFSMstate>> it2 =
//                    fsm2.states().entrySet().iterator();
//            while (it2.hasNext()) {
//                Entry<String, RifFSMstate> pairs2 = it2.next();
//                String newname = pairs1.getKey() + "&" + pairs2.getKey();
//                List<Principal> newprincipals = new LinkedList<Principal>();
//                for (Principal p : pairs1.getValue().principals()) {
//                    if (pairs2.getValue().principals().contains(p)) {
//                        newprincipals.add(p);
//                    }
//                }
//                states.put(newname, new RifFSMstate_c(new Id_c(null, newname),
//                        newprincipals, new HashMap<String, RifFSMstate>()));
//                if (fsm1.currentState().name().id() == pairs1.getKey()
//                        && fsm2.currentState().name().id() == pairs2.getKey())
//                    newcurrentstate = states.get(newname);
//            }
//        }
//
//        it1 = fsm1.states().entrySet().iterator();
//        while (it1.hasNext()) {
//            Entry<String, RifFSMstate> pairs1 = it1.next();
//            Iterator<Entry<String, RifFSMstate>> it2 =
//                    fsm2.states().entrySet().iterator();
//            while (it2.hasNext()) {
//                Entry<String, RifFSMstate> pairs2 = it2.next();
//                RifFSMstate currentstate =
//                        states.get(pairs1.getKey() + "&" + pairs2.getKey());
//                for (Id action : allPossibleActions) {
//                    RifFSMstate reachedstate1 =
//                            pairs1.getValue().reachedState(action.id());
//                    RifFSMstate reachedstate2 =
//                            pairs2.getValue().reachedState(action.id());
//                    String reachedname =
//                            reachedstate1.name().id() + "&"
//                                    + reachedstate2.name().id();
//                    currentstate.setTransition(action.id(),
//                            states.get(reachedname));
//                }
//            }
//        }

//        return new RifFSM_c(states, newcurrentstate);
        return new SymDFA(Info.util, new Concat(((SymDFA) dfa1).getExpr(), ((SymDFA) dfa2).getExpr()));
    }

    public RifFSM fsmDisjunction(RifFSM dfa1, RifFSM dfa2) {
        System.out.println("fsmDisjunction");
//        HashMap<String, RifFSMstate> states;
//        RifFSMstate newcurrentstate = null;
//
//        LinkedList<Id> allPossibleActions = new LinkedList<Id>();
//        int j;
//        for (j = 0; j < 5; j++) {
//            allPossibleActions.add(new Id_c(null, "f" + Integer.toString(j)));
//        }
//
//        states = new HashMap<String, RifFSMstate>();
//
//        Iterator<Entry<String, RifFSMstate>> it1 =
//                fsm1.states().entrySet().iterator();
//
//        while (it1.hasNext()) {
//            Entry<String, RifFSMstate> pairs1 = it1.next();
//            Iterator<Entry<String, RifFSMstate>> it2 =
//                    fsm2.states().entrySet().iterator();
//            while (it2.hasNext()) {
//                Entry<String, RifFSMstate> pairs2 = it2.next();
//                String newname = pairs1.getKey() + "&" + pairs2.getKey();
//                List<Principal> newprincipals = new LinkedList<Principal>();
//                newprincipals.addAll(pairs1.getValue().principals());
//                newprincipals.addAll(pairs2.getValue().principals());
//                states.put(newname, new RifFSMstate_c(new Id_c(null, newname),
//                        newprincipals, new HashMap<String, RifFSMstate>()));
//                if (fsm1.currentState().name().id() == pairs1.getKey()
//                        && fsm2.currentState().name().id() == pairs2.getKey())
//                    newcurrentstate = states.get(newname);
//            }
//        }
//
//        it1 = fsm1.states().entrySet().iterator();
//        while (it1.hasNext()) {
//            Entry<String, RifFSMstate> pairs1 = it1.next();
//            Iterator<Entry<String, RifFSMstate>> it2 =
//                    fsm2.states().entrySet().iterator();
//            while (it2.hasNext()) {
//                Entry<String, RifFSMstate> pairs2 = it2.next();
//                RifFSMstate currentstate =
//                        states.get(pairs1.getKey() + "&" + pairs2.getKey());
//                for (Id action : allPossibleActions) {
//                    RifFSMstate reachedstate1 =
//                            pairs1.getValue().reachedState(action.id());
//                    RifFSMstate reachedstate2 =
//                            pairs2.getValue().reachedState(action.id());
//                    String reachedname =
//                            reachedstate1.name().id() + "&"
//                                    + reachedstate2.name().id();
//                    currentstate.setTransition(action.id(),
//                            states.get(reachedname));
//                }
//            }
//        }
//
//        return new RifFSM_c(states, newcurrentstate);
        return new SymDFA(Info.util, new Plus(((SymDFA) dfa1).getExpr(), ((SymDFA) dfa2).getExpr()));
    }


    public RifReaderPolicy_c join(RifReaderPolicy_c p1, RifReaderPolicy_c p2) {
        RifFSM fsm1 = p1.getFSM();
        RifFSM fsm2 = p2.getFSM();
        RifFSM newfsm = fsmConjunction(fsm1, fsm2);

        return new RifReaderPolicy_c(newfsm, this, p1.position());
    }


    public RifWriterPolicy_c join(RifWriterPolicy_c p1, RifWriterPolicy_c p2) {
        RifFSM fsm1 = p1.getFSM();
        RifFSM fsm2 = p2.getFSM();
        RifFSM newfsm = fsmDisjunction(fsm1, fsm2);

        return new RifWriterPolicy_c(newfsm, this, p1.position());
    }



    @Override
    public ConfPolicy join(ConfPolicy p1, ConfPolicy p2) {
        if (p1.isTop() || p2.isBottom()) {
            return p1;
        }
        if (p2.isTop() || p1.isBottom()) {
            return p2;
        }
        Set<ConfPolicy> s = new HashSet<ConfPolicy>();
        s.add(p1);
        s.add(p2);
        Position pos = p1.position();
        if (pos == null) pos = p2.position();

        return rifjoinConfPolicy(pos, s);
    }

    @Override
    public ConfPolicy meet(ConfPolicy p1, ConfPolicy p2) {
        RifFSM fsm1 = ((RifReaderPolicy_c)p1).getFSM();
        RifFSM fsm2 = ((RifReaderPolicy_c)p2).getFSM();
        RifFSM newfsm = fsmDisjunction(fsm1, fsm2);

        return new RifReaderPolicy_c(newfsm, this, p1.position());
    }

    @Override
    public IntegPolicy meet(IntegPolicy p1, IntegPolicy p2) {
        RifFSM fsm1 = ((RifWriterPolicy_c)p1).getFSM();
        RifFSM fsm2 = ((RifWriterPolicy_c)p2).getFSM();
        RifFSM newfsm = fsmConjunction(fsm1, fsm2);

        return new RifWriterPolicy_c(newfsm, this, p1.position());
    }

    @Override
    public IntegPolicy join(IntegPolicy p1, IntegPolicy p2) {
        if (p1.isTop() || p2.isBottom()) {
            return p1;
        }
        if (p2.isTop() || p1.isBottom()) {
            return p2;
        }
        Set<IntegPolicy> s = new HashSet<IntegPolicy>();
        s.add(p1);
        s.add(p2);
        Position pos = p1.position();
        if (pos == null) pos = p2.position();

        return rifjoinIntegPolicy(pos, s);
    }


    public ConfPolicy rifjoinConfPolicy(Position pos, Set<ConfPolicy> components) {
        if (components.isEmpty()) {
            return bottomConfPolicy(pos);
        }
        return (ConfPolicy) new RifJoinConfPolicy_c(components, this, pos)
                .simplify();
    }


    public IntegPolicy rifjoinIntegPolicy(Position pos,
            Set<IntegPolicy> components) {
        if (components.isEmpty()) {
            return topIntegPolicy(pos);
        }
        return (IntegPolicy) new RifJoinIntegPolicy_c(components, this, pos)
                .simplify();
    }

    @Override
    public AccessPath exprToAccessPath(Expr e, Type expectedType,
            JifContext context) throws SemanticException {
        if (e instanceof Local) {
            Local l = (Local) e;
            return new AccessPathLocal(l.localInstance(), l.name(),
                    e.position());
        } else if (e instanceof Field) {
            Field f = (Field) e;
            Receiver target = f.target();
            if (target instanceof Expr) {
                AccessPath prefix =
                        exprToAccessPath((Expr) f.target(), null, context);
                return new AccessPathField(prefix, f.fieldInstance(), f.name(),
                        f.position());
            } else if (target instanceof TypeNode
                    && ((TypeNode) target).type().isClass()) {
                AccessPath prefix =
                        new AccessPathClass(((TypeNode) target).type()
                                .toClass(), target.position());
                return new AccessPathField(prefix, f.fieldInstance(), f.name(),
                        f.position());
            } else {
                throw new InternalCompilerError(
                        "Not currently supporting access paths for targets of "
                                + target.getClass());
            }
        } else if (e instanceof Special) {
            Special s = (Special) e;
            if (Special.THIS.equals(s.kind())) {
                if (context.currentClass() == null || context.inStaticContext()) {
                    throw new SemanticException(
                            "Cannot use \"this\" in this scope.", e.position());
                }
                return new AccessPathThis(context.currentClass(), s.position());
            }
            else {
                throw new InternalCompilerError(
                        "Not currently supporting access paths for special of kind "
                                + s.kind());
            }
        } else if (e instanceof LabelExpr) {
            LabelExpr le = (LabelExpr) e;
            return new AccessPathConstant(le.label().label(), le.type(),
                    le.position());
        } else if (e instanceof PrincipalNode) {
            PrincipalNode pn = (PrincipalNode) e;
            return new AccessPathConstant(pn.principal(), pn.type(),
                    pn.position());
        } else if (e instanceof NullLit && expectedType != null
                && isImplicitCastValid(expectedType, Principal())) {
            Principal bot = bottomPrincipal(e.position());
            return new AccessPathConstant(bot, Principal(), e.position());
        } else if (e instanceof Cast) {
            return exprToAccessPath(((Cast) e).expr(), expectedType, context);
        } else if (e instanceof DowngradeExpr) {
            return exprToAccessPath(((DowngradeExpr) e).expr(), expectedType,
                    context);
        }
        else if (e instanceof ReclassifyExpr) {
            return exprToAccessPath(((ReclassifyExpr) e).expr(), expectedType,
                    context);
        }
        throw new SemanticDetailedException(
                "Expression " + e + " not suitable for an access path.",
                "The expression "
                        + e
                        + " is not suitable for a final access "
                        + "path. A final access path is an expression starting with either "
                        + "\"this\" or a final local variable \"v\", followed by zero or more final field accesses. That is, "
                        + "a final access path is either this.f1.f2....fn, or v.f1.f2.....fn, where v is a "
                        + "final local variables, and each field f1 to fn is a final field.",
                e.position());
    }

    @Override
    public Principal exprToPrincipal(JifTypeSystem ts, Expr e,
            JifContext context) throws SemanticException {
        if (e instanceof PrincipalNode) {
            return ((PrincipalNode) e).principal();
        }
        if (e instanceof PrincipalExpr) {
            return ((PrincipalExpr) e).principal().principal();
        }
        if (e instanceof Cast) {
            return exprToPrincipal(ts, ((Cast) e).expr(), context);
        }
        if (e instanceof DowngradeExpr) {
            return exprToPrincipal(ts, ((DowngradeExpr) e).expr(), context);
        }
        if (e instanceof ReclassifyExpr) {
            return exprToPrincipal(ts, ((ReclassifyExpr) e).expr(), context);
        }
        if (e instanceof NullLit) {
            return ts.bottomPrincipal(e.position());
        }
        if (isFinalAccessExpr(e)) {
            return ts.dynamicPrincipal(e.position(),
                    ts.exprToAccessPath(e, ts.Principal(), context));
        }
        throw new InternalCompilerError(
                "Expected a final access expression, or constant");
    }

    @Override
    public Label exprToLabel(JifTypeSystem ts, Expr e, JifContext context)
            throws SemanticException {
        if (e instanceof LabelExpr) {
            return ((LabelExpr) e).label().label();
        }
        if (e instanceof DowngradeExpr) {
            return exprToLabel(ts, ((DowngradeExpr) e).expr(), context);
        }
        if (e instanceof ReclassifyExpr) {
            return exprToLabel(ts, ((ReclassifyExpr) e).expr(), context);
        }
        if (isFinalAccessExpr(e)) {
            return ts.dynamicLabel(e.position(),
                    ts.exprToAccessPath(e, ts.Label(), context));
        }
        throw new InternalCompilerError(
                "Expected a final access expression, or constant");
    }

    @Override
    public boolean isFinalAccessExpr(Expr e) {
        if (e instanceof Local) {
            Local l = (Local) e;
            if (l.type() != null && l.type().isCanonical()) {
                return l.localInstance().flags().isFinal();
            } else {
                return true;
            }
        }
        if (e instanceof Field) {
            Field f = (Field) e;
            if (f.type() != null && f.type().isCanonical()) {
                Flags flgs = f.flags();
                return flgs.isFinal()
                        && (flgs.isStatic() || (f.target() instanceof Expr && isFinalAccessExpr((Expr) f
                                .target())));
            } else {
                return true;
            }
        }
        if (e instanceof Special) {
            return ((Special) e).kind() == Special.THIS;
        }
        if (e instanceof Cast) {
            return isFinalAccessExpr(((Cast) e).expr());
        }
        if (e instanceof DowngradeExpr) {
            return isFinalAccessExpr(((DowngradeExpr) e).expr());
        }
        if (e instanceof ReclassifyExpr) {
            return isFinalAccessExpr(((ReclassifyExpr) e).expr());
        }
        return false;
    }

    @Override
    public boolean isFinalAccessExprOrConst(Expr e, Type expectedType) {
        return isFinalAccessExpr(e)
                || e instanceof LabelExpr
                || e instanceof PrincipalNode
                || (e instanceof Cast && isFinalAccessExprOrConst(((Cast) e)
                        .expr()))
                || (e instanceof DowngradeExpr && isFinalAccessExprOrConst(((DowngradeExpr) e)
                        .expr()))
                || (e instanceof ReclassifyExpr && isFinalAccessExprOrConst(((ReclassifyExpr) e)
                        .expr()))
                || (e instanceof NullLit && expectedType != null && isImplicitCastValid(
                        expectedType, Principal()))
        ;
    }
}