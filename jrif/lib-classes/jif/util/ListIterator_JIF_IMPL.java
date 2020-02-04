package jif.util;

public abstract class ListIterator_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof ListIterator) {
            ListIterator c = (ListIterator) o;
            return jif.lang.LabelUtil.singleton().
              equivalentTo(c.jif$getjif_util_ListIterator_L(), jif$L);
        }
        return false;
    }
    
    public static ListIterator jif$cast$jif_util_ListIterator(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (ListIterator) o;
        throw new ClassCastException();
    }
    
    public ListIterator_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
