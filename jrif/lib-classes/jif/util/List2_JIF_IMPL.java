package jif.util;

public abstract class List2_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof List2) {
            List2 c = (List2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_List2_S(),
                                                 jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().equivalentTo(
                                                 c.jif$getjif_util_List2_C(),
                                                 jif$C);
            return ok;
        }
        return false;
    }
    
    public static List2 jif$cast$jif_util_List2(final jif.lang.Label jif$S,
                                                final jif.lang.Label jif$C,
                                                final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (List2) o;
        throw new ClassCastException();
    }
    
    public List2_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAK1XXYxTRRSetrvdH1a6u/Kz8rNcoK4trt2gMUYLKlQWikVWCoksQp3ezi2Xnd65e+90KeAaNDEQH/ZBF8QHeMJEDUJiQnwwJDwpBGOi8ffBnzc1ygMv+qLimZne3v7AyoM3uXOn55w558w53zkzPXcdtbsO0mxGDxUp4wl+yCZuYgw7LimkKHbdnUDI6SfuH5l9c1/vByEUGUcR08pyzE09xSxOKnwc9ZRIKU8cd0OhQArjqM8ipJAljompeRgEmTWO+l2zaGFedoi7g7iMTgnBfrdsE0fa9IgZ1KMzy+VOWefMcTnqzRzAU3ikzE06kjFdnsygsGESWnAn0UsokEHtBsVFEFyU8XYxIjWOjAo6iHeb4KZjYJ14S9omTKvA0YrmFbUdR58GAVjaUSJ8P6uZarMwEFC/coliqziS5Y5pFUG0nZXBCkdLbqsUhDptrE/gIslxNNAsN6ZYINUlwyKWcLSwWUxqqjhoSVPO6rJ1/Zl1M0esLVZQ+lwgOhX+t8OiwaZFO4hBHGLpRC3sWZM5iRddOh5ECIQXNgkrmQ9fvPHk8ODlK0pm6S1ktucPEJ3n9LP5+Z8vS8UfDQk3Om3mmgIKDTuXWR2rcpIVG7C4qKZRMBMe8/KOj3cffY/8FkTdaRTWGS2XAFV9OivZJiXOZmIRB3NSSKMuYhVSkp9GHTDPmBZR1O2G4RKeRm1UksJM/oYQGaBChKgN5qZlMG9uY75fziv2TXgC8P4jXgSPIIgvwCFVdUKreWHbFbHuroOBAGxpWXN5UcDiFkYLxMnps+WNm26cz10L1gBWtcjR4gOmoaIggP9gbmt6NJfeNpZBgYDUu0DA0BOwJqB4QKwnnt279YXjq0KQH/tgG3goRKPNaPFrLA0zDBDI6ZFjv/xx4eQ083HDUbQFzq0rBRxXNe/RYTopQLn76tdo+GLu0nQ0KGLbBVXOMeQBSmaw2UYDLJNeiYu4BDNonsGcEqaC5dVlN9/vsIM+RQZ/nhgiKg8iWE0Oyu6wPmuf/vazXx8KCsVeI4nUdZws4ck68AplPRKmfX7sdzqEgNz3p8beOHH92B4ZeJBYfSuDUTEKvGDACXNevTL53Y8/nP0y6CeLo7BdzlNTl55HQNGQbwpwTaG2wBM3ussqsYJpmDhPiUj8X5F71178faZXZY8CRcXCQcP/rcCn37MRHb22789BqSagi77qh8MXU8V6t695g+PgQ8KPystfLH/rE3wayh5KzTUPk4otCkVuD8ldxWSaVsvxvibeGjEsqUjegipd/lgqx0ExrJT0oJiu4qgT5wGRWOfViDWsdNDy2zVK2eTPvjJ7prD97bWqnfU3Np9NVrn0/td/f5o49dPVW9RnF2f2A5RMEVpnMwgmVzSZ3CZPEb8S3t127urmIf31IArVCq3lMGpclKw3Doh3CJylltiGoHTIrQ9IN+aDE+JFywWckHrklwe+am1Vj2mTZeyak2XGScyV57qmAKjlGaMEWxq0oajnBzNiewzTwlRQj+BSfjohzkE1y+A8oXIq12SHtTsVTdVEAVDNsupAUcJs72hcO2IaWoxpZs0nTTZIYKiJpmvrtZiisWRtH2wCyNC7SFLOYBgaEtalKeXRLpF2F050SjizYvEEmSybU5gSi+9kMT0hfC0SDp+cQEhONeZsLD4sNxz/vzWnqppT8aTKOOhOTlenBqYuSU7HvdNm7jIJq+TWFYlfgCEpGZK/+zia3+i6oD7h8XplyUumyotU+PgcFf2UGB5RWutw5LZeR8YcswR9dqp6HSHHZ1+7mZiZDdbd2Va3XJvq16h7m7Tardo/WFk5lxW5YvTnC9MfvTN9LFj1eC1HHVXQyB09rArLO/IHvILyCILbeydZEMO6uTIgQ10bNkjRZ+eIbVYMGXVViOrY5dFGADVk1L9KVIDSeKcQ3Xyg5W+Auqzq589EOhef2fWNPLlrF8owHIxGmdK6tlTfosK2QwxTehlWB7IK0G7o2J43HLWJj/TyOcV+nqMQsMV0r62C8C+eMPPMpgwAAA==";
}
