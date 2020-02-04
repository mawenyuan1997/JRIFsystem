package java.lang;

public abstract class Cloneable_JIF_IMPL {
    public static boolean jif$Instanceof(final Object o) {
        return o instanceof Cloneable;
    }
    
    public static Cloneable jif$cast$java_lang_Cloneable(final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(o)) return (Cloneable) o;
        throw new ClassCastException();
    }
    
    public Cloneable_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAI1WXWwUVRS+O223P1S2LT9WfsoAK7YV2xBjjBZ/oKGwuNjagsYSWO/O3CkDd+dO79wtC1iDJgbiAw9SEB/oEyZqEBIT4oMh4UkhGBON8efBnzc1ygMv+qLiuffu7GxnS3WTuXvn3PN3z/nOOXPxFmoIODJ9Ro9MUCb6xBGfBH0jmAfEHqQ4CHYDIWedebB/5q39bR/WodQ4SrnemMDCtQaZJ0hJjKPWAinkCQ+22Daxx1G7R4g9RriLqXsUGJk3jjoCd8LDoshJMEoCRqckY0dQ9AlXNkNiFrVazAsEL1qC8UCgtuxBPIX7i8Kl/Vk3EANZlHRcQu1gEr2CElnU4FA8AYzLs+Et+pXG/iFJB/YWF9zkDrZIKFJ/yPVsgdbEJSo3Tj8DDCDaWCDiAKuYqvcwEFCHdolib6J/THDXmwDWBlYEKwKtuKtSYGrysXUIT5CcQJ1xvhF9BFzNKixSRKBlcTalqcTRiljOqrJ169nNp455OzxD+WwTi0r/G0CoKyY0ShzCiWcRLdjamz2Ll189aSAEzMtizJrno5dvP72x69p1zbNyHp7h/EFiiZx1Ib/4i1WDPY/VSTeafBa4Egpzbq6yOlI+GSj5gMXlFY3ysC88vDb6yYvH3ye/Gaglg5IWo8UCoKrdYgXfpYRvJx7hWBA7g5qJZw+q8wxqhH3W9YimDjtOQEQG1VNFSjL1DiFyQIUMUT3sXc9h4d7H4oDal/w78EvA8498EPwkQf4DHAbLTpgVL3y/JOXuOZxIwJVWxcuLAhZ3MGoTnrNmilu33b6Uu2lUAFa2KNAqCbM+CTPILvMIzlOS25kZymV2jWRRIqGUL1VMKlZw00NQQVAkrT1j+3a+dHJdHSTJP1wPbkrWdBwyUaFlYIcBBzkrdeKXPy6fnWYReARK12C6VlJicl38opxZxIaaj9T3mvhK7up02pABboZSFxiSAXXTFbcxB5sDYZ3L4BhZtMhhvICpPAqLs0Uc4OxwRFEZWCSXlE6GDFbMQdUinhjzz3/7+a8PG1Jx2E1SVW1njIiBKgRLZa0Kq+1R7HdzQoDv+3Mjp8/cOrFXBR441s9nMC1XCRoMYGH89euT3/34w4WvjChZAiX9Yp66lvI8BYo2RKYA3BQKDDwJ0nu8ArNdx5XQkIn/K3X/piu/n2rT2aNA0bHgaON/K4jo921Fx2/u/7NLqUlYsrlG4YjYdMUuiTRv4RwfkX6UXv1y9duf4vNQ+1BvgXuUlHxZLep6SN2qW6VpvVofiJ31ymVFSZ0tLdPVy0q1dsllraIbcrtOoCacB0RiS5QjNkeSo9V365aq0194bWbWHn5nk+5pHXM70DavWPjg678/6zv30415irRZMP8hSqYIrbJpgMk1MZO71CiJKuG9XRdvbN9gvWmgukqh1UykuUID1cYB8ZzAQPXkNSSlUV29U7mxGJyQD1ot4YT0L6H71enafvW4OVnEgTtZZIJ0B2q4mxqAZp4xSrBnHnSddOgHc7r3Oq6HqSlTfwwX8tOqTemd7v9qb7J9Qz3mMe2n2c1Mt6LBrLS0noHpnrBhLpzkpHatKsURfBLlNiff2+GzIeqd2h8l9OQCmNsql0cFWjz3pkHt1BzhbgE6wVR5apKTM2/c6Ts1Y1R9Wqyvme7VMvrzQllt0Q0KrKxdyIqSGPr58vTH706fMMoebxKosZwedaNHdOrDydQZpjwkyNO2/xNpuWxeKMry9SnFMLxARJ+Ty045xyCiFg5EWiYlJ5OSq2Q/TNiSeYZdSQ7Xmskne05nzRer/q6yLs2mmu6d3fONmi+Vb58ktG+nSGlV8VQXUtLnxHGVz0k9NnSQXoDyrrgloJ/Cn/L3eX0+DjR5Lvd7fR2UfwFFL4ZTUwsAAA==";
}
