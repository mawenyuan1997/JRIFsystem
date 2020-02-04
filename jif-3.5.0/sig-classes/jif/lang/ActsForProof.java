package jif.lang;

/**
 * A proof that the principal "actor" may act for the principal "granter"
 */
public abstract class ActsForProof {
    private static int __JIF_SIG_OF_JAVA_CLASS$20030619 = 0;
    private Principal actor;
    private Principal granter;
    
    ActsForProof(final Principal actor, final Principal granter) {
        super();
        this.actor = actor;
        this.granter = granter;
    }
    
    public Principal getActor() { return this.actor; }
    
    public Principal getGranter() { return this.granter; }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
}
