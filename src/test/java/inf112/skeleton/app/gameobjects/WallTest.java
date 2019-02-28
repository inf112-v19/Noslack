package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.TileGrid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class WallTest {

    private TileGrid grid;

    @Before
    public void setUp() {
        grid = new TileGrid(12, 12, 1, ".maplayoutWallTest.txt");
    }

    @Test
    public void WallIsImpassableOnWallNextTile() {
        grid.movePlayer(0, 0, 2);
        assertEquals(grid.getTile(grid.getPlayer(0).getPosition()), grid.getTile(2, 5));
    }

    public void WallIsImpassableOnWallTile() {
        grid = new TileGrid(12, 12, 1, "maplayoutWallTest2.txt");
        grid.movePlayer(0, 0, 3);
        assertEquals(grid.getTile(grid.getPlayer(0).getPosition()), grid.getTile(2, 5));
    }


}