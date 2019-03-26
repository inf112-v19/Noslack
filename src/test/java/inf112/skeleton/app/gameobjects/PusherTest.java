package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import org.junit.Test;

import static org.junit.Assert.*;

public class PusherTest {

    @Test
    public void pusherInitialized() {
        Pusher pusher = new Pusher(Orientation.FACING_NORTH);
        assertNotEquals(pusher, null);
    }
    @Test
    public void getOrientationNorth() {
        Pusher pusher = new Pusher(Orientation.FACING_NORTH);
        assertEquals(pusher.getOrientation(), Orientation.FACING_NORTH);
    }
    @Test
    public void getOrientationSouth() {
        Pusher pusher = new Pusher(Orientation.FACING_SOUTH);
        assertEquals(pusher.getOrientation(), Orientation.FACING_SOUTH);
    }
    @Test
    public void getOrientationWest() {
        Pusher pusher = new Pusher(Orientation.FACING_WEST);
        assertEquals(pusher.getOrientation(), Orientation.FACING_WEST);
    }
    @Test
    public void getOrientationEast() {
        Pusher pusher = new Pusher(Orientation.FACING_EAST);
        assertEquals(pusher.getOrientation(), Orientation.FACING_EAST);
    }
    @Test
    public void pushPlayerTest() {
        TileGrid tileGrid = new TileGrid("pusherTestMap.txt");
        Coordinate cor = tileGrid.getPlayerPosition(0);
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        Coordinate cor2 = new Coordinate(tileGrid.getPlayerPosition(0));
        assertEquals(cor,cor2);
    }
}