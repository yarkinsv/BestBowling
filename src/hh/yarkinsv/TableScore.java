package hh.yarkinsv;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class TableScore {
    private Player player;
    private Frame[] frames = new Frame[10];
    private Frame[] additionalFrames = new Frame[2];

    public TableScore(Player player) {
        this.player = player;
    }

    public int getTotalScore() {
        return 0;
    }
}
