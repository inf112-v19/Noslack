package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private int health;
    private Orientation orientation;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(){
        this.health = 9;
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }

    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(Orientation orientation){
        this.health = 9;
        this.orientation = orientation;
        evaluateSprite();

    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public void updateOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    @Override
    public int compareTo(Object other) {
        return 1;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.PLAYER;
    }

    /**
     * Method that evaluates the player's sprite based on the player's orientation.
     */
    public void evaluateSprite(){
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    /**
     * Removes one health from the player.
     */
    public void recieveDamage(){
        this.health--;
    }

    /**
     * Replenishes the players health by 1, up to a maximum of 9 (no damage).
     */
    public void repair(){
        if(health < 9) {
            this.health++;
        }
    }

}
