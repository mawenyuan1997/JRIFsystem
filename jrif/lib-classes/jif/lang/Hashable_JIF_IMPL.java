package jif.lang;

public abstract class Hashable_JIF_IMPL {
    public static boolean jif$Instanceof(final Label jif$L, final Object o) {
        if (o instanceof Hashable) {
            Hashable c = (Hashable) o;
            return LabelUtil.singleton().relabelsTo(
                                           c.jif$getjif_lang_Hashable_L(),
                                           jif$L);
        }
        return false;
    }
    
    public static Hashable jif$cast$jif_lang_Hashable(final Label jif$L,
                                                      final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (Hashable) o;
        throw new ClassCastException();
    }
    
    public Hashable_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1558403464000L;
    public static final String jlc$ClassType$jl =
      "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";
}
