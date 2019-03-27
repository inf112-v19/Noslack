package inf112.skeleton.app;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen
{
    private SpriteBatch batch;
    private boolean run;
    private Sprite startGame;
    private Sprite testsButton;
    private Sprite createNewMap;
    private SpriteContainer spriteContainer;
    private boolean runTests;
    private Sprite drawSpritesBtn;

    public MenuScreen(SpriteBatch batch)
    {
        this.batch=batch;
        this.run = true;
        this.spriteContainer = new SpriteContainer(batch);

        this.startGame = spriteContainer.setSprite("./assets/cards/startGameButton.png");
        this.startGame.setPosition(200, 220);
        this.testsButton = spriteContainer.setSprite("./assets/cards/ManualTestsButton.png");
        this.testsButton.setPosition(400,220);
        this.createNewMap = spriteContainer.setSprite("./assets/cards/CreateNewMapButton.png");
        this.createNewMap.setPosition(600,220);
        this.runTests = false;


    }
    public void startMenu(){




    }

    public void render(){


        batch.begin();
        startGame.draw(batch);
        testsButton.draw(batch);
        createNewMap.draw(batch);
        batch.end();

    }

    public boolean runTests(){
        return this.runTests;

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
    public boolean clickTestStart(int screenX, int screenY){
        if(runTests){
            this.runTests=false;

        } else {
            this.runTests=true;
        }
        return spriteContainer.isInsideSprite(screenX,screenY,testsButton);

    }

    public void testMenu(){
        batch.begin();
        spriteContainer.drawTextBox("Which manual test do you want to run?",500);
        this.drawSpritesBtn = spriteContainer.setSprite("./assets/cards/drawAllSpritesBtn.png");
        this.drawSpritesBtn.setPosition(500,50);
        this.drawSpritesBtn.draw(batch);
        testsButton.draw(batch);



        batch.end();
}





}