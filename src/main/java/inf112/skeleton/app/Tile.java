package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.*;
import inf112.skeleton.app.gameobjects.Robots.*;
import inf112.skeleton.app.gameobjects.tiletypes.LaserBeam;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Tile implements GameObject{

    private GameObjectType gameObjectType;
    private Sprite sprite;
    private PriorityQueue<GameObject> objectsOnTile;
    private ArrayList<Orientation> blocked;

    public Tile(GameObjectType gameObjectType){
        this.objectsOnTile = new PriorityQueue<>();
        this.gameObjectType = gameObjectType;
        this.blocked = new ArrayList<>();
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
        this.objectsOnTile.add(newObjectOnTile);
        tileBlocked(newObjectOnTile);
    }

    /**
     * Removes an Object from the Tile
     * @param objectToRemove Object to be removed
     */
    public void removeObjectFromTile(GameObject objectToRemove){
        this.objectsOnTile.remove(objectToRemove);
    }

    public void removeRobotLaserFromTile(int robotNumber){
        for(GameObject object : objectsOnTile){
            if(object.getGameObjectType().equals(GameObjectType.LASER_BEAM)){
                if(((LaserBeam)object).getRobotNumber()==robotNumber){
                    removeObjectFromTile(object);
                }
            }
        }
    }

    /**
     * Method that returns a PriorityQueue containing
     * the GameObjects on the tile.
     * @return  PriorityQueue of every GameObject on the tile
     */
    public PriorityQueue<GameObject> getObjectsOnTile(){
        return this.objectsOnTile;
    }

    public Boolean hasRobot(IRobot robot){
        return this.objectsOnTile.contains(robot);
    }

    public Boolean hasGameObject(GameObjectType objectType){
        for(GameObject gameObject : this.objectsOnTile){
            if(gameObject.getGameObjectType().equals(objectType)){
                return true;
            }
        }
        return false;
    }
    public Boolean hasGameObject(GameObject object){
        return objectsOnTile.contains(object);
    }

    public GameObject getGameObject(GameObjectType objectType){
        for(GameObject gameObject : this.objectsOnTile){
            if(gameObject.getGameObjectType().equals(objectType)){
                return gameObject;
            }
        }
        return null;
    }

    /**
     * Finds out which orientations on the tile are blocked
     * TODO Add new objects that kan block robot
     * @param gameObject Object that was just added to tile
     */
    private void tileBlocked(GameObject gameObject){
        switch (gameObject.getGameObjectType()){
            case WALL:
                this.blocked.add(gameObject.getOrientation());
                break;
            case LASER_OUTLET:
                this.blocked.add(gameObject.getOrientation().opposite());
                break;
            case PUSHER:
                this.blocked.add(gameObject.getOrientation().opposite());
                break;
        }
    }

    /**
     * Finds out if the robots path is blocked by object.
     * @param robot robot in question
     * @return if Robot can move
     */
    public boolean robotPathBlocked(IRobot robot, Orientation directionOfMove){
        if(this.objectsOnTile.contains(robot)){
                return this.blocked.contains(directionOfMove);
        }
        else{
            return this.blocked.contains(directionOfMove.opposite());
        }
    }

    /**
     * Finds out if an orientation is blocked
     * @param orientation The orientation in question
     * @return If the orientation is blocked
     */
    public boolean orientationBlocked(Orientation orientation){
        return this.blocked.contains(orientation);
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
