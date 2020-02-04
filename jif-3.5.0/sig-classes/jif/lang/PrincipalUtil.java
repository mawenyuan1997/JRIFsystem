package jif.lang;

/**
 * Utility methods for principals. In particular, there are methods to test if
 * one principal acts for another, if two principals both act for each other, 
 * helper methods to both find and verify delegates-chains, and a method for 
 * dynamically acquiring a Capbility, which encapsulates the authority of a
 * principal to execute a Closure.
 */
public class PrincipalUtil {
    private static int __JIF_SIG_OF_JAVA_CLASS$20030619 = 0;
    
    /**
     * Does the principal p act for the principal q? 
     */
    public static native boolean acts_for(final Principal p, final Principal q);
    
    /**
     * Are the principals p and q equivalent to each other? That is,
     * does p act for q, and q act for p?
     * 
     */
    public static boolean equivalentTo(final Principal p, final Principal q) {
        return PrincipalUtil.acts_for(p, q) && PrincipalUtil.acts_for(q, p);
    }
    
    /**
     * Search for an actsfor proof between p and q. An actsfor proof
     * between p and q is a proof that p acts for q. See the subclasses
     * of ActsForProof for more information.
     */
    public static native ActsForProof findActsForProof(final Principal p,
                                                       final Principal q,
                                                       final Object searchState);
    
    /**
     * Verify that the chain is a valid delegates-chain between p and q. That is,
     * q == chain[n], chain[n] delegates to chain[n-1], ..., chain[0] == p, i.e.,
     * p acts for q.

     */
    public static native boolean verifyProof(final ActsForProof proof,
                                             final Principal p, final Principal q);
    
    /**
     * Notification that a new delegation has been added. All principals
     * should call this method when adding a delegation, or risk an out-of-date
     * cache.
     */
    public static native void notifyNewDelegation(final Principal granter, final Principal superior);
    
    /**
     * Notification that an existing delegation has been revoked. All principals
     * should call this method when revoking a delegation, or risk an out-of-date
     * cache.
     */
    public static native void notifyRevokeDelegation(final Principal granter,
                                                     final Principal superior);
    
    /**
     * Obtain a Capability for the given principal and closure.
     */
    public static native Capability authorize(final Principal p,
                                              final Object authPrf,
                                              final Closure c, final Label lb);
    
    /**
     * Execute the closure for the given principal.
     */
    public static native Object
      execute(final Principal p, final Object authPrf, final Closure c, final Label lb);
    
    /**
     * returns the null principal, the principal that every other principal can
     * act for.
     */
    public static native Principal nullPrincipal();
    
    public static native String stringValue(final Principal p);
    
    public static native String toString(final Principal p);
    
