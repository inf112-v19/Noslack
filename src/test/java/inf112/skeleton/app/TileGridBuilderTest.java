package inf112.skeleton.app;

import inf112.skeleton.app.gameobjects.GameObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileGridBuilderTest {
    private TileGridBuilder builder = new TileGridBuilder("onePlayerTestMap.txt");

    @Test
    public void getRows() {
        assertEquals(4,builder.getRows());
    }

    @Test
    public void getColumns() {
        assertEquals(4,builder.getColumns());
    }

    @Test
    public void getFlagsInitiated() {
        assertEquals(2,builder.getFlagsInitiated());
    }

    @Test
    public void getPlayersInitiated() {
        assertEquals(1,builder.getPlayersInitiated());
    }

    @Test
    public void addLasersToGrid(){
        TileGrid grid = new TileGrid("laserOutletTestMap3.txt");

        assertTrue(grid.getTile(1,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(1,2).hasGameObject(GameObjectType.LASER_BEAM));
    }
    @Test
    public void addDualLasersToGrid(){
        TileGrid grid = new TileGrid("laserOutletTestMap4.txt");

        assertTrue(grid.getTile(1,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(1,2).hasGameObject(GameObjectType.LASER_BEAM));
    }

    @Test
    public void addLasersToGrid2(){
        TileGrid grid = new TileGrid("laserOutletTestMap5.txt");

        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,3).hasGameObject(GameObjectType.LASER_BEAM));
    }
}