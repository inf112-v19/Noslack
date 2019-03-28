package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
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
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        Player player = tileGrid.getPlayer(0);
        assertEquals(2,player.getFlagsVisited().length);
    }

    @Test
    public void flagsAreRegistered() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
    }

    @Test
    public void flagsCounted() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        tileGrid.getPlayer(0).setOrientation(Orientation.FACING_EAST);

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
        assertTrue(tileGrid.getPlayer(0).getFlag(2));
    }

    @Test
    public void flagsOutOfOrderNotCounted(){
        TileGrid tileGrid = new TileGrid("playerTestFlagMap2.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();

        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        assertFalse(tileGrid.getPlayer(0).getFlag(2));
    }
}
