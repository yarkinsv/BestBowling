package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class Frame {

    public enum FrameType {
        NotCompleted,
        OpenFrame,
        Strike,
        Spare
    }

    private int firstBall = -1;
    private int secondBall = -1;

    public void setScore(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be from 0 to 10");
        }

        if (firstBall == -1) {
            setFirstBall(score);
        } else {
            setSecondBall(score);
        }
    }

    private void setFirstBall(int score) {
        firstBall = score;
        if (score == 10) {
            secondBall = 0;
        }
    }

    private void setSecondBall(int score) {
        if (secondBall != -1) {
            throw new IllegalStateException("Second ball has been already scored.");
        }

        if (firstBall + score > 10) {
            throw new IllegalArgumentException("Total score can't be more that 10.");
        }

        secondBall = score;
    }

    public FrameType getFrameType() {
        if (firstBall == -1 || secondBall == -1) {
            return FrameType.NotCompleted;
        }

        if (firstBall == 10) {
            return FrameType.Strike;
        }

        if (firstBall + secondBall == 10) {
            return FrameType.Spare;
        }

        return FrameType.OpenFrame;
    }

    public int getFirstBall() {
        return firstBall;
    }

    public int getSecondBall() {
        return secondBall;
    }

    public int getScore() {
        if (getFrameType() == FrameType.NotCompleted) {
            return 0;
        }

        return firstBall + secondBall;
    }
}
