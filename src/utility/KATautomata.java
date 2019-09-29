package utility;


public class KATautomata {

    // deprecated
    /*
    private HashMap<State, HashMap<AtomAction, State>> transition;
    private List<State> states;
    private State initial;
    private List<String> principals;
    private List<String> actions;

    public KATautomata(List<String> principals, List<String> actions, KATexpression expr) {
        this.principals = principals;
        this.actions = actions;
        states = new ArrayList<>();
        initial = new State(expr);
        states.add(initial);
        transition = new HashMap<>();
        build(initial);
    }

    private List<HashMap<String, Boolean>> allatoms() {
        int l = principals.size();
        int[] select = new int[l+1];
        List<HashMap<String, Boolean>> res = new ArrayList<>();

        while(select[0] == 0) {
            HashMap<String, Boolean> a = new HashMap<>();
            for(int i=1; i<=l; i++) {
                if (select[i] == 0) a.put(principals.get(i-1), false);
                else a.put(principals.get(i-1), true);
            }
            res.add(a);
            int j = l;
            while(j > 0 && select[j] == 1) j--;
            select[j] = 1;
            for(int i = j+1; i<=l; i++) select[i] = 0;
        }
        return res;
    }

    public void build(State s) {
        if (s.expr instanceof TestExpression) return;
        HashMap<AtomAction, State> tr = new HashMap<>();
        for(HashMap<String, Boolean> atom : allatoms()) {
            for(String p : actions) {
                AtomAction ap = new AtomAction(atom, p);
                Derivative D = new Derivative(atom, p);
                Simplify S = new Simplify();
                KATexpression der = (KATexpression) s.expr.accept(D);
                KATexpression short_der = (KATexpression) der.accept(S);
                for(State exist : states) {
                    if (exist.expr.equals(short_der)) {  // if the dest state already exists
                        tr.put(ap, exist);
                    }
                }
                if (!tr.containsKey(ap)) {       // if the dest state is new
                    State ss = new State(short_der);
                    states.add(ss);
                    tr.put(ap, ss);
                    build(ss);
                }
            }
        }
        transition.put(s, tr);
    }

    public boolean check(List<AtomAction> aps, HashMap<String, Boolean> atom) {
        State current = initial;
        for(AtomAction ap: aps) {
            if (current.expr instanceof TestExpression) return false;
            current = transition.get(current).get(ap);
        }
        if (!(current.expr instanceof TestExpression)) return false;
        Evaluation eval = new Evaluation(atom);
        return (Boolean) current.expr.accept(eval);
    }

    public List<State> getStates() { return states; };
    */
}
