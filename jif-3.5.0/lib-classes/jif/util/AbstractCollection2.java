package jif.util;

/**
 * Base implementation for Collection.
 */
public abstract class AbstractCollection2 implements Collection2 {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractCollection2 jif$util$AbstractCollection2$() {
        this.jif$init();
        {  }
        return this;
    }
    
    public abstract int size();
    
    public final boolean isEmpty() { return this.size() == 0; }
    
    public boolean contains(final jif.lang.JifObject o) {
        return this.contains(this.jif$jif_util_AbstractCollection2_C, o);
    }
    
    public boolean add(final jif.lang.JifObject o) throws ClassCastException,
        IllegalArgumentException {
        return false;
    }
    
    public boolean addAll(final Collection2 c) throws ClassCastException,
        IllegalArgumentException {
        boolean modified = false;
        if (c != null) {
            Iterator2 e = c.iterator();
            while (e != null && e.hasNext()) {
                try { if (this.add(e.next())) { modified = true; } }
                catch (final NoSuchElementException impossible) {  }
            }
        }
        return modified;
    }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "H4sIAAAAAAAAAL07C3hU1ZnnTiBPICE8JDzikAQ0CBlAhc8GKmEACTtAJCQWWo03d+6EC3fundx7JgxQ+lFsBbHGLQXF7cLW/cBF14K7Kx9s66O6reBjbWn9FOlaLX77Fa244tZuLUX3/Oec+76ThG66+b455855/ed/P+7k8QtoqGmgSeuVVAPelJHNhmVKqkU0TDnZoqubVpOhDunTh19J7luTeTuCCteiYsVs00wxJSdQiZjF63RDwZswqkisF3vEWBYraiyhmLgxgcokXTOxISoaNrvR15CQQBUKGRE1rIhYTi4x9DRGkxMZAqhL1XFMzuFYRjTEdIxeJdYSV0XTJCcV0lHrkOKMofcoSdnA6OoEuThfrYqdshpr4XMJ+NaYM1DUOp7jx5CjJzPs9l4X2/PgHRX/XIDK16JyRWvFIlakuK5hcp+1aFhaTnfKhtmUTMrJtWikJsvJVtlQRFXZTBbq2lpUaSpdmoizhmyukk1d7YGFlWY2Q64IMK3BBBrGSJKVsG5Y6BSmFFlNWt+GplSxy8RorEMWht4SGCe0KCXklI2UKMnWliEbFC0JtPDtsHGs+yuygGwtSsuEXzaoIZpIBlAl45wqal2xVmwoWhdZOlTPYiDw+LyHNgIjRGmD2CV3YDTOv66FTZFVJZQQsAWjMf5l9CTCpfE+Lrn4c2HFvN4t2lItQu+clCUV7l9MNlX7Nq2SU7Iha5LMNg6blnhAHPv0zghCZPEY32K25vhXLy6YXv2jU2zNhJA1KzvXyxLukA52jjg9MV5/UwETQd1UgPkezKnwt/CZxlyGKNZY+0SYbLAmf7TqhTXbHpN/G0GlzahQ0tVsmsjRSElPZxRVNm6RNdkAFWlGJbKWjNP5ZlREnhOKJrPRlamUKeNmNESlQ4U6/U5IlCJHAImKyLOipXTrOSPidfQ5l0EIVZAPqiefQvLZwPtVGImxNpOIe2yjrG3KilpajBGxlkVDWheDw6IpVd8YW7aqeYm5ycRyOkYUcMb1DTc2zIypSucM05BghJGiqRPUX8Lk/iqhIcF7dgOZzPx/AMkBphUbBYEwYaLfBKhEe5bqKjETHdKe7MLFF490vByxVYLTCKOJYBUp30KAIEGgh48G7WGrCG82EC0n1m9Yfevty+7cWVNAxCqzcQihLCyt8VjZuGMKmqlVlIg8/uLmzJ29N06YF0FD1xJraS6SU2JWxS3xhXpWI1ZltD20SiYGR6NmLtTUFmUkugejqwJGkhlHss1wDoFtE4gO1Pk1Meya5TvO//7oA1t1RycxqguYiuBOUPUaPzcMXZKTxHg6x0+Lisc6nt5aF0FDiP0guGGCGZijaj8Mj8o3WuYTcBlK0EvpRlpUYcqiSileZ+gbnREqJiPo80jCpRGgFLXkU0Y4FuP9eJgdlYF2NBMrYLsPC2qe57dm9p959b3rIyjiWPJyl2dslXGjy3rAYeXUTox0pGi1Ictk3Vv7Wr6z98KOL1MRIitqwwDWQRsnVoP4R0Lmb57qfvPtXx18LeKIHUYlxF1iIrRyMmfjCVNoOMevivcVLjwJwKnOlRyxN+vatLSeVFKK2KnKIOp/Kp8y69gHvRVMFFQywghroOn9H+CMVy1E216+43+q6TGCBA7QIZuzjFnVUc7JTYYhboJ75L7+80kPnRT3E/tMbKKpbJapmUOUDIjybSbF/zraxnxzs6GJEo32TxJwExy9pfpDYgiFBRgd0tiPa2KZJYveoSwvJaKaInGTIpGIaGJA7eL2LOge+PEua/GkwOJmZxq05ir/HTj8IbdHkx9Ha75MVaUsKZuSoWQs2SJWv9RU0hmVkFtOUg0n8QbWlxHy2cGTIWqmSpwNswqr6eTiXMYA190jGpRPlCq1OZBT+xotEJN1SHPv3WHotbvmRDghR0AzOUciwiQzVNGMFFUtC/MFkGR6hgXWIaYDukPaP+bBpyr/cXcT88tXe3cEVs+bGb+744Z/+ncq9yAg1X5qrZJFYu0ZOTukjw+clVfd+OmHTE/1jZo/lsyQMEhSMiLEk/wJwlCDngJ4LCC3GhcQC378nPu+d/TCr1oWUFl2MQCChUC8yjnsIh20ca+7sO/TsFrP2FfqkO4Y+7PrJj615h43mXwbXKt7H/3bov+a/un3KNq2GNT6xMDe0KcoQHsTuy+1FR4GuS/p5tNVY9567VTP0g/Zdf1yELbj5tmjnzk/rmoL5ywAXMShQrc0lNm3kZTEYXa0IfH8s0WrXnIxm3KQkGAjXcj4CW2Tw4AV5OApYfRcqGOsp11UnV97dn3j5dNPWgqw2KZKvRdB3043moXTfljV+8ttK60zmhmqK12o3sqGbmA2/HPyJ5DPZ/ABOwUD0JN4Ps6jyKgdRmYyOWrW2+jmeSaYE1/UslQ01xGvc0Z9Y+3et6ZVMw65vBKf/8Gib+594MTxG1hgMwziyJsXIMQsJRx+MzTtFImvODi0e3AIGVrubBMdLrfbXA4OsX6cZaXhSw1tp0BzLR2PYJI6ZjuJHGBULPIYzvGC9K+ch7638n6h2ws6IIRAltyaJQe68pQvXdv+3DeqzHsZ6eptd02yyAaav/Kt/n1vnimYXYbr7qfyOaRTNGkAWkxk1YSVGEXzZ8j0LGY8ymy0igCtieRD2CMs4f0sjNYMXtAdjOj/codT7JgEjMEk3LZictcyGoYHeNMhjbzpu52p7QtOUkNcTslJfUcrI+xUlzV2NtW1etd5iwih3OyQzh+979Tk99tH0ezQYpw78lsuZhr9kR7oFBkfWnT2uX8be+fpAhRZgkpVXUwuEWnQjEpItCqb60imkstwNSvYWMzjtwg5rMrnjp0Qes6LjxX8tP0r+6lUuUPocV6k3fk8LyRUu1Z4TmV1hFwwoWoXXbnB7MfSn0RqCn8SQUUkhaGJiajhdlHNQli7FpUqZpwPJtBwz7y3GMEy70Y7wZjoD/5dYP2hv5PIkWdYDc+lvmh/EhC0jivICt7fArMG1X8zhxB9uCvcvBTAo0JsjElLRphcQdFElUJpx0hoNYM1CmL+0yT27+E1Cnnnnl2fN/TuibgKObWBWop7D2cCwBjODaKBJvcFhe5Y8pujW394eOsOZp0qvWWJxVo2/f3XL7/SsO+dF0Py4KF2+IYhm84jcxRMzbtFO5rPLzsUQQU0f43rNG7QmD7RoWbNO1SmmLZPpGj1QvPXSEBeYFboTUDy2Lf0EzH+1INNK+iVh2fcl8GoKp8UM9rNtaWgFaRgNbf+D/B+rzvnw2guBR6lIGQSMkRbo3oqKoHuRPsqEsD+vWYggKX3cfv/M4nLB+a//swxV5iz1b7gNLjgLH4xk/eq+4JcTP+mLzGFZgc0Ox35hIdv0YwWmt0+uFMB7gyuFrfxviVEPf7uCuHGbbjYgfsdG24bwG3neO7j/YP9MCQ+cIYcpJD2+cl8PYD9Agf3Vd5nQ8h8+M9Gl5E5BxpbF+av/K5nVqO04nn9pnoq4IV4nWLWzaSZcYjbamQh3mY4fFK+airV0YPb9xxIrjw0ywo2ezFxNHpmhir3yKor5Am+GFhO68eOqZ/78KK6ic939w5etQq+TggvTF3tQ8p/mUeXP/7iLVOl3cT02C4jUBP3bmr0OopSBnW1x11U2wIyGgTkahYyCvfz/htuAaE8zhOK2tKx3leGGcVPuov32B+Ahhcsnu5j7llojmNWBjE9xQNKUjnJvM+hw48faRz26CEWJFCVIqYJcyYUww7rOyPGcC8xxvMr7wojhk+rx8GGKr7wHt7vHDwz+2Ou1Y4h3MXdI3T3YVRAxC2svgMDN0BzgqLYCM0rPFGCdk3eNIdt+KmTnJwI5iveoeXOttecVOhEMDuyh6BladuTNilp7lLLHoTjvD90xYK40yeIFfykg7zfNzBBfLuPuV9D8yZGRYq5OJ3Bm+ii57yITODgjoYh4hOiKkvqYOER3n9/8IToP/sToqJOXVdlUetTkM46gnRhQILENnzkSM3ZoCCdDQoS2/aJIzVng4J0ti9BKoN71JDPGCIXAuuF9wcoSAIVJJ8MlfJD3uP9u/llSOB5tVckQKzRdH6lMt5HPCJhoPH+qLDJ6OIx4eHhP3n5wrglp2g9NCIpUFoNvJdIyvk8QDaTkQ23J4r0UF3ZzoJG0ggRImNtcGRaNzLrFF7cBBljrxuiotGVTcsajuowyN65RnkGZkav7YTT5WRU7NR75GjnpuiWc7sOnrt799Z6OH2oXXeyo8W4qGk6DhQ9CyXl2IVY6rLlxufnF7InobnEmAnNn/KwIpjZtWkbNH2jxtxFa9nj2buennHGgsjzD3gWhuW3AuzFwh8xKraIAIMjSCwbUKGQYh6/AWfv6NgH+1f+4dwT1hXmMsxYzCwUsW6UbxCjiH6Nu3pmydksLl8VYXKG0Uirgk04Gu3kQiFUDUSlhfEU82pbf8mAX6V9Q8udbbW2/lprXCrtGvLkRyQ+S+iSqDoR0epvnXxjzkPnd9Pw0UniJoS8OvftVA+qJxO/2/SqReZtDK5AA2BhWh8GwQl7dTqf9palxjMbIYi8X4HRbYNSOaIVA0ICVjTgRam/1NGUSQ1WSaoSeACLGuxFdKqHtlu4cAi0cBOxTZ6TeEywTB4QxOR9J4CYSck9iycewpwrTDwS8ACJhzCb7vk2RuWSlZAzsyVwLXRcLCR8qIErxHDeD/Mqxv8h+xLmu1zs4RwTZfDgAtVNoYCZWGgLoYFyrFAcqmwwUwrNDKqWVDIpY+gM6sMg0RL9H+FppM/9UI9YxZ3XH8I8Ytj7wGW6YhmokcVvv3PBqJljvQ9MZ3RN5nlOLRnRqI8gYTlzML5aRGUY+Itu8FQQmoNv0ewruF3EL0+/8cjf7ypL9eMiwOP0+6q2VcbbJu0+ff93t7VZx00POa7AJ06Q3dBkATD5b95/3I84DTxiE24NRGwO7DEAeyyH+Qnvfzd4otzmFuVQ6V3Tj/RSz+RILx1shtXv9RlgXrIDTOH2AQWYdINwpxNNXgoGmJeCASbbJjvR5KVggHmp3wCzin0irbxf7OYAtDzAhOZyMJyELYt4/0XX1gGFk5Ms2wq7U7y/zaPPlG/2hvqwDZJXZpqvOPoTk8mQwK+13h220ChN2GBHaYIajiFMjaCMyfkWROwETxjhrNoMzXqS/5JLdOcLvLp52ETgB2Mp96ATSzl6RguU13FaybxPDp6Ob/fruHCtl8ENHOa6MAbzoOXuKwhanoMN3ZR627stV8+U26SjUBIRqGfN0sN7oNllr7+723ZDejiPfGI6D7C4ld/+Xt73+MXURfKVsKGFL9zF+3sGj+TfDpjVMfB7OPASNNKhFcW4aOLFOUmmvzfxYUSr6hq/2GHe39cHRipsSPOF/8D7RwYPo4fCMKpxMGomq7tEtYmrr40X5WIv1SFotoQFDGP4dVeGWbh8DhEWruD98sHD82E3ntC8l0ffXXeCqJyGoXCXNt6vHrw7PdKHk4YcDM3mMFXebxg82I8FYPvcE7wpryYw3+f9m27Y3f26J9hyhvc/d20dkHuKwinXs90FAj/lQ7/0uNzTzLANn3mplRiQe5K87qlJVQfooY46HuqJ/jzUUwPyUM9AcwSjQnaP7n6d1NEwJ3XU46Qkv5Oy5QwohzjlPh88OXuhDydF2Xwjh10QxmbupF4akJOSHCd1jBLwBfiecZzUcTp6ApqfQfOveSPQH0DzqnWG8BI9iM78y4AcF5yIJiP6XqTgGt5X9mH+4P0JpQcsnMr7KYPHhtd95k/Y67sxLfx8kQNexPv6Pm5MffN8vjDO+4WDd+P/8N94P6X/L6hSQPNsPpcDUvRumNXK53Jg4Tne/3rwMDgXdDl5shfnTpO4CtK7fMD73w7enX7jp+rr5MBRIYuhijYu8D9C7P9apCMHyouvOtD2Bq0m2/97UpJAxamsqrp/Y+J6LswYckqh2lrCfnHCNOoCRsXWtTEaAh1V2Q/Y9EckRCfT8HjR/rlThROgsMpSjtlFz+/ewoscv/e++IPXt1n2H1Ud0kezZy165tTUk/yHmHl/3eTsOHpg2YotF+ewV4VDCQ82Q14BP1MrYp6F3gF+9j0572nWWYVL6y+NeKJkiucnw5UuX+vBbpzz2ooK1+Gc6fmpK/9diPv/vjqkDWjrvT/eUfl1ct21qEQxVxtZE8N/YJVI1rtwOOtrrP7Cfh4pfEYOvsb/o1TXse7SirD+oZWJos+/1HdphRruiv8FC+in32U3AAA=";
    
