package jif.util;

public class LinkedList2 extends AbstractList2 {
    private LinkedListEntry2 head;
    private LinkedListEntry2 tail;
    
    public LinkedList2 jif$util$LinkedList2$() {
        this.jif$init();
        {
            {
                this.head = null;
                this.tail = null;
            }
            this.jif$util$AbstractList2$();
        }
        return this;
    }
    
    public void clear() {
        this.head = null;
        this.tail = null;
    }
    
    public int size() {
        int s = 0;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            e = e.next;
            s++;
        }
        return s;
    }
    
    public boolean add(final jif.lang.JifObject o) {
        LinkedListEntry2 n =
          new LinkedListEntry2(this.jif$jif_util_LinkedList2_S,
                               this.jif$jif_util_LinkedList2_C).
          jif$util$LinkedListEntry2$();
        LinkedListEntry2 m = this.tail;
        n.data = o;
        if (m == null) {
            this.head = (this.tail = n);
            return true;
        }
        m.next = n;
        n.prev = m;
        this.tail = n;
        return true;
    }
    
    public boolean removeByRef(final jif.lang.JifObject o) {
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (e.data == o) {
                LinkedListEntry2 f = e.prev;
                if (f != null) { f.next = e.next; }
                f = e.next;
                if (f != null) { f.prev = e.prev; }
                if (this.tail == e) { this.tail = e.prev; }
                if (this.head == e) { this.head = e.next; }
                return true;
            }
            e = e.next;
        }
        return false;
    }
    
    public boolean remove(final jif.lang.JifObject o) {
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (e.data == o || o != null && o.equals(e.data)) {
                LinkedListEntry2 f = e.prev;
                if (f != null) { f.next = e.next; }
                f = e.next;
                if (f != null) { f.prev = e.prev; }
                if (this.tail == e) { this.tail = e.prev; }
                if (this.head == e) { this.head = e.next; }
                return true;
            }
            e = e.next;
        }
        return false;
    }
    
    public jif.lang.JifObject remove(final int index)
          throws IndexOutOfBoundsException {
        int s = 0;
        int size = this.size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedListEntry2 e = this.head;
        while (e != null && s != index) {
            e = e.next;
            s++;
        }
        if (e != null) {
            LinkedListEntry2 f = e.prev;
            if (f != null) { f.next = e.next; }
            f = e.next;
            if (f != null) { f.prev = e.prev; }
            if (this.tail == e) { this.tail = e.prev; }
            if (this.head == e) { this.head = e.next; }
            return e.data;
        }
        return null;
    }
    
    public int hashCode() {
        int hashCode = 1;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            jif.lang.JifObject mobj = e.data;
            hashCode = 31 * hashCode + (mobj == null ? 0 : mobj.hashCode());
            e = e.next;
        }
        return hashCode;
    }
    
    public jif.lang.JifObject get(final int index)
          throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        int s = 0;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (s == index) { return e.data; }
            e = e.next;
            s++;
        }
        return null;
    }
    
    public jif.lang.JifObject getFirst() throws IndexOutOfBoundsException {
        LinkedListEntry2 m = this.head;
        if (m != null) { return m.data; }
        throw new IndexOutOfBoundsException();
    }
    
    public jif.lang.JifObject getLast() throws IndexOutOfBoundsException {
        LinkedListEntry2 m = this.tail;
        if (m != null) { return m.data; }
        throw new IndexOutOfBoundsException();
    }
    
    public jif.lang.JifObject set(final int index,
                                  final jif.lang.JifObject element)
          throws IndexOutOfBoundsException {
        int s = 0;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (s == index) {
                jif.lang.JifObject old = e.data;
                e.data = element;
                return old;
            }
            e = e.next;
            s++;
        }
        throw new IndexOutOfBoundsException();
    }
    
    public void add(final int index, final jif.lang.JifObject element)
          throws IndexOutOfBoundsException {
        LinkedListEntry2 n =
          new LinkedListEntry2(this.jif$jif_util_LinkedList2_S,
                               this.jif$jif_util_LinkedList2_C).
          jif$util$LinkedListEntry2$();
        n.data = element;
        if (index == 0) {
            this.head = n;
            if (this.tail == null) { this.tail = n; }
            return;
        }
        int s = 0;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (s == index) {
                n.next = e;
                n.prev = e.prev;
                e.prev = n;
                LinkedListEntry2 p = n.prev;
                if (p != null) { p.next = e; }
                if (n.next == null) { this.tail = n; }
                if (n.prev == null) { this.head = n; }
                return;
            }
            if (s == index - 1 && e.next == null) {
                n.next = null;
                n.prev = e;
                e.next = n;
                this.tail = n;
                return;
            }
            e = e.next;
            s++;
        }
        throw new IndexOutOfBoundsException();
    }
    
    public int indexOf(final jif.lang.JifObject o) {
        int s = 0;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (o == e.data || o != null && o.equals(e.data)) { return s; }
            e = e.next;
            s++;
        }
        return -1;
    }
    
    public int lastIndexOf(final jif.lang.JifObject o) {
        int s = 0;
        int last = -1;
        LinkedListEntry2 e = this.head;
        while (e != null) {
            if (o == e.data || o != null && o.equals(e.data)) { last = s; }
            e = e.next;
            s++;
        }
        return last;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    public static final String jlc$ClassType$jif$1 =
      "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";
    
    public LinkedList2(final jif.lang.Label jif$S, final jif.lang.Label jif$C) {
        super(jif$S, jif$C);
        this.jif$jif_util_LinkedList2_S = jif$S;
        this.jif$jif_util_LinkedList2_C = jif$C;
    }
    
    public void jif$invokeDefConstructor() { this.jif$util$LinkedList2$(); }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof LinkedList2) {
            LinkedList2 c = (LinkedList2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_LinkedList2_S,
                                                 jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$jif_util_LinkedList2_C,
                                                 jif$C);
            return ok;
        }
        return false;
    }
    
    public static LinkedList2 jif$cast$jif_util_LinkedList2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (LinkedList2) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_LinkedList2_S;
    private final jif.lang.Label jif$jif_util_LinkedList2_C;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}

class LinkedListEntry2 {
    LinkedListEntry2 jif$util$LinkedListEntry2$() {
        this.jif$init();
        {  }
        return this;
    }
    
    LinkedListEntry2 next;
    LinkedListEntry2 prev;
    jif.lang.JifObject data;
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public LinkedListEntry2(final jif.lang.Label jif$S,
                            final jif.lang.Label jif$C) {
        super();
        this.jif$jif_util_LinkedListEntry2_S = jif$S;
        this.jif$jif_util_LinkedListEntry2_C = jif$C;
    }
    
    public void jif$invokeDefConstructor() {
        this.jif$util$LinkedListEntry2$();
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof LinkedListEntry2) {
            LinkedListEntry2 c = (LinkedListEntry2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_LinkedListEntry2_S, jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_LinkedListEntry2_C, jif$C);
            return ok;
        }
        return false;
    }
    
    public static LinkedListEntry2 jif$cast$jif_util_LinkedListEntry2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (LinkedListEntry2) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_LinkedListEntry2_S;
    private final jif.lang.Label jif$jif_util_LinkedListEntry2_C;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "EO0eJ6n+7vn0P8h7z1zcbv5hci7//wGUNUFrgicAAA==";
}
