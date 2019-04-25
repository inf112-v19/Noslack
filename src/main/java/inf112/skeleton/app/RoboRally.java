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
    private ArrayList<Integer> robotQueque;
    private int currentPlayer;

    private ProgramDeck programDeck;
    private AbilityDeck abilityDeck;

    private CardSpriteInteraction CSI;

    private ArrayList<ProgramCard> programHand;

    private AbilityCard currentAbility;
    private ProgramCard currentCard;

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

    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.

        this.gameSounds = new SoundContainer();
        Gdx.input.setInputProcessor(this);
        this.batch = new SpriteBatch();
        this.menuScreen = new MenuScreen(batch);
    }

    public void createGame(){
        this.currentPlayer=0;

        gameSounds.gameMusic();
        this.CSI = new CardSpriteInteraction();
        //NEW SPRITECONTAINER
        this.tileGrid = new TileGrid("teleporterMap.txt");
        this.robotQueque = new ArrayList<>();
        this.spriteContainer = new SpriteContainer(batch, this.tileGrid.getRows(), this.tileGrid.getColumns());
        this.currentPhase = 0;
        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");
        int playerHealth = this.tileGrid.getPlayer().getHealth();
        for(IRobot player :this.tileGrid.getRobots()){
            player.drawAbility(this.abilityDeck.dealOne());
            if(player.hasAbility(Ability.ExtraMemory)){
                playerHealth++;
            }
            player.drawPrograms(this.programDeck.deal(playerHealth));
        }
        this.programHand = tileGrid.getPlayerProgramHand(currentPlayer);
        this.animator = new CardSpriteAnimation(programHand);
        this.cardTestSprite = tileGrid.getPlayerProgramHand(currentPlayer).get(0).getSprite();
        this.emptyProgram = new ProgramCard(0, Program.NONE);
        this.emptyAbility = new AbilityCard(" ");
        this.currentAbility = emptyAbility;
        this.abilityText = "";
        this.roboTick = 0;
        this.animation = false;
        this.activatedTiles = false;
        dealNewCards();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(menuScreen.runMenu()){
            if(menuScreen.runTests()){
                menuScreen.testMenu();
            } else {
                menuScreen.render();
            }
        }
        else{
            this.batch.begin();
            spriteContainer.renderGrid(tileGrid);
            performPhase();
            if (roboTick % 20 == 0){
                if(sequenceReady){
                    if(currentPhase<6) {
                        this.robotQueque = tileGrid.playerQueue();
                    }
                    if(activatedTiles){
                        activateTiles();
                        activatedTiles = false;
                    } else {
                        tick();
                    }
                }
            }

            if (animation){
                programHand = animator.updatePositions();
            }

            spriteContainer.renderDealtCards(programHand);
            spriteContainer.drawAbilityText();
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
            if (diff < targetDelay) {
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
        if(this.tileGrid.getRobot(currentPlayer).isFinished()){
            this.currentPhase = 100;
        }
        if (this.currentPhase <= 5) {
            // Runs per phase
            if (this.tileGrid.getPlayerCurrentMove(currentPlayer) == Program.NONE) {
                this.tileGrid.applyNextProgram(currentPlayer);
                this.currentPhase++;
                activatedTiles = true;
            }
        }
        // Runs mid phase
        if (!(this.tileGrid.getPlayerCurrentMove(currentPlayer) == Program.NONE)) {
            this.tileGrid.continueMove(currentPlayer);
        } else if (this.currentPhase > 5){
            dealNewCards();
            sequenceReady = false;
            this.currentPhase = 0;
            activatedTiles = true;
        }
    }

    private void dealNewCards() {
        this.tileGrid.resetPlayer(currentPlayer);
        this.programDeck.reset();
        for(IRobot player : this.tileGrid.getRobots()){
            if(!player.isPoweredDown()) {
                int playerHealth = player.getHealth();
                if (this.tileGrid.playerHasAbility(currentPlayer, Ability.ExtraMemory)) {
                    playerHealth++;
                }
                player.drawPrograms(this.programDeck.deal(playerHealth));
            }
        }
        if(this.currentAbility.getAbility() == this.emptyAbility.getAbility()){
            try{
                this.currentAbility = this.tileGrid.getRobot(currentPlayer).getAbilityHand().get(0);

            }catch(Exception e){
                System.out.println("There is a bug in DealNewCards() in Roborally class - get(0)");

            }
            this.currentAbility.getSprite().setPosition(550,20);
            spriteContainer.getCardSprite(currentAbility);
        }
        animator = new CardSpriteAnimation(programHand);
        animation = true;
    }

    public void discardAbility(int playerNumber, RRCard card){
        this.tileGrid.getRobot(playerNumber).discardAbility(card);
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
        if(menuScreen.runMenu()){
            if(menuScreen.clickStart(screenX,screenY)){
                menuScreen.stopMenu();
                createGame();
            }
            else {
                menuScreen.clickTestStart(screenX,screenY);
            }
        } else {
            if (spriteContainer.isInsideGo(screenX, screenY)) {
                ArrayList<ProgramCard> chosenCards = this.CSI.getChosenCards();
                for(ProgramCard card : chosenCards){
                    if (card.getPriority() == 0) nulls++;
                }
                if (true//nulls == 0
                ) {
                    this.tileGrid.getRobot(currentPlayer).pushProgram(chosenCards);
                    CSI.reset();
                    sequenceReady = true;
                }
            }
            if (spriteContainer.isInsideMute(screenX,screenY)){
                if(gameSounds.isGameMusicPlaying()){
                    gameSounds.pauseGameMusic();
                } else {
                    gameSounds.resumeGameMusic();
                }
            }
            spriteContainer.isInsideCard(screenX,screenY,currentAbility);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = transformX(screenX);
        screenY = transformY(screenY);
        //Stops the function if the user is still in the menu
        if(menuScreen.runMenu()){
            return false;
        }

        float cardDeltaH = cardTestSprite.getHeight() / 2;
        float cardDeltaW = cardTestSprite.getWidth() / 2;

        if (insideSprite) {
            Vector2 newPos = CSI.cardSnapPosition(spriteContainer.getCurrentCard(), screenX + cardDeltaW, Gdx.graphics.getHeight() - screenY - cardDeltaH);
            ProgramCard overlap = CSI.getCardOverlap();
            if (overlap.getPriority() != this.emptyProgram.getPriority()) {
                spriteContainer.moveSprite(overlap.getSprite(), overlap.getPosition().x, overlap.getPosition().y);
            }
            spriteContainer.moveSprite(spriteContainer.getCurrentSprite(), newPos.x, newPos.y);
            insideSprite = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = transformX(screenX);
        screenY = transformY(screenY);
        //Stops the function if the user is still in the menu
        if(menuScreen.runMenu()){
            return false;
        }

        float cardDeltaH = cardTestSprite.getHeight() / 2;
        float cardDeltaW = cardTestSprite.getWidth() / 2;

        if (insideSprite) {
            spriteContainer.moveSprite(spriteContainer.getCurrentSprite(), screenX - cardDeltaW,  Gdx.graphics.getHeight() - screenY - cardDeltaH);
            return true;
        }

        for (ProgramCard card : this.programHand) {
            if(spriteContainer.isInsideCard(screenX,screenY,card)){
                insideSprite = true;
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