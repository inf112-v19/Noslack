package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeleporterTest {

    @Test
    public void teleporterInitialized() {
        Teleporter tele = new Teleporter(1, 0,0);
        assertNotEquals(tele, null);
    }

    @Test
    public void teleporterCoupling() {
        TileGrid tileGrid = new TileGrid("teleporterTestMap.txt");
        Teleporter teleporter1 = (Teleporter) tileGrid.getTile(1,1).getGameObject(GameObjectType.TELEPORTER);
        Teleporter teleporter2 = (Teleporter) tileGrid.getTile(0,1).getGameObject(GameObjectType.TELEPORTER);
        assertEquals(teleporter2.getPosition(), teleporter1.getTeleportLocation());
        assertEquals(teleporter1.getPosition(), teleporter2.getTeleportLocation());
    }

    @Test
    public void teleportationCheck() {
        TileGrid tileGrid = new TileGrid("teleporterTestMap.txt");
        tileGrid.activateTiles();
        assertEquals(new Coordinate(0,1), tileGrid.getPlayerPosition(0));
    }

}