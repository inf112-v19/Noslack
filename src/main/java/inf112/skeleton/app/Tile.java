package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Tile {

    private ArrayList<GameObject> objectsOnTile;
    private GameObjectType gameObjectType;
    private Texture texture;
    private Sprite sprite;

    public Tile(GameObjectType gameObjectType){
        objectsOnTile = new ArrayList<GameObject>();
        this.gameObjectType = gameObjectType;
        evaluateSprite();
    }


    /**
     * Evalautes what sprite should be loaded for
     * this tile, based on what the tile's
     * GameObjectType is.
     */
    public void evaluateSprite(){
        switch(gameObjectType){
            case STANDARD_TILE:
                texture = new Texture(Gdx.files.internal("./assets/tiles/standardTile.png"));
                sprite = new Sprite(texture);
                break;
            default:
                texture = new Texture(Gdx.files.internal("./assets/error.png"));
                sprite = new Sprite(texture);
                break;
        }
    }

    public ArrayList<GameObject> getObjectsOnTile(){
        return objectsOnTile;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
