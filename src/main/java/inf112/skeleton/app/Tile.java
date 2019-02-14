package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.ConveyorNorth;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Player;

import java.util.PriorityQueue;

public class Tile implements GameObject{

    private GameObjectType gameObjectType;
    private Texture texture;
    private Sprite sprite;
    private PriorityQueue<GameObject> objectsOnTile;

    public Tile(GameObjectType gameObjectType){
        objectsOnTile = new PriorityQueue<>();
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

    public void addObjectOnTile(GameObject newObjectOnTile){
        objectsOnTile.add(newObjectOnTile);
    }

    public void removeObjectFromTile(GameObject objectToRemove){
        objectsOnTile.remove(objectToRemove);
    }

    /**
     * Method that returns a PriorityQueue containing
     * the GameObjects on the tile.
     * @return  PriorityQueue of every GameObject on the tile
     */
    public PriorityQueue<GameObject> getObjectsOnTile(){
        return objectsOnTile;
    }

    public Boolean hasPlayer(Player player){
        return objectsOnTile.contains(player);
    }

    public Boolean hasConveyor(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.CONVEYOR_NORTH){
                return true;
            }
        }
        return false;
    }

    public ConveyorNorth getConveyor(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.CONVEYOR_NORTH){
                return (ConveyorNorth) gameObject;
            }
        }
        return new ConveyorNorth();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    @Override
    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public int compareTo(Object other) {
        return -1;
    }
}
