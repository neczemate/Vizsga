package hu.nye.progtech.torpedo.service.util;

import hu.nye.progtech.torpedo.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapUtilTest {
    private static final boolean[][] FIXED = {{true,true},{true,true}};
    private static final char[][] MAP1 = {{'0','0'},{'0','0'}};
    private static final char[][] MAP2 = {{'1','1'},{'1','0'}};
    private static final MapVO COMPLETED_MAP1 = new MapVO(2,2,MAP1, FIXED);
    private static final MapVO COMPLETED_MAP2 = new MapVO(2,2,MAP1, FIXED);
    private static final MapVO NOT_COMPLETED_MAP = new MapVO(2,2,MAP2, FIXED);

    private MapUtil underTest;

    @BeforeEach
    public void setUp(){
        underTest = new MapUtil();
    }

    @Test
    public void testWhenIsMapCompletedReturnsTrue(){
        //given

        //when
        boolean result = underTest.isMapCompleted(COMPLETED_MAP1, COMPLETED_MAP2);
        System.out.println(result);
        //then
        assertTrue(result);
    }

    @Test
    public void testWhenIsMapCompletedReturnsFalse(){
        //given

        //when
        boolean result = underTest.isMapCompleted(NOT_COMPLETED_MAP, COMPLETED_MAP2);
        System.out.println(result);
        //then
        assertTrue(result);
    }

    @Test
    public void testWhenIsMapCompletedReturnsFalseWithTwoMap(){
        //given

        //when
        boolean result = underTest.isMapCompleted(NOT_COMPLETED_MAP, NOT_COMPLETED_MAP);
        System.out.println(result);
        //then
        assertFalse(result);
    }
}
