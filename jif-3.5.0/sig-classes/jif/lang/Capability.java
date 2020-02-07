package jif.lang;

/**
 * A <code>Capability</code> represents the authority to execute
 * a given @link{Closure Closure}. A capability has the authority of 
 * the principal P, and can be used to invoke the contained Closure. 
 * The label L is a lower bound on the side-effects
 * of the closure.<p>
 * 
 * A capability can only be obtained through the runtime interface, and 
 * only if the principal P grants authority to invoke the given closure.
 */
public final class Capability {
    /**
     * The only way to construct a Capability is through the
     * PrincipalUtil class.
     */
    private Capability jif$lang$Capability$(final Closure closure) {
        this.jif$init();
        { this.closure = closure; }
        return this;
    }
    
    private Closure closure;
    
    public Closure getClosure() { return this.closure; }
    
    public Object invoke() {
        if (this.closure == null) return null;
        return this.closure.invoke();
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    
    public Capability(final Principal jif$P, final Label jif$L) {
        super();
        this.jif$jif_lang_Capability_P = jif$P;
        this.jif$jif_lang_Capability_L = jif$L;
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final Principal jif$P,
                                         final Label jif$L, final Object o) {
        if (o instanceof Capability) {
            Capability c = (Capability) o;
            boolean ok = true;
            ok = ok &&
                   PrincipalUtil.equivalentTo(c.jif$jif_lang_Capability_P,
                                              jif$P);
            ok =
              ok &&
                LabelUtil.singleton().equivalentTo(c.jif$jif_lang_Capability_L,
                                                   jif$L);
            return ok;
        }
        return false;
    }
    
    public static Capability jif$cast$jif_lang_Capability(final Principal jif$P,
                                                          final Label jif$L,
                                                          final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$P, jif$L, o)) return (Capability) o;
        throw new ClassCastException();
    }
    
    private final Principal jif$jif_lang_Capability_P;
    private final Label jif$jif_lang_Capability_L;
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "tfGwAG58/08QwFnq7vZZ+vq2EoaeLgcnSVz4eWi8+aVTXq/r/arsK++e9HDWukwEn/6e5m3fvv2B4Kfk8BUocwnaavc67Jmez4DDL5OtTmzj9pvhbfuSrttXjtlp7OrfbfX2O7fffGA0vHfJWui2Y1mS6/dOrbZ4443bjya2912CTY/z1jw8Pcj48E317gfuN2+f+H7rsRhPLJ+wpXZgenoWBm++9cNiZk6YmbfunQXXFve9d8+bhuyl+r3rFvrB+xB+/m3Cw7b3xFUi9+3LTe1nT6B/50PM9Je64r2ztP8DhvI4Y336XPAP2uvF9x+furDTi45u9LU/NY5+5UM4+tWu+LtZ79WOI1VOs8fp40RW1YbVq67ui4pPPfJd4dnXb+qvf/uFZ3702/t/d/rc5vILtVtM7xkj97wHPwZ4oH0rSnTDPhF16+zTgDN5vH8WFU5W1SV32+rEyT84G/5u1nvi/Gz1j6Iznv8fPeAPi/coAAA=";
}