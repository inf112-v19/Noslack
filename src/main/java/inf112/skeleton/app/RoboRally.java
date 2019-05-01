package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Robots.*;

import java.util.ArrayList;

public class RoboRally extends Game implements InputProcessor {
    // Dealt cards background texture and sprite.
    private Sprite cardTestSprite;
    private int currentPhase;
    private boolean activatedTiles;
    private SpriteBatch batch;
    private TileGrid tileGrid;
    private ArrayList<Integer> robotQueue;
    private int currentRobot;
    private ProgramDeck programDeck;
    private AbilityDeck abilityDeck;
    private CardSpriteInteraction CSI;
    private ArrayList<ProgramCard> programHand;
    private AbilityCard currentAbility;
    private String abilityText;
    private boolean insideSprite;
    private ProgramCard emptyProgram;
    private AbilityCard emptyAbility;
    private CardSpriteAnimation animator;
    private boolean sequenceReady;
    private int roboTick;
    private boolean animation;
    private SpriteContainer spriteContainer;
    private SoundContainer gameSounds;
    private MenuScreen menuScreen;
    private String selectedMap = "mapLayoutFinishedMap1.txt";

    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.

        this.gameSounds = new SoundContainer();
        Gdx.input.setInputProcessor(this);
        this.batch = new SpriteBatch();
        this.menuScreen = new MenuScreen(this.batch);
    }

    public void createGame(){
        this.currentRobot =0;

        this.gameSounds.gameMusic();
        this.CSI = new CardSpriteInteraction();
        //NEW SPRITECONTAINER
        this.tileGrid = new TileGrid(selectedMap);
        this.robotQueue = new ArrayList<>();
        this.spriteContainer = new SpriteContainer(this.batch, this.tileGrid.getRows(), this.tileGrid.getColumns());
        this.currentPhase = 0;
        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");
        for(IRobot robot :this.tileGrid.getRobots()){
            robot.drawAbility(this.abilityDeck.dealOne());
        }
        this.emptyAbility = new AbilityCard(" ");
        this.currentAbility = this.emptyAbility;
        this.abilityText = "";
        this.emptyProgram = new ProgramCard(0, Program.NONE);
        this.animation = false;
        this.activatedTiles = false;
        this.roboTick = 0;
        this.programHand = this.tileGrid.getRobotProgramHand(this.tileGrid.getPlayer().getRobotNumber());
        dealNewCards();
        this.animator = new CardSpriteAnimation(programHand);
        this.cardTestSprite = tileGrid.getRobotProgramHand(this.currentRobot).get(0).getSprite();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(this.menuScreen.runMenu()){
            if(this.menuScreen.runTests()){
                this.menuScreen.testMenu();
            } else {
                this.menuScreen.render();
            }
        }

        else if(this.menuScreen.win()){
            this.menuScreen.gameFinishMenu();
        }

        else if(this.menuScreen.loose()){

            this.menuScreen.gameFinishMenu();
        }


        else{
            this.batch.begin();
            this.spriteContainer.renderGrid(this.tileGrid);
            performPhase();
            if (this.roboTick % 20 == 0){
                if(sequenceReady){
                    this.robotQueue = this.tileGrid.robotQueue();
                    tick();
                }
            }

            if (this.animation){
                this.programHand = this.animator.updatePositions();
            }

            this.spriteContainer.renderDealtCards(this.programHand);
            this.spriteContainer.drawAbilityText();
            this.batch.end();
            this.roboTick++;
        }
    }

    private void performPhase() {
        if (this.currentPhase == 0) {
            this.currentPhase++;
        }
    }

    private void activateTiles() {
        this.tileGrid.activateTiles(this.currentPhase);
    }

    private long diff, start = System.currentTimeMillis();

    private void sleep(int fps) {
        if (fps > 0) {
            this.diff = System.currentTimeMillis() - this.start;
            long targetDelay = 1000 / fps;
            if (this.diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - this.diff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.start = System.currentTimeMillis();
        }
    }

    /**
     * Round Logic
     */
    private void tick() {
        if(this.tileGrid.getRobot(this.currentRobot).isFinished()){
            this.currentPhase = 100;
        }
        if (this.currentPhase <= 5) {
            // Runs per phase
            if (this.tileGrid.robotFinishedCurrentMove(this.currentRobot)) {
                this.tileGrid.applyNextProgram(this.currentRobot);
                this.currentRobot =this.robotQueue.remove(0);
                activateTiles();
                if(this.robotQueue.isEmpty() && this.tileGrid.robotFinishedCurrentMove(this.currentRobot)) {
                    this.robotQueue = this.tileGrid.robotQueue();
                    this.currentPhase++;
                }
            }
        }
        // Runs mid phase
        if (!(this.tileGrid.getRobotCurrentMove(this.currentRobot) == Program.NONE)) {
            this.tileGrid.continueMove(this.currentRobot);
        } else if (this.currentPhase > 5){
            dealNewCards();
            this.sequenceReady = false;
            this.currentPhase = 0;
            activateTiles();
        }


    }

    private void dealNewCards() {
        this.tileGrid.resetRobots();
        this.programDeck.reset();
        this.robotQueue.clear();
        for(IRobot robot : this.tileGrid.getRobots()){
            if(!robot.isPoweredDown()) {
                robot.drawPrograms(this.programDeck.deal(robot.getHealth()));
                if (this.tileGrid.robotHasAbility(robot.getRobotNumber(), Ability.ExtraMemory)) {
                    robot.extraCard(this.programDeck.dealOne());
                }
            }
        }
        this.tileGrid.decideAiPrograms();

        if(this.currentAbility.getAbility() == this.emptyAbility.getAbility()){
            try{
                this.currentAbility = this.tileGrid.getRobot(this.currentRobot).getAbilityHand().get(0);

            }catch(Exception e){
                System.out.println("There is a bug in DealNewCards() in Roborally class - get(0)");

            }
            this.currentAbility.getSprite().setPosition(550,20);
            this.spriteContainer.getCardSprite(this.currentAbility);
        }

        this.animator = new CardSpriteAnimation(this.programHand);
        this.animation = true;
    }

    public void discardAbility(int robotNumber, RRCard card){
        this.tileGrid.getRobot(robotNumber).discardAbility(card);
        this.abilityDeck.returnCard(card);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    /**
     * Transforms given X-coordinate to the correct one when transformed
     * @param screenX to be transformed
     * @return correct X
     */
    public int transformX(int screenX){return (int)((screenX/(double)Gdx.graphics.getWidth())*960);}

    /**
     * Transforms given Y-coordinate to the correct one when transformed
     * @param screenY to be transformed
     * @return correct Y
     */
    public int transformY(int screenY){return (int)((screenY/(double)(Gdx.graphics.getHeight()))*704) + (Gdx.graphics.getHeight() - 704); }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = transformX(screenX);
        screenY = transformY(screenY);

        int nulls = 0;
        if(this.menuScreen.runMenu()){
            if(this.menuScreen.clickStart(screenX,screenY)){
                this.menuScreen.stopMenu();
                createGame();
            } else if(!this.menuScreen.clickMap(screenX,screenY).equals("no")){
                this.selectedMap = this.menuScreen.clickMap(screenX,screenY);
            } else {
                this.menuScreen.clickCreate(screenX,screenY);
                this.menuScreen.clickTestStart(screenX,screenY);
            }
        } else if(this.menuScreen.win()){
            this.menuScreen.clickMenu(screenX,screenY);
        } else {
            if (this.spriteContainer.isInsideGo(screenX, screenY)) {
                ArrayList<ProgramCard> chosenCards = this.CSI.getChosenCards();
                for(ProgramCard card : chosenCards){
                    if (card.getPriority() == 0) nulls++;
                }
                if (true//nulls == 0
                ) {
                    this.tileGrid.getRobot(this.tileGrid.getPlayer().getRobotNumber()).pushProgram(chosenCards);
                    this.CSI.reset();
                    this.sequenceReady = true;
                }
            }
            if (this.spriteContainer.isInsideMute(screenX,screenY)){
                if(this.gameSounds.isGameMusicPlaying()){
                    this.gameSounds.pauseGameMusic();
                } else {
                    this.gameSounds.resumeGameMusic();
                }
            }
            if(this.spriteContainer.isInsidePowerDown(screenX,screenY)){
                this.tileGrid.getRobot(this.currentRobot).powerDown();
            }
            this.spriteContainer.isInsideCard(screenX,screenY,this.currentAbility);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = transformX(screenX);
        screenY = transformY(screenY);
        //Stops the function if the user is still in the menu
        if(this.menuScreen.runMenu()){
            return false;
        }

        float cardDeltaH = this.cardTestSprite.getHeight() / 2;
        float cardDeltaW = this.cardTestSprite.getWidth() / 2;

        if (this.insideSprite) {
            Vector2 newPos = this.CSI.cardSnapPosition(this.spriteContainer.getCurrentCard(), screenX + cardDeltaW, Gdx.graphics.getHeight() - screenY - cardDeltaH);
            ProgramCard overlap = this.CSI.getCardOverlap();
            if (overlap.getPriority() != this.emptyProgram.getPriority()) {
                this.spriteContainer.moveSprite(overlap.getSprite(), overlap.getPosition().x, overlap.getPosition().y);
            }
            this.spriteContainer.moveSprite(this.spriteContainer.getCurrentSprite(), newPos.x, newPos.y);
            this.insideSprite = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = transformX(screenX);
        screenY = transformY(screenY);
        //Stops the function if the user is still in the menu
        if(this.menuScreen.runMenu()){
            return false;
        }

        float cardDeltaH = this.cardTestSprite.getHeight() / 2;
        float cardDeltaW = this.cardTestSprite.getWidth() / 2;

        if (this.insideSprite) {
            this.spriteContainer.moveSprite(this.spriteContainer.getCurrentSprite(), screenX - cardDeltaW,  Gdx.graphics.getHeight() - screenY - cardDeltaH);
            return true;
        }

        for (ProgramCard card : this.programHand) {
            if(this.spriteContainer.isInsideCard(screenX,screenY,card)){
                this.insideSprite = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}