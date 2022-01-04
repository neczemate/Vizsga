package hu.nye.progtech.torpedo.service.map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.MapParseException;
import hu.nye.progtech.torpedo.service.exception.MapReadException;
import hu.nye.progtech.torpedo.service.map.parser.MapParser;
import hu.nye.progtech.torpedo.service.map.reader.MapReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CreateMapsTest {
    private List<String> playerMap = Arrays.asList("1000000000\n" +
            "1000000000\n" +
            "0000100000\n" +
            "0000000111\n" +
            "0000000000\n" +
            "0000000001\n" +
            "0011111001\n" +
            "0000000001\n" +
            "0000000001\n" +
            "0000000000");

    private List<String> enemyMap = Arrays.asList("0001111100\n" +
            "1000000000\n" +
            "0000100000\n" +
            "0000100111\n" +
            "0000000000\n" +
            "0000000100\n" +
            "0000000100\n" +
            "0000000100\n" +
            "0000000100\n" +
            "0000000000");

    private char MAP1[][] = {{'1','0','0','0','0','0','0','0','0','0'},
            {'1','0','0','0','0','0','0','0','0','0'},
            {'0','0','0','0','1','0','0','0','0','0'},
            {'0','0','0','0','0','0','0','1','1','1'},
            {'0','0','0','0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0','0','0','1'},
            {'0','0','1','1','1','1','1','0','0','1'},
            {'0','0','0','0','0','0','0','0','0','1'},
            {'0','0','0','0','0','0','0','0','0','1'},
            {'0','0','0','0','0','0','0','0','0','0'}};

    private char MAP2[][] = {{'0','0','0','1','1','1','1','1','0','0'},
            {'1','0','0','0','0','0','0','0','0','0'},
            {'0','0','0','0','1','0','0','0','0','0'},
            {'0','0','0','0','1','0','0','1','1','1'},
            {'0','0','0','0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0','1','0','0'},
            {'0','0','0','0','0','0','0','1','0','0'},
            {'0','0','0','0','0','0','0','1','0','0'},
            {'0','0','0','0','0','0','0','1','0','0'},
            {'0','0','0','0','0','0','0','0','0','0'}};

    private boolean FIXED1[][] = {{true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true}};
    private MapVO EXPECTED1 = new MapVO(10,10, MAP1, FIXED1);
    private MapVO EXPECTED2 = new MapVO(10,10, MAP2, FIXED1);
    private InputStream inputStream;
    @Mock
    private MapParser mapParser;
    private BufferedReader bufferedReader;
    @Mock
    private MapReader mapReader;
    private CreateMaps underTest;

    @BeforeEach
    public void setUp(){
        mapReader = Mockito.mock(MapReader.class);
        mapParser = Mockito.mock(MapParser.class);
        underTest = new CreateMaps();
    }

    @Test
    public void testShouldReturnWithPlayerMapVO(){
        //given

        //when
        MapVO mapVO = underTest.playerMapCreator();
        //then
        Assertions.assertEquals(mapVO, EXPECTED1);
    }

    @Test
    public void testShouldReturnWithEnemyMapVO(){
        //given

        //when
        MapVO mapVO = underTest.enemyMapCreator();
        //then
        Assertions.assertEquals(mapVO, EXPECTED2);
    }
}
