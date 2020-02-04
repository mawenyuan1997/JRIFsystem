package jif.util;

public class ArrayMap extends AbstractMap {
    static final int INITIAL_CAPACITY = 10;
    int size;
    private int capacity;
    ArrayMapEntry[] entryArray;
    private ArrayMapEntrySet entrySet;
    
    public ArrayMap jif$util$ArrayMap$() {
        this.jif$init();
        {
            {
                this.entryArray =
                  (new ArrayMapEntry[ArrayMap.INITIAL_CAPACITY]);
                this.size = 0;
                this.capacity = ArrayMap.INITIAL_CAPACITY;
                this.entrySet =
                  new ArrayMapEntrySet(this.jif$jif_util_ArrayMap_K,
                                       this.jif$jif_util_ArrayMap_V).
                    jif$util$ArrayMapEntrySet$(this);
            }
            this.jif$util$AbstractMap$();
        }
        return this;
    }
    
    private void extendCapacity() {
        int newCapacity = 1 + (int) (this.capacity * 1.5);
        try {
            ArrayMapEntry[] newEntryArray = new ArrayMapEntry[newCapacity];
            for (int i = 0; i < this.size; i++) {
                newEntryArray[i] = this.entryArray[i];
            }
            this.entryArray = newEntryArray;
            this.capacity = newCapacity;
        }
        catch (final NegativeArraySizeException impossible) {  }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException impossible) {  }
    }
    
    public jif.lang.JifObject get(final jif.lang.Label lbl,
                                  final jif.lang.JifObject key) {
        try {
            for (int i = 0; i < this.size; i++) {
                ArrayMapEntry e = this.entryArray[i];
                jif.lang.JifObject k = e.key;
                if (k == key || k != null && k.equals(lbl, key)) {
                    return e.value;
                }
            }
        }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException impossible) {  }
        return null;
    }
    
    public boolean containsKey(final jif.lang.Label lbl,
                               final jif.lang.JifObject key) {
        try {
            for (int i = 0; i < this.size; i++) {
                ArrayMapEntry e = this.entryArray[i];
                jif.lang.JifObject k = e.key;
                if (k == key || k != null && k.equals(lbl, key)) {
                    return true;
                }
            }
        }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException impossible) {  }
        return false;
    }
    
    public jif.lang.JifObject put(final jif.lang.JifObject key,
                                  final jif.lang.JifObject value) {
        boolean found = false;
        jif.lang.JifObject old = null;
        try {
            for (int i = 0; i < this.size; i++) {
                ArrayMapEntry e = this.entryArray[i];
                jif.lang.JifObject k = e.key;
                if (k == key || k != null && k.equals(key)) {
                    found = true;
                    old = e.value;
                    e.value = value;
                    break;
                }
            }
            if (!found) {
                if (this.capacity < this.size + 1) { this.extendCapacity(); }
                ArrayMapEntry e =
                  new ArrayMapEntry(this.jif$jif_util_ArrayMap_K,
                                    this.jif$jif_util_ArrayMap_V).
                  jif$util$ArrayMapEntry$(key, value);
                this.entryArray[this.size] = e;
                this.size++;
            }
            return old;
        }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException impossible) {  }
        return null;
    }
    
    public jif.lang.JifObject remove(final jif.lang.JifObject key) {
        jif.lang.JifObject old;
        try {
            for (int i = 0; i < this.size; i++) {
                ArrayMapEntry e = this.entryArray[i];
                jif.lang.JifObject k = e.key;
                if (k == key || k != null && k.equals(key)) {
                    old = e.value;
                    this.remove(i);
                    return old;
                }
            }
        }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException impossible) {  }
        return null;
    }
    
    protected void remove(final int i) throws ArrayIndexOutOfBoundsException {
        this.size--;
        try {
            for (int j = i; j < this.size; j++) {
                this.entryArray[j] = this.entryArray[j + 1];
            }
        }
        catch (final NullPointerException impossible) {  }
    }
    
    public void clear() { this.size = 0; }
    
    public int size() { return this.size; }
    
    public Set entrySet() { return this.entrySet; }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    public static final String jlc$ClassType$jif$1 =
      "vgej2YlsAW8y+EYvF1Kb2ve4CH5BGoKvEQkOj6vwVWUq/HD+VqfvcE06Ps2k1/ZqUsH0DzD5fCMRwSsZ5H9OzJt+lwYYUl4sHjU01dCCwq+W+rOxVjK4mBuzWz5hjMl46K0weLUospxPCHh1uNYpKRXd8gmhnnqEJtviql1Z8HWcBccflvvDKJuRf+l4rZZHuL6rBp1QKBm4hSYvt9DEuwV2rm5rPwQlaAKAl9cx2NhL7b81jVNAqZvMJlK9pI45hdu64xQYPow/ODf8vc3FKSi2IbdSugQ4YvVvMcfw34YD4Zu2bukT8qSaYbSVQdetbY72ldChijXcwuCdvaT9LpH2cHf3fPvTOdi2nGhae1XSqGrG31QklrarGn4cDtHdgXIHBSRPvuKufQg4PrxmBhisYfCqXmJyv+BD/LsM+mN5F39au1LdndgMtmj89+Ljw12jiEtg08qhqV7ztmQ877czmdcriYDbardJ0qBOBhM8kVulrpII6BJnUOe6pvGGB9O8exyKfSQxVHVNiXdTWPAkbRhbxI0MOtL0HgjLE4KweKdgMDVRtEGHGNyfGfWgy3cY3Ns96j2X5t3zUDzt/dUdXO4gNtf3vJYrUHUgdChgDR9n8LFeUvUlkarb0h1b+J+xji38R7G2i2ML2sH/ClYsoRX4zB1bCFWr7G70Sw9VXBvu2MKuSikMYLS2EyJ9zODvMhMG6PIOgye7JwxvpnkHeZH/f1P/3Bx/VwIH3rDf28zgGkO64tx9GJlMyn5Rcu4HbbfYgNva/pOI9GnTQfWzxM/7x96pf1aC13dHM3pEGITb//5TKMK/YT8r8f/eO57o+mcl/t/Ck/gbaEvfgP/vM/heL/XtD7y+cTvr/nfS6t1xW+8+6pbeYQf/x7aSHXfr3XG33tFu/2cr2XG33h136B18XtJEEG7VD3d9UJ9+BF498MDA3GEPrH6dfp/H/FB7HvscAv+1Qe45OxbXmkOIPD0xHRADkEXSplyTvsS8AoBFZUn0dRbJRclreOwjfrEAv8g0numbzuDVhrTmHH5/vAl+260a9sfNv7jBEd9sU8sG20JnN/PQttSfVoCfIONGEpClk8E2mCQXGJ6Vx7Qta0CG2mZ9WiEr30vbLoF5L5XoL/trGBR+wpDpJf2s8zhtEz+tgLsi0xiCBoNhD0SHZYjoyvSIwgdjpIsZgtUMVvXOrGSNTmFWsvq103RyOG9SPP1T1kTnFy/hYwdJ+v/YaFT/NGvmkh8enXKEfb435QeQ7B4HH1hRef2H8+g3Mkn0qGzE25C5FVIO3SvANcDHwsenHM0cK3v5tH8MeCxvsuND04WcB3dgN9z+GAxGNG+3e/0+zvF/AmlU10udd/xoc+FN+PWxvFCiLp4kihiEb/yZX46Asb6Fk86P4YWZrAvJwFPFTxlzw/I/QfKtu7eqIufzq6yfIHkacPp5nH8Cboa+bndlAAA=";
    
    public ArrayMap(final jif.lang.Label jif$K, final jif.lang.Label jif$V) {
        super(jif$K, jif$V);
        this.jif$jif_util_ArrayMap_K = jif$K;
        this.jif$jif_util_ArrayMap_V = jif$V;
    }
    
    public void jif$invokeDefConstructor() { this.jif$util$ArrayMap$(); }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof ArrayMap) {
            ArrayMap c = (ArrayMap) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_ArrayMap_K,
                                                 jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_ArrayMap_V,
                                                 jif$V);
            return ok;
        }
        return false;
    }
    
    public static ArrayMap jif$cast$jif_util_ArrayMap(
      final jif.lang.Label jif$K, final jif.lang.Label jif$V, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o)) return (ArrayMap) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_ArrayMap_K;
    private final jif.lang.Label jif$jif_util_ArrayMap_V;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}

