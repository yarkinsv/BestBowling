import hh.yarkinsv.GameState;
import hh.yarkinsv.Player;
import hh.yarkinsv.PlayersRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 */
public class GameStateTest {

    GameState game;
    PlayersRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(PlayersRepository.class);
        when(repository.getHighestScore(any(Player.class))).thenReturn(236);

        game = new GameState(3, repository)
            .addPlayer(new Player("Stas"))
            .addPlayer(new Player("Marina"))
            .addPlayer(new Player("Misha"))
            .startGame();
    }

    @Test
    public void testInitialState() {
        assertFalse(game.isComplete());
        assertEquals(0, game.getCurrentFrame());
        assertEquals(1, game.getCurrentBall());
        assertEquals(GameState.GameStage.InProgress, game.getGameStage());
        assertEquals(3, game.getPlayers().length);
        assertEquals("Stas", game.getPlayers()[0].getName());
        assertEquals("Marina", game.getPlayers()[1].getName());
        assertEquals("Misha", game.getPlayers()[2].getName());
        assertEquals("Stas", game.getTableScore(0).getPlayer().getName());
        assertEquals(0, game.getTableScore(0).getTotalScore());
        assertEquals(0, game.getTableScore(0).getCurrentFrame().getScore());
        assertFalse(game.getTableScore(0).isComplete());
        assertEquals(game.getPlayers()[0], game.getCurrentPlayer());
    }

    @Test(expected = IllegalStateException.class)
    public void testTooManyPlayers() {
        new GameState(3, repository)
            .addPlayer(new Player("Stas"))
            .addPlayer(new Player("Marina"))
            .addPlayer(new Player("Misha"))
            .addPlayer(new Player("Katya"))
            .startGame();
    }

    @Test(expected = IllegalStateException.class)
    public void testNotEnoughPlayers() {
        new GameState(3, repository)
            .addPlayer(new Player("Stas"))
            .startGame();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroPlayers() {
        new GameState(0, repository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyPlayers2() {
        new GameState(9, repository);
    }

    @Test
    public void testNormalGame() {
        game.setScore(3).setSpare()     //1 frame: Stas
            .setStrike()                //1 frame: Marina
            .setStrike()                //1 frame: Misha
            .setScore(0).setScore(1)    //2 frame: Stas
            .setScore(2).setScore(7)    //2 frame: Marina
            .setScore(9).setScore(0)    //2 frame: Misha
            .setScore(1).setScore(1)    //3 frame: Stas
            .setScore(3).setSpare()     //3 frame: Marina
            .setScore(5).setScore(5)    //3 frame: Misha
            .setStrike()                //4 frame: Stas
            .setStrike()                //4 frame: Marina
            .setStrike()                //4 frame: Misha
            .setScore(5).setScore(5)    //5 frame: Stas
            .setScore(0).setScore(5)    //5 frame: Marina
            .setScore(1).setScore(5)    //5 frame: Misha
            .setScore(8).setScore(1)    //6 frame: Stas
            .setScore(3).setScore(0)    //6 frame: Marina
            .setScore(0).setScore(0)    //6 frame: Misha
            .setScore(1).setScore(2)    //7 frame: Stas
            .setScore(4).setScore(1)    //7 frame: Marina
            .setScore(7).setScore(1)    //7 frame: Misha
            .setScore(0).setScore(5)    //8 frame: Stas
            .setStrike()                //8 frame: Marina
            .setScore(0).setScore(0)    //8 frame: Misha
            .setStrike()                //9 frame: Stas
            .setScore(3).setScore(3)    //9 frame: Marina
            .setScore(0).setScore(5)    //9 frame: Misha
            .setScore(8).setScore(0)    //10 frame: Stas
            .setStrike()                //10 frame: Marina
            .setScore(8).setScore(0)    //10 frame: Marina
            .setScore(7).setSpare()     //10 frame: Misha
            .setScore(2);               //10 frame: Misha

        assertTrue(game.isComplete());
        assertEquals(94, game.getTableScore(0).getTotalScore()); //Stas
        assertEquals(116, game.getTableScore(1).getTotalScore()); //Marina - winner!
        assertEquals(95, game.getTableScore(2).getTotalScore()); //Misha
    }

    @Test(expected = IllegalStateException.class)
    public void testCantSetScoreGameCompleted() {
        while (!game.isComplete()) {
            game.setStrike();
        }
        game.setScore(7);
    }

    @Test
    public void testMockedMethod() {
        assertEquals(236, game.getPlayerHighestScore(0));
        verify(repository).getHighestScore(game.getPlayers()[0]);
    }
}