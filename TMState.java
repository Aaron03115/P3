package tm;

/**
 * Represents a single state within the Turing Machine.
 * @author aEagleton
 */
public class TMState {
    private Transition[] transitions;

    /**
     * Constructs a state.
     * @param numSymbols The highest integer value in the alphabet.
     */
    public TMState(int numSymbols) {
        transitions = new Transition[numSymbols + 1];   
 }

    /**
     * Registers a new transition rule.
     * @param readSymbol    The symbol read from the tape.
     * @param nextState     The target state.
     * @param writeSymbol   The symbol to write.
     * @param moveDirection The direction to move ('L' or 'R').
     */
    public void addTransition(int readSymbol, int nextState, int writeSymbol, char moveDirection) {
        //todo
    }

    /**
     * Retrieves the transition rule for a read symbol.
     * @param readSymbol The symbol currently under the tape head.
     * @return The corresponding Transition.
     */
    public Transition getTransition(int readSymbol) {
        return transitions[readSymbol];
    }

    /**
     * Data container for a transition rule.
     */
    public static class Transition {
        // todo

        }
    }
}