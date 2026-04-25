package tm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The core Turing Machine engine.
 * @author aEagleton
 */
public class TM {
    private TMState[] states;
    private int numStates;
    private int numSymbols;
    private Map<Integer, Integer> tape;
    private int headPosition;
    private int currentState;
    private int minVisited;
    private int maxVisited;

    /**
     * Initializes the Turing Machine from a file.
     * @param filename Path to the configuration file.
     * @throws FileNotFoundException If the file cannot be read.
     */
    public TM(String filename) throws FileNotFoundException {
        tape = new HashMap<>();
        headPosition = 0;
        currentState = 0;
        minVisited = 0;
        maxVisited = 0;

        parseFile(filename);
    }

    private void parseFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        if (scanner.hasNextLine()) {
            numStates = Integer.parseInt(scanner.nextLine().trim());
        }

        if (scanner.hasNextLine()) {
            numSymbols = Integer.parseInt(scanner.nextLine().trim());
        }

        states = new TMState[numStates];
        for (int i = 0; i < numStates - 1; i++) {
            states[i] = new TMState(numSymbols);
        }

        for (int state = 0; state < numStates - 1; state++) {
            for (int symbol = 0; symbol <= numSymbols; symbol++) {
                if (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().trim().split(",");
                    int nextState = Integer.parseInt(parts[0]);
                    int writeSymbol = Integer.parseInt(parts[1]);
                    char move = parts[2].charAt(0);
                    states[state].addTransition(symbol, nextState, writeSymbol, move);
                }
            }
        }

        String inputStr = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        if (inputStr.equals("EOF")) {
            inputStr = "";
        }

        for (int i = 0; i < inputStr.length(); i++) {
            tape.put(i, Character.getNumericValue(inputStr.charAt(i)));
        }
        
        if (inputStr.length() > 0) {
            maxVisited = inputStr.length() - 1;
        }

        scanner.close();
    }

    /**
     * Executes the main Turing Machine loop.
     */
    public void run() {
        int haltingState = numStates - 1;

        while (currentState != haltingState) {
            int readSymbol = tape.getOrDefault(headPosition, 0);
            
            TMState.Transition trans = states[currentState].getTransition(readSymbol);

            tape.put(headPosition, trans.writeSymbol);
            
            currentState = trans.nextState;

            if (trans.moveDirection == 'L') {
                headPosition--;
            } else if (trans.moveDirection == 'R') {
                headPosition++;
            }

            if (headPosition < minVisited) {
                minVisited = headPosition;
            }
            if (headPosition > maxVisited) {
                maxVisited = headPosition;
            }
        }

        printVisitedTape();
    }

    private void printVisitedTape() {
        StringBuilder sb = new StringBuilder();
        for (int i = minVisited; i <= maxVisited; i++) {
            sb.append(tape.getOrDefault(i, 0));
        }
        System.out.println(sb.toString());
    }
}