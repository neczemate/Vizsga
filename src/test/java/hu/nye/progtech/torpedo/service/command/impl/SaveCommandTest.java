package hu.nye.progtech.torpedo.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.io.*;

import hu.nye.progtech.torpedo.model.GameState;
import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SaveCommandTest {
    private static final String SAVE_COMMAND = "save";

    private SaveCommand underTest;
    private GameSavesRepository gameSavesRepository;
    private GameState gameState;
    private BufferedReader bufferedReader;

    @BeforeEach
    public void setUp() {
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new SaveCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsRight() {
        //given in setup

        //when
        boolean result = underTest.canProcess(SAVE_COMMAND);
        //then
        assertTrue(result);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsWrong() {
        //given
        String input = "default";
        //when
        boolean result = underTest.canProcess(input);
        //then
        assertFalse(result);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    /*@Test
    public void testProcessShouldSaveCurrentGameState() throws IOException {
        // Given
        String input = "save";
        String playerName = "name";
        int score = 10;

        // When
        underTest.process(input);
        // Then
        Mockito.verify(gameSavesRepository).save(playerName, score);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }*/

    /*@Test
    public void testProcessShouldNSaveCurrentGameStateWhenInputIsNull(){
        //given
        String playerName = "Name";
        int score = 10;
        //when
        underTest.process(null);
        //then
        Mockito.verify(gameSavesRepository).save(playerName, score);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerName, score);
    }*/
}
