package jif.util;

public abstract class Iterator_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof Iterator) {
            Iterator c = (Iterator) o;
            return jif.lang.LabelUtil.singleton().
              equivalentTo(c.jif$getjif_util_Iterator_L(), jif$L);
        }
        return false;
    }
    
    public static Iterator jif$cast$jif_util_Iterator(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (Iterator) o;
        throw new ClassCastException();
    }
    
    public Iterator_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
