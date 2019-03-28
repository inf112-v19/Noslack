package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.Orientation;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotatorTest {

    TileGrid tileGridClockwise = new TileGrid("clockwiseRotatorTestMap.txt");
    TileGrid tileGridCounterClockwise = new TileGrid("counterClockwiseRotatorTestMap.txt");

    @Test
    public void ClockwiseRotatorRotatesClockwise(){
        Assert.assertEquals(Orientation.FACING_NORTH, tileGridClockwise.getPlayer(0).getOrientation());
        tileGridClockwise.activateTiles();
        assertEquals(Orientation.FACING_EAST, tileGridClockwise.getPlayer(0).getOrientation());
    }

    @Test
    public void CounterClockwiseRotatorRotatesCounterClockwise(){
        assertEquals( Orientation.FACING_NORTH, tileGridCounterClockwise.getPlayer(0).getOrientation());
        tileGridCounterClockwise.activateTiles();
        assertEquals(Orientation.FACING_WEST, tileGridCounterClockwise.getPlayer(0).getOrientation());
    }
}