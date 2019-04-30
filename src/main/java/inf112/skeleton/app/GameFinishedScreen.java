package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameFinishedScreen {

    private SpriteBatch batch;
    private boolean runWin;
    private boolean runLoose;
    private Sprite mainMenuBtn;
    private SpriteContainer spriteContainer;
    private Sprite background;
    private Sprite youWin;
    private Sprite youLose;

    public GameFinishedScreen(SpriteBatch batch) {
        this.batch=batch;
        this.runWin = true;
        this.runLoose = true;
        this.spriteContainer = new SpriteContainer(batch);
        this.background = spriteContainer.setSprite("./assets/menuScreen/mainMenu.png");
        this.background.setPosition(0, 0);
        this.mainMenuBtn = spriteContainer.setSprite("./assets/menuScreen/mainMenuBtn.png");
        this.mainMenuBtn.setPosition(325, 100);
        this.youLose = spriteContainer.setSprite("./assets/menuScreen/youLose.png");
        this.youLose.setPosition(225,400);
        this.youWin = spriteContainer.setSprite("./assets/menuScreen/youWon.png");
        this.youWin.setPosition(225,400);





    }

    public void render(){

        batch.begin();
        background.draw(batch);
        mainMenuBtn.draw(batch);
        if(runWin){
            youWin.draw(batch);
        }
        else if(runLoose){
            youLose.draw(batch);
        }
        batch.end();

    }

    public boolean win(){
        runLoose = false;
        return runWin;
    }

    public boolean loose(){
        runWin = false;
        return runLoose;}

    public void stop(){
        runWin = false;
        runLoose = false;
    }

}
