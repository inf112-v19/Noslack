package inf112.skeleton.app.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FastConveyor implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;

    public FastConveyor() {
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }

    public FastConveyor(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }


    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.FAST_CONVEYOR_NORTH;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/fastConveyorFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/fastConveyorFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/fastConveyorFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/fastConveyorFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.FAST_CONVEYOR_NORTH){
            return -1;
        } else {
            return 1;
        }
    }
}
