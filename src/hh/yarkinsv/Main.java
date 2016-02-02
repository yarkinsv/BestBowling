package hh.yarkinsv;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        TableScore tableScore = new TableScore(new Player("Stas"));
        tableScore.setNextScore(5);
        tableScore.setNextScore(5);
        tableScore.setNextScore(5);
        tableScore.setNextScore(5);
        tableScore.setNextScore(5);
        tableScore.setNextScore(5);
        tableScore.setNextScore(10);
        tableScore.setNextScore(3);
        tableScore.setNextScore(0);
        tableScore.setNextScore(10);

        printTableScore(tableScore);

        clearScreen();


        printTableScore(tableScore);

        clearScreen();
        try {
            System.in.read();
        }catch (IOException ex) {}

        printTableScore(tableScore);
    }

    private static void printTableScore(TableScore tableScore) {
        String I = Character.toString((char)0x2551);
        for (Frame frame : tableScore.getFrames()) {
            switch (frame.getFrameType()) {
                case OpenFrame:
                    System.out.print(I + frame.getFirstBall() + " " + frame.getSecondBall());
                    break;
                case Strike:
                    System.out.print(I + " X ");
                    break;
                case Spare:
                    System.out.print(I + frame.getFirstBall() + " /");
                    break;
                case NotCompleted:
                    if (frame.getFirstBall() == -1) {
                        System.out.print(I + "   ");
                    } else {
                        System.out.print(I + frame.getFirstBall() + "  ");
                    }
                    break;
            }
        }
        System.out.print(I);
    }


    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}
