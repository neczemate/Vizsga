package hu.nye.progtech.torpedo.service.command.impl;

import hu.nye.progtech.torpedo.model.MapVO;
import hu.nye.progtech.torpedo.service.exception.ShotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShotPerformerTest {
    private static final int TARGET_ROW_INDEX = 1;
    private static final int TARGET_COLUMN_INDEX = 1;

    private static final char[][] MAP = {{'0','0','0'},
            {'0','1','0'},
            {'0','0','0'}};
    private static final boolean[][] FIXED = {{true,true,true},
            {true,true,true},
            {true,true,true}};
    private static final boolean[][] EXPECTED_FIXED = {{true,true,true},
            {true,false,true},
            {true,true,true}};
    private static final boolean[][] WRONG_FIXED = {{false,false,false},
            {false,false,false},
            {false,false,false}};
    private static final char[][] EXPECTED_MAP = {{'0','0','0'},
            {'0','+','0'},
            {'0','0','0'}};

    private static final MapVO AI_MAP = new MapVO(3,3, MAP, FIXED);
    private static final MapVO WRONG_MAP = new MapVO(3,3, MAP, WRONG_FIXED);
    private static final MapVO EXPECTED_AI_MAP =
            new MapVO(3, 3, EXPECTED_MAP, EXPECTED_FIXED);

    private ShotPerformer underTest;

    @BeforeEach
    public void underTest(){
        underTest = new ShotPerformer();
    }

    @Test
    public void testAttackPerformerShouldCreateANewMap() throws ShotException {
        //given

        //when
        MapVO result = underTest.perform(AI_MAP, TARGET_ROW_INDEX, TARGET_COLUMN_INDEX);
        //then
        assertEquals(EXPECTED_AI_MAP, result);
    }

    @Test
    public void testAttackPerformShouldThrowAttackException(){
        //given

        //when
        assertThrows(ShotException.class, () ->
        {underTest.perform(WRONG_MAP, TARGET_ROW_INDEX, TARGET_COLUMN_INDEX);});
    }
}
