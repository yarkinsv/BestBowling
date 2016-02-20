package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class Frame {

    protected final int NAN = -1;
    protected final int MAX_SCORE = 10;
    
    public enum FrameType {
        NotCompleted,
        OpenFrame,
        Strike,
        Spare
    }

    protected int firstBall = NAN;
    protected int secondBall = NAN;

    public void setScore(int score) {
        if (score < 0 || score > MAX_SCORE) {
            throw new IllegalArgumentException("Score must be from 0 to MAX_SCORE");
        }

        if (firstBall == NAN) {
            setFirstBall(score);
        } else {
            setSecondBall(score);
        }
    }

    private void setFirstBall(int score) {
        firstBall = score;
        if (score == MAX_SCORE) {
            secondBall = 0;
        }
    }

    private void setSecondBall(int score) {
        if (secondBall != NAN) {
            throw new IllegalStateException("Second ball has been already scored.");
        }

        if (firstBall + score > MAX_SCORE) {
            throw new IllegalArgumentException("Total score can't be more that MAX_SCORE.");
        }

        secondBall = score;
    }

    public FrameType getFrameType() {
        if (firstBall == NAN || secondBall == NAN) {
            return FrameType.NotCompleted;
        }

        if (firstBall == MAX_SCORE) {
            return FrameType.Strike;
        }

        if (firstBall + secondBall == MAX_SCORE) {
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
