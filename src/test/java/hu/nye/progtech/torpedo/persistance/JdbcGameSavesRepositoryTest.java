package hu.nye.progtech.torpedo.persistance;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.model.RawMap;
import hu.nye.progtech.torpedo.persistance.impl.JdbcGameSavesRepository;
import hu.nye.progtech.torpedo.service.exception.MapParsingException;
import hu.nye.progtech.torpedo.service.map.parser.MapParser;
import hu.nye.progtech.torpedo.service.util.MapToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

public class JdbcGameSavesRepositoryTest {
    private JdbcGameSavesRepository underTest;

    private Connection connection;
    private MapToString mapToString;
    private MapParser mapParser;
    private String playerName = "Name";
    private int score = 10;

    @BeforeEach
    public void init() {
        connection = Mockito.mock(Connection.class);
        mapToString = Mockito.mock(MapToString.class);
        mapParser = Mockito.mock(MapParser.class);

        underTest = new JdbcGameSavesRepository(connection, mapToString, mapParser);
    }

    @Test
    public void testCloseShouldDelegateCloseCallToConnection() throws Exception {
        //given

        //when
        underTest.close();

        //then
        Mockito.verify(connection).close();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser);
    }

    @Test
    public void testSaveToJdbc(){
        //given

        //when

        //then
    }

    @Test
    public void testSaveShouldStoreTheNewPlayer() throws SQLException {
        //given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT))
                .thenReturn(preparedStatement);

        //when
        underTest.save(playerName, score);

        //then
        Mockito.verify(connection).prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT);
        Mockito.verify(preparedStatement).setString(1, playerName);
        Mockito.verify(preparedStatement).setInt(2, score);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(preparedStatement).close();
        Mockito.verifyNoMoreInteractions(connection, mapToString, mapParser,
                statement, preparedStatement);
    }

}
