package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Player;

import java.util.PriorityQueue;

public class RoboRally extends Game {
    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private final int GRID_ROWS = 12;
    private final int GRID_COLUMNS = 12;

    // Dealt cards background texture and sprite.
    private Texture dealtCardsBackgroundTexture;
    private Sprite dealtCardsBackgroundSprite;

    private int drawPositionX;
    private int drawPositionY;
    private int currentPhase;

    private SpriteBatch batch;
    private TileGrid tileGrid;

    //public IDeck abilityDeck;

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
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        performPhase();
        activateTiles();
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            tileGrid.movePlayer(0, 0, -1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            tileGrid.movePlayer(0, 0, 1);
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
        this.drawPositionY = 0;

        // Draw background for dealt cards.

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
        this.drawPositionY = 0;
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
}
