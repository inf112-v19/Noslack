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
    private Sprite previewMapButton;
    private SpriteContainer spriteContainer;
    private boolean runTests;
    private Sprite drawSpritesBtn;
    private Sprite background;
    private BitmapFont font;
    private Vector2 mapTextPos;
    private Vector2 robotTextPos;
    private boolean previewingMap;
    private TileGrid tileGrid;
    private String selectedMap = "mapLayoutFinishedMap1.txt";
    private Sprite map1;
    private Sprite map2;
    private Sprite map3;
    private Sprite map4;
    private Sprite map5;

    private String selectedRobot = "./assets/gameObjects/player/player32x32.png";
    private Sprite robot1;
    private Sprite robot2;
    private Sprite robot3;
    private Sprite robot4;

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
        this.previewMapButton = spriteContainer.setSprite("./assets/menuScreen/previewMapBtn.png");
        this.previewMapButton.setPosition(650,220);
        this.runTests = false;

        this.previewingMap = false;

        this.font = new BitmapFont();
        this.font.setColor(200,0,0,1);
        this.mapTextPos = new Vector2(110,520);
        this.robotTextPos = new Vector2(110,180);

        this.map1 = spriteContainer.setSprite("./assets/menuScreen/map1.png");
        this.map1.setPosition(110,400);
        this.map2 = spriteContainer.setSprite("./assets/menuScreen/map2.png");
        this.map2.setPosition(260,400);
        this.map3 = spriteContainer.setSprite("./assets/menuScreen/map2.png");
        this.map3.setPosition(410,400);
        this.map4 = spriteContainer.setSprite("./assets/menuScreen/map4.png");
        this.map4.setPosition(560,400);
        this.map5 = spriteContainer.setSprite("./assets/menuScreen/map4.png");
        this.map5.setPosition(710,400);

        this.robot1 = spriteContainer.setSprite("./assets/gameObjects/player/player32x32.png");
        this.robot1.setPosition(150,110);
        this.robot2 = spriteContainer.setSprite("./assets/gameObjects/player/robot3.png");
        this.robot2.setPosition(300,110);
        this.robot3 = spriteContainer.setSprite("./assets/gameObjects/player/robot4.png");
        this.robot3.setPosition(450,110);
        this.robot4 = spriteContainer.setSprite("./assets/gameObjects/player/robot5.png");
        this.robot4.setPosition(600,110);


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

        if(previewingMap){
            previewMenu();
        } else {
            batch.begin();
            background.draw(batch);
            startGame.draw(batch);
            testsButton.draw(batch);
            previewMapButton.draw(batch);
            map1.draw(batch);
            map2.draw(batch);
            map3.draw(batch);
            map4.draw(batch);
            map5.draw(batch);
            robot1.draw(batch);
            robot2.draw(batch);
            robot3.draw(batch);
            robot4.draw(batch);

            this.font.draw(batch,"Selected map:",mapTextPos.x,mapTextPos.y);
            this.font.draw(batch,"Selected robot:",robotTextPos.x,robotTextPos.y);
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

    public void clickPreview(int screenX, int screenY) {
        if(spriteContainer.isInsideSprite(screenX,screenY, previewMapButton)){
            this.tileGrid = new TileGrid(selectedMap);
            this.previewingMap = !this.previewingMap;
        }
    }

    public boolean clickStart(int screenX, int screenY){
        return (spriteContainer.isInsideSprite(screenX,screenY,startGame) && !previewingMap);

    }

    public void clickTestStart(int screenX, int screenY){
        if(spriteContainer.isInsideSprite(screenX,screenY,testsButton) && !previewingMap){
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
        } else if(spriteContainer.isInsideSprite(screenX,screenY,map3)){
            this.mapTextPos = new Vector2(410,520);
            this.selectedMap = "Venezia.txt";
            return this.selectedMap;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,map4)){
            this.mapTextPos = new Vector2(560,520);
            this.selectedMap = "PyramidMap.txt";
            return this.selectedMap;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,map5)){
            this.mapTextPos = new Vector2(710,520);
            this.selectedMap = "LevelX.txt";
            return this.selectedMap;
        }
        return "no";
    }

    public String clickRobot(int screenX, int screenY) {
        if(spriteContainer.isInsideSprite(screenX,screenY,robot1)){
            this.robotTextPos = new Vector2(110,180);
            this.selectedRobot = "./assets/gameObjects/player/player32x32.png";
            return this.selectedRobot;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,robot2)){
            this.robotTextPos = new Vector2(260,180);
            this.selectedRobot = "./assets/gameObjects/player/robot3.png";
            return this.selectedRobot;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,robot3)){
            this.robotTextPos = new Vector2(410,180);
            this.selectedRobot = "./assets/gameObjects/player/robot4.png";
            return this.selectedRobot;
        } else if(spriteContainer.isInsideSprite(screenX,screenY,robot4)){
            this.robotTextPos = new Vector2(560,180);
            this.selectedRobot = "./assets/gameObjects/player/robot5.png";
            return this.selectedRobot;
        }
        return "no";
    }

    private void previewMenu(){
        batch.begin();
        background.draw(batch);
        this.spriteContainer.renderFlexibleGrid(tileGrid, true, 0, 50, 50);
        previewMapButton.draw(batch);
        batch.end();
    }

    public void testMenu(){
        batch.begin();
        ArrayList<Sprite> sprites = new ArrayList<>();
        this.spriteContainer.renderFlexibleGrid(tileGrid, true, 0, 50, 50);


        sprites.add(startGame);
        sprites.add(testsButton);
        sprites.add(previewMapButton);
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