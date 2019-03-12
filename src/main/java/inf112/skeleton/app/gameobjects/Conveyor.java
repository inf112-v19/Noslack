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
    private int rotating;

    public Conveyor(){
        this.orientation = Orientation.FACING_NORTH;
        this.rotating = 0;
        this.fast = false;
        evaluateSprite();
    }

    public Conveyor(Orientation orientation, boolean fast, int rotating){
        this.orientation = orientation;
        this.rotating = rotating;
        this.fast = fast;
        evaluateSprite();
    }

    /**
     * Boolean check for fast conveyor
     * @return Conveyors Boolean
     */
    public boolean isFast(){
        return fast;
    }

    @Override
    public GameObjectType getGameObjectType() {
        if(fast){
            if(rotating > 0){
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.FAST_RTURN_CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.FAST_RTURN_CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.FAST_RTURN_CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.FAST_RTURN_CONVEYOR_WEST;
                    default:
                        return GameObjectType.FAST_RTURN_CONVEYOR_NORTH;
                }
            }
            else if(rotating < 0){
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.FAST_LTURN_CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.FAST_LTURN_CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.FAST_LTURN_CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.FAST_LTURN_CONVEYOR_WEST;
                    default:
                        return GameObjectType.FAST_LTURN_CONVEYOR_NORTH;
                }
            }
            else{
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.FAST_CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.FAST_CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.FAST_CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.FAST_CONVEYOR_WEST;
                    default:
                        return GameObjectType.FAST_CONVEYOR_NORTH;
                }
            }

        }
        else{
            if(rotating > 0){
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.RTURN_CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.RTURN_CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.RTURN_CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.RTURN_CONVEYOR_WEST;
                    default:
                        return GameObjectType.RTURN_CONVEYOR_NORTH;
                }
            }
            else if(rotating < 0){
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.LTURN_CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.LTURN_CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.LTURN_CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.LTURN_CONVEYOR_WEST;
                    default:
                        return GameObjectType.LTURN_CONVEYOR_NORTH;
                }
            }
            else{
                switch(orientation){
                    case FACING_NORTH:
                        return GameObjectType.CONVEYOR_NORTH;
                    case FACING_EAST:
                        return GameObjectType.CONVEYOR_EAST;
                    case FACING_SOUTH:
                        return GameObjectType.CONVEYOR_SOUTH;
                    case FACING_WEST:
                        return GameObjectType.CONVEYOR_WEST;
                    default:
                        return GameObjectType.CONVEYOR_NORTH;
                }
            }
        }


    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Evaluates if conveyor is fast.
     */
    @Override
    public void evaluateSprite() {

        Texture texture;
        if(this.fast){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDash32x32.png"));
            if(rotating != 0){
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDashTurn32x32.png"));
            }
        }
        else{
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDash32x32.png"));
            if(rotating != 0){
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDashTurn32x32.png"));
            }
        }

        this.sprite = new Sprite(texture);
        if(rotating < 0){
            sprite.flip(true,false);
        }

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

    /**
     * Get method for orientation
     * @return Conveyors Orientation
     */
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
