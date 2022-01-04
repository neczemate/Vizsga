package hu.nye.progtech.torpedo.service.command.impl;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.ShotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to do a shot at position.
 */
public class ShotPerformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShotPerformer.class);

    public static int score;

    /**
     * Shooting to the given position.
     * Shoot if the position is shootable.
     *
     * @param enemyMap    the map to update.
     * @param rowIndex    the index of the row.
     * @param columnIndex the index of the column.
     * @return with updated map.
     * @throws ShotException when position is not shootable.
     */

    public MapVO perform(MapVO enemyMap, int rowIndex, int columnIndex) throws ShotException {
        LOGGER.info("Performing shoot operation with map = {}, rowIndex = {}, columnIndex = {}",
                enemyMap, rowIndex, columnIndex);

        char[][] map = enemyMap.getMap();
        boolean[][] fixed = enemyMap.getFixed();

        if (!fixed[rowIndex][columnIndex]) {
            LOGGER.warn("Can't shoot to this position, as position at rowIndex = {} and columnIndex = {} is shooted",
                    rowIndex, columnIndex);
            throw new ShotException("Can't shoot to this position");
        }

        if (map[rowIndex][columnIndex] == '0') {
            map[rowIndex][columnIndex] = 'X';
            fixed[rowIndex][columnIndex] = false;
        } else if (map[rowIndex][columnIndex] == '1') {
            map[rowIndex][columnIndex] = '+';
            fixed[rowIndex][columnIndex] = false;
            score += 1;
        }

        return new MapVO(enemyMap.getNumberOfRows(), enemyMap.getNumberOfColumns(), map, fixed);
    }
}
