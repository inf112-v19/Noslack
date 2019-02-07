package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.gameobjects.GameObject;

import java.util.PriorityQueue;

public class RoboRally extends Game {
    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private final int GRID_ROWS = 12;
    private final int GRID_COLUMNS = 12;

    private int drawPositionX;
    private int drawPositionY;
    private int currentPhase;

    private SpriteBatch batch;
    private TileGrid tileGrid;

    //public IDeck abilityDeck;

    @Override
    public void create() {
        this.drawPositionX = 0;
        this.drawPositionY = 0;

        batch = new SpriteBatch();
        currentPhase = 0;
        tileGrid = new TileGrid(GRID_ROWS, GRID_COLUMNS);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        performPhase();
        activateTiles();
        renderGrid();
        batch.end();
    }

    private void performPhase(){
        if(currentPhase == 0){
            performProgrammingPhase();
            currentPhase++;
            return;
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
     * Renders the grid by looping through all
     * the tiles and drawing each one, whilst
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
            this.drawPositionX = 0; // Resetting the horizontal drawPosition.
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
