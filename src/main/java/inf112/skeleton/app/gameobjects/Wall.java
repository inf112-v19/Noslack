package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Wall implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;

    public Wall(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/wallFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/wallFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/wallFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/wallFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.NORTH_WALL){
            return -1;
        } else {
            return 1;
        }
    }

}
