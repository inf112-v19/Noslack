package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RepairStation implements GameObject {
    private Texture texture;
    private Sprite sprite;

    public RepairStation() {
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.REPAIR_STATION;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/repairStation.png"));
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.REPAIR_STATION){
            return -1;
        } else {
            return 1;
        }
    }
}
