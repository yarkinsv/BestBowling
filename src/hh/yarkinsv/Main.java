package hh.yarkinsv;

import hh.yarkinsv.gui.GameTableFrame;
import hh.yarkinsv.gui.TableScoreFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GameState game = new GameState(2, new JustForFunPlayersRepository());
        game.addPlayer(new Player("Stas"));
        game.addPlayer(new Player("Misha"));
        game.startGame();
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);
        game.setScore(3);


        GameTableFrame frame = new GameTableFrame(game);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);


    }
}
