package inf112.skeleton.app.GameObjectTest;

import inf112.skeleton.app.gameobjects.Orientation;
import inf112.skeleton.app.gameobjects.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {

    private Player player = new Player(0);


    @Before
    public void setup() {
        player = new Player(1);

    }
    @Test
    public void PlayerInitialized(){
        assertNotEquals(null,player);
    }

    @Test
    public void PlayerStartsNorth() {
        //Player player = new Player();
        assertEquals(Orientation.FACING_NORTH,player.getOrientation());
    }
    @Test
    public void PlayerRotatesRight() {
        //Player player = new Player();
        assertEquals(Orientation.FACING_NORTH,player.getOrientation());
    }

    @Test
    public void PlayerStartsNineHP() {
        //Player player = new Player();
        assertEquals(9, player.getHealth());
    }

    @Test
    public void PlayerTakesDamage() {
        int oldHP = player.getHealth();
        player.receiveDamage();
        assertEquals(oldHP - 1,player.getHealth() );
    }

    @Test
    public void PlayerDrawsCards() {

        //player.drawCards();
    }

}
