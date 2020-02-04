package jif.util;

/**
 * An implementation of List, using a linked list to store the
 * list elements.
 */
public class LinkedList extends AbstractList {
    private LinkedListEntry head;
    private LinkedListEntry tail;
    
    public LinkedList jif$util$LinkedList$() {
        this.jif$init();
        {
            {
                this.head = null;
                this.tail = null;
            }
            this.jif$util$AbstractList$();
        }
        return this;
    }
    
    public int size() {
        int s = 0;
        LinkedListEntry e = this.head;
        while (e != null) {
            e = e.next;
            s++;
        }
        return s;
    }
    
    public boolean add(final jif.lang.JifObject o) {
        LinkedListEntry n =
          new LinkedListEntry(this.jif$jif_util_LinkedList_L).
          jif$util$LinkedListEntry$();
        LinkedListEntry m = this.tail;
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
    
    public boolean remove(final jif.lang.JifObject o) {
        LinkedListEntry e = this.head;
        while (e != null) {
            if (e.data == o || o != null && o.equals(e.data)) {
                LinkedListEntry f = e.prev;
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
        LinkedListEntry e = this.head;
        while (e != null && s != index) {
            e = e.next;
            s++;
        }
        if (e != null) {
            LinkedListEntry f = e.prev;
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
        LinkedListEntry e = this.head;
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
        LinkedListEntry e = this.head;
        while (e != null) {
            if (s == index) { return e.data; }
            e = e.next;
            s++;
        }
        return null;
    }
    
    public jif.lang.JifObject getFirst() throws IndexOutOfBoundsException {
        LinkedListEntry m = this.head;
        if (m != null) { return m.data; }
        throw new IndexOutOfBoundsException();
    }
    
    public jif.lang.JifObject getLast() throws IndexOutOfBoundsException {
        LinkedListEntry m = this.tail;
        if (m != null) { return m.data; }
        throw new IndexOutOfBoundsException();
    }
    
    public jif.lang.JifObject set(final int index,
                                  final jif.lang.JifObject element)
          throws IndexOutOfBoundsException {
        int s = 0;
        LinkedListEntry e = this.head;
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
        LinkedListEntry n =
          new LinkedListEntry(this.jif$jif_util_LinkedList_L).
          jif$util$LinkedListEntry$();
        n.data = element;
        if (index == 0) {
            this.head = n;
            if (this.tail == null) { this.tail = n; }
            return;
        }
        int s = 0;
        LinkedListEntry e = this.head;
        while (e != null) {
            if (s == index) {
                n.next = e;
                n.prev = e.prev;
                e.prev = n;
                LinkedListEntry p = n.prev;
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
        LinkedListEntry e = this.head;
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
        LinkedListEntry e = this.head;
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
      "qYRVUdS4I5eo4Uk028MDiRCKu1GMacMDFj9CNuBwpnGLNw2tItyJIoTiOsFGjPQd+F3nZqTQ1HK23csNocaK0PGzbeMGH/RVS3C+tc9O+dYHHe7cxV6g3EwiIZ7yz0dkvO5HxuW8agBqbipYzKlzMafOYV++x00Fizl1zqiC0vhDBIcSNGdQgt+KSgCP95kH2b4HcOK+fVx8ae0r7bkrMVzU6ANZNFqYDFbuy1kC8PhcTh6Bs/lw56TzQgek4xYHYXPjQ7b6uIPg9Vbtbs4aBwHlOoLfsqDmFAf90Ms4wr6f4J2ijXO5HWPiWN3hOMisUKvLLRa+nlMsPGLGrr9mi4XHbORD+V+ox8eTHemv2YiFb2ewIRzkZU0qtsy4OWs8POIWD49kjIcoswqS1YNuMmvW4+FHOWn/GaT/E1PVzzi1/4xT+znaF6aqn3Fq/xlnPDyjx8OvOhUPSYysKEDHcjyt94DFYoEH2YDDNRuu8K30YizogmKE4t108RD4vc3NWNPFQyvCDVaEPOIh9HUjwa3WPjsTDwt6usdDu81A+Tc9HnKe5hIPj6SLh0es8fCIU+eOOHUO+yoYYirYEafOHXGPh6ISiDesrUpQJioBVBaZ8bCgG068oLyz8ZAYLmr0uCwaLUwGaS3PWQLQemJOHoGz+cLOSWdqB6TDtLW7qYlw73eg4z+G4P+ZQfDRnSVdB+xceRS/FGP8hwPdAlLXcEs0av1epuW5MJlSwhGcfjcseyWRxplsOaybgiYVAACyCqr469ma5GOv4XEOp9387AUYH55ywh+gXEUwoEn1/7yv6M9thA9QBTXLN/r/hb3jjOfi/PppUj/DQ1jb4dtcv38BW6i4YAPOhAnCHyQXVKPV1ND3Lwrq3CNA9u9fFOAK4FYw5uGWb08UXNLGY+1Aqwm4m/il9g+YwvfDWvj/bNIQ/GjypJr9h8YcpM8lp/0SoImxd+fCxdecmcY/edqFudpNm2DQrgGpiCdHSAN8nH1k2t70vgoXjPtHr591G237sHcfSyZom91AM2he16bavkVNH0e1/tcrDcH10uZtz27t8x1Gab3ULaKuSLUwhQnB90P177BBX1twvOlJ7ucuZx2PFb8abenWepvfs+6uJYGir1cbt/ldfQ0S3Pv/AYru7mDoZgAA";
    
    public LinkedList(final jif.lang.Label jif$L) {
        super(jif$L);
        this.jif$jif_util_LinkedList_L = jif$L;
    }
    
    public void jif$invokeDefConstructor() { this.jif$util$LinkedList$(); }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof LinkedList) {
            LinkedList c = (LinkedList) o;
            return jif.lang.LabelUtil.singleton().equivalentTo(
                                                    c.jif$jif_util_LinkedList_L,
                                                    jif$L);
        }
        return false;
    }
    
    public static LinkedList jif$cast$jif_util_LinkedList(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (LinkedList) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_LinkedList_L;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}

class LinkedListEntry {
    LinkedListEntry jif$util$LinkedListEntry$() {
        this.jif$init();
        {  }
        return this;
    }
    
    LinkedListEntry next;
    LinkedListEntry prev;
    jif.lang.JifObject data;
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public LinkedListEntry(final jif.lang.Label jif$L) {
        super();
        this.jif$jif_util_LinkedListEntry_L = jif$L;
    }
    
    public void jif$invokeDefConstructor() { this.jif$util$LinkedListEntry$(); }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof LinkedListEntry) {
            LinkedListEntry c = (LinkedListEntry) o;
            return jif.lang.LabelUtil.singleton().
              equivalentTo(c.jif$jif_util_LinkedListEntry_L, jif$L);
        }
        return false;
    }
    
    public static LinkedListEntry jif$cast$jif_util_LinkedListEntry(
      final jif.lang.Label jif$L, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (LinkedListEntry) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_LinkedListEntry_L;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
}
