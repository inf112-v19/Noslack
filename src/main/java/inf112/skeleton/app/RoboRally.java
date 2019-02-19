package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Player;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RoboRally extends Game implements InputProcessor {

    private CardSpriteInteraction interact;


    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private final int GRID_ROWS = 12;
    private final int GRID_COLUMNS = 12;

    // Dealt cards background texture and sprite.
    private Texture dealtCardsBackgroundTexture;
    private Sprite dealtCardsBackgroundSprite;

    private Texture selectedCardsBackgroundTexture;
    private Sprite selectedCardsBackgroundSprite;


    private Texture cardTestTexture;
    private Sprite cardTestSprite;

    private int drawPositionX;
    private int drawPositionY;
    private int currentPhase;

    private SpriteBatch batch;
    public TileGrid tileGrid;

    public ProgramDeck programDeck;
    public AbilityDeck abilityDeck;

    private CardSpriteInteraction CSI;

    private ArrayList<ProgramCard> programHand;

    private boolean insideSprite;
    private Sprite currentSprite;


    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.

        Gdx.input.setInputProcessor(this);

        this.dealtCardsBackgroundTexture = new Texture(Gdx.files.internal("./assets/cards/dealtCardsBackground.png"));
        this.dealtCardsBackgroundSprite = new Sprite(dealtCardsBackgroundTexture);

        this.selectedCardsBackgroundTexture = new Texture(Gdx.files.internal("./assets/cards/KortBakgrunn.png"));
        this.selectedCardsBackgroundSprite = new Sprite(selectedCardsBackgroundTexture);

        this.cardTestTexture = new Texture(Gdx.files.internal("./assets/cards/back-up.png"));
        this.cardTestSprite = new Sprite(cardTestTexture);

        this.drawPositionX = 0;
        this.drawPositionY = 0;


        CSI = new CardSpriteInteraction();

        batch = new SpriteBatch();
        currentPhase = 0;
        tileGrid = new TileGrid(GRID_ROWS, GRID_COLUMNS, 1);

        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");

        int playerHealth = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).drawCards(programDeck.deal(playerHealth), abilityDeck.deal(playerHealth));

        programHand = tileGrid.getPlayer(0).getProgramHand();

        cardTestSprite = tileGrid.getPlayer(0).getProgramHand().get(0).getSprite();

        cardTestSprite.setPosition(33,300);

        for(int i = 0; i < programHand.size(); i++){
            programHand.get(i).getSprite().setPosition(33+i*75,300);
        }

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderGrid();
        performPhase();
        activateTiles();
        tick();
        renderGrid();
        renderDealtCards();
        batch.end();
    }

    private void performPhase(){
        if(currentPhase == 0){
            performProgrammingPhase();
            currentPhase++;
            return;
        }

        // Limit fps
        sleep(10);
    }

    private void activateTiles(){
        tileGrid.activateTiles();
    }

    /**
     * This method renders the cards the player has
     * been dealt.
     */
    private void renderDealtCards(){
        this.drawPositionX = 0;
        this.drawPositionY = 4*TILE_SIZE;

        // Draw background for dealt cards.

        dealtCardsBackgroundSprite.setPosition(drawPositionX, drawPositionY);
        dealtCardsBackgroundSprite.draw(batch);

        selectedCardsBackgroundSprite.draw(batch);

        for(int i = 0; i < programHand.size(); i++){
            programHand.get(i).getSprite().draw(batch);
        }

        cardTestSprite.draw(batch);
    }

    /**
     * This method Renders the grid by looping through
     * all the tiles and drawing each one, whilst
     * keeping track of- and updating the
     * drawposition.
     */
    private void renderGrid(){

        /*
         * Todo:
         * Render the grid and all the objects residing
         * on it.
         */

        // Work in progress

        // Start draw position after the dealt cards.
        this.drawPositionX = TILE_SIZE*4;
        this.drawPositionY = TILE_SIZE*4;
        for(int row = 0; row<GRID_ROWS; row++){
            for(int column = 0; column<GRID_COLUMNS; column++){

                // Retrieve current tile from grid
                Tile tileBeingDrawn = tileGrid.getTile(row, column);
                // Retrieve PriorityQueue of GameObjects on current tile
                PriorityQueue<GameObject> objectsOnTile = tileBeingDrawn.getObjectsOnTile();

                // Draw the tile
                Sprite spriteOfTile = tileBeingDrawn.getSprite();
                spriteOfTile.setPosition(drawPositionX, drawPositionY);
                spriteOfTile.draw(batch);

                // Draw GameObjects on tile
                for (GameObject gameObject : objectsOnTile){
                    Sprite spriteOfGameObject = gameObject.getSprite();
                    spriteOfGameObject.setPosition(drawPositionX, drawPositionY);
                    spriteOfGameObject.draw(batch);
                }

                this.drawPositionX += TILE_SIZE;    // Moving the horizontal drawPosition, one tile over.
            }
            this.drawPositionX = TILE_SIZE*4; // Resetting the horizontal drawPosition.
            this.drawPositionY += TILE_SIZE;    // Moving the vertical drawPosition, one tile up.
        }
        // Resetting the vertical drawPosition.
        this.drawPositionY = 0;

    }

    private long diff, start = System.currentTimeMillis();

    public void sleep(int fps) {
        if (fps > 0) {
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                }
            }
            start = System.currentTimeMillis();
        }
    }



    //   ROUND LOGIC   //
    public void tick(){
        if(currentPhase == 0){
            performProgrammingPhase();
            currentPhase++;
        }
        if(currentPhase <= 6) {
            // Runs per phase
            if (tileGrid.getPlayer(0).getCurrentMove() == Program.NONE) {
                tileGrid.applyNextProgram(0);
                activateTiles();
                currentPhase++;
            // Runs mid phase
            } else {
                tileGrid.continueMove(0);
            }
        }else{
            dealNewCards();
            this.currentPhase = 0;
        }
        Scanner scan = new Scanner(System.in);
        String pause =scan.nextLine();
        System.out.println(pause);
    }

    private void performProgrammingPhase(){

        /*
         * Todo:
         * Implement programming-phase where the
         * player chooses which cards their robot
         * should use.
         */

    }

    private void dealNewCards(){
        this.programDeck.reset();
        this.abilityDeck.reset();
        int playerHealth = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).drawCards(programDeck.deal(playerHealth), abilityDeck.deal(playerHealth));
    }

    /**
     * Checks if cursor is inside given sprite
     * @param sprite to be checked
     * @param screenX coordinate of cursor
     * @param screenY coordinate of cursor
     * @return boolean true if inside given sprite
     */
    private boolean isInsideSprite(Sprite sprite, float screenX, float screenY){

        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX()+sprite.getWidth()){
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            if (Gdx.graphics.getHeight()-screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight()-screenY < sprite.getY()+sprite.getHeight()){
                // Moves the given sprite
                moveSprite(sprite,screenX-sprite.getWidth()/2,Gdx.graphics.getHeight()-screenY-sprite.getHeight()/2);
                currentSprite = sprite;
                return true;
            }
        }
        return false;
    }

    private void moveSprite(Sprite sprite, float newX, float newY ){
        System.out.println("MOVE");
        batch.begin();
        sprite.setPosition(newX, newY);
        sprite.draw(batch);
        batch.end();
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
        batch.dispose();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        float cardDeltaH = cardTestSprite.getHeight()/2;
        float cardDeltaW = cardTestSprite.getWidth()/2;


        if (insideSprite){
            Vector2 newPos = CSI.cardSnapPosition(screenX+cardDeltaW,Gdx.graphics.getHeight()-screenY-cardDeltaH);
            moveSprite(currentSprite,newPos.x-currentSprite.getWidth(),newPos.y);
            insideSprite = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        float cardDeltaH = cardTestSprite.getHeight()/2;
        float cardDeltaW = cardTestSprite.getWidth()/2;

        if(insideSprite){
            moveSprite(currentSprite,screenX-cardDeltaW,Gdx.graphics.getHeight()-screenY-cardDeltaH);
            return true;
        }

        for (ProgramCard card : programHand) {
            if (isInsideSprite(card.getSprite(), screenX, screenY)){

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
