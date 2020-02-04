package jif.util;

public abstract class Collection_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof Collection) {
            Collection c = (Collection) o;
            return jif.lang.LabelUtil.singleton().
              equivalentTo(c.jif$getjif_util_Collection_L(), jif$L);
        }
        return false;
    }
    
    public static Collection jif$cast$jif_util_Collection(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (Collection) o;
        throw new ClassCastException();
    }
    
    public Collection_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
