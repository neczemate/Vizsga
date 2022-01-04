package hu.nye.progtech.torpedo.service.game;

import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.torpedo.service.command.Command;

/**
 * Class used to handle inputs.
 */
public class InputHandler {
    private final List<Command> commandList;

    /**
     * Method used to read input.
     *
     * @return with string.
     */
    public String inputReader() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        return choice;
    }

    public InputHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles an input through a list of {@link Command}s.
     * <p>
     * Only the first applicable command will be run.
     *
     * @param input the input as a string to be handled
     */
    public void handleInput(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }
}
