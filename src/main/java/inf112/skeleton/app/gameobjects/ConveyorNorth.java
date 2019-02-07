package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;

public class ConveyorNorth implements GameObject {
    private Sprite sprite;

    public ConveyorNorth(){
        Texture texture = new Texture(Gdx.files.internal("/assets/gameObjects/conveyorNorth.png"));
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

    @Override
    public void evaluateSprite() {

    }

    @Override
    public int compareTo(Object other) {
        if(((GameObject) other).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        }else{
            return 1;
        }
    }
}
