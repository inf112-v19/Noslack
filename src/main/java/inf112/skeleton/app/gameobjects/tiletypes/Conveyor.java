package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class Conveyor implements GameObject {
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;
    private boolean fast;
    private int turn;

    public Conveyor(){
        this.orientation = Orientation.FACING_NORTH;
        this.turn = 0;
        this.fast = false;
        this.type = GameObjectType.CONVEYOR;
        evaluateSprite();
    }

    public Conveyor(Orientation orientation, boolean fast, int turn){
        this.orientation = orientation;
        this.turn = turn;
        this.fast = fast;
        this.type = GameObjectType.CONVEYOR;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture;
            if (this.fast) {
                if (turn != 0) {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDashTurn32x32.png"));
                } else {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDash32x32.png"));
                }
            } else {
                if (turn != 0) {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDashTurn32x32.png"));
                } else {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDash32x32.png"));
                }
            }
            this.sprite = new Sprite(texture);
            if (turn < 0) {
                sprite.flip(true, false);
            }
            sprite.setRotation(this.orientation.turnSprite());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error in Pusher evaluateSprite()");
        }
    }
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
    /**
     * Get method for orientation
     * @return Conveyors Orientation
     */
    public Orientation getOrientation(){
        return this.orientation;
    }
    /**
     * Boolean check for fast conveyor
     * @return Conveyors Boolean
     */
    public boolean isFast(){
        return this.fast;
    }

    /**
     * Get value of the turn of this conveyor
     * @return integer value of turn
     */
    public int getTurn(){
        return this.turn;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
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
