package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;
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
        TileGrid tileGrid = new TileGrid("wallTestMap.txt");
        Coordinate cor = tileGrid.getRobotPosition(0);
        tileGrid.getRobot(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        Coordinate cor2 = new Coordinate(tileGrid.getRobotPosition(0));
        assertEquals(cor,cor2);
    }

    @Test
    public void playerHitWallOnNextTile() {
        TileGrid tileGrid = new TileGrid("wallTestMap2.txt");
        Coordinate cor = tileGrid.getRobotPosition(0);
        tileGrid.getRobot(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        Coordinate cor2 = new Coordinate(tileGrid.getRobotPosition(0));
        assertEquals(cor,cor2);
    }
}