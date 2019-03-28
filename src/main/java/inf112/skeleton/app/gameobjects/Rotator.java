package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rotator implements GameObject{

    private Sprite sprite;
    private GameObjectType gameObjectType;
    private boolean counterClockwise;

    public Rotator(boolean counterClockwise) {
        this.counterClockwise = counterClockwise;
        evaluateGameObjectType();
        evaluateSprite();
    }

    public void evaluateGameObjectType(){
        this.gameObjectType = this.counterClockwise ?
                GameObjectType.ROTATOR_COUNTER_CLOCKWISE : GameObjectType.ROTATOR_CLOCKWISE;
    }

    @Override
    public void evaluateSprite() {
        try {

            Texture texture = this.counterClockwise ?
                    new Texture(Gdx.files.internal("./assets/gameObjects/rotator/leftWheel32x32.png"))
                    : new Texture(Gdx.files.internal("./assets/gameObjects/rotator/rightWheel32x32.png"));

            this.sprite = new Sprite(texture);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in Rotator evaluateSprite()");
        }
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