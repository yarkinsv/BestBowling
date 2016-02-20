import hh.yarkinsv.FinalFrame;
import hh.yarkinsv.Frame;
import hh.yarkinsv.FrameView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameViewTest {

    private Frame frame;
    private FinalFrame finalFrame;

    @Before
    public void before() {
        frame = new Frame();
        finalFrame = new FinalFrame();
    }

    @Test
    public void openFrameViewTest() {
        frame.setScore(1);
        frame.setScore(3);

        assertEquals("1  3", FrameView.getFrameText(frame));
    }

    @Test
    public void notFinishedCommonFrameTest() {
        frame.setScore(7);

        assertEquals("7", FrameView.getFrameText(frame));
    }

    @Test
    public void missedCommonFrameTest() {
        frame.setScore(7);
        frame.setScore(0);

        assertEquals("7  -", FrameView.getFrameText(frame));
    }

    @Test
    public void strikeFrameViewTest() {
        frame.setScore(10);

        assertEquals("   X", FrameView.getFrameText(frame));
    }

    @Test
    public void spareFrameViewTest() {
        frame.setScore(5);
        frame.setScore(5);

        assertEquals("5  /", FrameView.getFrameText(frame));
    }

    @Test
    public void notFinishedFinalFrameViewTest() {
        finalFrame.setScore(6);

        assertEquals("6    ", FrameView.getFrameText(finalFrame));
    }

    @Test
    public void openFinalFrameViewTest() {
        finalFrame.setScore(6);
        finalFrame.setScore(3);

        assertEquals("6  3", FrameView.getFrameText(finalFrame));
    }

    @Test
    public void strikeFinalFrameViewTest() {
        finalFrame.setScore(10);
        finalFrame.setScore(3);

        assertEquals("X  -  3", FrameView.getFrameText(finalFrame));
    }

    @Test
    public void spareFinalFrameViewTest() {
        finalFrame.setScore(6);
        finalFrame.setScore(4);
        finalFrame.setScore(6);

        assertEquals("6  /  6", FrameView.getFrameText(finalFrame));
    }

    @Test
    public void missedFinalFrameViewTest() {
        finalFrame.setScore(0);
        finalFrame.setScore(6);

        assertEquals("-  6", FrameView.getFrameText(finalFrame));
    }
}
