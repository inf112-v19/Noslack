package inf112.skeleton.app;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileGridTest {

    @Test
    public void movePlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        grid.getPlayer(0).setCurrentMove(Program.MOVE1);
        Coordinate coordinate = grid.getPlayerPosition(0);
        grid.continueMove(0);
        Coordinate coordinate2 = grid.getPlayerPosition(0);
        Coordinate newPosition = new Coordinate(1,3);

        assertNotEquals(coordinate,coordinate2);
        assertEquals(newPosition,coordinate2);
    }

    @Test
    public void getPlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        assertEquals(GameObjectType.ROBOT,grid.getPlayer(0).getGameObjectType());
    }

    @Test
    public void getCoordinatesOfPlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        Coordinate coordinate = new Coordinate(1,2);
        assertEquals(coordinate,grid.getPlayerPosition(0));
    }

    @Test
    public void getCoordinatesOfPlayers() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        Coordinate coordinate1 = new Coordinate(1,2);
        Coordinate coordinate2 = new Coordinate(4,1);
        assertEquals(coordinate1,grid.getPlayerPosition(1));
        assertEquals(coordinate2,grid.getPlayerPosition(0));
    }

    @Test
    public void getRows() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(6,grid.getRows());
    }

    @Test
    public void getColumns() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(6,grid.getColumns());
    }

    @Test
    public void getPlayersInitiated() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(2,grid.getPlayersInitiated());
    }

    @Test
    public void firePlayerLaser() {
        TileGrid grid = new TileGrid("fireLaserTestMap.txt");
        grid.firePlayerLaser(0);

        assertFalse(grid.getTile(0,0).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,3).hasGameObject(GameObjectType.LASER_BEAM));
    }

    @Test
    public void firePlayerLaser2() {
        TileGrid grid = new TileGrid("fireLaserTestMap.txt");
        grid.firePlayerLaser(0);

        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
    }

    @Test
    public void removePlayerLaser() {
        TileGrid grid = new TileGrid("fireLaserTestMap.txt");
        grid.firePlayerLaser(0);
        grid.removePlayerLaser(0);

        assertFalse(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
    }
}