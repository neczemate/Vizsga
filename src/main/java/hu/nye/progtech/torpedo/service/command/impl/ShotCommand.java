package hu.nye.progtech.torpedo.service.command.impl;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.service.exception.ShotException;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to shoot a position.
 */
public class ShotCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShotCommand.class);

    private static final String SHOT_COMMAND_REGEX = "^shoot [0-9] [0-9]$";
    private static final String SHOT_ERROR_MESSAGE = "Can't shoot to this position";

    private final GameState gameState;
    private final ShotPerformer shotPerformer;
    private final PrintWrapper printWrapper;

    private Random random = new Random();

    public ShotCommand(GameState gameState, ShotPerformer shotPerformer, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.shotPerformer = shotPerformer;
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(SHOT_COMMAND_REGEX, input);
    }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);
        int columnIndex = Integer.parseInt(parts[2]);

        LOGGER.info("Attack command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);
        try {
            MapVO newEnemyMap = shotPerformer.perform(gameState.getActulEnemyMap(), rowIndex, columnIndex);
            gameState.setCurrentEnemyMap(newEnemyMap);

            System.out.println("Enemy map:");
            System.out.println(Arrays.deepToString(newEnemyMap.getMap()));

            MapVO newPlayerMap = shotPerformer.perform(gameState.getActualPlayerMap(),
                    random.nextInt(10), random.nextInt(10));
            gameState.setCurrentPlayerMap(newPlayerMap);

            System.out.println("Your map:");
            System.out.println(Arrays.deepToString(newPlayerMap.getMap()));
        } catch (ArithmeticException | ShotException e) {
            LOGGER.error("Exception occurred while performing shoot operation", e);
            printWrapper.printLine(SHOT_ERROR_MESSAGE);
        }
    }
}
