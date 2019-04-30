package inf112.skeleton.app.gameobjects.robots;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Orientation;
import inf112.skeleton.app.gameobjects.Robots.Player;
import org.junit.Assert;
import org.junit.Test;

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
    public void PlayerTakesDamage2() {
        int oldHP = player.getHealth();
        assertTrue(player.receiveDamage(9));
        assertEquals(player.getHealth(), oldHP);
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
}