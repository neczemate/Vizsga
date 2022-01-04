package hu.nye.progtech.torpedo.service.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hu.nye.progtech.torpedo.Main;
import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.MapParseException;
import hu.nye.progtech.torpedo.service.exception.MapReadException;
import hu.nye.progtech.torpedo.service.map.parser.MapParser;
import hu.nye.progtech.torpedo.service.map.reader.MapReader;
import hu.nye.progtech.torpedo.service.map.reader.impl.BufferedReaderMapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Map creator class.
 */
public class CreateMaps {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMaps.class);

    /**
     * Creating player map.
     *
     * @return with MapVO
     */
    public MapVO playerMapCreator() {

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/player.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        try {
            List<String> strings = mapReader.readMap();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("MapReader readMap output: " + strings);
            }

            MapParser mapParser = new MapParser(10, 10);
            MapVO mapVO = mapParser.parseMap(strings);
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("MapParser parseMap output: " + mapVO);
            }
            return mapVO;
        } catch (MapReadException e) {
            LOGGER.error("MapReadException error: ", e);
            return null;
        } catch (MapParseException e) {
            LOGGER.error("MapParseException error: ", e);
            return null;
        }
    }

    /**
     * Creating enemy map.
     *
     * @return with MapVO
     */
    public MapVO enemyMapCreator() {

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/enemy.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        try {
            List<String> strings = mapReader.readMap();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("MapReader readMap output: " + strings);
            }

            MapParser mapParser = new MapParser(10, 10);
            MapVO mapVO = mapParser.parseMap(strings);
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("MapParser parseMap output: " + mapVO);
            }
            return mapVO;
        } catch (MapReadException e) {
            LOGGER.error("MapReadException error: ", e);
            return null;
        } catch (MapParseException e) {
            LOGGER.error("MapParseException error: ", e);
            return null;
        }
    }
}
