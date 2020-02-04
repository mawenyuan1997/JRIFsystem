package jif.lang;

public final class JifObjectWrapper implements JifObject {
    public Object obj;
    
    public JifObjectWrapper jif$lang$JifObjectWrapper$(final Object obj) {
        this.jif$init();
        { this.obj = obj; }
        return this;
    }
    
    public Object getObject() { return this.obj; }
    
    public boolean equals(final IDComparable obj) {
        return this.equals(this.jif$jif_lang_JifObjectWrapper_L, obj);
    }
    
    public boolean equals(final Label lbl, final IDComparable other) {
        if (!JifObjectWrapper.jif$Instanceof(lbl, other)) return false;
        return this.obj ==
          JifObjectWrapper.jif$cast$jif_lang_JifObjectWrapper(lbl, other).obj;
    }
    
    public int hashCode() { return 0; }
    
    public String toString() { return null; }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public JifObjectWrapper(final Label jif$L) {
        super();
        this.jif$jif_lang_JifObjectWrapper_L = jif$L;
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final Label jif$L, final Object o) {
        if (o instanceof JifObjectWrapper) {
            JifObjectWrapper c = (JifObjectWrapper) o;
            return LabelUtil.singleton().relabelsTo(
                                           c.jif$jif_lang_JifObjectWrapper_L,
                                           jif$L);
        }
        return false;
    }
    
    public static JifObjectWrapper jif$cast$jif_lang_JifObjectWrapper(
      final Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (JifObjectWrapper) o;
        throw new ClassCastException();
    }
    
    private final Label jif$jif_lang_JifObjectWrapper_L;
    private Label jif$jif_lang_JifObject_L;
    
    public final Label jif$getjif_lang_JifObject_L() {
        if (this.jif$jif_lang_JifObject_L == null)
            this.jif$jif_lang_JifObject_L =
              this.jif$jif_lang_JifObjectWrapper_L;
        return this.jif$jif_lang_JifObject_L;
    }
    
    private Label jif$jif_lang_IDComparable_L;
    
    public final Label jif$getjif_lang_IDComparable_L() {
        if (this.jif$jif_lang_IDComparable_L == null)
            this.jif$jif_lang_IDComparable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_IDComparable_L;
    }
    
    private Label jif$jif_lang_Hashable_L;
    
    public final Label jif$getjif_lang_Hashable_L() {
        if (this.jif$jif_lang_Hashable_L == null)
            this.jif$jif_lang_Hashable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_Hashable_L;
    }
    
    private Label jif$jif_lang_ToStringable_L;
    
    public final Label jif$getjif_lang_ToStringable_L() {
        if (this.jif$jif_lang_ToStringable_L == null)
            this.jif$jif_lang_ToStringable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_ToStringable_L;
    }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}
