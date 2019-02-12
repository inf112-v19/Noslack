package inf112.skeleton.app.GameObjectTest;

import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.cards.ProgramDeck;
import inf112.skeleton.app.gameobjects.Orientation;
import inf112.skeleton.app.gameobjects.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {

    private Player player = new Player();

    /*
    @Before
    public void setup() {
        Player player = new Player();

    }
    */

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
        player.recieveDamage();
        assertEquals(player.getHealth(), oldHP - 1);
    }

    @Test
    public void PlayerDrawsCards() {

        //player.drawCards();
    }

}
