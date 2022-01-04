package hu.nye.progtech.torpedo.service.command.impl;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PrintCommandTest {
    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "wasd";

    private static final char[][] MAP = {{'0', '~'}, {'~', '~'}};
    private static final boolean[][] SHOOTABLE = {{true, true, true, true}, {true, true, true, true}};

    private static final MapVO PLAYER_MAP = new MapVO(2, 2, MAP, SHOOTABLE);
    private static final MapVO AI_MAP = new MapVO(2, 2, MAP, SHOOTABLE);

    private GameState gameState;

    private PrintCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(PLAYER_MAP, AI_MAP, false);
        underTest = new PrintCommand(gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenCommandIsPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PRINT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenCommandIsNotPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PRINT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPrintTheCurrentMapFromGameState() {
        // given in setup

        // when
        underTest.process(PRINT_COMMAND);

        // then
    }
}
