package jif.util;

public abstract class AbstractCollection2 implements Collection2 {
    protected AbstractCollection2 jif$util$AbstractCollection2$() {
        this.jif$init();
        {  }
        return this;
    }
    
    public abstract int size();
    
    public final boolean isEmpty() { return this.size() == 0; }
    
    public boolean contains(final jif.lang.JifObject o) {
        return this.contains(this.jif$jif_util_AbstractCollection2_C, o);
    }
    
    public boolean add(final jif.lang.JifObject o) throws ClassCastException,
        IllegalArgumentException {
        return false;
    }
    
    public boolean addAll(final Collection2 c) throws ClassCastException,
        IllegalArgumentException {
        boolean modified = false;
        if (c != null) {
            Iterator2 e = c.iterator();
            while (e != null && e.hasNext()) {
                try { if (this.add(e.next())) { modified = true; } }
                catch (final NoSuchElementException impossible) {  }
            }
        }
        return modified;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public AbstractCollection2(final jif.lang.Label jif$S,
                               final jif.lang.Label jif$C) {
        super();
        this.jif$jif_util_AbstractCollection2_S = jif$S;
        this.jif$jif_util_AbstractCollection2_C = jif$C;
    }
    
    public void jif$invokeDefConstructor() {
        this.jif$util$AbstractCollection2$();
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof AbstractCollection2) {
            AbstractCollection2 c = (AbstractCollection2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_AbstractCollection2_S, jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_AbstractCollection2_C, jif$C);
            return ok;
        }
        return false;
    }
    
    public static AbstractCollection2 jif$cast$jif_util_AbstractCollection2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (AbstractCollection2) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_AbstractCollection2_S;
    private final jif.lang.Label jif$jif_util_AbstractCollection2_C;
    private jif.lang.Label jif$jif_util_Collection2_S;
    
    public final jif.lang.Label jif$getjif_util_Collection2_S() {
        if (this.jif$jif_util_Collection2_S == null)
            this.jif$jif_util_Collection2_S =
              this.jif$jif_util_AbstractCollection2_S;
        return this.jif$jif_util_Collection2_S;
    }
    
    private jif.lang.Label jif$jif_util_Collection2_C;
    
    public final jif.lang.Label jif$getjif_util_Collection2_C() {
        if (this.jif$jif_util_Collection2_C == null)
            this.jif$jif_util_Collection2_C =
              this.jif$jif_util_AbstractCollection2_C;
        return this.jif$jif_util_Collection2_C;
    }
    
    private jif.lang.Label jif$jif_lang_JifObject_L;
    
    public final jif.lang.Label jif$getjif_lang_JifObject_L() {
        if (this.jif$jif_lang_JifObject_L == null)
            this.jif$jif_lang_JifObject_L =
              this.jif$jif_util_Collection2_S.join(
                                                this.jif$jif_util_Collection2_C,
                                                true);
        return this.jif$jif_lang_JifObject_L;
    }
    
    private jif.lang.Label jif$jif_lang_IDComparable_L;
    
    public final jif.lang.Label jif$getjif_lang_IDComparable_L() {
        if (this.jif$jif_lang_IDComparable_L == null)
            this.jif$jif_lang_IDComparable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_IDComparable_L;
    }
    
    private jif.lang.Label jif$jif_lang_Hashable_L;
    
    public final jif.lang.Label jif$getjif_lang_Hashable_L() {
        if (this.jif$jif_lang_Hashable_L == null)
            this.jif$jif_lang_Hashable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_Hashable_L;
    }
    
    private jif.lang.Label jif$jif_lang_ToStringable_L;
    
    public final jif.lang.Label jif$getjif_lang_ToStringable_L() {
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