class ArrayMapEntry implements MapEntry {
    jif.lang.JifObject key;
    jif.lang.JifObject value;
    
    /**
     * Create new entry.
     */
    ArrayMapEntry jif$util$ArrayMapEntry$(final jif.lang.JifObject k,
                                          final jif.lang.JifObject v) {
        this.jif$init();
        {
            this.value = v;
            this.key = k;
        }
        return this;
    }
    
    public jif.lang.JifObject getKey() { return this.key; }
    
    public jif.lang.JifObject getValue() { return this.value; }
    
    public jif.lang.JifObject setValue(final jif.lang.JifObject newValue) {
        jif.lang.JifObject oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }
    
    public boolean equals(final jif.lang.IDComparable obj) {
        return this.equals(this.jif$jif_util_ArrayMapEntry_K, obj);
    }
    
    public boolean equals(final jif.lang.Label lbl,
                          final jif.lang.IDComparable obj) {
        return this == obj;
    }
    
    public int hashCode() { return this.key == null ? 0 : this.key.hashCode(); }
    
    public String toString() {
        return (this.key == null ? "null" : this.key.toString()) + " entry";
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public ArrayMapEntry(final jif.lang.Label jif$K,
                         final jif.lang.Label jif$V) {
        super();
        this.jif$jif_util_ArrayMapEntry_K = jif$K;
        this.jif$jif_util_ArrayMapEntry_V = jif$V;
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof ArrayMapEntry) {
            ArrayMapEntry c = (ArrayMapEntry) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_ArrayMapEntry_K,
                                                 jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_ArrayMapEntry_V,
                                                 jif$V);
            return ok;
        }
        return false;
    }
    
    public static ArrayMapEntry jif$cast$jif_util_ArrayMapEntry(
      final jif.lang.Label jif$K, final jif.lang.Label jif$V, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o)) return (ArrayMapEntry) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_ArrayMapEntry_K;
    private final jif.lang.Label jif$jif_util_ArrayMapEntry_V;
    private jif.lang.Label jif$jif_util_MapEntry_K;
    
    public final jif.lang.Label jif$getjif_util_MapEntry_K() {
        if (this.jif$jif_util_MapEntry_K == null)
            this.jif$jif_util_MapEntry_K = this.jif$jif_util_ArrayMapEntry_K;
        return this.jif$jif_util_MapEntry_K;
    }
    
    private jif.lang.Label jif$jif_util_MapEntry_V;
    
    public final jif.lang.Label jif$getjif_util_MapEntry_V() {
        if (this.jif$jif_util_MapEntry_V == null)
            this.jif$jif_util_MapEntry_V = this.jif$jif_util_ArrayMapEntry_V;
        return this.jif$jif_util_MapEntry_V;
    }
    
    private jif.lang.Label jif$jif_lang_JifObject_L;
    
    public final jif.lang.Label jif$getjif_lang_JifObject_L() {
        if (this.jif$jif_lang_JifObject_L == null)
            this.jif$jif_lang_JifObject_L = this.jif$jif_util_MapEntry_K;
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

class ArrayMapEntrySet extends AbstractSet {
    protected ArrayMap parent;
    
    ArrayMapEntrySet jif$util$ArrayMapEntrySet$(final ArrayMap parent) {
        this.jif$init();
        {
            { this.parent = parent; }
            this.jif$util$AbstractSet$();
        }
        return this;
    }
    
    public int size() { return this.parent == null ? 0 : this.parent.size(); }
    
    public boolean contains(final jif.lang.Label lbl,
                            final jif.lang.JifObject o) {
        try {
            for (int i = 0; i < this.parent.size; i++) {
                if (this.parent.entryArray[i].equals(lbl, o)) { return true; }
            }
        }
        catch (final NullPointerException unlikely) {  }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        return false;
    }
    
    public boolean remove(final jif.lang.JifObject o) {
        try {
            for (int i = 0; i < this.parent.size; i++) {
                if (this.parent.entryArray[i].equals(o)) {
                    this.parent.remove(i);
                    return true;
                }
            }
        }
        catch (final NullPointerException unlikely) {  }
        catch (final ArrayIndexOutOfBoundsException imposs) {  }
        return true;
    }
    
    public Iterator iterator() {
        return new ArrayMapEntrySetIterator(
                 this.jif$jif_util_ArrayMapEntrySet_K,
                 this.jif$jif_util_ArrayMapEntrySet_V).
          jif$util$ArrayMapEntrySetIterator$(this.parent);
    }
    
    public jif.lang.JifObject get(final int index)
          throws IndexOutOfBoundsException {
        try { return this.parent.entryArray[index]; }
        catch (final NullPointerException unlikely) {  }
        return null;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public ArrayMapEntrySet(final jif.lang.Label jif$K,
                            final jif.lang.Label jif$V) {
        super(jif$K);
        this.jif$jif_util_ArrayMapEntrySet_K = jif$K;
        this.jif$jif_util_ArrayMapEntrySet_V = jif$V;
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof ArrayMapEntrySet) {
            ArrayMapEntrySet c = (ArrayMapEntrySet) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_ArrayMapEntrySet_K, jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_ArrayMapEntrySet_V, jif$V);
            return ok;
        }
        return false;
    }
    
    public static ArrayMapEntrySet jif$cast$jif_util_ArrayMapEntrySet(
      final jif.lang.Label jif$K, final jif.lang.Label jif$V, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o)) return (ArrayMapEntrySet) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_ArrayMapEntrySet_K;
    private final jif.lang.Label jif$jif_util_ArrayMapEntrySet_V;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}

