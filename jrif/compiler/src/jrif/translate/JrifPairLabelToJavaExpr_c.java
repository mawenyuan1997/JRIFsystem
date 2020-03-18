package jrif.translate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jif.translate.JifToJavaRewriter;
import jif.translate.PairLabelToJavaExpr_c;
import jif.types.label.ConfPolicy;
import jif.types.label.IntegPolicy;
import jif.types.label.JoinPolicy_c;
import jif.types.label.MeetPolicy_c;
import jif.types.label.Policy;
import jif.types.label.ReaderPolicy;
import jif.types.label.WriterPolicy;
import jif.types.principal.Principal;
import jrif.types.RifFSM;
import jrif.types.JrifTypeSystem;
import jrif.types.label.RifJoinConfPolicy;
import jrif.types.label.RifJoinIntegPolicy;
import jrif.types.label.RifReaderPolicy_c;
import jrif.types.label.RifWriterPolicy_c;
import polyglot.ast.Expr;
import polyglot.types.SemanticException;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;

public class JrifPairLabelToJavaExpr_c extends PairLabelToJavaExpr_c{
	private static final long serialVersionUID = SerialVersionUID.generate();
	
	public Expr policyToJava(Policy p, JifToJavaRewriter rw, Expr thisQualifier,
            boolean simplify) throws SemanticException {
        if (p instanceof ConfPolicy
                && ((ConfPolicy) p).isBottomConfidentiality()) {
            return rw.qq().parseExpr(rw.runtimeLabelUtil() + ".bottomConf()");
        }
        if (p instanceof IntegPolicy && ((IntegPolicy) p).isTopIntegrity()) {
            return rw.qq().parseExpr(rw.runtimeLabelUtil() + ".topInteg()");
        }
        if (p instanceof WriterPolicy  && !(p instanceof RifJoinIntegPolicy)) {
            WriterPolicy policy = (WriterPolicy) p;
            Expr owner = rw.principalToJava(policy.owner(), thisQualifier);
            Expr writer = rw.principalToJava(policy.writer(), thisQualifier);
            return rw.qq().parseExpr(
                    rw.runtimeLabelUtil() + ".writerPolicy(%E, %E)", owner,
                    writer);
        }

        if (p instanceof ReaderPolicy && !(p instanceof RifJoinConfPolicy)) {
            ReaderPolicy policy = (ReaderPolicy) p;
            Expr owner = rw.principalToJava(policy.owner(), thisQualifier);
            Expr reader = rw.principalToJava(policy.reader(), thisQualifier);
            return (Expr) rw.qq()
                    .parseExpr(rw.runtimeLabelUtil() + ".readerPolicy(%E, %E)",
                            owner, reader)
                    .position(Position.compilerGenerated(
                            p.toString() + ":" + p.position().toString()));
        }
/*        TODO
        if (p instanceof RifJoinConfPolicy && p.isSingleton()) {
            String curr = null;
            RifJoinConfPolicy policy = (RifJoinConfPolicy) p;
            LinkedList<Policy> l =
                    new LinkedList<Policy>(policy.joinComponents());
            Iterator<Policy> iter = l.iterator();
            RifReaderPolicy_c rpol = (RifReaderPolicy_c) iter.next();
            RifFSM fsm = rpol.getFSM();
            Map<String, RifFSMstate> states = fsm.states();
            Expr e =
                    (Expr) rw
                            .qq()
                            .parseExpr(
                                    rw.runtimeLabelUtil()
                                            + ".rifreaderPolicy()")
                            .position(Position.compilerGenerated());
            for (Entry<String, RifFSMstate> st : states.entrySet()) {
                List<Principal> principals = st.getValue().EquivPrincipals();
                if (fsm.currentState().name().toString() == st.getKey()) {
                    curr = "\"true\"";
                } else {
                    curr = "\"false\"";
                }
                e =
                        (Expr) rw
                                .qq()
                                .parseExpr(
                                        rw.runtimeLabelUtil() + ".addstate("
                                                + "\"" + st.getKey() + "\""
                                                + "," + curr + ",%E)", e)
                                .position(Position.compilerGenerated());
                if (principals == null || principals.isEmpty()) {
                    e =
                            (Expr) rw
                                    .qq()
                                    .parseExpr(
                                            rw.runtimeLabelUtil()
                                                    + ".addprincipal(" + "\""
                                                    + st.getKey() + "\""
                                                    + ",%E,%E)",
                                            rw.principalToJava(((JrifTypeSystem) rpol
                                                    .typeSystem())
                                                    .topPrincipal(rpol
                                                            .position())), e)
                                    .position(Position.compilerGenerated());
                } else {
                    for (Principal princ : principals) {
                        e =
                                (Expr) rw
                                        .qq()
                                        .parseExpr(
                                                rw.runtimeLabelUtil()
                                                        + ".addprincipal("
                                                        + "\"" + st.getKey()
                                                        + "\"" + ",%E,%E)",
                                                rw.principalToJava(princ), e)
                                        .position(Position.compilerGenerated());
                    }
                }
            }
            for (Entry<String, RifFSMstate> st : states.entrySet()) {
                HashMap<String, RifFSMstate> transitions =
                        st.getValue().getTransitions();
                if (transitions != null) {
                    Iterator<Entry<String, RifFSMstate>> it =
                            transitions.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, RifFSMstate> pairs = it.next();
                        e =
                                (Expr) rw
                                        .qq()
                                        .parseExpr(
                                                rw.runtimeLabelUtil()
                                                        + ".addtransition("
                                                        + "\""
                                                        + pairs.getKey()
                                                        + "\""
                                                        + ","
                                                        + "\""
                                                        + st.getKey()
                                                        + "\""
                                                        + ","
                                                        + "\""
                                                        + pairs.getValue()
                                                                .name()
                                                                .toString()
                                                        + "\"" + ",%E)", e)
                                        .position(Position.compilerGenerated());
                    }
                }

            }
            return e;
        }

        if (p instanceof RifJoinIntegPolicy && p.isSingleton()) {
            String curr = null;
            RifJoinIntegPolicy policy = (RifJoinIntegPolicy) p;
            LinkedList<Policy> l =
                    new LinkedList<Policy>(policy.joinComponents());
            Iterator<Policy> iter = l.iterator();
            RifWriterPolicy_c rpol = (RifWriterPolicy_c) iter.next();
            RifFSM fsm = rpol.getFSM();
            Map<String, RifFSMstate> states = fsm.states();
            Expr e =
                    (Expr) rw
                            .qq()
                            .parseExpr(
                                    rw.runtimeLabelUtil()
                                            + ".rifwriterPolicy()")
                            .position(Position.compilerGenerated());
            for (Entry<String, RifFSMstate> st : states.entrySet()) {
                List<Principal> principals = st.getValue().EquivPrincipals();
                if (fsm.currentState().name().toString() == st.getKey()) {
                    curr = "\"true\"";
                } else {
                    curr = "\"false\"";
                }
                e =
                        (Expr) rw
                                .qq()
                                .parseExpr(
                                        rw.runtimeLabelUtil() + ".addstate("
                                                + "\"" + st.getKey() + "\""
                                                + "," + curr + ",%E)", e)
                                .position(Position.compilerGenerated());
                if (principals == null || principals.isEmpty()) {
                    e =
                            (Expr) rw
                                    .qq()
                                    .parseExpr(
                                            rw.runtimeLabelUtil()
                                                    + ".addprincipal(" + "\""
                                                    + st.getKey() + "\""
                                                    + ",%E,%E)",
                                            rw.principalToJava(((JrifTypeSystem) rpol
                                                    .typeSystem())
                                                    .topPrincipal(rpol
                                                            .position())), e)
                                    .position(Position.compilerGenerated());
                } else {
                    for (Principal princ : principals) {
                        e =
                                (Expr) rw
                                        .qq()
                                        .parseExpr(
                                                rw.runtimeLabelUtil()
                                                        + ".addprincipal("
                                                        + "\"" + st.getKey()
                                                        + "\"" + ",%E,%E)",
                                                rw.principalToJava(princ), e)
                                        .position(Position.compilerGenerated());
                    }
                }
            }
            for (Entry<String, RifFSMstate> st : states.entrySet()) {
                HashMap<String, RifFSMstate> transitions =
                        st.getValue().getTransitions();
                if (transitions != null) {
                    Iterator<Entry<String, RifFSMstate>> it =
                            transitions.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, RifFSMstate> pairs = it.next();
                        e =
                                (Expr) rw
                                        .qq()
                                        .parseExpr(
                                                rw.runtimeLabelUtil()
                                                        + ".addtransition("
                                                        + "\""
                                                        + pairs.getKey()
                                                        + "\""
                                                        + ","
                                                        + "\""
                                                        + st.getKey()
                                                        + "\""
                                                        + ","
                                                        + "\""
                                                        + pairs.getValue()
                                                                .name()
                                                                .toString()
                                                        + "\"" + ",%E)", e)
                                        .position(Position.compilerGenerated());
                    }
                }

            }
            return e;
        }
*/
        if (p instanceof RifJoinConfPolicy && !p.isSingleton()) {
            RifJoinConfPolicy jp = (RifJoinConfPolicy) p;
            LinkedList<ConfPolicy> l =
                    new LinkedList<ConfPolicy>(jp.joinComponents());
            Iterator<ConfPolicy> iter = l.iterator();
            Policy head = iter.next();
            Expr e = policyToJava(head, rw, thisQualifier, simplify);
            while (iter.hasNext()) {
                head = iter.next();
                Expr f = policyToJava(head, rw, thisQualifier, simplify);
                e = rw.qq().parseExpr("%E.join(%E)", e, f);
            }
            return e;
        }

        if (p instanceof RifJoinIntegPolicy && !p.isSingleton()) {
            RifJoinIntegPolicy jp = (RifJoinIntegPolicy) p;
            LinkedList<IntegPolicy> l =
                    new LinkedList<IntegPolicy>(jp.joinComponents());
            Iterator<IntegPolicy> iter = l.iterator();
            Policy head = iter.next();
            Expr e = policyToJava(head, rw, thisQualifier, simplify);
            while (iter.hasNext()) {
                head = iter.next();
                Expr f = policyToJava(head, rw, thisQualifier, simplify);
                e = rw.qq().parseExpr("%E.join(%E)", e, f);
            }
            return e;
        }


        if (p instanceof JoinPolicy_c) {
            @SuppressWarnings("unchecked")
            JoinPolicy_c<Policy> jp = (JoinPolicy_c<Policy>) p;
            LinkedList<Policy> l = new LinkedList<Policy>(jp.joinComponents());
            Iterator<Policy> iter = l.iterator();
            Policy head = iter.next();
            Expr e = policyToJava(head, rw, thisQualifier, simplify);
            while (iter.hasNext()) {
                head = iter.next();
                Expr f = policyToJava(head, rw, thisQualifier, simplify);
                e = rw.qq().parseExpr("%E.join(%E, %E)", e, f, rw.java_nf()
                        .BooleanLit(Position.compilerGenerated(), simplify));
            }
            return e;
        }

        if (p instanceof MeetPolicy_c) {
            @SuppressWarnings("unchecked")
            MeetPolicy_c<Policy> mp = (MeetPolicy_c<Policy>) p;
            LinkedList<Policy> l = new LinkedList<Policy>(mp.meetComponents());
            Iterator<Policy> iter = l.iterator();
            Policy head = iter.next();
            Expr e = policyToJava(head, rw, thisQualifier, simplify);
            while (iter.hasNext()) {
                head = iter.next();
                Expr f = policyToJava(head, rw, thisQualifier, simplify);
                e = rw.qq().parseExpr("%E.meet(%E, %E)", e, f, rw.java_nf()
                        .BooleanLit(Position.compilerGenerated(), simplify));
            }
            return e;
        }

        throw new InternalCompilerError("Cannot translate policy " + p);
    }

}
