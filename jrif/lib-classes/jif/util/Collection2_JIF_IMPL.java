package jif.util;

public abstract class Collection2_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof Collection2) {
            Collection2 c = (Collection2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$getjif_util_Collection2_S(), jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$getjif_util_Collection2_C(), jif$C);
            return ok;
        }
        return false;
    }
    
    public static Collection2 jif$cast$jif_util_Collection2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (Collection2) o;
        throw new ClassCastException();
    }
    
    public Collection2_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
