package utility;

import KAT.ConcatExpression;
import KAT.KATexpression;
import KAT.OneTest;
import KAT.ZeroTest;

import java.util.HashMap;
import java.util.Map;

public class Lin {

    // deprecated

    public HashMap<AtomAction, KATexpression> lf;

    public Lin() { lf = new HashMap<>(); }
    public Lin(HashMap<AtomAction, KATexpression> l) { lf = l; }

    public void add(AtomAction ap, KATexpression t) {
        lf.put(ap, t);
    }

    public void odot(KATexpression t) {
        if (t instanceof ZeroTest) {
            lf.clear();
        } else if (t instanceof OneTest) {
            // do nothing
        } else if (lf.isEmpty()) {
            // remain empty
        } else {
            for (Map.Entry<AtomAction, KATexpression> entry : lf.entrySet()) {
                AtomAction ap = entry.getKey();
                KATexpression p = entry.getValue();
                if (p instanceof ZeroTest) {
                    // do nothing
                } else if (p instanceof OneTest) {
                    lf.put(ap, t);
                } else {
                    lf.put(ap, new ConcatExpression(p, t));
                }
            }
        }
    }

    public Lin union(Lin other) {
        HashMap<AtomAction, KATexpression> u = new HashMap<>();
        u.putAll(lf);
        u.putAll(other.lf);
        return new Lin(u);
    }

}
