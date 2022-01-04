package hu.nye.progtech.torpedo.service.command.impl;

import java.util.Scanner;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to save actual score.
 */
public class SaveCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";
    private Scanner in = new Scanner(System.in);
    private int score;

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;
    private ShotPerformer sp = new ShotPerformer();

    public SaveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Save command was called");
        System.out.print("Add your username: ");
        String playerName = in.nextLine();
        score = ShotPerformer.score;
        gameSavesRepository.save(playerName, score);
        LOGGER.info("Save was successfully persisted");
    }
}
