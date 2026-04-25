package tm;

import java.io.FileNotFoundException;

/**
 * Main entry point for the Turing Machine Simulator.
 * @author aEagleton
 */
public class TMSimulator {

    /**
     * Executes the TM simulation.
     * @param args Command line arguments containing the input file path.
     */
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.err.println("Usage: java tm.TMSimulator <input_file>");
            System.exit(1);
        }
        
        try {
            TM tm = new TM(args[0]);
            tm.run();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + args[0]);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
            System.exit(1);
        }
    }
}