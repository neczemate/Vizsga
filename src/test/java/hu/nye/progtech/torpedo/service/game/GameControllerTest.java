package hu.nye.progtech.torpedo.service.game;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
    private static final char[][] MAP = {{'0', '1'}, {'0', '0'}};
    private static final boolean[][] SHOOTABLE = {{true, true}, {true, true}};

    private static final MapVO PLAYER_MAP = new MapVO(2, 2, MAP, SHOOTABLE);
    private static final MapVO ENEMY_MAP = new MapVO(2, 2, MAP, SHOOTABLE);

    private GameController underTest;
    @Mock
    private GameState gameState;
    @Mock
    private GameStepPerformer gameStepPerformer;
    @Mock
    private MapUtil mapUtil;

    @BeforeEach
    public void setUp() {
        mapUtil = Mockito.mock(MapUtil.class);
        gameStepPerformer = Mockito.mock(GameStepPerformer.class);
        gameState = Mockito.mock(GameState.class);
    }

    @Test
    public void testStartShouldRun() {
        //given
        gameState = new GameState(PLAYER_MAP, ENEMY_MAP, true);
        underTest = new GameController(gameState, gameStepPerformer, mapUtil);
        //when
        underTest.start();
        //then
        verifyNoMoreInteractions(gameStepPerformer);
    }

    @Test
    public void testStartShouldRunUntilTheMapIsNotCompleted() {
        // given
        gameState = new GameState(PLAYER_MAP, ENEMY_MAP, false);
        underTest = new GameController(gameState, gameStepPerformer, mapUtil);
        given(mapUtil.isMapCompleted(PLAYER_MAP, ENEMY_MAP)).willReturn(false, true);

        // when
        underTest.start();

        // then
        verify(mapUtil, times(2)).isMapCompleted(PLAYER_MAP, ENEMY_MAP);
        verify(gameStepPerformer, times(1)).performGameStep();
    }
}
