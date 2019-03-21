package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pusher implements GameObject {

    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;



    public Pusher(Orientation orientation) {
        this.orientation = orientation;
        this.type = GameObjectType.PUSHER;
    }


    @Override
    public GameObjectType getGameObjectType() {
        return type;
    }

    @Override
    public void evaluateSprite() {
        Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/pusher/pusher24-32x32.png"));

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
        return sprite;
    }
    @Override
    public Orientation getOrientation(){
        return this.orientation;
    }
    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.PUSHER){
            return -1;
        } else {
            return 1;
        }
    }
}
