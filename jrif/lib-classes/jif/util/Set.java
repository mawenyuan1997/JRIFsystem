package jif.util;

public interface Set extends Collection {
    String jlc$CompilerVersion$jif = "3.5.0";
    long jlc$SourceLastModified$jif = 1466731420000L;
    String jlc$ClassType$jif =
      "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";
    
    jif.lang.Label jif$getjif_util_Set_L();
    
    String jlc$CompilerVersion$jl = "2.7.1";
    long jlc$SourceLastModified$jl = 1466731420000L;
    String jlc$ClassType$jl =
      "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";
}
