package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Flag implements GameObject {
    private Texture texture;
    private Sprite sprite;


    public Flag() {
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return null;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public void evaluateSprite() {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
