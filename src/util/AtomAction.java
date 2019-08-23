package util;

import java.util.HashMap;

public class AtomAction {

    private final HashMap<String, Boolean> atom;
    private final String action;
    public AtomAction(HashMap<String, Boolean> atom, String action) {
        this.atom = new HashMap<>(atom);
        this.action = action;
    }

    public HashMap<String, Boolean> getAtom() {
        return atom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtomAction)) return false;
        AtomAction ap = (AtomAction) o;
        for (String a : atom.keySet()) {
            if (atom.get(a) != ap.atom.get(a)) return false;
        }
        return action.equals(ap.action);
    }

    @Override
    public int hashCode() {
        return atom.hashCode()*31 + action.hashCode();
    }
}
