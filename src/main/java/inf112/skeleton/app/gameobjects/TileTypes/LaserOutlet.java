package inf112.skeleton.app.gameobjects.TileTypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class LaserOutlet implements GameObject {
    private Sprite sprite;
    private Orientation orientation;
    private boolean dual;

    public LaserOutlet() {
        this.orientation = Orientation.FACING_NORTH;
        this.dual = false;
        evaluateSprite();
    }
    public LaserOutlet(Orientation orientation, boolean dual) {
        this.orientation = orientation;
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
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
            }
            else {
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
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
                    sprite.setRotation(270);
                    break;
                case FACING_WEST:
                    sprite.setRotation(90);
                    break;
                case FACING_SOUTH:
                    sprite.setRotation(180);
                    break;
            }
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

    @Override
    public Orientation getOrientation() {
        return this.orientation;
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
