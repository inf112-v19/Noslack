package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ConveyorNorth implements GameObject{
    Texture texture;
    Sprite sprite;

    public ConveyorNorth(){
        this.texture = new Texture(Gdx.files.internal("/assets/gameObjects/conveyorNorth.png"));
        this.sprite = new Sprite(texture);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.CONVEYOR_NORTH;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
