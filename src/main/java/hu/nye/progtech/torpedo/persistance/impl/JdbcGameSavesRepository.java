package hu.nye.progtech.torpedo.persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.service.map.parser.MapParser;
import hu.nye.progtech.torpedo.service.util.MapToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC based implementation of {@link GameSavesRepository}.
 */
public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    public static final String INSERT_STATEMENT = "INSERT INTO gamescore " +
            "(id, playername, playerscore) VALUES (DEFAULT, ?, ?);";

    private Connection connection;
    private MapToString mapToString;
    private MapParser mapParser;

    public JdbcGameSavesRepository(Connection connection, MapToString mapToString, MapParser mapParser) {
        this.connection = connection;
        this.mapToString = mapToString;
        this.mapParser = mapParser;
    }

    @Override
    public void save(String playerName, int score) {
        try {
            insertNewSave(playerName, score);
        } catch (SQLException e) {
            LOGGER.error("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Insert actual game state to JDBC.
     *
     * @param playerName name to save.
     * @param score      score to save.
     * @throws SQLException when failed to save game state.
     */
    public void insertNewSave(String playerName, int score) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
        }
    }
}
