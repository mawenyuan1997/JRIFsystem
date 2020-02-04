package jif.lang;

public abstract class Closure_JIF_IMPL {
    public static boolean jif$Instanceof(final Principal jif$P,
                                         final Label jif$L, final Object o) {
        if (o instanceof Closure) {
            Closure c = (Closure) o;
            boolean ok = true;
            ok = ok &&
                   PrincipalUtil.equivalentTo(c.jif$getjif_lang_Closure_P(),
                                              jif$P);
            ok =
              ok &&
                LabelUtil.singleton().equivalentTo(
                                        c.jif$getjif_lang_Closure_L(), jif$L);
            return ok;
        }
        return false;
    }
    
    public static Closure jif$cast$jif_lang_Closure(final Principal jif$P,
                                                    final Label jif$L,
                                                    final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$P, jif$L, o)) return (Closure) o;
        throw new ClassCastException();
    }
    
    public Closure_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
