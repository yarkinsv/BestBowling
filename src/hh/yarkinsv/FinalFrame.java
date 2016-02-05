package hh.yarkinsv;

import org.junit.validator.ValidateWith;

/**
 * Created by RRP-YarkinSV on 03.02.2016.
 */
public class FinalFrame extends Frame {

    private int additionalBall1 = -1;
    private int additionalBall2 = -1;

    @Override
    public void setScore(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be from 0 to 10");
        }

        if (super.getFrameType() == FrameType.NotCompleted) {
            super.setScore(score);
            if (super.getFrameType() == FrameType.OpenFrame) {
                additionalBall1 = 0;
                additionalBall2 = 0;
            }
        } else {
            if (additionalBall1 == -1) {
                setFirstAdditionalBall(score);
            } else {
                setSecondAdditionalBall(score);
            }
        }
    }

    private void setFirstAdditionalBall(int score) {
        if (super.getFrameType() != FrameType.Strike && super.getFrameType() != FrameType.Spare) {
            throw new IllegalStateException("Additional balls are not available for this frame.");
        }

        additionalBall1 = score;
        if (super.getFrameType() == FrameType.Spare) {
            additionalBall2 = 0;
        }
    }

    private void setSecondAdditionalBall(int score) {
        if (super.getFrameType() != FrameType.Strike) {
            throw new IllegalStateException("Second additional ball is not available for this frame.");
        }

        if (additionalBall2 != -1) {
            throw new IllegalStateException("Second additional ball has been already scored.");
        }

        additionalBall2 = score;
    }

    @Override
    public FrameType getFrameType() {
        if (super.getFrameType() == FrameType.Strike) {
            if (additionalBall1 == -1 || additionalBall2 == -1) {
                return FrameType.NotCompleted;
            }
        } else if (super.getFrameType() == FrameType.Spare) {
            if (additionalBall1 == -1) {
                return FrameType.NotCompleted;
            }
        }

        return super.getFrameType();
    }

    public int getFirstAdditionalBall() {
        return additionalBall1;
    }

    public int getSecondAdditionalBall() {
        return additionalBall2;
    }

    @Override
    public int getScore() {
        if (getFrameType() == FrameType.NotCompleted) {
            return 0;
        }

        return firstBall + secondBall + additionalBall1 + additionalBall2;
    }
}
