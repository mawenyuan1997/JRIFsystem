package java.io;

public abstract class Serializable_JIF_IMPL {
    public static boolean jif$Instanceof(final Object o) {
        return o instanceof Serializable;
    }
    
    public static Serializable jif$cast$java_io_Serializable(final Object o) {
        if (o == null) return null;
        if (jif$Instanceof(o)) return (Serializable) o;
        throw new ClassCastException();
    }
    
    public Serializable_JIF_IMPL() { super(); }
    
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1558403437000L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAAI1WXWwUVRS+O223P1S2rYAVaBlgxRZxG2KM0eIPNBQWF1spaCyB9e7MnXJhdu70zt2ygDVoYiA+8KCl4gM8YaIGITEhPhgSnhSCMdEYfx78eVOjPPCiLyqee+/Ozna2VDeZu3fOPX/3nO+cMxduoqaAI9Nn7pEJl4mMOOKTIDOKeUDsIRcHwW4g5K3TDwzMvLW/48MGlBpHKeqNCSyoNcQ8QcpiHLUXSbFAeLDZtok9jjo9Quwxwil26VFgZN446grohIdFiZNgFwmYOyUZu4KST7iyGRJzqN1iXiB4yRKMBwJ15A7iKTxQEtQdyNFADOZQ0qHEtYNJ9DJK5FCT4+IJYFyWC28xoDQODEs6sLdRcJM72CKhSOMh6tkCrYpLVG+cfhoYQLS5SMQBVjXV6GEgoC7tkou9iYExwak3AaxNrARWBFp+R6XA1OJj6xCeIHmBuuN8o/oIuFpVWKSIQEvjbEpTmaPlsZzVZOvmM5tOHfO2e4by2SaWK/1vAqHemNAu4hBOPItowfb1uVm87MpJAyFgXhpj1jwfvXTrqQ29V69pnhXz8IwUDhJL5K3zhcVfrBzqf7RButHis4BKKMy5ucrqaOVksOwDFpdVNcrDTHh4ddcnLxx/n/xmoLYsSlrMLRUBVZ0WK/rUJXwb8QjHgthZ1Eo8e0idZ1Ez7HPUI5o64jgBEVnU6CpSkql3CJEDKmSIGmFPPYeFex+LA2pf9m/DLwHPP/JB8JME+Q9wGKo4YVa98P2ylLvrcCIBV1oZLy8XsLiduTbheWumtGXrrYv5G0YVYBWLAvVImGUoy1RrqeCS/I7scD67czSHEgmlfYniUsGCqx6CEoIqae8f27fjxZNrGiBL/uFG8FOypuOYiSotCzsMQMhbqRO//HFpdppF6BEoXQfqekkJyjXxm3JmERuKPlK/3sSX81em04aMcCvUusCQDSic3riNOeAcDAtdRsfIoUUO40XsyqOwOtvEAc4ORxSVgkVySelsyGDFHFQ94vEx/+y3n//6kCEVh+0kVdN3xogYrIGwVNauwNoZxX43JwT4vj8z+ubpmyf2qsADx9r5DKblKlGDAS2Mv3Zt8rsffzj/lRElS6CkXyq41FKep0DRusgUoNuFCgNPgvQer8hs6lAJDZn4v1L3bbz8+6kOnT0XKDoWHG34bwUR/d4t6PiN/X/2KjUJS3bXKBwRmy7ZuyPNmznHR6Qf5Ve+7Hn7U3wWih8KLqBHSdmX5aKuh9St+lSa1qr1/tjZerksL6uzJRW6elmh1l65rFZ0Q27XCNSCC4BIbIlKxOZIctRzp3apWv35V2fO2SPvbNRNrWtuC9rqlYoffP33Z5kzP12fp0pbBfMfdMkUcWtsGmByVczkTjVLokp4b+eF69vWWW8YqKFaaHUjaa7QYK1xQDwnMFE9eQ1JaVZX71ZuLAYn5IN6JJyQ/iV0w5qtb1iPmZMlHNDJEhOkL1DT3dQANAuMuQR75kHqpEM/mNO316Eedk2Z+mO4WJjOyHGod3oAqL3J9g33m8e0n2YfM2lVg1nb1foHp/vDprlwnpPau5osRwhKVDqdfO+ETweFS+lXxSUl9MQCsNsil0cEWjz3skH95BzltAjNYKoyOcnJmddvZ07NGDWfF2vrJnytjP7EUFbbdI8CK6sXsqIkhn++NP3xu9MnjIrHGwVqrmRI3ehhnf1wOnWHWQ8J8rTj/0RaLpsWirJ8fVIxjCwQ0WflskPOMoiohQORlknJU5avTX+YsSXzTbwyfAXNOwFl7+mu+3TVH1jWxXOplnvO7flGzZnqR1AS2rhTct2aIqotqKTPiUOV40k9PnSknocoV1wTyKBM+fucPhsXqFGeyf1eX0flX1AP7NRYCwAA";
}
