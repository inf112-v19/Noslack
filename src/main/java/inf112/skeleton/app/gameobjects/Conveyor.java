package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;

public class Conveyor implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;

    public Conveyor(){
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }

    public Conveyor(Orientation orientation){
        this.orientation = orientation;
        evaluateSprite();
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

        texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/conveyor.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.rotate(0);
                break;
            case FACING_NORTH:
                sprite.rotate(0);
                this.type = GameObjectType.CONVEYOR_NORTH;
                break;
            case FACING_EAST:
                sprite.rotate(90);
                this.type = GameObjectType.CONVEYOR_EAST;
                break;
            case FACING_WEST:
                sprite.rotate(270);
                this.type = GameObjectType.CONVEYOR_WEST;
                break;
            case FACING_SOUTH:
                sprite.rotate(180);
                this.type = GameObjectType.CONVEYOR_SOUTH;
                break;
        }
    }

    public Orientation getOrientation(){
        return this.orientation;
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
