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
        this(Orientation.FACING_NORTH,false,0);
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
                if (this.turn == 1) {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDashTurn32x32.png"));
                }
                //Fast colored T-shaped intersection(2 ways in, one way out)
                else if(this.turn == 2){
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDashT32x32.png"));
                }
                //Fast colored X-shaped intersection(3 ways in, one way out)
                else if(this.turn == 3){
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDashX32x32.png"));
                }
                else {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/twoDash32x32.png"));
                }
            }
            else {
                if (this.turn == 1) {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDashTurn32x32.png"));
                }
                //T-shaped intersection(2 ways in, one way out)
                else if(this.turn == 2){
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDashT32x32.png"));
                }
                //X-shaped intersection(3 ways in, one way out)
                else if(this.turn == 3){
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDashX32x32.png"));
                }
                else {
                    texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/oneDash32x32.png"));
                }
            }
            this.sprite = new Sprite(texture);
            if (turn < 0) {
                sprite.flip(true, false);
            }
            sprite.setRotation(this.orientation.turnSprite());
        } catch(Exception e) {
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
        if(((GameObject) other).getGameObjectType() == GameObjectType.ROBOT){
            return -1;
        }else{
            return 1;
        }
    }
}
