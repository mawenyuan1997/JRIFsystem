package jif.util;

public abstract class Map_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof Map) {
            Map c = (Map) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_Map_K(),
                                                 jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_Map_V(),
                                                 jif$V);
            return ok;
        }
        return false;
    }
    
    public static Map jif$cast$jif_util_Map(final jif.lang.Label jif$K,
                                            final jif.lang.Label jif$V,
                                            final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o)) return (Map) o;
        throw new ClassCastException();
    }
    
    public Map_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "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";
}
