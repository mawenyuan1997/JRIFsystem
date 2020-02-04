package jif.util;

public abstract class List_JIF_IMPL {
    public static boolean jif$Instanceof(final jif.lang.Label jif$L,
                                         final Object o) {
        if (o instanceof List) {
            List c = (List) o;
            return jif.lang.LabelUtil.singleton().equivalentTo(
                                                    c.jif$getjif_util_List_L(),
                                                    jif$L);
        }
        return false;
    }
    
    public static List jif$cast$jif_util_List(final jif.lang.Label jif$L,
                                              final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$L, o)) return (List) o;
        throw new ClassCastException();
    }
    
    public List_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 0L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAI1WTYwURRSu6d2d/WFhdlfAlZ+lgRFncJ0NMcbogAorC4ODrCwksghjTXf10FDT1dtdswzgGDQxEA970AXxACdM1CAkJsSDIeGkEIyJxvhz8OemRjlw0YuKr6qm56cHVieZ6upX76/e+957feEm6vA9pLuMHilQxlP8iEv81Dj2fGKOUuz7u4CQM049ODL71v6+D9tQbBLFbGeCY24bo8zhpMwnUW+RFPPE8zeaJjEnUb9DiDlBPBtT+ygwMmcSDfh2wcG85BF/J/EZnRaMA37JJZ60GRCzqNdgjs+9ksGZ53PUlz2Ip/FIidt0JGv7PJ1FUcsm1PSn0MsokkUdFsUFYFycDW4xIjWOjAk6sPfY4KZnYYMEIu2HbMfkaEVYonbj+DPAAKKdRcIPsJqpdgcDAQ0olyh2CiMT3LOdArB2sBJY4WjJXZUCU5eLjUO4QHIcDYb5xtURcHXLsAgRjhaF2aSmsoeWhHLWkK2bz66fOeZsdTTps0kMKvzvAKGhkNBOYhGPOAZRgr1rs6fx4isnNYSAeVGIWfF89NKtp4aHrl5TPEvvwLMjf5AYPGeczy/4Ytlo8rE24UaXy3xbQKHp5jKr49WTdNkFLC6uaRSHqeDw6s5P9hx/n/ymoZ4MihqMloqAqn6DFV2bEm8LcYiHOTEzqJs45qg8z6BO2GdthyjqDsvyCc+gdipJUSbfIUQWqBAhaoe97Vgs2LuYH5D7snsbfhH4/yP+CH6CIJ4Ah9GqE3rNC9ctC7n5hyMRuNKycHlRwOJWRk3i5YzZ0qbNty7mbmg1gFUtAqIP2paKggB+bltmLJfZPp5FkYhUu1CgMDh3DkHtAFdvcmLfthdPrmqD9LiH28FBwRoPg6VeYhnYYUBAzoid+OWPS6crrA4bjuItaG6VFGhcFb6ixwxiQrXX1a/V8eXclUpcE6HthiLnGNIAFTMUttGEynRQ4SIsWhbNs5hXxFQcBWXZww947HCdImM/TywxlQYRrJCDsjlsmHDPfvv5rw9rQnHQR2INDWeC8HQDdoWyXonS/nrsd3mEAN/3Z8bfPHXzxF4ZeOBYfSeDcbEKuGCACfNeuzb13Y8/nP9KqyeLo6hbylPbkJ7HQNGauimANYXSAk/8+G6nyEzbsnGeEpH4v2L3r7v8+0yfyh4FioqFh4b/W0Gdft8mdPzG/j+HpJqIIdpqPRx1NlWr99Q1b/Q8fET4UX7ly+Vvf4rPQtVDpfn2UVJ2RZ3I6yF5q4RM02q5PhA6WyuWJWV5trBKly9L5ToklpWSrontKo66cB4QiQ1ejViTpIeW361Pyh5//tXZc+aOd9apbjbQ3Hs2O6XiB1///VnqzE/X71Ce3Zy5D1EyTWiDTQ1MrgiZ3C6HSL0S3tt+4fqWNcYbGmqrFVrLLGoWSjcaB8R7BEapI64hKJ3y6oPSjQXghPij5QJOSP3kk0eGWzvV4/pUCfv2VIlxkvDlWNcVAPU8Y5RgR4cuFA/8YFZir2U7mArqMVzMV1JiDKpdFucJlVspkx3Wq6yAkjCvGhKKme0bS+rHbEtPMN2uGdIFmoAun7qhb9ATksLSurq9MCI1KsO7Rcp8GMaUcOYkkikyVbKnMSUO38USRkq4VCAcHjmR3ZzsqdlEclj6mkxXqlotTH2SriSDHj43+qIqZg3Yq+NaqyJCvPdztKDZ34DcJ4tI0lVQpK4n5qiRp8XyqFLYkBm/db6Pe3YROtd0db6Tk7Ov307NzGoNH0GrW75DGmXUh5C02qMaKlhZOZcVKTH286XKx+9WTmhVj9dx1FmFk7zRIwqqwQwdDCAaEMRp3/9JgFjWzxV88fqkWDZKrufmCOuEWLJq7MYN7PN4E1aCfM1vmsplIDRNZ9EXB1u+p9VXn3HxXKzr3nO7v5EzsPZlFoURY5UobSjwxmKPuh6xbOliVI02FZg90PsCXzhqFw/p4/Pq+AWO2uBYbPe5KgL/Aga8e1zvCwAA";
}
