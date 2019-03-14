package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.FileNotFoundException;

public class Flag implements GameObject {
    private Sprite sprite;
    private int flagNumber;


    public Flag() {
        this.flagNumber = 1;
        evaluateSprite();
    }

    public Flag(int flagNumber){
        this.flagNumber = flagNumber;
        evaluateSprite();
    }

    public int getFlagNumber(){
        return flagNumber;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.FLAG;
    }

    @Override
    public Sprite getSprite() {return sprite;}

    /**
     * Evaluates flagNumber.
     */
    @Override
    public void evaluateSprite() {
        try {
            //add more, at this point of time we only have 2 flags.
            Texture texture;
            if(flagNumber == 1){
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/flags/oneFlag32x32.png"));
            }
            else if(flagNumber == 2){
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/flags/twoFlag32x32.png"));
            }
            else{
                texture = new Texture(Gdx.files.internal("./assets/gameObjects/flags/flag32x32.png"));
            }


            this.sprite = new Sprite(texture);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in flags evaluateSprite");
        }
    }

    @Override
    public int compareTo(Object other) {
        if(((GameObject) other).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        }else{
            return 1;
        }
    }
}
