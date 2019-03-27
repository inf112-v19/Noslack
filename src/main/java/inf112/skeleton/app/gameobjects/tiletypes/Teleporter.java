package inf112.skeleton.app.gameobjects.tiletypes;

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

    public Teleporter(){
        this.type = GameObjectType.TELEPORTER;
    }

    @Override
    public void evaluateSprite() {

    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Set the location of where to teleport the player to
     * @param teleportLocation Other teleport location
     */
    public void setTeleportLocation(Coordinate teleportLocation) {
        this.teleportLocation = teleportLocation;
    }

    /**
     * @return The location of where to teleport the
     */
    public Coordinate getTeleportLocation() {
        return teleportLocation;
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
