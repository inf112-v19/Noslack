package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface GameObject extends Comparable {

    GameObjectType getGameObjectType();

    Sprite getSprite();

    void evaluateSprite();

}
