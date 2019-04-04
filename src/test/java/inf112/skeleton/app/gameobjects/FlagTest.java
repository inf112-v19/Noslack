package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Robots.IRobot;
import inf112.skeleton.app.gameobjects.Robots.Player;
import inf112.skeleton.app.gameobjects.tiletypes.Flag;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {

    @Test
    public void flagInitialized() {
        Flag flag = new Flag();
        assertNotEquals(flag, null);
        assertEquals(flag.getFlagNumber(), 1);
    }

    @Test
    public void setFlagNumber() {
        Flag flag = new Flag(2);
        assertEquals(flag.getFlagNumber(), 2);
    }
    @Test
    public void flagsSizeTest(){
        TileGrid tileGrid = new TileGrid("playerFlagTestMap.txt");
        IRobot player = tileGrid.getPlayer(0);
        assertEquals(2,player.getFlagsVisited().length);
    }

    @Test
    public void flagsAreRegistered() {
        TileGrid tileGrid = new TileGrid("playerFlagTestMap.txt");
        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.activateTiles(0);
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
    }

    @Test
    public void flagsCounted() {
        TileGrid tileGrid = new TileGrid("playerFlagTestMap.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles(0);
        tileGrid.getPlayer(0).setOrientation(Orientation.FACING_EAST);

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles(0);
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
        assertTrue(tileGrid.getPlayer(0).getFlag(2));
    }

    @Test
    public void flagsOutOfOrderNotCounted(){
        TileGrid tileGrid = new TileGrid("playerFlagTestMap2.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles(0);

        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        assertFalse(tileGrid.getPlayer(0).getFlag(2));
    }
}
