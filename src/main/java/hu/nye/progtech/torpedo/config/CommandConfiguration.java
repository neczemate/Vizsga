package hu.nye.progtech.torpedo.config;

import java.util.List;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.command.Command;
import hu.nye.progtech.torpedo.service.command.impl.DefaultCommand;
import hu.nye.progtech.torpedo.service.command.impl.ExitCommand;
import hu.nye.progtech.torpedo.service.command.impl.PrintCommand;
import hu.nye.progtech.torpedo.service.command.impl.SaveCommand;
import hu.nye.progtech.torpedo.service.command.impl.ShotCommand;
import hu.nye.progtech.torpedo.service.command.impl.ShotPerformer;
import hu.nye.progtech.torpedo.service.game.InputHandler;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for command specific Spring Beans.
 */
@Configuration
public class CommandConfiguration {
    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    PrintCommand printCommand(GameState gameState) {
        return new PrintCommand(gameState);
    }

    @Bean
    ShotCommand shotCommand(GameState gameState, ShotPerformer attackPerformer,
                            PrintWrapper printWrapper) {
        return new ShotCommand(gameState, attackPerformer, printWrapper);
    }

    @Bean
    SaveCommand saveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new SaveCommand(gameSavesRepository, gameState);
    }

    @Bean
    ExitCommand exitCommand(GameState gameState) {
        return new ExitCommand(gameState);
    }

    @Bean
    DefaultCommand defaultCommand(PrintWrapper printWrapper) {
        return new DefaultCommand(printWrapper);
    }
}
