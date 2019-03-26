package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class WallTest {
    @Test
    public void getOrientationNorth() {
        Wall wall = new Wall(Orientation.FACING_NORTH);
        assertEquals(wall.getOrientation(), Orientation.FACING_NORTH);
    }
    @Test
    public void getOrientationSouth() {
        Wall wall = new Wall(Orientation.FACING_SOUTH);
        assertEquals(wall.getOrientation(), Orientation.FACING_SOUTH);
    }
    @Test
    public void getOrientationEast() {
        Wall wall = new Wall(Orientation.FACING_EAST);
        assertEquals(wall.getOrientation(), Orientation.FACING_EAST);
    }
    @Test
    public void getOrientationWest() {
        Wall wall = new Wall(Orientation.FACING_WEST);
        assertEquals(wall.getOrientation(), Orientation.FACING_WEST);
    }

    @Test
    public void playerHitWallOnTile() {
        Player player = new Player(0,Orientation.FACING_NORTH);
        player.setCurrentMove(Program.BACK);
        Wall wall = new Wall(Orientation.FACING_SOUTH);
    }

    @Test
    public void playerHitWallOnNextTile() {
        TileGrid tileGrid = new TileGrid("wallTestMap2.txt");
        Coordinate cor = tileGrid.getPlayerPosition(0);
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        Coordinate cor2 = new Coordinate(tileGrid.getPlayerPosition(0));
        System.out.println(cor+" after "+cor2);
        assertEquals(cor,cor2);
    }
}