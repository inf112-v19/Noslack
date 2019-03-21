package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pusher implements GameObject {

    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;

    public Pusher(Orientation orientation, GameObjectType type) {
        this.orientation = orientation;
        this.type = type;
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
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/pusher.png"));

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
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.NORTH_PUSHER){
            return -1;
        } else {
            return 1;
        }
    }
}
