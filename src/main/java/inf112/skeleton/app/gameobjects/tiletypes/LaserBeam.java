package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class LaserBeam implements GameObject {

    private Orientation orientation;
    private Sprite sprite;
    private Coordinate coordinate;
    private boolean dual;

    public LaserBeam(Orientation orientation, boolean dual) {
        this.orientation = orientation;
        this.dual = dual;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        Texture texture;
        if (dual) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
        }
        else
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
        this.sprite = new Sprite(texture);
        if(this.orientation == Orientation.HORIZONTAL){
            this.sprite.setRotation(90);
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * @return Is this a dual Laser
     */
    public boolean isDual(){
        return this.dual;
    }

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.LASER_BEAM;
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
