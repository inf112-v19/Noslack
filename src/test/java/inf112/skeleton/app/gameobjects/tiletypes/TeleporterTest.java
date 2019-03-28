package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeleporterTest {

    @Test
    public void teleporterInitialized() {
        Teleporter tele = new Teleporter(1, 0,0);
        assertNotEquals(tele, null);
    }

    @Test
    public void teleportationCheck() {
        TileGrid tileGrid = new TileGrid("teleporterTestMap.txt");
        tileGrid.activateTiles();
        assertEquals(new Coordinate(1,2), tileGrid.getPlayerPosition(0));
    }

}