class ArrayMapEntrySetIterator implements Iterator {
    private ArrayMap parent;
    private int current;
    private int next;
    
    ArrayMapEntrySetIterator jif$util$ArrayMapEntrySetIterator$(
      final ArrayMap parent) {
        this.jif$init();
        {
            this.parent = parent;
            this.current = -1;
            this.next = 0;
        }
        return this;
    }
    
    public boolean hasNext() {
        try { return this.next < this.parent.size; }
        catch (final NullPointerException unlikely) {  }
        return false;
    }
    
    public jif.lang.JifObject next() throws NoSuchElementException {
        try {
            if (this.next >= this.parent.size) {
                throw new NoSuchElementException().
                  jif$util$NoSuchElementException$();
            }
            this.current = this.next;
            this.next++;
            return this.parent.entryArray[this.current];
        }
        catch (final NullPointerException impossible) {  }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        return null;
    }
    
    public void remove() throws IllegalStateException {
        try {
            if (this.current < 0 || this.current >= this.parent.size) {
                throw new IllegalStateException();
            }
            this.parent.remove(this.current);
        }
        catch (final ArrayIndexOutOfBoundsException impossible) {  }
        catch (final NullPointerException unlikely) {  }
        this.current = -1;
        this.next--;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public ArrayMapEntrySetIterator(final jif.lang.Label jif$K,
                                    final jif.lang.Label jif$V) {
        super();
        this.jif$jif_util_ArrayMapEntrySetIterator_K = jif$K;
        this.jif$jif_util_ArrayMapEntrySetIterator_V = jif$V;
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$K,
                                         final jif.lang.Label jif$V,
                                         final Object o) {
        if (o instanceof ArrayMapEntrySetIterator) {
            ArrayMapEntrySetIterator c = (ArrayMapEntrySetIterator) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_ArrayMapEntrySetIterator_K, jif$K);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_ArrayMapEntrySetIterator_V, jif$V);
            return ok;
        }
        return false;
    }
    
    public static ArrayMapEntrySetIterator
      jif$cast$jif_util_ArrayMapEntrySetIterator(final jif.lang.Label jif$K,
                                                 final jif.lang.Label jif$V,
                                                 final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$K, jif$V, o))
            return (ArrayMapEntrySetIterator) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_ArrayMapEntrySetIterator_K;
    private final jif.lang.Label jif$jif_util_ArrayMapEntrySetIterator_V;
    private jif.lang.Label jif$jif_util_Iterator_L;
    
    public final jif.lang.Label jif$getjif_util_Iterator_L() {
        if (this.jif$jif_util_Iterator_L == null)
            this.jif$jif_util_Iterator_L =
              this.jif$jif_util_ArrayMapEntrySetIterator_K;
        return this.jif$jif_util_Iterator_L;
    }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "Z+9PyP6xs6TQMzcpotx69SLfdNYi9Xk92L15+mB/3jolBU/Ns7vQvo0md+u1OO6SCg8evSKiejCDehfp1atU4rX7E12X8Lqkz0sPu0ZySvh840ff+rop/E3o+gXgSd57Ko/iT/pWafl3bPJkC+nD90DiT1dnrpKOP8l/8xcnHzf+0vXeI7fzhffdwLl70a27s4RPt29FRRrKd+UKP3B3LmjQPr/b6730gfP6xe/emUk6ca4r4nuysdeuknTnWbEffpt07Re64vN575VOSB1DbzxM625c6Vt9G80uW9V7qd2thfjSVy/qH7kPzSuNfOVuCp++WPKFi7q5Y+nbYP3Ftxn7s13xo3nvCUfPFlaVPyg19MQuinxLDx9EzYdbVJ7p9V7mL2ri90dNtwS/qAfvjJqvPliAl7r+4du6voikwnDo9tXACnO6Mqy4S/OfYHytK75ykSC8XPnC7QT+zLXP09sPovh97WYv9npn77mon/r9UdwtefKivv7OKP7r34/i26lB1vetve53d/Ssuyn+G13xV/Pe46kVRKX1wAxgGbnmPRQ/2SHx/P2O8E++Q0fYgTz5Ozd081dfO/v82ac/I529+drtayzv2Pl2o38nvtd672TTt95m7JRf/8m89+QlLt3/v3UPse++MM97iL3+ie9D7Hl2+Oz8ksvZhbGcqL70ZpH96qdPqeN3EBnmr5+906nq7amtBtw791yDzydHn2Fa3rv22avRmXsbp7OHOa9OTg8bOzPOPnX26kNXRrduMyA6tDPztLBunVpt8fGPn919SUbp7DRzw71v5VH46ms3raRoFcdvDVaOXjVuvsPzxOsnvr32h72PetpHfe3WeTBqd7r15kXT1v3MunWvXj/wDPOxrpDuCNZXGvvI1a2DU6h//QE3Cc7Felr+s2+j8N/uin9wfivpDjXser98p9pfXjZ7/6W6X3Z0oy/9AZPTFf/4NPXn3wb1X+iKf5r3PtGhbuhZ/v1Fc1+wvaTiPf9fZMkPJOtOXH/lbcb+ZVd8p7uI0dKxt/KHHeqqvPf+hxHWXUl7/31XtM8vEhs//fVnn/zBryu/cbqvePuy7+Nc70m78P0771jd0X48Ti3bPSH4+PmNq3Nm/Otz53gKnW0s6KoTVb96Pvzree+RixeF34jP5fj/AGfdhv9CLgAA";
}
