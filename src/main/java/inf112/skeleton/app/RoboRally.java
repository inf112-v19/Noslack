package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class RoboRally extends Game implements InputProcessor {

    private CardSpriteInteraction interact;


    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private final int GRID_ROWS = 12;
    private final int GRID_COLUMNS = 12;

    // Dealt cards background texture and sprite.

    private Sprite dealtCardsBackgroundSprite;

    private Sprite selectedCardsBackgroundSprite;

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

    private ProgramCard currentCard;
    private boolean insideSprite;
    private Sprite currentSprite;

    private Sprite goButton;

    private ProgramCard empty;

    private boolean sequenceReady;

    private int roboTick;

    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.

        Gdx.input.setInputProcessor(this);

        this.dealtCardsBackgroundSprite = setSprite("./assets/cards/dealtCardsBackground.png");
        this.selectedCardsBackgroundSprite = setSprite("./assets/cards/KortBakgrunn.png");
        this.cardTestSprite = setSprite("./assets/cards/back-up.png");

        this.goButton = setSprite("./assets/cards/dontpress.png");

        this.drawPositionX = 0;
        this.drawPositionY = 0;

        this.CSI = new CardSpriteInteraction();

        batch = new SpriteBatch();
        currentPhase = 0;
        tileGrid = new TileGrid(GRID_ROWS, GRID_COLUMNS, 1);

        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");

        int playerHealth = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).drawCards(programDeck.deal(playerHealth), abilityDeck.deal(playerHealth));

        programHand = tileGrid.getPlayer(0).getProgramHand();

        cardTestSprite = tileGrid.getPlayer(0).getProgramHand().get(0).getSprite();

        goButton.setPosition(33, 220);

        empty = new ProgramCard(0, Program.NONE);

        roboTick = 0;

        dealNewCards();
        /*
        for (int i = 0; i < programHand.size(); i++) {
            Vector2 pos = new Vector2(33 + i * 75, 300);
            programHand.get(i).setPosition(pos);
            programHand.get(i).getSprite().setPosition(pos.x, pos.y);
        }
        */

    }

    

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderGrid();
        performPhase();
        activateTiles();
        if (sequenceReady && (roboTick % 20 == 0)) {
            tick();
        }
        renderGrid();
        renderDealtCards();
        goButton.draw(batch);
        batch.end();
        roboTick++;
    }


    private void performPhase() {
        if (currentPhase == 0) {
            performProgrammingPhase();
            currentPhase++;
            return;
        }

        // Limit fps
        sleep(60);
    }

    private void activateTiles() {
        tileGrid.activateTiles();
    }

    /**
     * This method renders the cards the player has
     * been dealt.
     */
    private void renderDealtCards() {
        this.drawPositionX = 0;
        this.drawPositionY = 4 * TILE_SIZE;

        // Draw background for dealt cards.

        dealtCardsBackgroundSprite.setPosition(drawPositionX, drawPositionY);
        dealtCardsBackgroundSprite.draw(batch);

        selectedCardsBackgroundSprite.draw(batch);

        for (ProgramCard card : programHand) {
            card.getSprite().draw(batch);
        }

        //cardTestSprite.draw(batch);
    }

    /**
     * This method Renders the grid by looping through
     * all the tiles and drawing each one, whilst
     * keeping track of- and updating the
     * drawposition.
     */
    private void renderGrid() {

        /*
         * Todo:
         * Render the grid and all the objects residing
         * on it.
         */

        // Work in progress

        // Start draw position after the dealt cards.
        this.drawPositionX = TILE_SIZE * 4;
        this.drawPositionY = TILE_SIZE * 4;
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {

                // Retrieve current tile from grid
                Tile tileBeingDrawn = tileGrid.getTile(row, column);
                // Retrieve PriorityQueue of GameObjects on current tile
                PriorityQueue<GameObject> objectsOnTile = tileBeingDrawn.getObjectsOnTile();

                // Draw the tile
                Sprite spriteOfTile = tileBeingDrawn.getSprite();
                spriteOfTile.setPosition(drawPositionX, drawPositionY);
                spriteOfTile.draw(batch);

                // Draw GameObjects on tile
                for (GameObject gameObject : objectsOnTile) {
                    Sprite spriteOfGameObject = gameObject.getSprite();
                    spriteOfGameObject.setPosition(drawPositionX, drawPositionY);
                    spriteOfGameObject.draw(batch);
                }

                this.drawPositionX += TILE_SIZE;    // Moving the horizontal drawPosition, one tile over.
            }
            this.drawPositionX = TILE_SIZE * 4; // Resetting the horizontal drawPosition.
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
    public void tick() {
        /*
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        /*
        if (currentPhase == 0) {
            performProgrammingPhase();
            currentPhase++;
        }
        */
        if (currentPhase <= 5) {
            // Runs per phase
            if (tileGrid.getPlayer(0).getCurrentMove() == Program.NONE) {
                tileGrid.applyNextProgram(0);
                activateTiles();
                currentPhase++;
                // Runs mid phase
            } else {
                tileGrid.continueMove(0);
            }
        } else {
            tileGrid.continueMove(0);
            activateTiles();

            dealNewCards();
            sequenceReady = false;
            this.currentPhase = 0;

        }
    }

    private void performProgrammingPhase() {

        /*
         * Todo:
         * Implement programming-phase where the
         * player chooses which cards their robot
         * should use.
         */

    }

    private void dealNewCards() {
        tileGrid.getPlayer(0).reset();
        this.programDeck.reset();
        this.abilityDeck.reset();
        int playerHealth = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).drawCards(programDeck.deal(playerHealth), abilityDeck.deal(playerHealth));
        //FIX THIS

        for (int i = 0; i < programHand.size(); i++) {
            Vector2 pos = new Vector2(33 + i * 75, 500);
            programHand.get(i).setPosition(pos);
            programHand.get(i).getSprite().setPosition(pos.x, pos.y);
        }
    }


    private boolean isInsideSprite(float screenX, float screenY, Sprite sprite) {
        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()) {
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            if (Gdx.graphics.getHeight() - screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight() - screenY < sprite.getY() + sprite.getHeight()) {
                // Moves the given sprite
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if cursor is inside given sprite
     *
     * @param screenX coordinate of cursor
     * @param screenY coordinate of cursor
     * @return boolean true if inside given sprite
     */
    private boolean isInsideCard(float screenX, float screenY, ProgramCard card) {

        Sprite sprite = card.getSprite();
        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()) {
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            if (Gdx.graphics.getHeight() - screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight() - screenY < sprite.getY() + sprite.getHeight()) {
                // Moves the given sprite
                moveSprite(sprite, screenX - sprite.getWidth() / 2, Gdx.graphics.getHeight() - screenY - sprite.getHeight() / 2);
                currentCard = card;
                currentSprite = sprite;
                return true;
            }
        }
        return false;
    }

    private void moveSprite(Sprite sprite, float newX, float newY) {
        batch.begin();
        sprite.setPosition(newX, newY);
        sprite.draw(batch);
        batch.end();
    }

    private Sprite setSprite(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        return new Sprite(texture);
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
        int nulls = 0;
        if (isInsideSprite(screenX, screenY, goButton)) {
            ArrayList<ProgramCard> chosenCards = CSI.getChosenCards();
            for (int i = 0; i < chosenCards.size(); i++) {
                if (chosenCards.get(i).getPriority() == 0) {
                    nulls++;
                }
            }
            if (nulls == 0) {
                tileGrid.getPlayer(0).pushProgram(chosenCards);
                CSI.reset();
                sequenceReady = true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        float cardDeltaH = cardTestSprite.getHeight() / 2;
        float cardDeltaW = cardTestSprite.getWidth() / 2;


        if (insideSprite) {
            Vector2 newPos = CSI.cardSnapPosition(currentCard, screenX + cardDeltaW, Gdx.graphics.getHeight() - screenY - cardDeltaH);
            ProgramCard overlap = CSI.getCardOverlap();
            if (overlap.getPriority() != empty.getPriority()) {
                moveSprite(overlap.getSprite(), overlap.getPosition().x, overlap.getPosition().y);
            }
            moveSprite(currentSprite, newPos.x, newPos.y);
            insideSprite = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        float cardDeltaH = cardTestSprite.getHeight() / 2;
        float cardDeltaW = cardTestSprite.getWidth() / 2;

        if (insideSprite) {
            moveSprite(currentSprite, screenX - cardDeltaW, Gdx.graphics.getHeight() - screenY - cardDeltaH);
            return true;
        }

        for (ProgramCard card : programHand) {
            if (isInsideCard(screenX, screenY, card)) {

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
