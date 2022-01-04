package hu.nye.progtech.torpedo.service.map.parser;

import java.util.List;
import java.util.regex.Pattern;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.MapParseException;

/**
 * Class used to parse map.
 */
public class MapParser {

    private static final String VALID_ROW_REGEX = "[0-1]+";

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }


    /**
     * Parsing map.
     *
     * @param rawMap parameter
     * @return with MapVO
     * @throws MapParseException when something went wrong
     */
    public MapVO parseMap(List<String> rawMap) throws MapParseException {
        checkNumberOfRows(rawMap);
        checkNumberOfColumns(rawMap);
        checkForInvalidValues(rawMap);

        char[][] map = getMap(rawMap);
        boolean[][] fixed = getFixed(map);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParseException {
        if (rows.size() != numberOfRows) {
            throw new MapParseException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new MapParseException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkForInvalidValues(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParseException("Row contains invalid characters");
            }
        }
    }

    private char[][] getMap(List<String> rawMap) {
        char[][] result = new char[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new char[numberOfColumns];

            String line = rawMap.get(i);
            String[] parts = line.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                char parsed = parts[j].charAt(0);
                result[i][j] = (char) parsed;
            }
        }

        return result;
    }

    private boolean[][] getFixed(char[][] map) {
        boolean[][] result = new boolean[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new boolean[numberOfColumns];

            for (int j = 0; j < numberOfColumns; j++) {
                result[i][j] = map[i][j] != 0;
            }
        }

        return result;
    }
}
