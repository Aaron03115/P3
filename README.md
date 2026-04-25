****************
* Project 3: Turing Machine Simulator
* CS 361
* April 24, 2026
* aEagleton & [Partner's Name]
****************

OVERVIEW:
This program is a deterministic Turing Machine simulator written in Java. It reads an encoded text file containing state definitions, an alphabet, transition functions, and an initial input string, and then simulates the machine's execution on a bi-infinite tape until it reaches a finishing state.

INCLUDED FILES:
* tm/TMSimulator.java
* tm/TM.java
* tm/TMState.java
* README

COMPILING AND RUNNING:
From the directory containing the `tm` package folder (not inside the `tm` folder itself), compile all source files with the command:
$ javac tm/*.java

Run the compiled simulation by executing the main class and providing the input file as an argument:
$ java tm.TMSimulator file0.txt

PROGRAM DESIGN AND IMPORTANT CONCEPTS:
The biggest architectural hurdle was figuring out how to represent a bi-infinite tape in Java. Standard arrays or `ArrayLists` immediately break down if the tape head needs to move left past index 0 into negative numbers. 

To solve this, we used a `HashMap<Integer, Integer>`. The key is the physical index on the tape (which easily handles negative numbers), and the value is the symbol written in that cell. This gave us O(1) lookups and let the tape dynamically expand in both directions without having to constantly resize or shift arrays.

For state management, we built a `TMState` class. Since the machines are deterministic and have rules for every symbol (including the blank `0`), we stored the transitions in an array where the index matches the symbol being read. When the machine reads a `1`, it just grabs index 1 from the state's transition array to instantly find its next move.

TESTING:
We tested the program against the provided `file0.txt`, `file2.txt`, and `file5.txt` to make sure it met the 5-minute timeout constraints and handled scaling properly.

We also wrote a few custom edge-case files to really push the logic:
1. We tested a machine that forced the tape head to repeatedly move Left from the start to ensure our `HashMap` correctly tracked negative indices and didn't crash.
2. We tested string appending to ensure the machine could scan past existing symbols and overwrite blank `0`s without corrupting the initial input.
3. We threw bad command-line arguments at the main class to make sure it failed gracefully with a usage message rather than throwing raw Java exceptions.

DISCUSSION:
Writing the parsing logic for the TM encoding felt super familiar after previously building a recursive descent parser and lexer in Java. Translating raw text files into structured state transitions was basically the same concept, just applied to a Turing Machine instead of math expressions. 

The most annoying bug we ran into was tracking the boundaries for the final print statement. We initially had trouble figuring out exactly which chunk of the tape to print when the machine wandered into negative indices and back. Adding the `minVisited` and `maxVisited` tracking variables inside the main execution loop fixed this, ensuring we always captured the exact contiguous block the tape head touched.

EXTRA CREDIT:
N/A

SOURCES:
docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

gameprogrammingpatterns.com/state.html

rosettacode.org/wiki/Universal_Turing_machine

geeksforgeeks.org/theory-of-computation/turing-machine-construction-transducers-turing-machine-in-java