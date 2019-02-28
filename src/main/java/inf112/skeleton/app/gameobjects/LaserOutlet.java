package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LaserOutlet implements GameObject {
        private Texture texture;
        private Sprite sprite;
    private Orientation orientation;

    public LaserOutlet(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.NORTH_LASER_OUTLET;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/laserOutletFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/laserOutletFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/laserOutletFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/laserOutletFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.NORTH_LASER_OUTLET){
            return -1;
        } else {
            return 1;
        }
    }
}
