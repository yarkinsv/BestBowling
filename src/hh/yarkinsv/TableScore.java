package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class TableScore {
    private Player player;
    private Frame[] frames = new Frame[12];
    private int currentFrame = 0;

    public TableScore(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player must be specified.");
        }

        this.player = player;
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new Frame();
        }
    }

    public int getTotalScore() {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            Frame frame = frames[i];

            if (frame.getFrameType() == Frame.FrameType.NotCompleted) {
                return total;
            }

            switch (frame.getFrameType()) {
                case OpenFrame:
                    total += frame.getScore();
                    break;
                case Strike:
                    total += frame.getScore();
                    if (frames[i + 1].getFrameType() != Frame.FrameType.NotCompleted) {
                        total += frames[i + 1].getScore();
                    }
                    if (frames[i + 2].getFrameType() != Frame.FrameType.NotCompleted) {
                        total += frames[i + 2].getScore();
                    }
                    break;
                case Spare:
                    total += frame.getScore();
                    if (frames[i + 1].getFirstBall() > -1) {
                        total += frames[i + 1].getFirstBall();
                    }
                    break;
            }
        }
        return total;
    }

    public Player getPlayer() {
        return player;
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
        if (currentFrame == 11) {
            return frames[11].getFrameType() != Frame.FrameType.NotCompleted;
        }

        if (currentFrame == 10) {
            if (frames[9].getFrameType() == Frame.FrameType.Strike) {
                return false;
            } else if (frames[9].getFrameType() == Frame.FrameType.Spare) {
                return frames[10].getFirstBall() != -1;
            } else {
                throw new IllegalStateException("Illegal frames state.");
            }
        }

        if (currentFrame == 9) {
            return frames[9].getFrameType() == Frame.FrameType.OpenFrame;
        }

        return false;
    }

    public Frame getCurrentFrame() {
        return frames[currentFrame];
    }
}
