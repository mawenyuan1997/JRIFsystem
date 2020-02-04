package jif.util;

public abstract class Iterator2_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof Iterator2) {
            Iterator2 c = (Iterator2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_Iterator2_S(
                                                     ), jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_Iterator2_C(
                                                     ), jif$C);
            return ok;
        }
        return false;
    }
    
    public static Iterator2 jif$cast$jif_util_Iterator2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (Iterator2) o;
        throw new ClassCastException();
    }
    
    public Iterator2_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
