package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.TileTypes.Hole;
import org.junit.Test;

import static org.junit.Assert.*;

public class HoleTest {

    @Test
    public void holeInitialized() {
        Hole hole = new Hole();
        assertNotEquals(hole, null);
    }

    @Test
    public void playerFallsInHole(){
        TileGrid grid = new TileGrid("holeTest.txt");
        grid.getPlayer(0).setCurrentMove(Program.MOVE1);
        int lives = grid.getPlayer(0).getLives();
        grid.continueMove(0);
        grid.continueMove(0);
        grid.activateTiles();
        assertNotEquals(lives,grid.getPlayer(0).getLives());
        assertEquals(--lives,grid.getPlayer(0).getLives());
    }
}