package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LaserOutlet implements GameObject {
    private Sprite sprite;
    private Orientation orientation;

    public LaserOutlet() {
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }
    public LaserOutlet(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.LASER_OUTLET;
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/laser/laser32x32.png"));
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

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.LASER_OUTLET){
            return -1;
        } else {
            return 1;
        }
    }
}
