package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class WallTest {

    @Test
    public void WallIsImpassableOnWallNextTile() {
        TileGrid grid = new TileGrid("mapLayoutWallTest.txt");
        Coordinate cor = grid.getPlayerPosition(0);
        grid.movePlayer(0, 0, 2);
        assertEquals(cor, grid.getPlayerPosition(0));
    }

    @Test
    public void WallIsImpassableOnWallTile() {
        TileGrid grid = new TileGrid("mapLayoutWallTest2.txt");
        grid.movePlayer(0, 0, 3);
        assertEquals(grid.getTile(grid.getPlayer(0).getPosition()), grid.getTile(2, 5));
    }


}