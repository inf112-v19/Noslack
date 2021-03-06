package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class Pusher implements GameObject {

    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;
    private boolean evenPusher;

    public Pusher() {
        this(Orientation.FACING_NORTH);
    }

    public Pusher(Orientation orientation){
        this.orientation = orientation;
        this.type = GameObjectType.PUSHER;
        this.evenPusher = true;
        evaluateSprite();
    }

    public Pusher(Orientation orientation, boolean evenPusher) {
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
            sprite.setRotation(this.orientation.turnSprite());
        }catch(Exception e){
        }
    }
    public boolean isEven(){
        return this.evenPusher;
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
        if(((GameObject)o).getGameObjectType() == GameObjectType.ROBOT){
            return -1;
        } else {
            return 1;
        }
    }
}
