package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LaserBeam implements GameObject{

    private Orientation orientation;
    private Sprite sprite;

    public LaserBeam(Orientation orientation) {
        this.orientation = orientation;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
        this.sprite = new Sprite(texture);
        if(this.orientation == Orientation.HORIZONTAL){
            this.sprite.setRotation(90);
        }
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.LASER_BEAM;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
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
