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
import inf112.skeleton.app.cards.CardSpriteInteraction;
import inf112.skeleton.app.cards.RRCard;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Player;

import java.util.PriorityQueue;

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
    private TileGrid tileGrid;

    //public IDeck abilityDeck;

    private CardSpriteInteraction CSI;

    private boolean insideSprite;


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


        cardTestSprite.setPosition(33,300);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderGrid();
        performPhase();
        activateTiles();
        renderDealtCards();
        batch.end();
    }

    private void performPhase(){
        if(currentPhase == 0){
            performProgrammingPhase();
            currentPhase++;
            return;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            tileGrid.movePlayer(0, 0, -1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            tileGrid.movePlayer(0, 0, 1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            tileGrid.movePlayer(0, 1, 0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            tileGrid.movePlayer(0, -1, 0);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.J)){
            System.out.println("leggo");
            cardTestSprite.setPosition(Gdx.input.getX(),Gdx.input.getY());
        }

        /*
         * Todo:
         * Implement phases with card turning.
         */

    }

    private void performProgrammingPhase(){

        /*
         * Todo:
         * Implement programming-phase where the
         * player chooses which cards their robot
         * should use.
         */
    }

    private void activateTiles(){

        /*
         * Todo:
         * Implement activation of tiles and
         * calculate the logic that should then
         * be applied to the players
         */

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

    private boolean isInsideSprite(Sprite sprite, float screenX, float screenY){
        if (screenX >= sprite.getX() && screenX < sprite.getX()+sprite.getWidth()){
            if (Gdx.graphics.getHeight()-screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight()-screenY < sprite.getY()+sprite.getHeight()){
                moveSprite(sprite,screenX-sprite.getWidth()/2,Gdx.graphics.getHeight()-screenY-sprite.getHeight()/2);
                return true;
            }
        }
        return false;
    }

    private void moveSprite(Sprite sprite, float newX, float newY ){
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
            moveSprite(cardTestSprite,newPos.x-cardTestSprite.getWidth(),newPos.y);
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
            moveSprite(cardTestSprite,screenX-cardDeltaW,Gdx.graphics.getHeight()-screenY-cardDeltaH);
            return true;
        }

        if (isInsideSprite(cardTestSprite, screenX, screenY)){
            insideSprite = true;
            return true;
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
