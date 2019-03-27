package inf112.skeleton.app.gameobjects.TileTypes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Tile;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

public class Teleporter implements GameObject {

    Tile teleportLocation;

    public Teleporter(){

    }

    @Override
    public void evaluateSprite() {

    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
