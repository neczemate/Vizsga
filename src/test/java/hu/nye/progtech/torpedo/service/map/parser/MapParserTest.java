package hu.nye.progtech.torpedo.service.map.parser;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.MapParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTest {

    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;

    private static final List<String> RAW_MAP = List.of(
            "01",
            "10"
    );

    private static final char[][] MAP = {
            {'0','1'},
            {'1','0'}
    };

    private static final boolean[][] FIXED = {
            {true,true},
            {true,true}
    };

    private static final MapVO EXPECTED_MAP = new MapVO(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS,MAP,FIXED);

    private MapParser underTest;

    @BeforeEach
    public void setUp(){
        underTest = new MapParser(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseMapShouldReturnValidMapVORepresentation() throws MapParseException {
        //given in setup

        // when
        MapVO result = underTest.parseMap(RAW_MAP);
        // then
        assertEquals(EXPECTED_MAP,result);
    }
}
