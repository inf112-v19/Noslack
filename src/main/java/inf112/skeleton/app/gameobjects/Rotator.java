package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rotator implements GameObject{

    private GameObjectType gameObjectType;
    private Sprite sprite;
    private boolean counterClockwise;

    public Rotator(boolean counterClockwise) {
        this.counterClockwise = counterClockwise;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        Texture texture;

        if(!counterClockwise) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/rotator/leftWheel32x32.png"));
            this.gameObjectType = GameObjectType.ROTATOR_CLOCKWISE;
        }
        else {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/rotator/rightWheel32x32.png"));
            this.gameObjectType = GameObjectType.ROTATOR_COUNTER_CLOCKWISE;
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    public boolean isCounterClockwise() {
        return counterClockwise;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
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
