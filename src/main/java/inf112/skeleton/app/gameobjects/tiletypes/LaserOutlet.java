package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class LaserOutlet implements GameObject {
    private Sprite sprite;
    private boolean dual;
    private Coordinate coordinate;

    public LaserOutlet(Orientation orientation, boolean dual) {
        this.coordinate = new Coordinate(0,0, orientation);
        this.dual = dual;
        evaluateSprite();
    }

    public LaserOutlet(Orientation orientation, boolean dual, int row, int column) {
        this.coordinate = new Coordinate(row, column, orientation);
        this.dual = dual;
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.LASER_OUTLET;
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture;
            if(dual){
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/dualLaserOutlet32x32.png"));
            }
            else {
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laserOutlet32x32.png"));
            }
            this.sprite = new Sprite(texture);

            sprite.setRotation(this.coordinate.getOrientation().turnSprite());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in LaserOutlets evaluateSprite");
        }
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @return If the laser is a dual output
     */
    public boolean isDual() {
        return dual;
    }

    /**
     * Get the Coordinate of the outlet
     * @return The coordinate of the Outlet which contains the orientation.
     */
    public Coordinate getPosition(){
        return this.coordinate;
    }

    @Override
    public Orientation getOrientation() {
        return this.coordinate.getOrientation();
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
