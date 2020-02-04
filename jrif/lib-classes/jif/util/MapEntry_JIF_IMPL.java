package jif.util;

public abstract class MapEntry_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof MapEntry) {
            MapEntry c = (MapEntry) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_MapEntry_K(),
                                                 jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_MapEntry_V(),
                                                 jif$V);
            return ok;
        }
        return false;
    }
    
    public static MapEntry jif$cast$jif_util_MapEntry(
      final jif.lang.Label jif$K, final jif.lang.Label jif$V, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o)) return (MapEntry) o;
        throw new ClassCastException();
    }
    
    public MapEntry_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
