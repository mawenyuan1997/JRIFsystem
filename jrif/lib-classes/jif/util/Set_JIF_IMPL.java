package jif.util;

public abstract class Set_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof Set) {
            Set c = (Set) o;
            return jif.lang.LabelUtil.singleton().equivalentTo(
                                                    c.jif$getjif_util_Set_L(),
                                                    jif$L);
        }
        return false;
    }
    
    public static Set jif$cast$jif_util_Set(final jif.lang.Label jif$L,
                                            final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (Set) o;
        throw new ClassCastException();
    }
    
    public Set_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAI1WTYwURRSu6d2d/WFldlfAlZ+lgRFncJ0NMcbogIorC4ODrAwksghjTXf10FDT1dtdswzgGjQxEA8cdEE8wAkTNQiJCfFgSDgpBGOiMf4c/LmpUQ5c9KLiq6rpmd4eWJ1kqqtfvb9673vv9fkbqMP3kO4yeqhMGc/wQy7xM+PY84k5SrHv7wBC0Tj5wMjMW3v7PmxDiQmUsJ0Cx9w2RpnDSY1PoN4KqZSI528wTWJOoH6HELNAPBtT+zAwMmcCDfh22cG86hF/O/EZnRKMA37VJZ60GRDzqNdgjs+9qsGZ53PUl9+Pp/BIldt0JG/7PJtHccsm1PQn0csolkcdFsVlYFyUD24xIjWOjAk6sPfY4KZnYYMEIu0HbMfkaHlUonHj5DPAAKKdFcL3sYapdgcDAQ0olyh2yiMF7tlOGVg7WBWscLT4jkqBqcvFxgFcJkWOBqN84+oIuLplWIQIRwujbFJTzUOLIzkLZevGs+tOHHE2O5r02SQGFf53gNBQRGg7sYhHHIMowd41+VN40eXjGkLAvDDCrHg+eunmk8NDV64qniW34dlW2k8MXjTOleZ/sXQ0/WibcKPLZb4toDDr5jKr4/WTbM0FLC5qaBSHmeDwyvZPdh19n/ymoZ4cihuMViuAqn6DVVybEm8TcYiHOTFzqJs45qg8z6FO2OdthyjqNsvyCc+hdipJcSbfIUQWqBAhaoe97Vgs2LuY75P7mnsLfjH4/yP+CH6CIJ4Ah9G6E3rDC9etCbm7DsZicKWl0fKigMXNjJrEKxoz1ac23rxQvK41AFa3CLnfb1sqCgXCi1tyY8Xc1vE8isWk1gUChOoYrngASgeqozdd2LPlxeMr2yA77sF28E+wJqNYaVZYDnYYAFA0Esd++ePiqWnWRA1HyRYwt0oKMK6M3tBjBjGh2Jvq1+j4UvHydFITke2GGucYsgAFMxS1MQuU2aDARVS0PJpnMa+CqTgKqrKH7/PYwSZFhn6eWBIqCyJYEQdlb1hfcM98+/mvD2lCcdBGEqF+A2HPhqArlPVKkPY3Y7/DIwT4vj89/ubJG8d2y8ADx6rbGUyKVaAFA0qY99rVye9+/OHcV1ozWRzF3WqJ2ob0PAGKVjdNAaopVBZ44id3OhVm2paNS5SIxP+VuG/tpd9P9KnsUaCoWHho+L8VNOn3PoWOXt/755BUEzNEV22Go8mmSvXupuYNnocPCT9qr3y57O1P8Rkoeig03z5Maq4oE3k9JG+VkmlaJdf7I2drxLK4Js8W1OnyZYlch8SyQtI1sV3JURcuASKxwesRmyXpoWV3apOyxZ97deasue2dtaqZDcxuPRudauWDr//+LHP6p2u3qc5uztwHKZkiNGRTA5PLIya3yhnSrIT3tp6/tmm18YaG2hqF1jKKZgtlw8YB8R6BSeqIawhKp7z6oHRjPjgh/miZgBNSP/nksVRro3pMn6xi356sMk5SvpzqugKgXmKMEuzo0ISSgR/MSu22bAdTQT2CK6XpjJiCapfHJULlVsrkh/U6K6AkyqtmhGJme8bS+hHb0lNMtxuGdCgqIIuHbujr9ZR4Z1ldXV1YkOqU1Z0iXz4MYko4c1LpDJms2lOYEofvYCkjI/wpEw6PokhtUfTTfCo9LP1MZ6frSi1MfZKdTgfte27kxVW8QrhrYlqro0G893M0f7a7AblPFpCkq4BIXY/PUR9Pi+URpTCUFb91tI97dgW61lR9tJPjM6/fypyY0ULfP6taPkHCMuobSFrtUc0UrKyYy4qUGPv54vTH704f0+oer+Wosw4leaOHFUyD8TkYwDMgiNO+/5MAsaybK/ji9QmxbJBcz80R1oJY8mriJg3s82QYKkG6esPzuAbv4bEsGuJgy3e0+tozLpxNdN1zduc3cvg1vsjiMFusKqWhyg5Xedz1iGVL/+Jqpqmo7IKmF3jCUbt4SA+fV8cvcNQGx2K7x1XX/xfMFB+d5wsAAA==";
}
