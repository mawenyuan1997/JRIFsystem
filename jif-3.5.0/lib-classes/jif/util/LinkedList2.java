package jif.util;

/**
 * An implementation of List2, using a linked list to store the
 * list elements.
 */
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
}
