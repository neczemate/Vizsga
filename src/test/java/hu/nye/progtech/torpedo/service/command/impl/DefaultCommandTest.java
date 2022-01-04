package hu.nye.progtech.torpedo.service.command.impl;

import hu.nye.progtech.torpedo.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DefaultCommandTest {
    private static final String USER_INPUT = "default command";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";

    private DefaultCommand underTest;

    @Mock
    private PrintWrapper printWrapper;

    @BeforeEach
    public void setUp() {
        underTest = new DefaultCommand(printWrapper);
    }

    @Test
    public void testProcessWhenUserInputCommandIsUnknown() {
        //given in setup

        //when
        underTest.process(USER_INPUT);
        //then
        verify(printWrapper).printLine(UNKNOWN_COMMAND_MESSAGE);
    }

    @Test
    public void testCanProcessWhenReturnTrue() {
        //given in setup

        //when
        boolean result = underTest.canProcess(USER_INPUT);
        //then
        assertTrue(result);
    }
}
