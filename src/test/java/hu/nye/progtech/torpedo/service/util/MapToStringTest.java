package hu.nye.progtech.torpedo.service.util;

import hu.nye.progtech.torpedo.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapToStringTest {
    private static final char[][] MAP = {{'0','0'},{'0','1'}};
    private static final boolean[][] FIXED = {{true,true},{true,true}};

    private static final String EXPECTED_MAP = "00\n01\n";
    private static final String EXPECTED_FIXED = "11\n11\n";

    private static final MapVO mapVO = new MapVO(2,2, MAP, FIXED);

    private MapToString underTest;

    @BeforeEach
    public void setUp(){
        underTest = new MapToString();
    }

    @Test
    public void testMapToStringShouldReturnWithAMapRepresentation(){
        //given

        //when
        String result = underTest.convertMapVoMapToString(mapVO);
        //then
        assertEquals(EXPECTED_MAP, result);
    }

    @Test
    public void testMapToStringShouldReturnWithAShootAbleRepresentation(){
        //given

        //when
        String result = underTest.convertMapVoFixedToString(mapVO);
        //then
        assertEquals(EXPECTED_FIXED, result);
    }
}
