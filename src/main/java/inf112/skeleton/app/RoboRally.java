package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.cards.AbilityDeck;
import inf112.skeleton.app.cards.IDeck;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramDeck;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Player;

import java.util.PriorityQueue;

public class RoboRally extends Game implements InputProcessor {
    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private final int GRID_ROWS = 12;
    private final int GRID_COLUMNS = 12;

    private final int PHASES = 5;

    // Dealt cards background texture and sprite.
    private Texture dealtCardsBackgroundTexture;
    private Sprite dealtCardsBackgroundSprite;

    private int drawPositionX;
    private int drawPositionY;
    private int currentPhase;

    private SpriteBatch batch;
    public TileGrid tileGrid;

    public IDeck programDeck;
    public IDeck abilityDeck;

    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.
        this.dealtCardsBackgroundTexture = new Texture(Gdx.files.internal("./assets/cards/dealtCardsBackground.png"));
        this.dealtCardsBackgroundSprite = new Sprite(dealtCardsBackgroundTexture);

        this.drawPositionX = 0;
        this.drawPositionY = 0;

        batch = new SpriteBatch();
        currentPhase = 0;
        tileGrid = new TileGrid(GRID_ROWS, GRID_COLUMNS, 1);

        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");

        int playerHealth = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).drawCards(programDeck.deal(playerHealth), abilityDeck.deal(playerHealth));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        tick();
        renderGrid();
        renderDealtCards();
        batch.end();

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
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
