package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rotator implements GameObject{

    private GameObjectType gameObjectType;
    private Sprite sprite;

    public Rotator(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        Texture texture = new Texture(Gdx.files.internal("./assets/error.png"));
        this.sprite = new Sprite(texture);

        switch(this.gameObjectType){
            case ROTATOR_CLOCKWISE:
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/rotator/leftWheel32x32.png"));
                break;
            case ROTATOR_COUNTER_CLOCKWISE:
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/rotator/rightWheel32x32.png"));
                break;
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public Orientation getOrientation() {
        return null;
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
