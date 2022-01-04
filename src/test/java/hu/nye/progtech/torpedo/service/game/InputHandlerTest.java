package hu.nye.progtech.torpedo.service.game;

import hu.nye.progtech.torpedo.service.command.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InputHandlerTest {
    private static final String INPUT = "something";
    private static String EXPECTED_VALUE = "Test";
    private static String DATA = "Test";

    private InputHandler underTest;

    @Mock
    private Command command1;
    @Mock
    private Command command2;

    @BeforeEach
    public void setUp() {
        underTest = new InputHandler(List.of(command1, command2));
    }

    @Test
    public void testHandleInputShouldRunOnlyTheFirstApplicableCommand() {
        // given
        given(command1.canProcess(INPUT)).willReturn(true);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verify(command1).process(INPUT);
        verifyNoInteractions(command2);
    }

    @Test
    public void testHandleInputShouldRunNoCommandsWhenNoneOfThemIsApplicable() {
        // given
        given(command1.canProcess(INPUT)).willReturn(false);
        given(command1.canProcess(INPUT)).willReturn(false);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verifyNoMoreInteractions(command1);
        verify(command2).canProcess(INPUT);
        verifyNoMoreInteractions(command2);
    }
    @Test
    public void testingUserInput() {
        //given in setUp

        //when
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(DATA.getBytes()));
        String input = underTest.inputReader();
        System.setIn(stdin);
        //then
        Assertions.assertEquals(EXPECTED_VALUE, input);
    }
}
