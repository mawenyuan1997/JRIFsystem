package java.io;

public class IOException extends Exception {
    private static int __JIF_SIG_OF_JAVA_CLASS$20030619 = 0;
    
    public IOException() { super(); }
    
    public IOException(final String s) { super(s); }
    
    public static final String jlc$CompilerVersion$jif = "3.5.0";
    public static final long jlc$SourceLastModified$jif = 1466731420000L;
    public static final String jlc$ClassType$jif =
      "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";
    public static final String jlc$CompilerVersion$jl = "2.7.1";
    public static final long jlc$SourceLastModified$jl = 1466731420000L;
    public static final String jlc$ClassType$jl =
      "H4sIAAAAAAAAALU5WcwsWVn935m5s8JsDuDAjANzJYFibldXd1V3Z8BQXV3dtXftXV0ql9q7uvatq6p1zGCiEElGEwfERHiCxOAIiQnxwZDwomAgJhrj8qDwYKIGeeBBfVGxuv//bv+94JOd1Dmnz/nOd779++rUWz/oPVTkvZfSJGy9MCmvl23qFNd5Iy8cGwuNopC7iRvWZ4D+m7/zsaf+6IHek3rvST+WSqP0LSyJS6cp9d4TkROZTl6gtu3Yeu/p2HFsycl9I/QPHWAS671nCt+LjbLKnUJ0iiTcHwGfKarUyU9n3pxkek9YSVyUeWWVSV6UvaeYnbE3+lXph33GL8pXmd5V13dCu8h6v9I7Y3oPuaHhdYDvYG5y0T9h7C+O8x34Y35HZu4alnNzy4OBH9tl72cu77jF8TW6A+i2Phw55Ta5ddSDsdFN9J45Jyk0Yq8vlbkfex3oQ0nVnVL2nv+xSDugR1LDCgzPuVH23nUZjj9f6qAePYnluKXsPXcZ7ISpyXvPX9LZHdr6AffhN34pJuIrJ5ptxwqP9D/UbXrx0ibRcZ3ciS3nfOMTH2Q+a7zj65+60ut1wM9dAj6H+eNf/uFHP/TiN751DvPu+8CszJ1jlTesL5pv/8v3YB+YPnAk45E0KfyjKdzF+Umr/MXKq03a2eI7bmE8Ll6/ufgN8c82r3/Z+f6V3mNk76qVhFXUWdXTVhKlfujkSyd2cqN0bLL3qBPb2Gmd7D3cjRk/ds5nV65bOCXZezA8TV1NTv87EbkdiqOIHuzGfuwmN8epUW5P4ybt9XoPd0/vXd3zaK93du2if7bsaX2l6Iy/XztxWxlxZPQ7I3eM3Nr2j8hecsOk7lMiuSjaonSi/s53Xxleh6+D/c4nXilyq38yJz/pkyu8sZz0yO/1Dir9f8TdHPl6W3121on8PZfdP+x8hUhC28lvWG9WM/yHX7nx7Su3HOBCImXv2SPu635y/Q7cvbOzE86fOq2dVNgpIOgcu/PdJz4g/SL18U+974HOdtL6wU6EVzrQa5ct+bb/k93I6MzzhvXkJ//lP7762deS2zZd9q7d42r37jy6yvsu85cnlmN3oeg2+g++ZHztxtdfu3blqPdHuwhUGp2NdO784uUz7nKZV2+Gn6NMrjC9x90kj4zwuHQzZjxWbvOkvj1zEvzjp/HbOwE8ftOousHZGxf9J46rT6XH9ulzRR0leomLU3j7iJR+/u/+4l+HV46n34yET94RMiWnfPUO7zsie+LkZ0/fVpCcO04H9w+f43/7Mz/45M+ftNNBvHy/A68dW6zzOqNztyT/tW9lf//df/ziX1+5pdGzsnc1rczQt5pbTB7ne49dMPf6RX+4g8nutPffpqfz3rCLIB25xTUljhLbd33DDJ2jCf3Xkz87+Nq/vfHUuR2E3cy5VPPeh/5vBLfnf3rWe/3bH/vPF09ozqxj9rgts9tg5yHp2duY0Tw32iMdzSf+6oXf/abx+S64dQGl8A/OKUb0TjLonZQGnPh//6n94KW1V47Nu09L77zbFt7fPd2fs29e9G/dawvH9oVj8957JXzc8gcX/ZfulPDdtJyd6+r0/5kuyZ44PCa06+cJ7XTS8CcwMD42189V/Nxp/sHi3vyyOCbq216m99/6veexn/v+Seq3veyI4/nm3jikGncEAOjL0b9fed/VP73Se1jvPXWqEYy4VI2wOtqu3mX5AruYZHpvu2v97ox9np5evRVF3nPZw+849rJ/345/3fgIfRw/fMmlnz0K6cPn+jirLnrmTjWe9U6Dj5xr8tS+dGxePo+JZe/hNPf3XTrrfKk4lVqXfOmZC6z0Rf+RO7CXvZdu3KDIxQ2JXN5YLW5QqIrewBhUkq5BIDgEkcH0Ppricz/qIsT+ohJwPvXmb/zo+htvXrmjXHr5norlzj3nJdNJFI+dE/uj7nfWPf9zfI5UHyeOfacP7CJvv3Qrcadp05H13p9E1umIxT9/9bU/+f3XPnlefzxzd7WAx1X0h3/z39+5/rnv/fl9EtYDXSV424Wmx4ZozrqI9dApZx7/c/fXyQPH4bVjM+ugXT82whOrRNl75y60rt3kR+2SdRc3rnUZ9rg8us9pHZdvv+1uTNJVj5/+p9/6zm++/N2OYqr30P5osh1rd/gkVx3L619/6zMvPP7m9z59irWd0pXXzzYfP2KVjw3flZ5HSqSkyi2HMYqSPcU9xz4ScyIBPyfhNKbKLnp2Z99PHE9+lBgVJHrzxyi6AdVKM4yrCcqam6Yyt8PALTNia1vWOPGshQVhdbVmNM4Txl0GQswJ7EBcaduVW7CwQCvZWhBbSEU9PFWwbCai9KLEUhryBTDz0RGlqNwSVsCFEgKlKqSGsijXpSABxZDdVxMPsFeyGet7PYf3+729G1d1qS53icGTEC0qEdokUF0MpCzUhLneFKqzns3TLe0Tdh/Ax61Eu5AioqptENsgX4jsRlB2GSuRCoeoKuijtag2uUW5ChkIrQDjqcXiCbTDDTpb4oJGkcpmNcWGCoataH+9wUXBxwQhXg6VgWxjYIVuiKnlZb6DFv7ax5hM2O26sswA6/UcZeuFudCxSVjLqjtOMXmN6YkxmzDc0GSEnaOS2ThYp2PAX4vQguGFyIVreJuGBxvaQvBqfli3ZZ/1GtWmcVpaBzGaEnhEJ0JCW0xoqE213ediwLa+ztkGJWGWSdoITHrSoXt9QVIwkdI5EuJ4UC4bcLPhVAfZLZGK3aGbANytylb2slHLUD4uq4xm0MqWXgdTY1+nQjGdefhO0/C6DPOUZz1/QArUVJe9xCsDkimDlA6K/WwGdWTmI4tDF5nVZOgeQDxM3KCUyioDqi5pGZFshGIkdFAwA6Hdoum4FmhRzLztbIiNwREqzjgXnuZaTdmpUgsqKniFOSoPCzahFYr3IXhKcHk9ZmaalSCUtU3kiBLTvcDPEDTlMxSKWRJRA5lFrTJqhAmzmfY3BCiQXNJvTEcfa/s4GuvqIc1ruOFt9sCs4lQrzEUQYjwX2v0KCSC2DeuxR2WJjodOfxvPwn6nH6njzlsvuXJHCI2JQQDk6msYRrQJZs9ULsP9QFQPmTzbBQZlhJms0+2+EfJ1tinARKQLaskVeyFQ62FpKTvMNZUonOOtoZAEmyapMt2WeIiSiorLiwk2SmlbRQa1LLVT5LAM6GTJIMksH4UYqmWHtTUQ9GK3rMF6Cnp91du0XkquyWi+XnPzxdDbLhZBThkxEFQGlIKiV2M4TNQoETNevZ8XAOPNXJbThKYuGkrxOY5ZVy1ZbpI9RGxSF3SttZVqsDb1BIx0KcYdrbU61NbZHMe3pDhm80V6AAYH3yhnELpg/dlClYfLBTU3yzoBMXQkHrhB6tGCQJi1rmwbSuNMri/1WRmAooW3GqR9dNPRtShz3sBBeL8dd0Y2Jbc7ezzQE7muqzj3Ch/NxiQmGNlwQbesH3CmTh8kT1gFpqlA4CZJcmwTxo6zyApfaJcJgcOBPiMEdC4TkqCYGLlx8qE7dGlxbRJ7OHLjOTBdE5a1y+ajmKhETImBaiqVMxVcW1t0lybURMX9UTAst3ZnPwsck1Cupi1Q9w6NBiwbBNipSRvsZwLmtKEq10WrmXSgBClim5obrYm1Yo0dcbRci6G/nrfATHBXcIjAa2MoWAcDorJySMw2+1k7rQYbYOoCkz1GogkyMnMsdMcGu4kQwRMXarHEamlH+LsU3C0JdyetyuFhChrT/u5Aip44gAAU2KdLkhdQPBAa/qCA5Kjv8vK2dR2sLIMFtpRJmbEKX41MSc54KRhG03THx9IoGpmCPt+BwiYSSAvEKsWcipJRUrSkUEOZjuz92PWr+MD0m0FBMaYn1AeNTpBozy8w6gBPjL0QZ6XZP6wXiRCRHKvAbLyCJuZWH69nFUioW4+jxaG7N1vF2WOs5aEBr7BdPIhQpW2WEhpxTMS6cFwcCH89KNFtg42K4UTcpftkhQ0OROZJ1ALGKFZqbCjZrSs+28ERIE0WK2WA6pLEKDzFtS4GR/mAIRI4QpppMlmt7UMw5lhtbVhxbR76JFkmjmQrq+HugE+XFINvkZSKlqKJT1VqLkVF68+KONLKuagQir7FXE0cp17EkAEOj6TV1vOhqo0CRLZgwGMPYJPZYpjphKEzspT5xZbf5wUfQqUD6RQ4E4OVCEW6QURSn0wWNoMHy3YRIlOJhTajfMnaGzklqI7VDQz3907OeoLhOIaNkBoFMXChASDsg10gI2TB1vW6T9ITebeA64x3Q2QAEWbRGnW+RAgWtvr9OEvrke3u16ooN1E4IhB64BQi67deGwKEKW5Rl1yqAwrbE/wEnbYQsELiIa+pbqG6HpQKfBJJVIkiA6LdwcZ+VSzmaUNiWbRtduwU3wMOQk3GFmAuhhTPSqEapgHMSXBaQqMFzhBiNFkWVUDZiG/IKoUN5znEjdl4b4/hcrEf8d5cGxe4prU7AILbMdXszDImVGBzYGaxn3j2oqENRxMljIfKAV7lSr/jkZuOTGQQD+nWVU31gJALoVxq40ZnhyOtaBwuNodrnlKAQbSHQx7clHN6VQT8FsCmfcU+QDTstJGgz2b8oCCnVneiNcb23pKkEEeGkVzFGaXEq6wFCgCaGdMpAPOMaRs6WIZpas8IRDIPBqXnI9Dtr1bOwiu7YMobcEQPPHBSjtJ4y8c1NuEhy81iFaxjZDsfeXpouKS5nwwYoZAaw1y6aMtl49qpIVvD14QcmNgicTDZmywco7MUc1aJoGOXychh59osmqRxzVhWURbqZBhAHKzJhb5ZsUNSplAF3hxgvhJXAQcruEMnA55cGo0UWTAzxDObiw+gxmi7w2JOG3gkiriyJJewgyv1PrXbyjMMkT3stpSsN1TTai2AVJv5IR165dRa7i2XxQ2b0HIYyRC98VdrMsF2ok1xcoZp5cjfZ3kMbgdLAUS42YZcB9ISaGWDyA+J7QXVMplUe06MRgQFiP4IWRpoMjNoJhtm62wawEklRF7eTHwwQOaQzi10sqUxOe+8YYp7Q0qoYwGkh1Biz4fCaqsZosbGJp8SHgdNB4U9Cig/o6UYbSQKTzQzUKVp22jrLtVHVWjNa3wAtwW9qmYbBcAIvtnb00rc9adj0p6BSqTSsZdS8lKNnQ1hqTK+MYpZ51XSerpaismaklC9LcBtlKTCWNlKQhwiynJ0cK2GVc3IHHDJAODSFUBnNLtdZfawGVJaLo3GDs92r1wIUYw9ecKbAq/sRqk2ivsbADV24y51FmmIo2FTK/vUrfXcb0eEIi9ATmnnXkBCmEYv1Ty1s4XQbio9IMghmBPFntR2BKEpA2Cc7eHYMs0aDxt3mVrZlJdlm5sYgwm/VdHxSjP5eVtbcmIT+TJDClpJUa+tpyW1rZcQBYJEaA2rzZbbJTPRX2dEsO0HNrbjVqvdgeOKhKeV1t2AhpYfZqGnF6v9utmFKDLX2mRtjeb7lbgEuYOTOFlXWZvzuZwfqoMw3E63MjPnG2IngtZeMzcSDUDEjBXtdtwHRwRvg3OGQ2omdC1aTFmMEempZLFIBrulVkuclSqTfIZxbkqkgDfO/bSZbhSmMOBJnQxdRAKMdMdC0zILFQUIylE/8IL50p3IbTRJ+Em6JQOQjHM8FBcbNl3M8hKdMqqvSuZ66uiHSijGgrHAkzxKWZtwyrkpguk2McudEpC7xXKam0jjstHYrocpA0vNcI4MJb0RIicOpn1aZ0e17gGjdteKNFzJgBTp9RiapBbeThV6gA1SZVm5jhQR02Y6gWN4NFnOBVvj9HDIwQhpbkvYmmz0BAO5JbiFhxg5y/suMR4aiD0cAysxm6Tj+dhetobYn/LleDySeJdvoDirvI2NdZkBNUc8w8F+4UG0S+7nfs6m07Dv0X4TBS116Ce7lBedPkN5E0dmjK4s6cepXksqvlibNMACDFEKuCxjQYN4ycSHR4A5LCoaHjXavGmGrkMN1Zm2GTEyLodyRVclwnAzKKZwHcc2QLqMS2vmF8Ogns4EJeykPfQNkMUXNjoA9gikB9rC8K0M2otRoFrVfIQwg3E7WCSSnEPuSKtJauJMGDgDgNVc3vllIEL8Yceg0YGvS5ZEXb/PE1FXU1YMQKhrA0+ww143rV29wvKI2mxhFZAgRp5A2MAVqzTu0xoA60wWkwjcdmWkMw7riJ2v46nG9zUdF0qEtw+DsaGOFc1fNRilM52jW6ywn4zoGV5u+jjk9SUaNBGxBBJOj8fayPT3wLwIu3RbQb5fj9dbZs/vADjpygOPWA77E1TqCBjPSaN7Yz5dx/zCxVv506f7gVtflu66GWjud5d3+l29+L7wzEX/xOW7vOcuLuOKvPfCj/sUdLow+eKvvvkFe/WlwZWLmzu67D1aJukrobN3wrtQlb3H77jJP958vuueD4Pnn6+sr3zhyUfe+QXlb0/35bc+MV1leo+4VRjeeUt2x/hqmjuufyLi6vmdWXrqtmXv4YtPCWXvip+ciPLO14Ky9+Bx7TgO05v3lc/evhu5RW/zv4yAi23NHAAA";
}
