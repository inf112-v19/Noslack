package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pusher implements GameObject {

    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;

    public Pusher() {
        this.orientation = Orientation.FACING_NORTH;
        this.type = GameObjectType.PUSHER;
        evaluateSprite();
    }

    public Pusher(Orientation orientation) {
        this.orientation = orientation;
        this.type = GameObjectType.PUSHER;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/pusher.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.setRotation(0);
                break;
            case FACING_NORTH:
                sprite.setRotation(0);
                break;
            case FACING_EAST:
                sprite.setRotation(90);
                break;
            case FACING_WEST:
                sprite.setRotation(270);
                break;
            case FACING_SOUTH:
                sprite.setRotation(180);
                break;
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public Orientation getOrientation(){
        return this.orientation;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
    }

    @Override
    public int compareTo(Object other) {
        if(((GameObject)other).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        } else {
            return 1;
        }
    }
}
