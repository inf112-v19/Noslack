package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RepairStation implements GameObject {
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
        try {
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/RepairPlaceholder.png"));
            this.sprite = new Sprite(texture);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in RepairStation evaluateSprite");
        }
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        } else {
            return 1;
        }
    }
}
