package hu.nye.progtech.torpedo.service.command.impl;

import java.util.Arrays;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to print maps.
 */
public class PrintCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;

    public PrintCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing print command");
        System.out.println("Your map:");
        System.out.println(Arrays.deepToString(gameState.getActualPlayerMap().getMap()));
        System.out.println("\nEnemy map:");
        System.out.println(Arrays.deepToString(gameState.getActulEnemyMap().getMap()));
    }
}
