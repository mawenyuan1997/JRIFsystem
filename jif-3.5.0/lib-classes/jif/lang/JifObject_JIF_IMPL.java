package jif.lang;

public abstract class JifObject_JIF_IMPL {
    public static boolean jif$Instanceof(final Label jif$L, final Object o) {
        if (o instanceof JifObject) {
            JifObject c = (JifObject) o;
            return LabelUtil.singleton().relabelsTo(
                                           c.jif$getjif_lang_JifObject_L(),
                                           jif$L);
        }
        return false;
    }
    
    public static JifObject jif$cast$jif_lang_JifObject(final Label jif$L,
                                                        final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (JifObject) o;
        throw new ClassCastException();
    }
    
    public JifObject_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAI1WXYwTVRS+nd3t/rDS3RVw5WcZoGKLazfEGKMFFFYWikVWCoksQr2duVMGbufOztwuBaxBEwPxYR90QXyAJ0zUICQmxAdDwpNCMCYa48+DP29qlAde9EXFc+/tdLotrDbpnTvnnr97znfOmQs3UYfvId1l9EiRMp7iR1zip8ax5xNzlGLf3wWEvHHqoZGZt/b3fdiGYhMoZjs5jrltjDKHkwqfQL0lUioQz99omsScQP0OIWaOeDam9lFgZM4EGvDtooN52SP+TuIzOiUYB/yySzxpMyBmUa/BHJ97ZYMzz+eoL3sQT+GRMrfpSNb2eTqLopZNqOlPopdRJIs6LIqLwLgoG9xiRGocGRN0YO+xwU3PwgYJRNoP2Y7J0fJmifqN488AA4h2lgg/wOqm2h0MBDSgXKLYKY7kuGc7RWDtYGWwwtHiuyoFpi4XG4dwkeQ5GmzmG1dHwNUtwyJEOFrYzCY1VTy0uClnDdm6+ey66WPOVkeTPpvEoML/DhAaahLaSSziEccgSrB3TfY0XnTlpIYQMC9sYlY8H71066nhoavXFM+SO/DsKBwkBs8b5wvzv1g6mny8TbjR5TLfFlCYdXOZ1fHaSbriAhYX1TWKw1RweHXnJ3uOv09+01BPBkUNRsslQFW/wUquTYm3hTjEw5yYGdRNHHNUnmdQJ+yztkMUdYdl+YRnUDuVpCiT7xAiC1SIELXD3nYsFuxdzA/IfcW9Db8I/P8RfwQ/QRBPgMNozQm97oXrVoTcPYcjEbjS0ubyooDFrYyaxMsbM+VNm29dzN/Q6gCrWeRoyUHbSgmUpbbZVi2q2zJj+cz28SyKRKTuBQKKKlRw0UNQQFAjvcncvm0vnlzZBjlyD7eDl4I13oyYsM4ysMMAg7wRO/HLH5dOV1mIHY7iLZBulRSQXNl8T48ZxISSD9Wv0fHl/JVqXBPx7YZK5xhyAWUz1GxjFjTTQZmL2GhZNM9iXglTcRTUZg8/4LHDIUUmYJ5YYioXIlhNDsoOsT7nnv32818f0YTioJnEGrpOjvB0A4CFsl4J1f4w9rs8QoDv+zPjb566eWKvDDxwrLqTwbhYBWYwYIV5r12b/O7HH85/pYXJ4ijqlgvUNqTnMVC0OjQF2KaABPDEj+92Ssy0LRsXKBGJ/yv2wNrLv0/3qexRoKhYeGj4vxWE9Ps3oeM39v85JNVEDNFbw3CEbKpg7w01b/Q8fET4UXnly2Vvf4rPQulDufn2UVJxRbHI6yF5q4RM0yq5Pth0tkYsiyvybEGNLl+WyHVILCskXRPblRx14QIgEhu8FrFZkh5adrdmKRv9+Vdnzpk73lmrWtrA7Aa02SmXPvj6789SZ366foca7ebMfZiSKUIbbGpgcnmTye1ykoSV8N72C9e3rDbe0FBbvdBaBtJsoXSjcUC8R2CeOuIagtIprz4o3ZgPTog/WibghNRPPnlkQ2u7ekKfLGPfniwzThK+nO26AqBeYIwS7OjQiuKBH8xK7LVsB1NBPYZLharsUmqXxQVC5VbKZIf1GiugpJlX9TTFzPaNJfVjtqUnmG7XDen1zgeH4Ytu6Ov1RHjG0roKhl5vmdKP3SKDPgxoSjhzEsmUR6ig+7tYwkgJ/4qEwyMvRPJhl80mksPS+2S6WlNsYeqTdDUZtPa58RhVUWxAY4h0rYYR8d7P0fzZLgfkPllWkq6ckro2zFE1T4vlMaWwIVd+69gf9+wS9LKp2tgnJ2dev52antEavo1WtXyeNMqo7yNptUe1WLCyYi4rUmLs50vVj9+tntBqHq/lqLMGMHmjRxV4g9E6GIA2IIjTvv+TALGsmyv44vVJsWyUXM/NEdacWLJqGscN7PN4K2CCpA20TuwKUFvHt2icgy1f3erb0Lh4LtZ137nd38ghWf9+i8IMssqUNnSAxm4QdT1i2dLjqJp9Kk57oDkGXnGYCfCQ3j6vjl/gqA2OxXafqwLyL3jM7I4VDAAA";
}
