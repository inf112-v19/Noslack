package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player implements GameObject{
    private Sprite sprite;
    private int health;

    public Player(){
        Texture texture = new Texture(Gdx.files.internal("/assets/gameObjects/player.png"));
        this.sprite = new Sprite(texture);
        this.health = 9;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.PLAYER;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Removes one health from the player.
     */
    public void recieveDamage(){
        this.health--;
    }

}
