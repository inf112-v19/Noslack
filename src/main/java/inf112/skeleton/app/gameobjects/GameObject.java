package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface GameObject extends Comparable {

    /**
     * Evaluates sprite for direction or special behaviour and creates texture and sprite
     */
    void evaluateSprite();

    /**
     * Returns the sprite
     * @return GameObjects sprite
     */
    Sprite getSprite();

    /**
     * Get the Orientation of the object
     * @return Objects orientation.
     */
    Orientation getOrientation();

    /**
     * Get method for enum GameObjectType
     * @return GameObjects GameObjectType
     */
    GameObjectType getGameObjectType();
}
