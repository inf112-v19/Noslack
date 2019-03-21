package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class WallTest {

    /*@Test
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
    }*/


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
    public void possibleEffectPlayer() {
        Wall wall = new Wall(Orientation.FACING_NORTH);
        assertTrue(wall.possibleEffectPlayer(Orientation.FACING_NORTH));

    }
    @Test
    public void possibleEffectPlayerOpposite() {
        Wall wall = new Wall(Orientation.FACING_SOUTH);
        assertTrue(wall.possibleEffectPlayer(Orientation.FACING_NORTH));

    }
    @Test
    public void possibleEffectPlayerFalse() {
        Wall wall = new Wall(Orientation.FACING_WEST);
        assertFalse(wall.possibleEffectPlayer(Orientation.FACING_NORTH));
    }

    @Test
    public void playerHitWallOnTile() {
        Player player = new Player(0,Orientation.FACING_NORTH);
        player.setCurrentMove(Program.BACK);
        Wall wall = new Wall(Orientation.FACING_SOUTH);
        wall.playerHitWall(player, true);
        assertEquals(player.getCurrentMove(), Program.NONE);
    }

    @Test
    public void playerHitWallOnNextTile() {
        TileGrid tileGrid = new TileGrid("wallTestMap2.txt");
        Coordinate cor = tileGrid.getPlayerPosition(0);
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE3);
        tileGrid.getPlayer(0).updateOrientation(Program.LEFT);
        System.out.println(cor.getRow()+" "+cor.getColumn());
        tileGrid.movePlayer(0, 0,1);
        tileGrid.movePlayer(0, 0,1);
        tileGrid.movePlayer(0, 0,1);


        assertEquals(tileGrid.getPlayer(0).getCurrentMove(), Program.NONE);
    }
    @Test
    public void wallStopsPlayer(){
        TileGrid tileGrid = new TileGrid("wallTestMap2.txt");
        Coordinate cor = tileGrid.getPlayerPosition(0);
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE3);
        tileGrid.getPlayer(0).updateOrientation(Program.LEFT);
        System.out.println(cor.getRow()+" "+cor.getColumn());
        tileGrid.movePlayer(0, 0,1);
        tileGrid.movePlayer(0, 0,1);
        tileGrid.movePlayer(0, 0,1);

        Coordinate cor2 = tileGrid.getPlayerPosition(0);

        assertEquals(cor, cor2);
    }
}