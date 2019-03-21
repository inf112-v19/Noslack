package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface GameObject extends Comparable {

    /**
     * Get method for enum GameObjectType
     * @return GameObjects GameObjectType
     */
    GameObjectType getGameObjectType();

    /**
     * Returns the sprite
     * @return GameObjects sprite
     */
    Sprite getSprite();

    /**
     * Evaluates sprite for direction or special behaviour and creates texture and sprite
     */
    void evaluateSprite();

}
