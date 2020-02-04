package jif.lang;

/**
 * A <code>Closure</code> is code to be executed that needs the authority
 * of the principal P. The label L is a lower bound on the side effects
 * of the closure.<p>
 * 
 * To execute a closure, a @link{Capability Capability} must be obtained
 * through the runtime interface. A capability can only be obtained
 * if the principal P grants authority to invoke this closure.
 * 
 * A subclass of Closure should be a final class, because this allows
 * the possible invokers of closures (typically Principal objects, via the
 * isAuthorized() method call) to reliably inspect closure objects via
 * instanceof.
 */
public interface Closure {
    /**
     * The code that requires the authority of the principal P.
     * The label {L} is a lower bound on the side-effects
     * of the code.
     */
    Object invoke();
    
    String jlc$CompilerVersion$jif = "3.5.0";
    long jlc$SourceLastModified$jif = 1466731420000L;
    String jlc$ClassType$jif =
      "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";
    
    Principal jif$getjif_lang_Closure_P();
    
    Label jif$getjif_lang_Closure_L();
    
    String jlc$CompilerVersion$jl = "2.7.1";
    long jlc$SourceLastModified$jl = 1466731420000L;
    String jlc$ClassType$jl =
      "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";
}
