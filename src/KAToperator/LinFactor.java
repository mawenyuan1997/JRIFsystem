package KAToperator;

import KAT.*;
import utility.AtomAction;
import utility.Lin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinFactor implements KATexpressionVisitor {
    private List<String> primtest;

    public LinFactor(List<String> l) {primtest = l;}

    private List<HashMap<String, Boolean>> allatoms() {
        int l = primtest.size();
        int[] select = new int[l+1];
        List<HashMap<String, Boolean>> res = new ArrayList<>();

        while(select[0] == 0) {
            HashMap<String, Boolean> a = new HashMap<>();
            for(int i=1; i<=l; i++) {
                if (select[i] == 0) a.put(primtest.get(i-1), false);
                else a.put(primtest.get(i-1), true);
            }
            res.add(a);
            int j = l;
            while(j > 0 && select[j] == 1) j--;
            select[j] = 1;
            for(int i = j+1; i<=l; i++) select[i] = 0;
        }
        return res;
    }

    @Override
    public Object visit(PrimitiveAction expr) {
        Lin lf = new Lin();
        for(HashMap<String, Boolean> a : allatoms()) {
            AtomAction ap = new AtomAction(a, expr.id);
            lf.add(ap, new OneTest());
        }
        return lf;
    }

    @Override
    public Object visit(PlusExpression expr) {
        Lin linleft = (Lin) expr.left.accept(this);
        Lin linright = (Lin) expr.right.accept(this);
        return linleft.union(linright);
    }

    @Override
    public Object visit(ConcatExpression expr) {
        Lin l1 = (Lin) expr.left.accept(this);
        l1.odot(expr.right);
        Lin l2 = (Lin) expr.right.accept(this);
        Lin l3 = new Lin();
        for (Map.Entry<AtomAction, KATexpression> entry : l2.lf.entrySet()) {
            AtomAction ap = entry.getKey();
            KATexpression p = entry.getValue();
            Evaluation E = new Evaluation(ap.getAtom());
            if ((Boolean) expr.left.accept(E)) {
                l3.add(ap, p);
            }
        }
        l1.union(l3);
        return l1;
    }

    @Override
    public Object visit(StarExpression expr) {
        Lin l = (Lin) expr.p.accept(this);
        l.odot(expr.p);
        return l;
    }

    @Override
    public Object visit(ConcatTest expr) {
        return new Lin();
    }

    @Override
    public Object visit(NegateTest expr) {
        return new Lin();
    }

    @Override
    public Object visit(OneTest expr) {
        return new Lin();
    }

    @Override
    public Object visit(PlusTest expr) {
        return new Lin();
    }

    @Override
    public Object visit(PrimitiveTest expr) {
        return new Lin();
    }

    @Override
    public Object visit(ZeroTest expr) {
        return new Lin();
    }
}
