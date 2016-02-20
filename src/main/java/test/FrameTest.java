package test;

import hh.yarkinsv.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {
    Frame frame;

    @Before
    public void testFrame() {
        frame = new Frame();
    }

    @Test
    public void testSetScoreOpenFrame() {
        frame.setScore(2);
        frame.setScore(5);
        assertEquals(Frame.FrameType.OpenFrame, frame.getFrameType());
        assertEquals(2, frame.getFirstBall());
        assertEquals(5, frame.getSecondBall());
    }

    @Test
    public void testNotCompletedFrame() {
        assertEquals(Frame.FrameType.NotCompleted, frame.getFrameType());
        frame.setScore(5);
        assertEquals(Frame.FrameType.NotCompleted, frame.getFrameType());
    }

    @Test
    public void testStrike() {
        frame.setScore(10);
        assertEquals(Frame.FrameType.Strike, frame.getFrameType());
        assertEquals(10, frame.getFirstBall());
        assertEquals(0, frame.getSecondBall());
    }

    @Test
    public void testSpare() {
        frame.setScore(3);
        frame.setScore(7);
        assertEquals(Frame.FrameType.Spare, frame.getFrameType());
        assertEquals(3, frame.getFirstBall());
        assertEquals(7, frame.getSecondBall());
    }

    @Test(expected = IllegalStateException.class)
    public void testThirdScore() {
        frame.setScore(3);
        frame.setScore(0);
        frame.setScore(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument1() {
        frame.setScore(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument2() {
        frame.setScore(12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument3() {
        frame.setScore(6);
        frame.setScore(7);
    }
}