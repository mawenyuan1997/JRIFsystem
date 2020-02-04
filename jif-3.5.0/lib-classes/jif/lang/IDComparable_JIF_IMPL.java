package jif.lang;

public abstract class IDComparable_JIF_IMPL {
    public static boolean jif$Instanceof(final Label jif$L, final Object o) {
        if (o instanceof IDComparable) {
            IDComparable c = (IDComparable) o;
            return LabelUtil.singleton().relabelsTo(
                                           c.jif$getjif_lang_IDComparable_L(),
                                           jif$L);
        }
        return false;
    }
    
    public static IDComparable jif$cast$jif_lang_IDComparable(final Label jif$L,
                                                              final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (IDComparable) o;
        throw new ClassCastException();
    }
    
    public IDComparable_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
