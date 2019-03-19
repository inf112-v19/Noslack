package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BatchTest {

    private Sprite goButton;
    private SpriteBatch batch;


    public BatchTest(SpriteBatch batch){
        this.batch = batch;
        this.goButton = setSprite("./assets/cards/dontpress.png");
        this.goButton.setPosition(200, 220);



    }

    public void draw(){

        System.out.println("HELLO");
        this.batch.begin();
        this.goButton.draw(this.batch);
        this.batch.end();

    }

    private Sprite setSprite(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        return new Sprite(texture);
    }
}
