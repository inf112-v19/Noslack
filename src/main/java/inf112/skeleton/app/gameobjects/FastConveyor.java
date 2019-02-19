package inf112.skeleton.app.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FastConveyor implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;


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
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/fastConveyor.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.rotate(0);
                break;
            case FACING_NORTH:
                sprite.rotate(0);
                this.type = GameObjectType.FAST_CONVEYOR_NORTH;
                break;
            case FACING_EAST:
                sprite.rotate(90);
                this.type = GameObjectType.FAST_CONVEYOR_EAST;
                break;
            case FACING_WEST:
                sprite.rotate(270);
                this.type = GameObjectType.FAST_CONVEYOR_WEST;
                break;
            case FACING_SOUTH:
                sprite.rotate(180);
                this.type = GameObjectType.FAST_CONVEYOR_SOUTH;
                break;
        }
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
