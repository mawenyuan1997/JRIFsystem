package jif.util;

/**
 * Base implementation for Set.
 */
public abstract class AbstractSet extends AbstractCollection implements Set {
    protected AbstractSet jif$util$AbstractSet$() {
        this.jif$init();
        { this.jif$util$AbstractCollection$(); }
        return this;
    }
    
    public boolean equals(final jif.lang.IDComparable o) {
        return this.equals(this.jif$jif_util_AbstractSet_L, o);
    }
    
    public boolean equals(final jif.lang.Label lbl,
                          final jif.lang.IDComparable o) {
        if (o == this) return true;
        if (!Set_JIF_IMPL.jif$Instanceof(lbl, o)) return false;
        Set that = Set_JIF_IMPL.jif$cast$jif_util_Set(lbl, o);
        if (this.size() != that.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            try {
                jif.lang.JifObject elem = this.get(i);
                if (!that.contains(this.jif$jif_util_AbstractSet_L, elem))
                    return false;
            }
            catch (final IndexOutOfBoundsException imposs) {  }
        }
        return true;
    }
    
    public int hashCode() {
        int hashCode = 0;
        int i = 0;
        while (i < this.size()) {
            try {
                jif.lang.JifObject obj = this.get(i);
                hashCode += obj == null ? 0 : obj.hashCode();
            }
            catch (final IndexOutOfBoundsException e) {  }
            i++;
        }
        return hashCode;
    }
    
    public boolean removeAll(final Collection c) {
        if (c == null) return false;
        boolean modified = false;
        if (this.size() > c.size()) {
            for (Iterator i = c.iterator(); i != null && i.hasNext(); )
                try { modified |= this.remove(i.next()); }
                catch (final NoSuchElementException impossible) {  }
        } else {
            for (Iterator i = this.iterator(); i != null && i.hasNext(); ) {
                try {
                    if (c.contains(i.next())) {
                        i.remove();
                        modified = true;
                    }
                }
                catch (final NoSuchElementException impossible) {  }
                catch (final IllegalStateException impossible) {  }
            }
        }
        return modified;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public AbstractSet(final jif.lang.Label jif$L) {
        super(jif$L);
        this.jif$jif_util_AbstractSet_L = jif$L;
    }
    
    public void jif$invokeDefConstructor() { this.jif$util$AbstractSet$(); }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof AbstractSet) {
            AbstractSet c = (AbstractSet) o;
            return jif.lang.LabelUtil.singleton().
              equivalentTo(c.jif$jif_util_AbstractSet_L, jif$L);
        }
        return false;
    }
    
    public static AbstractSet jif$cast$jif_util_AbstractSet(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (AbstractSet) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_AbstractSet_L;
    private jif.lang.Label jif$jif_util_Set_L;
    
    public final jif.lang.Label jif$getjif_util_Set_L() {
        if (this.jif$jif_util_Set_L == null)
            this.jif$jif_util_Set_L = this.jif$jif_util_AbstractSet_L;
        return this.jif$jif_util_Set_L;
    }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}
