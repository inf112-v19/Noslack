package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface GameObject {

    GameObjectType getGameObjectType();

    Sprite getSprite();

}
