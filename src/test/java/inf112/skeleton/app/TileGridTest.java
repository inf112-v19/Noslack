package inf112.skeleton.app;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileGridTest {

    @Test
    public void movePlayer() {
        TileGrid grid = new TileGrid("testMapOnePlayer.txt");
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
        TileGrid grid = new TileGrid("testMapOnePlayer.txt");
        assertEquals(GameObjectType.PLAYER,grid.getPlayer(0).getGameObjectType());
    }

    @Test
    public void getCoordinatesOfPlayer() {
        TileGrid grid = new TileGrid("testMapOnePlayer.txt");
        Coordinate coordinate = new Coordinate(1,2);
        assertEquals(coordinate,grid.getPlayerPosition(0));
    }

    @Test
    public void getCoordinatesOfPlayers() {
        TileGrid grid = new TileGrid("testMapTwoPlayers.txt");
        Coordinate coordinate1 = new Coordinate(1,2);
        Coordinate coordinate2 = new Coordinate(4,1);
        assertEquals(coordinate1,grid.getPlayerPosition(1));
        assertEquals(coordinate2,grid.getPlayerPosition(0));
    }

    @Test
    public void getRows() {
        TileGrid grid = new TileGrid("testMapTwoPlayers.txt");
        assertEquals(6,grid.getRows());
    }

    @Test
    public void getColumns() {
        TileGrid grid = new TileGrid("testMapTwoPlayers.txt");
        assertEquals(6,grid.getColumns());
    }

    @Test
    public void getPlayersInitiated() {
        TileGrid grid = new TileGrid("testMapTwoPlayers.txt");
        assertEquals(2,grid.getPlayersInitiated());
    }
}