    public AbstractCollection2(final jif.lang.Label jif$S,
                               final jif.lang.Label jif$C) {
        super();
        this.jif$jif_util_AbstractCollection2_S = jif$S;
        this.jif$jif_util_AbstractCollection2_C = jif$C;
    }
    
    public void jif$invokeDefConstructor() {
        this.jif$util$AbstractCollection2$();
    }
    
    private void jif$init() {  }
    
    public static boolean jif$Instanceof(final jif.lang.Label jif$S,
                                         final jif.lang.Label jif$C,
                                         final Object o) {
        if (o instanceof AbstractCollection2) {
            AbstractCollection2 c = (AbstractCollection2) o;
            boolean ok = true;
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_AbstractCollection2_S, jif$S);
            ok =
              ok &&
                jif.lang.LabelUtil.singleton().
                equivalentTo(c.jif$jif_util_AbstractCollection2_C, jif$C);
            return ok;
        }
        return false;
    }
    
    public static AbstractCollection2 jif$cast$jif_util_AbstractCollection2(
      final jif.lang.Label jif$S, final jif.lang.Label jif$C, final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(jif$S, jif$C, o)) return (AbstractCollection2) o;
        throw new ClassCastException();
    }
    
    private final jif.lang.Label jif$jif_util_AbstractCollection2_S;
    private final jif.lang.Label jif$jif_util_AbstractCollection2_C;
    private jif.lang.Label jif$jif_util_Collection2_S;
    
    public final jif.lang.Label jif$getjif_util_Collection2_S() {
        if (this.jif$jif_util_Collection2_S == null)
            this.jif$jif_util_Collection2_S =
              this.jif$jif_util_AbstractCollection2_S;
        return this.jif$jif_util_Collection2_S;
    }
    
    private jif.lang.Label jif$jif_util_Collection2_C;
    
    public final jif.lang.Label jif$getjif_util_Collection2_C() {
        if (this.jif$jif_util_Collection2_C == null)
            this.jif$jif_util_Collection2_C =
              this.jif$jif_util_AbstractCollection2_C;
        return this.jif$jif_util_Collection2_C;
    }
    
    private jif.lang.Label jif$jif_lang_JifObject_L;
    
    public final jif.lang.Label jif$getjif_lang_JifObject_L() {
        if (this.jif$jif_lang_JifObject_L == null)
            this.jif$jif_lang_JifObject_L =
              this.jif$jif_util_Collection2_S.join(
                                                this.jif$jif_util_Collection2_C,
                                                true);
        return this.jif$jif_lang_JifObject_L;
    }
    
    private jif.lang.Label jif$jif_lang_IDComparable_L;
    
    public final jif.lang.Label jif$getjif_lang_IDComparable_L() {
        if (this.jif$jif_lang_IDComparable_L == null)
            this.jif$jif_lang_IDComparable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_IDComparable_L;
    }
    
    private jif.lang.Label jif$jif_lang_Hashable_L;
    
    public final jif.lang.Label jif$getjif_lang_Hashable_L() {
        if (this.jif$jif_lang_Hashable_L == null)
            this.jif$jif_lang_Hashable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_Hashable_L;
    }
    
    private jif.lang.Label jif$jif_lang_ToStringable_L;
    
    public final jif.lang.Label jif$getjif_lang_ToStringable_L() {
        if (this.jif$jif_lang_ToStringable_L == null)
            this.jif$jif_lang_ToStringable_L = this.jif$jif_lang_JifObject_L;
        return this.jif$jif_lang_ToStringable_L;
    }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "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";
    public static final String jlc$ClassType$jl$1 =
      "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";
}
