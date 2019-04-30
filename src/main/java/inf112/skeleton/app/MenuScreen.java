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
    private Sprite hostGame;
    private SpriteContainer spriteContainer;
    private boolean runTests;
    private Sprite drawSpritesBtn;
    private Sprite background;

    private Sprite youWin;
    private Sprite youLose;
    private boolean runWin;
    private boolean runLoose;
    private Sprite mainMenuBtn;

    public MenuScreen(SpriteBatch batch)
    {
        this.batch=batch;
        this.run = true;
        this.spriteContainer = new SpriteContainer(batch);
        this.background = spriteContainer.setSprite("./assets/menuScreen/mainMenu.png");
        this.background.setPosition(0, 0);
        this.startGame = spriteContainer.setSprite("./assets/menuScreen/startBtn.png");
        this.startGame.setPosition(100, 220);
        this.testsButton = spriteContainer.setSprite("./assets/menuScreen/testsBtn.png");
        this.testsButton.setPosition(375,220);
        this.createNewMap = spriteContainer.setSprite("./assets/menuScreen/createMapBtn.png");
        this.createNewMap.setPosition(650,220);
        this.hostGame = spriteContainer.setSprite("./assets/menuScreen/hostGameBtn.png");
        this.hostGame.setPosition(375,95);
        this.runTests = false;

        this.youLose = spriteContainer.setSprite("./assets/menuScreen/youLose.png");
        this.youLose.setPosition(225,400);
        this.youWin = spriteContainer.setSprite("./assets/menuScreen/youWon.png");
        this.youWin.setPosition(225,400);
        this.mainMenuBtn = spriteContainer.setSprite("./assets/menuScreen/mainMenuBtn.png");
        this.mainMenuBtn.setPosition(325, 100);
        this.runWin = true;
        this.runLoose = true;

    }


    public void render(){

        batch.begin();
        background.draw(batch);
        startGame.draw(batch);
        testsButton.draw(batch);
        createNewMap.draw(batch);
        hostGame.draw(batch);
        batch.end();

    }

    public boolean runTests(){ return this.runTests; }

    public boolean runMenu() { return run; }

    public void stopMenu(){ run = false; }

    public boolean clickStart(int screenX, int screenY){
        return spriteContainer.isInsideSprite(screenX,screenY,startGame);

    }
    public void clickTestStart(int screenX, int screenY){
        if(spriteContainer.isInsideSprite(screenX,screenY,testsButton)){
            if(runTests){
                this.runTests=false;
            } else {
                this.runTests=true;
            }
        }
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

    public void gameFinishMenu(){

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
}