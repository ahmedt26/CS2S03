import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;

    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;

        try {
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                if (line.startsWith("PRINT ")) {
                    commands.add(makePrintCommand(line));
                } else if (line.startsWith("BEGIN_")) {
                    commands.add(makeBlockCommand(line));
                } else if (line.equals("FINISH")) {
                    break;
                } else if (!line.equals("")) {
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");
                }
            }
        } catch (BadCommandException e) {
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }

    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        boolean ageCheck = false;   // Flags to check if Age and Wealth has been input twice in the command.
        boolean wealthCheck = false;
        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                String [] tokens = line.split(" ", 3);
                   // Check for AGE, checks if two ages are input
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");

                // Checking for duplicate WEALTH and AGE inputs.
                if (!ageCheck & tokens[0].equals("CUSTOMER.AGE")) {   // Make the first CUSTOMER.AGE a different name so it can be processed.
                    // System.out.println("CUSTOMER IS AGE LOW");
                    tokens[0] = "CUSTOMER.AGELOW";
                    ageCheck = true;
                }
                if (ageCheck & tokens[0].equals("CUSTOMER.AGE")) {
                    // System.out.println("CUSTOMER IS AGE HIGH");
                    tokens[0] = "CUSTOMER.AGEHIGH";
                }
                if (!wealthCheck & tokens[0].equals("CUSTOMER.WEALTH")) {   // Same process for wealth.
                    // System.out.println("CUSTOMER IS WEALTH LOW");
                    tokens[0] = "CUSTOMER.WEALTHLOW";
                    wealthCheck = true;
                }
                if (wealthCheck & tokens[0].equals("CUSTOMER.WEALTH")) {
                    // System.out.println("CUSTOMER IS WEALTH HIGH");
                    tokens[0] = "CUSTOMER.WEALTHHIGH";
                }
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
                throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}