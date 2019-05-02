package inf112.skeleton.app;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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
    private Sprite background;
    private BitmapFont font;
    private Vector2 mapTextPos;
    private boolean creatingMap;
    private TileGrid tileGrid;
    private String selectedMap = "mapLayoutFinishedMap1.txt";
    private Sprite map1;
    private Sprite map2;

    private Sprite youWin;
    private Sprite youLose;
    private boolean runWin;
    private boolean runLoose;
    private Sprite mainMenuBtn;

    public MenuScreen(SpriteBatch batch)
    {
        this.tileGrid = new TileGrid(selectedMap);
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
        this.runTests = false;

        this.creatingMap = false;

        this.font = new BitmapFont();
        this.font.setColor(200,0,0,1);
        this.mapTextPos = new Vector2(110,520);

        this.map1 = spriteContainer.setSprite("./assets/menuScreen/map1.png");
        this.map1.setPosition(110,400);
        this.map2 = spriteContainer.setSprite("./assets/menuScreen/map2.png");
        this.map2.setPosition(260,400);

        this.youLose = spriteContainer.setSprite("./assets/menuScreen/youLose.png");
        this.youLose.setPosition(225,400);
        this.youWin = spriteContainer.setSprite("./assets/menuScreen/youWon.png");
        this.youWin.setPosition(225,400);
        this.mainMenuBtn = spriteContainer.setSprite("./assets/menuScreen/mainMenuBtn.png");
        this.mainMenuBtn.setPosition(325, 100);
        this.runWin = false;
        this.runLoose = false;

    }


    public void render(){

        if(creatingMap){
            createMenu();
        } else {
            batch.begin();
            background.draw(batch);
            startGame.draw(batch);
            testsButton.draw(batch);
            createNewMap.draw(batch);
            map1.draw(batch);
            map2.draw(batch);
            this.font.draw(batch,"Selected map:",mapTextPos.x,mapTextPos.y);
            batch.end();
        }
    }

    public boolean runTests(){ return this.runTests; }

    public boolean runMenu() { return run; }

    public void stopMenu(){ run = false; }

    public void clickMenu(int screenX, int screenY) {
        if(spriteContainer.isInsideSprite(screenX,screenY,mainMenuBtn)){
            this.runLoose = false;
            this.runWin = false;
            this.run = true;
        }
    }

    public void clickCreate(int screenX, int screenY) {
        if(spriteContainer.isInsideSprite(screenX,screenY,createNewMap)){
            this.tileGrid = new TileGrid(selectedMap);
            this.creatingMap = !this.creatingMap;
        }
    }

    public boolean clickStart(int screenX, int screenY){
        return spriteContainer.isInsideSprite(screenX,screenY,startGame);

    }

    public void clickTestStart(int screenX, int screenY){
        if(spriteContainer.isInsideSprite(screenX,screenY,testsButton)){
            this.runTests = !this.runTests;
        }
    }

    public void clickMenuBtn(float screenX, float screenY){{
        if(spriteContainer.isInsideSprite(screenX,screenY,mainMenuBtn)){
            runWin = false;
            runLoose = false;
            run = true;
        }
    }

    }

    public String clickMap(int screenX, int screenY) {
        if(spriteContainer.isInsideSprite(screenX,screenY,map1)){
            this.mapTextPos = new Vector2(110,520);
            this.selectedMap = "mapLayoutFinishedMap1.txt";
            return this.selectedMap;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,map2)){
            this.mapTextPos = new Vector2(260,520);
            this.selectedMap = "ConveyorLoops.txt";
            return this.selectedMap;
        }
        return "no";
    }

    private void createMenu(){
        batch.begin();
        background.draw(batch);
        this.spriteContainer.renderFlexibleGrid(tileGrid, true, 0, 50, 50);
        createNewMap.draw(batch);
        batch.end();
    }

    public void testMenu(){
        batch.begin();
        ArrayList<Sprite> sprites = new ArrayList<>();

        sprites.add(startGame);
        sprites.add(testsButton);
        sprites.add(createNewMap);
        sprites.add(map1);
        sprites.add(map2);
        sprites.add(youWin);
        sprites.add(youLose);
        spriteContainer.drawAll(sprites);
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

    public boolean hasWon(){
        return runWin;
    }

    public boolean hasLost(){
        return runLoose;
    }

    public void drawWin(){
        runWin = true;
    }
    public void drawLost(){
        runLoose = true;
    }
}