package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Conveyor implements GameObject{
    Texture texture;
    Sprite sprite;

    public Conveyor(){
        this.texture = new Texture(Gdx.files.internal("/assets/gameObjects/conveyor.png"));
        this.sprite = new Sprite(texture);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.CONVEYOR;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
