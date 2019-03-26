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
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(new Coordinate(1,1),tileGrid.getPlayerPosition(0));
    }
    @Test
    public void pushPlayerEvenTest() {
        TileGrid tileGrid = new TileGrid("pusherEvenTestMap.txt");
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(new Coordinate(2,1),tileGrid.getPlayerPosition(0));
    }
}