package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.*;

import java.util.PriorityQueue;

public class Tile implements GameObject{

    private GameObjectType gameObjectType;
    private Sprite sprite;
    private PriorityQueue<GameObject> objectsOnTile;

    public Tile(GameObjectType gameObjectType){
        this.objectsOnTile = new PriorityQueue<>();
        this.gameObjectType = gameObjectType;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite(){
        try {
            Texture texture;
            if (gameObjectType == GameObjectType.STANDARD_TILE) {
                texture = new Texture(Gdx.files.internal("./assets/tiles/standardTile32x32.png"));
            }
            else {
                texture = new Texture(Gdx.files.internal("./assets/error.png"));
            }
            sprite = new Sprite(texture);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Tile evaluateSprite");
        }
    }

    @Override
    public Sprite getSprite(){
        return this.sprite;
    }

    @Override
    public Orientation getOrientation(){
        return null;
    }

    /**
     * Adds an object on the Tile
     * @param newObjectOnTile Object to be added on the Tile
     */
    public void addObjectOnTile(GameObject newObjectOnTile){
        objectsOnTile.add(newObjectOnTile);
    }

    /**
     * Removes an Object from the Tile
     * @param objectToRemove Object to be removed
     */
    public void removeObjectFromTile(GameObject objectToRemove){
        objectsOnTile.remove(objectToRemove);
    }

    /**
     * Method that returns a PriorityQueue containing
     * the GameObjects on the tile.
     * @return  PriorityQueue of every GameObject on the tile
     */
    public PriorityQueue<GameObject> getObjectsOnTile(){
        return this.objectsOnTile;
    }

    public Boolean hasPlayer(Player player){
        return this.objectsOnTile.contains(player);
    }

    public Boolean hasFlag(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.FLAG){
                return true;
            }
        }
        return false;
    }
    public Flag getFlag(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.FLAG){
                return (Flag) gameObject;
            }
        }
        return null;
    }
    public Boolean hasWall(){
        for(GameObject gameObject : objectsOnTile){
            if( gameObject.getGameObjectType().equals(GameObjectType.WALL)) {
                return true;
            }
        }
        return false;
    }
    public Wall getWall(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.WALL){
                return (Wall) gameObject;
            }
        }
        return null;
    }

    public Boolean hasConveyor(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.CONVEYOR ||
                    gameObject.getGameObjectType() == GameObjectType.F_CONVEYOR ){
                return true;
            }
        }
        return false;
    }

    public Conveyor getConveyor(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.CONVEYOR){
                return (Conveyor) gameObject;
            }
        }
        return null;
    }

    public Boolean hasRepairStation(){
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.REPAIR_STATION){
                return true;
            }
        }
        return false;
    }

    public Boolean hasHole() {
        for(GameObject gameObject : objectsOnTile){
            if(gameObject.getGameObjectType() == GameObjectType.HOLE){
                return true;
            }
        }
        return false;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    @Override
    public int compareTo(Object other) {
        return -1;
    }
}
