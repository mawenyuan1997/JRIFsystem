package jif.util;

public abstract class ListIterator2_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof ListIterator2) {
            ListIterator2 c = (ListIterator2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$getjif_util_ListIterator2_S(), jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$getjif_util_ListIterator2_C(), jif$C);
            return ok;
        }
        return false;
    }
    
    public static ListIterator2 jif$cast$jif_util_ListIterator2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (ListIterator2) o;
        throw new ClassCastException();
    }
    
    public ListIterator2_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
