package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;

public class Conveyor implements GameObject {
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;
    private boolean fast;


    public Conveyor(){
        this.orientation = Orientation.FACING_NORTH;
        this.fast = false;
        evaluateSprite();
    }
    public Conveyor(boolean fast){
        this.orientation = Orientation.FACING_NORTH;
        this.fast = fast;
        evaluateSprite();

    }
    public Conveyor(Orientation orientation){
        this.orientation = orientation;
        this.fast = false;
        evaluateSprite();
    }

    public Conveyor(Orientation orientation, boolean fast){
        this.orientation = orientation;
        this.fast = fast;
        evaluateSprite();
    }
    public boolean isFast(){
        return fast;
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

        Texture texture;
        if(this.fast){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDash32x32.png"));
        }
        else{
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDash32x32.png"));
        }

        this.sprite = new Sprite(texture);

        if(fast){
            switch (orientation) {
                default:
                    sprite.setRotation(0);
                    break;
                case FACING_NORTH:
                    sprite.setRotation(0);
                    this.type = GameObjectType.FAST_CONVEYOR_NORTH;
                    break;
                case FACING_EAST:
                    sprite.setRotation(270);
                    this.type = GameObjectType.FAST_CONVEYOR_EAST;
                    break;
                case FACING_SOUTH:
                    sprite.setRotation(180);
                    this.type = GameObjectType.FAST_CONVEYOR_SOUTH;
                    break;
                case FACING_WEST:
                    sprite.setRotation(90);
                    this.type = GameObjectType.FAST_CONVEYOR_WEST;
                    break;
            }
        }
        else {
            switch (orientation) {
                default:
                    sprite.setRotation(0);
                    break;
                case FACING_NORTH:
                    sprite.setRotation(0);
                    this.type = GameObjectType.CONVEYOR_NORTH;
                    break;
                case FACING_EAST:
                    sprite.setRotation(270);
                    this.type = GameObjectType.CONVEYOR_EAST;
                    break;
                case FACING_SOUTH:
                    sprite.setRotation(180);
                    this.type = GameObjectType.CONVEYOR_SOUTH;
                    break;
                case FACING_WEST:
                    sprite.setRotation(90);
                    this.type = GameObjectType.CONVEYOR_WEST;
                    break;
            }
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
