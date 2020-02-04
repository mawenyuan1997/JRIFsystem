package jif.lang;

public abstract class ToStringable_JIF_IMPL {
    public static boolean jif$Instanceof(final Label jif$L, final Object o) {
        if (o instanceof ToStringable) {
            ToStringable c = (ToStringable) o;
            return LabelUtil.singleton().relabelsTo(
                                           c.jif$getjif_lang_ToStringable_L(),
                                           jif$L);
        }
        return false;
    }
    
    public static ToStringable jif$cast$jif_lang_ToStringable(final Label jif$L,
                                                              final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (ToStringable) o;
        throw new ClassCastException();
    }
    
    public ToStringable_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1558403464000L;
    public static final String jlc$ClassType$jl =
      "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";
}
