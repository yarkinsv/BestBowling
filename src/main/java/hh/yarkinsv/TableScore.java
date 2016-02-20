package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class TableScore {
    private Player player;
    private Frame[] frames = new Frame[10];
    private int currentFrame = 0;

    public TableScore(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player must be specified.");
        }

        this.player = player;
        for (int i = 0; i < 9; i++) {
            frames[i] = new Frame();
        }
        frames[9] = new FinalFrame();
    }

    private int getFrameScore(int frameNumber) {
        Frame frame = frames[frameNumber];

        if (frame.getFrameType() == Frame.FrameType.NotCompleted) {
            return 0;
        }

        if (frame instanceof FinalFrame || frames[frameNumber + 1].getFrameType() == Frame.FrameType.NotCompleted) {
            return frame.getScore();
        }

        switch (frame.getFrameType()) {
            case OpenFrame:
                return frame.getScore();
            case Spare:
                return frame.getScore() + frames[frameNumber + 1].getFirstBall();
            case Strike:
                if (frames[frameNumber + 1].getFrameType() == Frame.FrameType.Strike) {
                    if (frames[frameNumber + 1] instanceof FinalFrame) {
                        return frame.getScore() + frames[frameNumber + 1].getFirstBall() + ((FinalFrame) frames[frameNumber + 1]).getFirstAdditionalBall();
                    } else if (frames[frameNumber + 2].getFrameType() != Frame.FrameType.NotCompleted) {
                        return frame.getScore() + 10 + frames[frameNumber + 2].getFirstBall();
                    } else {
                        return frame.getScore() + 10;
                    }
                } else {
                    return frame.getScore() + frames[frameNumber + 1].getFirstBall() + frames[frameNumber + 1].getSecondBall();
                }
        }

        return 0;
    }

    public int getCumulativeScore(int num) {
        int result = 0;
        for (int i = 0; i < num; i++) {
            result += getFrameScore(i);
        }
        return result;
    }

    public int getTotalScore() {
        return getCumulativeScore(10);
    }

    public Player getPlayer() {
        return player;
    }

    public Frame[] getFrames() {
        return frames;
    }

    public void setNextScore(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10.");
        }

        if (isComplete()) {
            throw new IllegalStateException("Score table is complete, it is not possible to set new score.");
        }

        if (frames[currentFrame].getFrameType() != Frame.FrameType.NotCompleted) {
            currentFrame++;
        }
        frames[currentFrame].setScore(score);
    }

    public boolean isComplete() {
        for (Frame frame : frames) {
            if (frame.getFrameType() == Frame.FrameType.NotCompleted) {
                return false;
            }
        }

        return true;
    }

    public Frame getCurrentFrame() {
        return frames[currentFrame];
    }
}
