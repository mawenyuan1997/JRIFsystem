package java.net;

public abstract class SocketAddress {
    private static int __JIF_SIG_OF_JAVA_CLASS$20030619 = 0;
    
    public SocketAddress() { super(); }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
}