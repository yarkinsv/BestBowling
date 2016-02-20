package test;

import hh.yarkinsv.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by RRP-YarkinSV on 02.02.2016.
 */
public class PlayerTest {

    @Test
    public void testGetName() {
        Player player = new Player("Stas");
        assertEquals("Stas", player.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLongName() {
        Player player = new Player("Lijdncnkn# lkjsdijfjsdkm vkjskdhg asdlkfnknsdlkfsdiukvn");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        Player player = new Player("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        Player player = new Player(null);
    }
}