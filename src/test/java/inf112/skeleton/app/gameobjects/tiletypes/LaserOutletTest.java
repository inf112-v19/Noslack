package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.Tile;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;
import org.junit.Test;

import static org.junit.Assert.*;

public class LaserOutletTest {

    @Test
    public void takesDamageSingle() {
        TileGrid tileGrid = new TileGrid("laserOutletTestMap.txt");
        int oldHP = tileGrid.getPlayer(0).getHealth();
        tileGrid.activateTiles();
        int newHP = tileGrid.getPlayer(0).getHealth();
        assertEquals(oldHP-1, newHP);
    }

    @Test
    public void takesDamageDouble() {
        TileGrid tileGrid = new TileGrid("laserOutletTestMap2.txt");
        int oldHP = tileGrid.getPlayer(0).getHealth();
        tileGrid.activateTiles();
        int newHP = tileGrid.getPlayer(0).getHealth();
        assertEquals(oldHP-2, newHP);
    }

    @Test
    public void checkOrientation() {
        TileGrid tileGrid = new TileGrid("laserOutletTestMap.txt");
        Tile tile = tileGrid.getTile(1,1);
        assertEquals(Orientation.FACING_NORTH, tile.getGameObject(GameObjectType.LASER_OUTLET).getOrientation());
    }

}