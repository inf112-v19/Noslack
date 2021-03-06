package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class Wall implements GameObject {
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type= GameObjectType.WALL;

    public Wall() {
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }
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
        try{
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/oneWall32x32.png"));
            this.sprite = new Sprite(texture);
            sprite.setRotation(this.orientation.turnSprite());
        } catch(Exception e){
        }

    }

    @Override
    public GameObjectType getGameObjectType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.WALL){
            return -1;
        } else {
            return 1;
        }
    }

}
