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
    private boolean dual;
    private int robotNumber;

    public LaserBeam(Orientation orientation, boolean dual) {
        this.orientation = orientation;
        this.dual = dual;
        this.robotNumber = -1;
        evaluateSprite();
    }
    public LaserBeam(Orientation orientation, boolean dual, int robotNumber) {
        this.orientation = orientation;
        this.dual = dual;
        this.robotNumber = robotNumber;
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
     * Find out which robot fired the laser
     * @return the number of the robot who fired the laser
     */
    public int getRobotNumber(){
        return robotNumber;
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
