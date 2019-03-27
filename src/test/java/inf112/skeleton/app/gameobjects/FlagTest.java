package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.Tile;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.TileTypes.Flag;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlagTest {

    @Test
    public void flagInitialized() {
        Flag flag = new Flag();
        assertNotEquals(flag, null);
        assertEquals(flag.getFlagNumber(), 1);
    }

    @Test
    public void setFlagNumber() {
        Flag flag = new Flag(2);
        assertEquals(flag.getFlagNumber(), 2);
    }

    @Test
    public void flagNumberOnMapTest1() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        Tile tile1 =tileGrid.getTile(2 ,1);
        assertTrue(tile1.hasGameObject(GameObjectType.FLAG));
        int flag1=((Flag)tile1.getGameObject(GameObjectType.FLAG)).getFlagNumber();
        assertEquals(1,flag1);
    }
    @Test
    public void flagNumberOnMapTest2() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        Tile tile2 =tileGrid.getTile(2 ,2);
        assertTrue(tile2.hasGameObject(GameObjectType.FLAG));
        int flag2=((Flag)tile2.getGameObject(GameObjectType.FLAG)).getFlagNumber();
        assertEquals(2,flag2);
    }
}
