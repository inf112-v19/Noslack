package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.Test;

import static org.junit.Assert.*;

public class HoleTest {

    @Test
    public void holeInitialized() {
        Hole hole = new Hole();
        assertNotEquals(hole, null);
    }

    @Test
    public void checkSprite() {
        Hole hole = new Hole();
        Sprite s1 = new Sprite(new Texture(Gdx.files.internal("./assets/gameObjects/blackHole/blackHole32x32.png")));

        assertEquals(s1, hole.getSprite());
    }

}