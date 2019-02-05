package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RoboRally extends Game {
    //private BitmapFont font;
    public final int TILE_SIZE = 32;
    public final int GRID_ROWS = 12;
    public final int GRID_COLUMNS = 12;

    private int drawPositionX;
    private int drawPositionY;

    private SpriteBatch batch;
    private int currentPhase;
    public TileGrid tileGrid;
    public ProgramDeck programDeck;

    //public Deck abilityDeck;

    @Override
    public void create() {
        this.drawPositionX = 0;
        this.drawPositionY = 0;

        batch = new SpriteBatch();
        currentPhase = 0;
        tileGrid = new TileGrid(GRID_ROWS, GRID_COLUMNS);
        programDeck = new ProgramDeck();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        performPhase();
        activateTiles();
        renderGrid();
        batch.end();
    }

    public void performPhase(){
        if(currentPhase == 0){
            performProgrammingPhase();
            currentPhase++;
            return;
        }

        /**
         * Todo:
         * Implement phases with card turning.
         */

    }

    public void performProgrammingPhase(){
        /**
         * Todo:
         * Implement programming-phase where the
         * player chooses which cards their robot
         * should use.
         */
    }

    public void activateTiles(){

        /**
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
    public void renderGrid(){

        /**
         * Todo:
         * Render the grid and all the objects residing
         * on it.
         */

        // Work in progress
        for(int row = 0; row<GRID_ROWS; row++){
            for(int column = 0; column<GRID_COLUMNS; column++){
                Sprite tileSprite = tileGrid.getTile(row, column).getSprite();
                tileSprite.setPosition(drawPositionX, drawPositionY);
                tileSprite.draw(batch);

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
