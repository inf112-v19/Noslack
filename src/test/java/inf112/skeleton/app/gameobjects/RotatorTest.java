package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotatorTest {

    TileGrid tileGrid = new TileGrid("rotatorTestMap.txt");

    @Test
    public void ClockwiseRotatorRotatesClockwise(){
        assertEquals(tileGrid.getPlayer(0).getOrientation(), Orientation.FACING_NORTH);
        tileGrid.activateTiles();
        assertEquals(tileGrid.getPlayer(0).getOrientation(), Orientation.FACING_EAST);

    }
}