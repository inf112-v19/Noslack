package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * Declared in map as one of 'w' / 'a' / 's' / 'd'
 */
public class Wall implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type;

    public Wall(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/oneWall32x32.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.setRotation(0);
                break;
            case FACING_NORTH:
                sprite.setRotation(0);
                this.type = GameObjectType.NORTH_WALL;
                break;
            case FACING_EAST:
                sprite.setRotation(90);
                this.type = GameObjectType.EAST_WALL;
                break;
            case FACING_WEST:
                sprite.setRotation(270);
                this.type = GameObjectType.WEST_WALL;
                break;
            case FACING_SOUTH:
                sprite.setRotation(180);
                this.type = GameObjectType.SOUTH_WALL;
                break;
        }
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.NORTH_WALL){
            return -1;
        } else {
            return 1;
        }
    }

}
