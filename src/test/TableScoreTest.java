package test;

import hh.yarkinsv.Player;
import hh.yarkinsv.TableScore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 */
public class TableScoreTest {

    TableScore tableScore;
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("Stas");
        tableScore = new TableScore(player);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPlayer() {
        new TableScore(null);
    }

    @Test
    public void testPlayer() {
        assertEquals(player, tableScore.getPlayer());
    }

    @Test
    public void testSimpleComplete() {
        for (int i = 0; i < 20; i++) {
            assertFalse(tableScore.isComplete());
            tableScore.setNextScore(4);
        }
        assertTrue(tableScore.isComplete());
        assertEquals(80, tableScore.getTotalScore());
    }

    @Test
    public void testAllStrikes() {
        for (int i = 0; i < 12; i++) {
            assertFalse(tableScore.isComplete());
            tableScore.setNextScore(10);
        }
        assertTrue(tableScore.isComplete());
        assertEquals(300, tableScore.getTotalScore());
    }

    @Test
    public void testAllSpares() {
        for (int i = 0; i < 21; i++) {
            assertFalse(tableScore.isComplete());
            tableScore.setNextScore(5);
        }
        assertTrue(tableScore.isComplete());
        assertEquals(150, tableScore.getTotalScore());
    }

    @Test
    public void testPartialComplete() {
        tableScore.setNextScore(10);
        tableScore.setNextScore(2);
        tableScore.setNextScore(0);
        tableScore.setNextScore(7);
        tableScore.setNextScore(3);
        tableScore.setNextScore(1);
        tableScore.setNextScore(4);
        assertEquals(30, tableScore.getTotalScore());
    }

    @Test
    public void testZeroScore() {
        tableScore.setNextScore(0);
        assertEquals(0, tableScore.getCurrentFrame().getFirstBall());
        assertEquals(0, tableScore.getTotalScore());
    }

    @Test
    public void testAllZerosScore() {
        while (!tableScore.isComplete()) {
            tableScore.setNextScore(0);
        }
        assertEquals(0, tableScore.getTotalScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentNextScore() {
        tableScore.setNextScore(120);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeScore() {
        tableScore.setNextScore(-1);
    }

    @Test(expected = IllegalStateException.class)
    public void testNextScoreWhenComplete() {
        while (!tableScore.isComplete()) {
            tableScore.setNextScore(10);
        }
        tableScore.setNextScore(6);
    }
}