package hu.nye.progtech.torpedo.service.command.impl;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.ShotException;
import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ShotCommandTest {
    private static final String SHOT_COMMAND = "shoot 5 5";
    private static final String SHOT_ERROR_MESSAGE = "Can't shoot to this position";

    private static final char[][] MAP = {{'1','0','1','0','1','0','1','0','1','0'},
            {'1','0','1','0','1','0','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},
            {'0','1','0','1','0','1','0','1','0','1'},};
    private static final boolean[][] FIXED = {{true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},};

    private static final MapVO PLAYER_MAP = new MapVO(10, 10, MAP, FIXED);
    private static final MapVO ENEMY_MAP = new MapVO(10, 10, MAP, FIXED);
    private static final MapVO NEW_PLAYER_MAP = new MapVO(10, 10, MAP, FIXED);
    private static final MapVO NEW_ENEMY_MAP = new MapVO(10, 10, MAP, FIXED);

    private static final int ROW_INDEX = 5;
    private static final int COLUMN_INDEX = 5;

    private ShotCommand underTest;
    private GameState gameState;

    @Mock
    private PrintWrapper printWrapper;

    @Mock
    private ShotPerformer shotPerformer;

    @BeforeEach
    public void setUp() {
        shotPerformer = Mockito.mock(ShotPerformer.class);
        gameState = new GameState(PLAYER_MAP, ENEMY_MAP, false);
        underTest = new ShotCommand(gameState, shotPerformer, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsRight() {
        //given in setup

        //when
        boolean result = underTest.canProcess(SHOT_COMMAND);
        //then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsWrong() {
        //given
        String input = "default";
        //when
        boolean result = underTest.canProcess(input);
        //then
        assertFalse(result);
    }

    /*@Test
    public void testProcessShouldPerformAttack() throws ShotException {
        // given
        given(shotPerformer.perform(ENEMY_MAP, ROW_INDEX, COLUMN_INDEX)).willReturn(NEW_ENEMY_MAP);

        // when
        underTest.process(SHOT_COMMAND);

        // then
        verify(shotPerformer).perform(ENEMY_MAP, ROW_INDEX, COLUMN_INDEX);
        assertEquals(NEW_ENEMY_MAP, gameState.getActulEnemyMap());
    }*/
}
