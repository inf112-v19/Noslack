package inf112.skeleton.app;

import inf112.skeleton.app.gameobjects.*;
import inf112.skeleton.app.gameobjects.Robots.Player;
import inf112.skeleton.app.gameobjects.tiletypes.LaserOutlet;
import inf112.skeleton.app.gameobjects.tiletypes.Pusher;
import inf112.skeleton.app.gameobjects.tiletypes.Wall;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    @Test
    public void hasPlayer() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0);
        tile.addObjectOnTile(player);
        assertTrue(tile.hasPlayer(player));
    }

    @Test
    public void playerPathBlockedByWallOnTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0);
        tile.addObjectOnTile(new Wall());
        tile.addObjectOnTile(player);
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
    @Test
    public void playerPathBlockedByWallOnNextTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0);
        tile.addObjectOnTile(new Wall(Orientation.FACING_SOUTH));
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
    @Test
    public void playerPathBlockedByPusherOnTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0);
        tile.addObjectOnTile(new Pusher(Orientation.FACING_SOUTH));
        tile.addObjectOnTile(player);
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
    @Test
    public void playerPathBlockedByPusherOnNextTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0,Orientation.FACING_EAST);
        tile.addObjectOnTile(new Pusher(Orientation.FACING_EAST));
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
    @Test
    public void playerPathBlockedByLaserOutletOnTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0);
        tile.addObjectOnTile(new LaserOutlet(Orientation.FACING_SOUTH,false));
        tile.addObjectOnTile(player);
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
    @Test
    public void playerPathBlockedByLaserOutletOnNextTile() {
        Tile tile = new Tile(GameObjectType.STANDARD_TILE);
        Player player = new Player(0,Orientation.FACING_EAST);
        tile.addObjectOnTile(new LaserOutlet(Orientation.FACING_EAST,false));
        assertTrue(tile.playerPathBlocked(player, player.getOrientation()));
    }
}