package hh.yarkinsv;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YarkinSV on 2/1/2016.
 */
public class GameState {

    public enum GameStage {
        SettingUp,
        InProgress,
        Completed
    }

    private Player[] players;
    private Map<Player, TableScore> scores = new HashMap<>();
    private Player currentPlayer;
    private int currentFrame;
    private int currentBall;
    private GameStage gameStage;
    private PlayersRepository repository;

    public Player[] getPlayers() {
        return players;
    }

    public TableScore getTableScore(Player player) {
        return scores.get(player);
    }

    public TableScore getTableScore(int number) {
        return scores.get(players[number]);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public GameState(int playersNumber, PlayersRepository repository) {
        if (playersNumber < 1) {
            throw new IllegalArgumentException("Number of players can't be less than or equal to zero.");
        }

        if (playersNumber > 8) {
            throw new IllegalArgumentException("Number of players can't be more than 8.");
        }

        this.repository = repository;
        players = new Player[playersNumber];
        gameStage = GameStage.SettingUp;
    }

    public GameState addPlayer(Player player) {
        if (gameStage != GameStage.SettingUp || getFreeSlots() == 0) {
            throw new IllegalStateException("Can't add more players. Either wrong game stage or there are not free slots.");
        }

        players[players.length - getFreeSlots()] = player;
        scores.put(player, new TableScore(player));

        return this;
    }

    public GameState startGame() {
        if (gameStage != GameStage.SettingUp || getFreeSlots() > 0) {
            throw new IllegalStateException("Can't start the game. Either wrong game stage or game still have free slots.");
        }

        gameStage = GameStage.InProgress;
        currentBall = 1;
        currentFrame = 0;
        currentPlayer = players[0];

        return this;
    }

    public GameState setScore(int score) {
        if (gameStage != GameStage.InProgress) {
            throw new IllegalStateException("Can't set a score, game isn't in progress.");
        }

        TableScore tableScore = scores.get(currentPlayer);
        tableScore.setNextScore(score);

        if (tableScore.getCurrentFrame().getFrameType() == Frame.FrameType.NotCompleted ||
                currentFrame == 9 && !tableScore.isComplete()) {
            currentBall++;
        } else {
            nextPlayer();
        }

        if (isComplete()) {
            gameStage = GameStage.Completed;
        }

        return this;
    }

    public GameState setStrike() {
        return setScore(10);
    }

    public GameState setSpare() {
        if (scores.get(currentPlayer).getCurrentFrame().getFirstBall() == -1) {
            throw new IllegalArgumentException("Can't set a spare, first ball have to be performed first.");
        }

        return setScore(10 - scores.get(currentPlayer).getCurrentFrame().getFirstBall());
    }

    public boolean isComplete() {
        return scores.values().stream().allMatch(t -> t.isComplete());
    }

    private int getFreeSlots() {
        int freeSlots = 0;
        for (Player p : players) {
            if (p == null) {
                freeSlots++;
            }
        }
        return freeSlots;
    }

    private void nextPlayer() {
        int index = 0;
        while (players[index] != currentPlayer) {
            index++;
        }
        if (index == players.length - 1) {
            index = 0;
            currentFrame++;
        } else {
            index++;
        }
        currentBall = 1;
        currentPlayer = players[index];
    }

    public int getPlayerHighestScore(int number) {
        return repository.getHighestScore(players[number]);
    }
}
