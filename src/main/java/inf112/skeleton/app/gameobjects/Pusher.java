package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pusher implements GameObject {

    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;
    private boolean evenPusher;

    public Pusher() {
        this.orientation = Orientation.FACING_NORTH;
        this.type = GameObjectType.PUSHER;
        this.evenPusher = true;
        evaluateSprite();
    }

    public Pusher(Orientation orientation){
        this.orientation = orientation;
        this.type = GameObjectType.PUSHER;
        this.evenPusher = true;
        evaluateSprite();
    }

    public Pusher(Orientation orientation,boolean evenPusher) {
        this.orientation = orientation;
        this.type = GameObjectType.PUSHER;
        this.evenPusher = evenPusher;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture;
            if (this.evenPusher) {
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/pusher/pusherEven32x32.png"));
            } else {
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/pusher/pusherOdd32x32.png"));
            }

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
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in Pusher evaluateSprite()");
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
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        } else {
            return 1;
        }
    }
}
