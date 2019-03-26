package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen
{
    private SpriteBatch batch;
    private boolean run;
    private Sprite startGame;
    private SpriteContainer spriteContainer;

    public MenuScreen(SpriteBatch batch)
    {
        this.batch=batch;
        this.run = true;
        this.spriteContainer = new SpriteContainer(batch);

        this.startGame = spriteContainer.setSprite("./assets/cards/dontpress.png");
        this.startGame.setPosition(200, 220);


    }
    public void startMenu(){

        batch.begin();
        startGame.draw(batch);
        batch.end();


    }


    public boolean runMenu()
    {
        return run;

    }

    public void stopMenu(){
        run = false;
    }

    public boolean clickStart(int screenX, int screenY){
        return spriteContainer.isInsideSprite(screenX,screenY,startGame);

    }



}