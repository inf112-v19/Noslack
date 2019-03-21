package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pusher implements GameObject {

    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;

    public Pusher(Orientation orientation) {
        this.orientation = orientation;
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return type;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/pusher/pusher24-32x32.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.setRotation(0);
                break;
            case FACING_NORTH:
                sprite.setRotation(0);
                this.type = GameObjectType.NORTH_PUSHER;
                break;
            case FACING_EAST:
                sprite.setRotation(90);
                this.type = GameObjectType.EAST_PUSHER;
                break;
            case FACING_WEST:
                sprite.setRotation(270);
                this.type = GameObjectType.WEST_PUSHER;
                break;
            case FACING_SOUTH:
                sprite.setRotation(180);
                this.type = GameObjectType.SOUTH_PUSHER;
                break;
        }
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
