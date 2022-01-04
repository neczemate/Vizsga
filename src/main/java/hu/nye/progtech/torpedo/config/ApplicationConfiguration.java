package hu.nye.progtech.torpedo.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.service.command.impl.ShotPerformer;
import hu.nye.progtech.torpedo.service.exception.MapParseException;
import hu.nye.progtech.torpedo.service.game.GameController;
import hu.nye.progtech.torpedo.service.game.GameStepPerformer;
import hu.nye.progtech.torpedo.service.game.InputHandler;
import hu.nye.progtech.torpedo.service.input.UserInputReader;
import hu.nye.progtech.torpedo.service.map.CreateMaps;
import hu.nye.progtech.torpedo.service.map.parser.MapParser;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Game configuration class for the Torpedo game.
 */
@Configuration
public class ApplicationConfiguration {
    @Bean(initMethod = "start")
    GameController gameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtil mapUtil) {
        return new GameController(gameState, gameStepPerformer, mapUtil);
    }

    @Bean
    GameState gameState() throws MapParseException {
        CreateMaps createMaps = new CreateMaps();
        MapParser mapParser = new MapParser(10, 10);
        return new GameState(createMaps.playerMapCreator(), createMaps.enemyMapCreator(), false);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    ShotPerformer shotPerformer() {
        return new ShotPerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

    @Bean
    MapParser mapParser() {
        return new MapParser(10, 10);
    }
}