    public PrincipalUtil() { super(); }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "H4sIAAAAAAAAAMU9C3hUxblnn0l4P+T9MEB4igRQQA0oGORlJCkhoFSJm81JWNjs8wQCll6lVCxaWhVfV7FqwfpCvLXUV6EWtMFi4WprRWnFSyvKFUTtw2sr9s7/z5wzc87MnpyN9Cvfxz+b2fln/vnf/+zZ2SdOaqFsRhu0PNYwzlid0rPj5sUaqiKZrF5flYyvXki6aqOfP/hq/d1XpY74tfASrTCWrUlkIw16hVYUaTaWJTMxY7WhdatYHlkZKW02YvHSiljWKKvQOkaTiayRicQSRjatfVPzVWjdYqQnkjBiEUOvn5VJNhnakIoUWagxnjRK9RajNBXJRJpKkZTSqvJ4JJslM4Wx15ykMJVJrozV6xlDO7uCEM5GxyN1ery0ir1XAX+VtWS0YnN6tj+6OZyZ7u6Oc0o337W0248DWtclWtdYotqIGLFoeTJhEHqWaJ2a9KY6PZOdUV+v1y/Ruid0vb5az8Qi8dgaMjCZWKL1yMYaExGjOaNnF+jZZHwlDOyRbU4REmFNs7NC60RZ0hw1khlzO+GGmB6vN/8KNcQjjVlD683ZQrc3C/oJLzoQduqZhkhUN1GCK2KJeuCFA8PaY8nlZABBLWjSibyspYKJCOnQelDJxSOJxtJqIxNLNJKhoWSzAQzun3PSMhBEJLoi0qjXGlpf57gq+hYZVYSMABRD6+UchjMRKfV3SEmQz8n5Uzddl5iT8CPN9Xo0DvQXEqTBDqQFeoOe0RNRnSJ2GlNxZ6T3rpv8mkYG93IMpmOe+cYn08cOfnEfHTNAMaaybrkeNWqjW+u6vDawfPSFAaqCyWwMhG/bOSp/FXunrCVFDKu3NSO8Oc5888UFv7zq+sf0D/1ah7laOJqMNzcRPeoeTTalYnE9M1tP6BkwkblakZ6oL8f352oF5HVFLKHT3sqGhqxuzNWCcewKJ/FvwqIGMgWwqIC8jiUakubrVMRYhq9bUpqmFZD/Wn/yv4Om+b7P2rWG9vXSmixR99JVemJ1cyTRFCklaq1HMtFlpTBZcUM8uap03oK5s7Krs4beVEoM8Nzzxk0aN76UWMG52UwUeqg2VRFlisZSkXgN7J10p/6107fA7rqt8vkI4wc6zT5OLGZOMk5cQ210c/Oll33yZO1+v2UGjC/E7sATwvTjbNNrPh9OexbYCpUlkcQKYtPE13UaXX3NvGtvGhogSpRaFSS8hKFDbT61nBv+XPSBUaJ9v7kkde2mSQOm+rXQEuIbszP1hkhz3KgqvzTZnCA+5Cyra4FO3EsCnZrSsRakoohjaH0kl0hdIUHL8EkAbQDR+BKn3anI7Lrhg7/tuHNtklugoZVIjkHGBMMe6pRDJhnV64mr5NOPKY7srN21tsSvBYm3IHszyM7A+Qx2rmEz8DLTWcJeQmR7DclMUyQOb5lc6WAsyyRX8R5UkC4AelBdAYk6CEQ/O606teXQgePn+TU/d8ldhRBXrRtlghuAybqiwXfnCrIwo+tk3B/urrr9jpMbvo7aQUYMUy1YArCcmD8JdISD396XfuvIO1t/6+caZZAo2FwXj0VbcC/d/0n++cj/L+E/2DJ0QEs8ejnzI8WWI0nByiM4bcSlxIlbI6RnS2oSTcn6WEMsUhfXQZ2/6Dp8ws4Tm7pRccdJD2VeRhvb9gS8v9+l2vX7l342GKfxRSGkcf7xYdRP9uQzz8hkIquBjpYbXh90T2tkC/G4xMtlY2t0dFwa8kNDAY5HXpyDsNTx3kQAxcRqnW+S5QZw20QbIVlBjKYMtdHenw4tTc2a+S7KvgNRxwaSCcWiJMcZKJlWufUu2BdE5kZz8CBp8Fz+NlhGHycNbP3gNcX1nxYP/TqaQ8d6PRvNxFKmkhE/3iEba0rFCbv1erRikkEYyXmEfVY6lIkksnEidWr5C/HNy1pSGQjGKyMZlBNyZWgLKKxFRhVkWbXRKTdvyCSHbZzsZ4xEgxndkoLmIlBgxDAX4azjC9VGt/S662c9Hr9tBo2rZ9sxpNFTx5ffWHv+f/3az0ykj9PLzolklxFTOhR/c8kdfxgzmM4qmBp7//mZ377jzmefOZ864k5E2N0uma5pptQHOzm+QI+QeEBFUhv99P639QWTPv+IGn1yVcKZYabMiMBDDySnGZwFuHMpoaqvpFps+snffWDHyXeqpqM9CEKEFELKYpmWCOwHeJk9rFj0jFuYTFkk1UaX9v7vcwb+7KrviMx3IAijNz16X8GpsZ8/gNu2VGmYQ5UsBFd1AnghpRf9jU3sIpGi9Pv0+sNv962c8xEl16ldKoxLJp61+4O+/a5DfUnh2rPYqtDMS6mEvZgUKlzYxeMq9vy8YMGvBGGjBAkLVuFAKk+A5VwAVWTi4Sp+Xpo0jGSTwNVpw95eXnb6tZ+YRjTb4spo+wYdmOI2w2Ne6Lfp8PWV5hyX061+TdhqNe06XzDRxdhThvBipxFA5wy+nyW5JlR0VXK0WhXL5S7a9rUyooG2jGgWlD08C4iumXbsti/TJAsILNG6LItk5yZIaIUqixRz4Futvwytu2Aw6LQgF4iLeY2zNHAstqT0ifv6l1/8IdoiTzkA++wWOXlcFBGyoYmPNf3VPzT8sl8rIEkbpmKkol0UiTdDtF9CCrRsOeus0Drb3rcXW7SyKLNSqoHOdEdY1pns8KSVvIbR8LoD9Rc0PyA87wkyn0r+dyT8P8jah+HdnqgpZ7X4NHyxAlGGICwBMBJl5jdIXpmJEXdAKA9nsS5usWbHYNqDzbqNtXcJsxtacW3tvLmzaqvnzq6tnFU7b8aiGbXlFTOqq0smjh9/3vjJEy7M2sIgxgW9nlZm2x554smyTo9uQwMtQuESURss5BUChvk33XdnQffQHuLUHpo92UMc52jhyh+X7SEu2wNFW+sRTdZLYvdNRLFXspJVv2nzxn+O27TZL9T1w6TSWsShtb2NARltiNsqiDHr/R1rX3hk7Qbqc3vYq9TLEs1N2393+tVxd7/7iqJECpA8B7NnH+Uv3d4QAMMt7cB/YVZWfoO1KwXtEHwDBudBuU4AkN6t6zbfX1+5bYLpCm80tCIjmTo3rq/U48JUHaXDrCvwzIOb75QHZ5YM3JPedOZqLqaSqvLqbMemnMQ8esUTr8weEb2NOD3LDUjnOHakMrvxd6CrLrS5gMGWGPoBeyeQ/12JOc9mbanoAmgdpLT/ALwcBYBIPZyIgAY57L8vm3Ica/s7Jcxzcz/1Kfh31pplIsxSxrC/xto5IoFEov2dsXxGppFly490fnn/yb6z9mG27I/GIPGWKtN6PRf3mlMpPSNK0b8yBq+WI1+mALivxdBqYMqmZCa1LBYtRhKKkw3FtOAsjmQam5v0hFGcgk56xlYciRrZWjKgeFQdzK6TnrrkSr24bnXxdUc3bj16421rR8PsP7AyCisjKI8kEklDSpLD0djOk6UNp00TmJrbo30fwGYqWgB3uImC7hGbLfQ1wAeQ+w+l8ppPjpw1iRUJklxR71bd8Ynmb+0695C5g848o9iO8HGXcm4HgNsNrdDkLKJpmqq8k3I/RgbTmbNKT2yp/L+jT5l0TKHbY5nkg7T5saPT0Hws17Lr7jSms9Uq3SWpSj11JqAlxXWmoj3rKSY9h9Tt4pnVc3KyZe+q5Gh7eAB6To5JzwkxyZ6WVSSjkTj3UAtvaX1z8j0f3IZxwD3HcmDGt8ZbK/6y+oDJ5WV0XZZyvKJ2OT6D5DmxRITG1dEW03uR1Ek+mZOUFsB/U8UFuBXAr5HhuCaAAw4HdAkwfQ4T3lLW1tgckLXK655dQTpPV2BqGcBHya7TDlVDKucx6q5VUQnweQBvetKtQ4hwmCvSIVm3Dsm6RdHe5Yp0SNYte1cr7WJify+H2OHlq5bM01xQjO8AfwvgOIA3uHQ/wC3j1IiEIx928SMfoh8B8JRDEzBWjmK8vVgVK1UnR/OSMdOvdC888u7JzNDJ5slRUyqZ0Fm+MJT0JFBJSAlDgw31PdbyxarlJ4vL4yGeoz62lhdDxeHX3nz4oY0dG6xQkcIo0+bhHalfrh9022vfu/f6GhN1rEKPArmCham9KW5ykhBTXICPmqJ+k6sGZu4fm263LMU19mPE/4Lr2cey6tm7Ki3eDgeyRzKemh67zGE+eMA/QlXdz1xNcoZYVCjva07PG/D5ycv30PQYPllRHQ/OiEb1bLaKvEsz9Nl2gkYwQqaqCCK0jLGL2kmFKPFb6qqevuL3XzYLRwW2kx+WNln0oK+eMLf/lKlLfroHS+6iBEmlM/Ob4yTZ9cdjitN3m3+3JVItipDL11qQTBqTbo0/+fSk41fTekPO56zByeCFZbNfmDPCb/uYDVbpiuWcL6yhslg+EjyVz28dOVDJr0NPQju/TarYumQyrkcSqsNgS+k2E+fezYzWKebbZTXc3CJ4yM2y09wsO03MnHyDuJpuljXX6gK42ND8qZHciLg7zFJLUrlGH/rTN5R+H90lmTQ9UuUu/zO3u/SNcrpLACcpAR8xW4VxY7Dnk9xR58+eHAlMhS73Idd9iMxR8mJifrywuR7An+QSRLkbqqS6B/AC6PAFUENxw74ClK6vyKa0dFVfV8QZA6B7G0oJY3oDuNhTXKcIM9qnorPyUFFaGFgODQ6CsNDsTcQ6kLb+02nNU6Hph5e00HTUl93ZTF+w9pQwo0Nr26gvga4RrB0k0mXpj6+aeICr8q709HRzbGUkTroXJtuo9nwLaZqXW4ZQafkqrErLd4XbJinNIv0Aa1CBF6XymQ/+3I4Ap6h1cQgRAJcbWidx34iqqsWg48eUBrm8EjvdyisQ2SiV6ABCzuuLebENHxb1viYeNpZLtuHoquRoGcsQzDGCbTi6WmkXzXl9qzzlvLilA5IwAVyHAs3li3xXAliDbMDl+ESKKgd4eB5rx6jN4JuezSDdDjMQKh3fNW6VDlA4SUWpJfVveZL6emTSBi7i9bLU18tSp2i3cBGvl6W+Xpb6elPqt3qSuqPSIbwHeD2AOwDc4C71zcgGXA4nwneWuFjv3Wi9AOocGoIOvITxu6vKgdOYbyH0UyAEgiICM+5PUh4SAIfzMrUjxdVaYhK+dwNXJM4LK3767lNUEj7MNXwPczneJ4uWd2W0IaqaT/g0vzbqf2ZizeEfrt1F09uBqvHm0OSInjOWvHJ3GovEwmWR7DI4nzS0nsInQzi3Dh+wdllOUMvtlaT1oSTbyJPWYyd4OsKQexx9YOtnN2y4wA9PT4RWwsdPhLRufNz8Zniu8cYn7hjUcfO7N+NHqdqoxD05ZUWDDq+pBkNnMZN7R9YWOQzV9xOaF9mxzmajO6iwpOTpGQ3lb0UItNiddou15h8C8w9j83ZhbScnVbscVCHWUDa6swpLoupFpOpWex2y26pDaBrpmtzRuOzrLStohVBjkD8kd1Uhuys61wGuwBWyTlfYEjgzjZajBrUxpVN6ow2nZGW4Tqf0NRen9JbTKcHL/+Q1BlgxNPS0qt01hhhS3027h1QHc5S8+GN+vLA5JWiOuYQvy0H5HueGfhw7dmijmpvbslJqIgBPUlVHQ3KxLbbdFzj6bkT/lKO/6GIEbKcvtSDSYc+6D+D/PIVxivCPdtmFX8vDLvA1L2wwzLHzueAa6ryCC9Navp+gAbhL8ekZTFfN2kuEaR32EqDzqYL2AEbcetZeJxJnaa6/M0nrrsm7ummIJepnRI3srGSmKpMk/e4Vjp8+fdBGheMPWxWJv8Bto5RucQ8Au8Le/N1S+cwHf27nAOfpl9sn+QcACBlaNycDEN2t1CHEyKWO2KkodVCOg5j8blTJESAkvf5hXqzFX4KbGGmZBulwWoujq5KjjbVMwxwjWIujq5V20aTXP0FtBu6ljilVAJOoZAH2AHA+bhmn5khc/yGRwDNz4NetrL1Jrf9TPOt/up36L5Q2/j6K0gapHcOovF1FrSXlqZ6kPA0ZN52LdJos5WmylCnaZVyk02QpT5OlPM2U8jxPUnaUNoT/AOHpQP98ABdxKV+BW8apVVIeC9ueyPh1L2vvUEu5iki50ZOU6bcO4Ns++pmRd0dhRofkcQcXM8p/pNqBJfkaT5JfhMy8iot5kSz5RbLkKdpSLuZFsuQXyZJfZEo+6kXyEh96GWLdQR97EyUGcAEASLb81VwvGpAhuDCARhzpjIyi114BIARgoEOHMIyfy+LsMlUYx6G8vB2uQrhWRMinvHVELtND4Avq3iRDSXEj6cPMidmIlUX5k4rylnQCXM0lmpSFbO/i5RN4J9RW2G0ja3WHovq/SZNCO9Y5bHSDCgsRhMzRf4OGW7GVT/7/cHyMQxSnl/Wpv2iVbqklTQYUZRXEYL5WWDaYsGwwOJf/Vs64sMzLsC19NFNpOWpR2Sod4t0oa6XNozGYCbbsJKkFSdaUEizJ0DoJFjlSZVMdXWzqB06bgpdNvDoDJYSG5nztrs7ELOBhtBJXdog8VrL00fxYarMpaJ5w8cPcviqp2gLcQY0CldtF39mq30acBwFs8KLN8HKnp9BAVfbZ9mn6rjw0HV87CqWxNB8LPcjaTWntTBVKMN13WbtamNahuupCCRy5NoNh/5S1D4nEUSWyEC5UITwpIhha2ls1hWkEzzBW6plYw+qcyUWq+OjGzSQFBXh04zaSaBBYVpw+euP3aYW1n2YdubUAK6K9vCJ6yY1DMOBXgv949d+yL7S4A6l8yIc/nQXdIRc39jaAPSQ7EehETNdabq+qltsr1nIh5AP3hq96zwvs2jmZ/B9NlOyvrD1hV7Yr8y7d2xCIKov9naI2ReIuYER9piIOIOauH3pyUCdQHqe4NzohO6gTsoOiaH/h3uiE7KDsXa20i+Wun6s9Txu16fu4Kjxlhh+G+I/nDAjw3KH/C2QDX47L+CIYWE7ZF/YzNn7eHhmnv7qMnZUpEncZIy6oIs6UcSDoRcaBECAECi2Bkg6njB1dlRytsyVQc4wgY0dXK+2iMg708CRjR2UaQPkFYFAA4mzA7yrjQC9kA1/ufJvZiwknLhnoz/XzVVll7V08E4dqUZvOAs9O1j7tEEngbJp02LEuYaN/osJyZiaBoRpuxZaJB4odmXir3R3MZjP/TBVJmSxGeZGF4D3FUOTdk5qGGeitlAXnfyXdFsDzrUwtMDQnPyxJj0CccTDxhn93nARKLoDlD7ir6FiT5MAoTjcy+H0h1jPfhl7hOPf/acuFcTvJUjuRbAYZ7Od+hZsG4uICr+eOyoFLYcAeAIetdcwH9/INp/8+sbD0hXHhK2QEnqSShxC820SNF5swJx/RIlj4GSfaw0enND9U1fh7xRp/r5xY7JUTC5wr0MBd8l7ZS++1VT5wFwPIeKRNyDAL/UpC+4tgnm4E8MDHJd2wiuAcDAZgpN3DmVmOqtQFela5BHulGq3xpkZsVepa42lvfgqGXZ8fU9JtOjAA6/PjUtrybPtcPBuc67fl2WDcxq+oMf++QPS9tHsgUltJu11h+y0h9RWswNVp/qAd2n57Wuk5oXfrGbD4H+W3V0TcmPbqb2H04y4+QfTDMPbJ9vnip/PwxfjacQoFzyGPJ5XMX1j7+7R2pk6hYLrDrG0VpnV4gjYeRgbsL1n7V5E4LtMXiV03eLLrxkwEroQTLDuRNIhlz9dXzdTjeiPeUuelOqSshMWfs45fAs+77YwSKhINcA9ay9685oM/+UlOYL+LZ4Uv9AWeNbSeim3iDG4nOoQU+URH7DS0AsZQqv124ZVToRWEVcIDiDXyG15MJPA73M1blj2QDqeJOLoqOdo7lj2YY8Qa2d7VSrtYXfZHte7b6zKTB2nNVikz+QJ4n8oY4EsAjuHGcQGOxFUfDhe0+Yx73VlbqFb9494/w4WrBWPJM6D7sO4rhlZozugQPpK/mJHdX0W+JfyPPAn/FHLyUy7pU7LwT8nCp2ifcUmfkoV/Shb+KVP4X3gRvsUEh/SP4+IQfIIY2k5w6f8Td44rIBKO/GVuIw760YgB0AUc39gKrkzG6t0iEnUpigoAbJmzUP7GdED+xjSdK9iFs1D+xjTvAkgqgEJmISOVek/ZJbEuJbDNYDdjEj6PVHFvtwv3+nLuwZ8BL5yCgf096SZlx6D2cXFoHlxUxe7zNTwKL+xB24LPnEbW/tgN0/2NtUeFaT3FbvgqAh6vAXEDWdtTJM6SdrCUOLDYV4rdC/SVyRV6O8N3cJQVboOj2wjf";
    public static final String jlc$ClassType$jif$1 =
      "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";
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
    public static final String jlc$ClassType$jl$2 =
      "fLdDcL3wRVruf/wBsvkzj+I/2HZXd9g2wDZZ36X0za8e4daul+RJv7yflD6+HdTT0yf++FYft/pf/fpJ6UHux1/W1W9ASo/iTz2K/+R5Av/5B/D/XzyKP7vtZeEc+kMfvpLya2zut4PdYM3Pvax/z9ePzQe5H3tZ51+bzfdO+1c+oO+/ehR/oX/6hnLI86/c99+Pscft/Pbkv/ay/s++fow9yP2nL+tf/NqMvfHiIxKvjPm/+wDu/vqj+Kubq+6e08efv1DwaGLfj7dtt3gSNpT4iRf1J/+frx9vD3L/98v67/wGePvVD+Dtf3kUf2NDXX31Kjmenbel/IrvrTy+SfFtX/UdpxdfG/L/5M9/5uO/6ef1v/78VZN3vwj0UeHp49GmDu/9AMN7zj9at2GUPM/hoy8+x1A/V39nm8s7buCRDr9VzxP/2y+6/27/9KGX/+3+e8/c//j8/wErLhSMZ0oAAA==";
}
