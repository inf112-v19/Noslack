package inf112.skeleton.app.gameobjects.tiletypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Tile;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class Teleporter implements GameObject {

    private Coordinate teleportLocation;
    private Sprite sprite;
    private GameObjectType type;
    private int teleporterNr;
    private Coordinate position;
    private boolean coupled;

    public Teleporter(int teleporterNr, int row, int column){
        this.type = GameObjectType.TELEPORTER;
        this.teleporterNr = teleporterNr;
        this.position = new Coordinate(row, column);
        this.coupled =false;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        try{
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/teleporterTest32x32.png"));
            this.sprite = new Sprite(texture);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in Teleporter evaluateSprite");
        }
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * @return Is the teleporter coupled.
     */
    public boolean isCoupled(){
        return this.coupled;
    }

    /**
     * Set the location of where to teleport the player to
     * @param teleportLocation Other teleport location
     */
    public void setTeleportLocation(Coordinate teleportLocation) {
        this.teleportLocation = teleportLocation;
        this.coupled = true;
    }

    /**
     * @return The location of where to teleport the
     */
    public Coordinate getTeleportLocation() {
        return teleportLocation;
    }

    /**
     * @return Get position of Teleporter
     */
    public Coordinate getPosition(){
        return this.position;
    }

    public int getTeleporterNr() {
        return teleporterNr;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
