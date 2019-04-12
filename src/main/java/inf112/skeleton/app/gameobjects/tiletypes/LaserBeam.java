package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class LaserBeam implements GameObject {

    private Orientation orientation;
    private Sprite sprite;
    private Coordinate coordinate;
    private boolean dual;
    private int playerNumber;

    public LaserBeam(Orientation orientation, boolean dual) {
        this.orientation = orientation;
        this.dual = dual;
        this.playerNumber = -1;
        evaluateSprite();
    }
    public LaserBeam(Orientation orientation, boolean dual, int playerNumber) {
        this.orientation = orientation;
        this.dual = dual;
        this.playerNumber = playerNumber;
        evaluateSprite();

    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture;
            if (dual) {
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/dualLaser32x32.png"));
            } else
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
            this.sprite = new Sprite(texture);
            this.sprite.setRotation(this.orientation.turnSprite());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error in LaserBeams evaluateSprite");
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * @return Is this a dual Laser
     */
    public boolean isDual(){
        return this.dual;
    }

    /**
     * Find out which player fired the laser
     * @return the number of the player who fired the laser
     */
    public int getPlayerNumber(){
        return playerNumber;
    }

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.LASER_BEAM;
    }

    @Override
    public int compareTo(Object other) {
        GameObjectType obj = ((GameObject) other).getGameObjectType();
        if(obj == GameObjectType.ROBOT || obj == GameObjectType.WALL || obj == GameObjectType.LASER_OUTLET || obj == GameObjectType.PUSHER){
            return -1;
        }else{
            return 1;
        }
    }
}
