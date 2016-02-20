package hh.yarkinsv;

public class FinalFrame extends Frame {

    private int additionalBall1 = NAN;
    private int additionalBall2 = NAN;

    @Override
    public void setScore(int score) {
        if (score < 0 || score > MAX_SCORE) {
            throw new IllegalArgumentException("Score must be from 0 to 10");
        }

        if (super.getFrameType() == FrameType.NotCompleted) {
            super.setScore(score);
            if (super.getFrameType() == FrameType.OpenFrame) {
                additionalBall1 = 0;
                additionalBall2 = 0;
            }
        } else {
            if (additionalBall1 == NAN) {
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

        if (additionalBall2 != NAN) {
            throw new IllegalStateException("Second additional ball has been already scored.");
        }

        additionalBall2 = score;
    }

    @Override
    public FrameType getFrameType() {
        if (super.getFrameType() == FrameType.Strike) {
            if (additionalBall1 == NAN || additionalBall2 == NAN) {
                return FrameType.NotCompleted;
            }
        } else if (super.getFrameType() == FrameType.Spare) {
            if (additionalBall1 == NAN) {
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
