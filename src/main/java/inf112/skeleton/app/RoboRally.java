package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Player;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class RoboRally extends Game implements InputProcessor {

    private CardSpriteInteraction interact;


    //private BitmapFont font;

    // Grid and tile specifications
    private final int TILE_SIZE = 32;
    private int GRID_ROWS;
    private int GRID_COLUMNS;

    // Dealt cards background texture and sprite.

    private Sprite dealtCardsBackgroundSprite;

    private Sprite selectedCardsBackgroundSprite;

    private Sprite cardTestSprite;

    private int drawPositionX;
    private int drawPositionY;
    private int currentPhase;

    private SpriteBatch batch;
    private TileGrid tileGrid;

    private ProgramDeck programDeck;
    private AbilityDeck abilityDeck;

    private CardSpriteInteraction CSI;

    private ArrayList<ProgramCard> programHand;

    private AbilityCard currentAbility;
    private ProgramCard currentCard;

    private String abilityText;
    private BitmapFont font;

    private boolean insideSprite;
    private Sprite currentSprite;

    private Sprite goButton;

    private ProgramCard emptyProgram;
    private AbilityCard emptyAbility;

    private boolean sequenceReady;

    private int roboTick;

    private Sound gameMusic;

    @Override
    public void create() {
        // Load Dealt cards background texture and sprite.

        this.gameMusic = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/gameTheme.wav"));
        this.gameMusic.loop();

        Gdx.input.setInputProcessor(this);

        this.font = new BitmapFont();

        this.dealtCardsBackgroundSprite = setSprite("./assets/cards/dealtCardsBackground.png");
        this.selectedCardsBackgroundSprite = setSprite("./assets/cards/KortBakgrunn2.png");
        this.cardTestSprite = setSprite("./assets/cards/back-up.png");

        this.goButton = setSprite("./assets/cards/dontpress.png");

        this.drawPositionX = 0;
        this.drawPositionY = 0;

        this.CSI = new CardSpriteInteraction();

        this.batch = new SpriteBatch();

        this.currentPhase = 0;
        this.tileGrid = new TileGrid();
        this.GRID_COLUMNS = tileGrid.getColumns();
        this.GRID_ROWS = tileGrid.getRows();

        this.programDeck = new ProgramDeck("ProgramCards.txt");
        this.abilityDeck = new AbilityDeck("AbilityCards.txt");

        for(Player player : this.tileGrid.getPlayers()){
            int playerHealth = player.getHealth();
            player.drawCards(this.programDeck.deal(playerHealth), this.abilityDeck.deal(playerHealth));
        }

        this.programHand = tileGrid.getPlayerProgramHand(0);

        this.cardTestSprite = tileGrid.getPlayerProgramHand(0).get(0).getSprite();

        this.goButton.setPosition(33, 220);

        this.emptyProgram = new ProgramCard(0, Program.NONE);
        this.emptyAbility = new AbilityCard(" ");

        this.currentAbility = emptyAbility;
        this.abilityText = "";

        this.roboTick = 0;

        dealNewCards();
    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        renderGrid();
        performPhase();
        //activateTiles();
        if (sequenceReady && (roboTick % 20 == 0)) {
            tick();
        }
        renderDealtCards();
        this.goButton.draw(batch);
        this.font.draw(batch,abilityText,20,50);
        this.batch.end();
        this.roboTick++;
    }


    private void performPhase() {
        if (this.currentPhase == 0) {
            performProgrammingPhase();
            this.currentPhase++;
            return;
        }

        // Limit fps
        sleep(60);
    }

    private void activateTiles() {
        this.tileGrid.activateTiles();
    }

    /**
     * This method renders the cards the player has
     * been dealt.
     */
    private void renderDealtCards() {
        this.drawPositionX = 0;
        this.drawPositionY = 40 + TILE_SIZE * 4;

        // Draw background for dealt cards.

        this.dealtCardsBackgroundSprite.setPosition(this.drawPositionX, this.drawPositionY);
        this.dealtCardsBackgroundSprite.draw(this.batch);

        this.selectedCardsBackgroundSprite.draw(this.batch);

        this.currentAbility.getSprite().draw(this.batch);

        for (ProgramCard card : this.programHand) {
            card.getSprite().draw(this.batch);
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
        this.drawPositionY = 40 + TILE_SIZE * 4;
        for (int row = 0; row < this.GRID_ROWS; row++) {
            for (int column = 0; column < this.GRID_COLUMNS; column++) {

                // Retrieve current tile from grid
                Tile tileBeingDrawn = this.tileGrid.getTile(row, column);
                // Retrieve PriorityQueue of GameObjects on current tile
                PriorityQueue<GameObject> objectsOnTile = tileBeingDrawn.getObjectsOnTile();

                // Draw the tile
                Sprite spriteOfTile = tileBeingDrawn.getSprite();
                spriteOfTile.setPosition(this.drawPositionX, this.drawPositionY);
                spriteOfTile.draw(this.batch);

                // Draw GameObjects on tile
                for (GameObject gameObject : objectsOnTile) {
                    Sprite spriteOfGameObject = gameObject.getSprite();
                    spriteOfGameObject.setPosition(this.drawPositionX, this.drawPositionY);
                    spriteOfGameObject.draw(this.batch);
                }

                this.drawPositionX += this.TILE_SIZE;    // Moving the horizontal drawPosition, one tile over.
            }
            this.drawPositionX = this.TILE_SIZE * 4; // Resetting the horizontal drawPosition.
            this.drawPositionY += this.TILE_SIZE;    // Moving the vertical drawPosition, one tile up.
        }
        // Resetting the vertical drawPosition.
        this.drawPositionY = 0;

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


    //   ROUND LOGIC   //
    private void tick() {

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
        if (this.currentPhase <= 5) {
            // Runs per phase
            if (this.tileGrid.getPlayerCurrentMove(0) == Program.NONE) {
                activateTiles();
                this.tileGrid.applyNextProgram(0);
                this.currentPhase++;
                // Runs mid phase
            }
        }

        if (!(this.tileGrid.getPlayerCurrentMove(0) == Program.NONE)) {
            this.tileGrid.continueMove(0);
        } else if (this.currentPhase > 5){
            dealNewCards();
            sequenceReady = false;
            this.currentPhase = 0;
            activateTiles();
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

        this.tileGrid.resetPlayer(0);
        this.programDeck.reset();
        this.abilityDeck.reset();
        for(Player player : this.tileGrid.getPlayers()){
            int playerHealth = player.getHealth();
            player.drawCards(this.programDeck.deal(playerHealth), this.abilityDeck.deal(playerHealth));
        }

        if(this.currentAbility.getAbility() == this.emptyAbility.getAbility()){
            this.currentAbility = this.tileGrid.getPlayer(0).getAbilityHand().get(0);
            this.currentAbility.getSprite().setPosition(550,20);
        }

        for (int i = 0; i < this.programHand.size(); i++) {
            Vector2 pos = new Vector2(5 + i * 75, 560);

            this.programHand.get(i).setPosition(pos);
            this.programHand.get(i).getSprite().setPosition(pos.x, pos.y);
        }
    }


    private boolean isInsideSprite(float screenX, float screenY, Sprite sprite) {
        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()) {
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            // Moves the given sprite
            return Gdx.graphics.getHeight() - screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight() - screenY < sprite.getY() + sprite.getHeight();
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
    private boolean isInsideCard(float screenX, float screenY, RRCard card) {

        Sprite sprite = card.getSprite();
        if (isInsideSprite(screenX,screenY,sprite)){
            // Moves the given sprite
            if (card instanceof ProgramCard){
                moveSprite(sprite, screenX - sprite.getWidth() / 2, Gdx.graphics.getHeight() - screenY - sprite.getHeight() / 2);
                this.currentCard = (ProgramCard) card;
                this.currentSprite = sprite;
            }
            if (card instanceof AbilityCard){
                if(this.abilityText.equals("")){
                    this.abilityText = ((AbilityCard) card).getAbility().toString();
                } else {
                    this.abilityText = "";
                }
            }
            return true;
        }
        return false;
    }

    private void moveSprite(Sprite sprite, float newX, float newY) {
        this.batch.begin();
        sprite.setPosition(newX, newY);
        sprite.draw(this.batch);
        this.batch.end();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int nulls = 0;
        if (isInsideSprite(screenX, screenY, this.goButton)) {
            ArrayList<ProgramCard> chosenCards = this.CSI.getChosenCards();
            for(ProgramCard card : chosenCards){
                if (card.getPriority() == 0) nulls++;
            }
            if (nulls == 0) {
                this.tileGrid.getPlayer(0).pushProgram(chosenCards);
                CSI.reset();
                sequenceReady = true;
            }
        }
        isInsideCard(screenX,screenY,currentAbility);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        float cardDeltaH = cardTestSprite.getHeight() / 2;
        float cardDeltaW = cardTestSprite.getWidth() / 2;


        if (insideSprite) {
            Vector2 newPos = CSI.cardSnapPosition(this.currentCard, screenX + cardDeltaW,
                    Gdx.graphics.getHeight() - screenY - cardDeltaH);
            ProgramCard overlap = CSI.getCardOverlap();
            if (overlap.getPriority() != this.emptyProgram.getPriority()) {
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

        for (ProgramCard card : this.programHand) {
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
