package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
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
    public void getSprite() {
        Flag f1 = new Flag();
        Flag f2 = new Flag(2);
        Sprite s1 = new Sprite(new Texture(Gdx.files.internal("./assets/gameObjects/flags/oneFlag32x32.png")));
        Sprite s2 = new Sprite(new Texture(Gdx.files.internal("./assets/gameObjects/flags/twoFlag32x32.png")));

        assertEquals(s1, f1.getSprite());
        assertEquals(s2, f2.getSprite());
    }

    @Test
    public void flagsAreRegistered() {
        TileGrid tileGrid = new TileGrid("playerTestMap.txt");
        int oldValue = tileGrid.getPlayer(0).getFlagsVisited().size();
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        assertEquals(tileGrid.getPlayer(0).getFlagsVisited().size(), oldValue+1);
    }

    @Test
    public void flagsOutOfOrderNotCounted() {
        TileGrid tileGrid = new TileGrid("playerTestMap.txt");
        int oldValue = tileGrid.getPlayer(0).getFlagsVisited().size();
        tileGrid.getPlayer(0).setPosition(new Coordinate(1, 2, Orientation.FACING_NORTH));
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        assertEquals(oldValue, tileGrid.getPlayer(0).getFlagsVisited().size());
    }
}
