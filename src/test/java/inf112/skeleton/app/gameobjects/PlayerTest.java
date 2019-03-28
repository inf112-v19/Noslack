package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.Tile;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player = new Player(0);

    @Test
    public void PlayerInitialized(){
        assertNotEquals(player,null);
    }

    @Test
    public void PlayerStartsNorth() {
        //Player player = new Player();
        assertEquals(player.getOrientation(), Orientation.FACING_NORTH);
    }

    @Test
    public void PlayerStartsNineHP() {
        //Player player = new Player();
        assertEquals(player.getHealth(), 9);
    }

    @Test
    public void PlayerTakesDamage() {
        int oldHP = player.getHealth();
        player.receiveDamage();
        assertEquals(player.getHealth(), oldHP - 1);
    }

    @Test
    public void PlayerDrawsCards() {

        //player.drawCards();
    }

    @Test
    public void updateOrientationLeft() {
        Orientation o;
        Program p1 = Program.LEFT;
        o=player.getOrientation();
        player.updateOrientation(p1);
        assertEquals(o.rotate(p1),player.getOrientation());
    }
    @Test
    public void updateOrientationRight() {
        Orientation o;
        Program p2 = Program.U;
        o=player.getOrientation();
        player.updateOrientation(p2);
        assertEquals(o.rotate(p2),player.getOrientation());
    }
    @Test
    public void updateOrientationU() {
        Orientation o;
        Program p3 = Program.RIGHT;
        o=player.getOrientation();
        player.updateOrientation(p3);
        assertEquals(o.rotate(p3),player.getOrientation());
    }
    @Test
    public void updateOrientationMove() {
        Orientation o;
        Program p4 = Program.MOVE3;
        o=player.getOrientation();
        player.updateOrientation(p4);
        assertEquals(o,player.getOrientation());
    }

    @Test
    public void flagsSizeTest(){
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        Player player = tileGrid.getPlayer(0);
        assertEquals(2,player.getFlagsVisited().length);
    }

    @Test
    public void flagsAreRegistered() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");
        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
    }

    @Test
    public void flagsCounted() {
        TileGrid tileGrid = new TileGrid("playerTestFlagMap.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        tileGrid.getPlayer(0).setOrientation(Orientation.FACING_EAST);

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();
        assertTrue(tileGrid.getPlayer(0).getFlag(1));
        assertTrue(tileGrid.getPlayer(0).getFlag(2));
    }

    @Test
    public void flagsOutOfOrderNotCounted(){
        TileGrid tileGrid = new TileGrid("playerTestFlagMap2.txt");

        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        tileGrid.continueMove(0);
        tileGrid.activateTiles();

        assertFalse(tileGrid.getPlayer(0).getFlag(1));
        assertFalse(tileGrid.getPlayer(0).getFlag(2));
    }
}