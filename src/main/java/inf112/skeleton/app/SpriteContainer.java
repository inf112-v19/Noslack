package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.cards.AbilityCard;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.cards.RRCard;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.tiletypes.Flag;
import inf112.skeleton.app.gameobjects.tiletypes.Teleporter;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SpriteContainer {

    private Sprite cardTestSprite;
    private Sprite cardSlot;
    private Sprite currentSprite;
    private ProgramCard currentCard;
    private String abilityText;
    private Sprite goButton;
    private Sprite muteButton;
    private Sprite unMuteButton;
    private Sprite background;
    private Sprite lifeHeart;
    private Sprite cardBack;
    private Sprite emptyCard;
    private Sprite powerDownButton;
    private Sprite poweredDownButton;
    private SpriteBatch batch;
    private int drawPositionX;
    private int drawPositionY;
    private final int TILE_SIZE = 32;
    private AbilityCard currentAbility;
    private AbilityCard emptyAbility;
    private BitmapFont font;
    private int gridRows = 12;
    private int gridColumns = 12;
    private boolean mute = false;
    private float inc = 0;
    private ProgramCard hoverCard;
    private boolean hovered;
    private boolean poweredDown = false;

    public SpriteContainer(SpriteBatch batch){
        this.batch = batch;
        initiate();
    }

    public SpriteContainer(SpriteBatch batch, int rows, int columns){
        this.gridRows = rows;
        this.gridColumns = columns;
        this.batch = batch;
        initiate();
    }

    private void initiate(){
        this.background = setSprite("./assets/background.png");
        this.background.setPosition(0, 0);
        this.cardSlot = setSprite("./assets/cards/cardSlot.png");
        this.cardTestSprite = setSprite("./assets/cards/back-up.png");
        this.goButton = setSprite("./assets/buttons/runButton.png");
        this.goButton.setPosition(33, 220);
        this.muteButton = setSprite("./assets/buttons/muteButton.png");
        this.muteButton.setPosition(25, 300);
        this.unMuteButton = setSprite("./assets/buttons/unMuteButton.png");
        this.unMuteButton.setPosition(25, 300);
        this.lifeHeart = setSprite("./assets/player/lifeHeart32x32.png");
        this.cardBack = setSprite("./assets/cards/cardBackside.png");
        this.cardBack.setPosition(730,400);
        this.emptyCard = setSprite("./assets/cards/emptyCard.png");
        this.emptyCard.setPosition(550,30);
        this.powerDownButton = setSprite("./assets/buttons/powerDownButton.png");
        this.powerDownButton.setPosition(33,170);
        this.poweredDownButton = setSprite("./assets/buttons/poweredDownButton.png");
        this.poweredDownButton.setPosition(33,170);

        this.emptyAbility = new AbilityCard(" ");
        this.currentAbility = emptyAbility;
        this.abilityText = "";

        this.font = new BitmapFont();
        this.font.setColor(0,0,0,1);
    }

    public void renderDealtCards(ArrayList<ProgramCard> programHand) {
        this.drawPositionX = 0;
        this.drawPositionY = 40 + TILE_SIZE * 4;

        for (int i = 0; i < 5; i++) {
            this.cardSlot.setPosition((20+100*i),25);
            this.cardSlot.draw(this.batch);
            this.font.draw(this.batch,(i+1)+"",(60+100*i),94);
        }
        String ability = (""+currentAbility).substring(0, (""+currentAbility).indexOf(':'));
        this.emptyCard.draw(this.batch);
        font.draw(this.batch,ability,emptyCard.getX()+20-ability.length()/2,emptyCard.getY()+60);
        //this.currentAbility.getSprite().draw(this.batch);
        try {
            for (ProgramCard card : programHand) {
                card.getSprite().draw(this.batch);
                if (card != hoverCard || !hovered) {
                    font.setColor(255, 255, 255, 1);
                    font.draw(this.batch, "" + card.getPriority(), card.getSprite().getX() + 7, card.getSprite().getY() + 100);
                    font.draw(this.batch, "" + card.getProgram(), card.getSprite().getX() + 7, card.getSprite().getY() + 17);
                }
            }
            this.cardBack.draw(this.batch);
        }catch (Exception e){
            System.out.println("Error in renderDealtCards");
        }
    }

    public void getCardSprite(AbilityCard abilityCard){
        this.currentAbility=abilityCard;

    }

    public Sprite setSprite(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        return new Sprite(texture);
    }

    public void renderGrid(TileGrid tileGrid){
        renderFlexibleGrid(tileGrid, false, 0, 0,0);
    }

    public void renderFlexibleGrid(TileGrid tileGrid, boolean creating, int size, int startx, int starty) {
        float scale = 1;
        if (size == 0){
            size = TILE_SIZE;
        } else {
            scale = 0.3f;
        }
        background.draw(this.batch);

        // Work in progress

        // Start draw position after the dealt cards.
        if(!creating){
            this.drawPositionX = size * 4;
            this.drawPositionY = 40 + size * 4;
        } else {
            this.drawPositionX = startx;
            this.drawPositionY = starty;
        }
        for (int row = 0; row < this.gridRows; row++) {
            for (int column = 0; column < this.gridColumns; column++) {

                // Retrieve current tile from grid
                Tile tileBeingDrawn = tileGrid.getTile(row, column);
                // Retrieve PriorityQueue of GameObjects on current tile
                PriorityQueue<GameObject> objectsOnTile = tileBeingDrawn.getObjectsOnTile();

                // Draw the tile
                Sprite spriteOfTile = tileBeingDrawn.getSprite();
                spriteOfTile.setPosition(this.drawPositionX, this.drawPositionY);
                spriteOfTile.setScale(scale);
                spriteOfTile.draw(this.batch);

                // Draw GameObjects on tile
                for (GameObject gameObject : objectsOnTile) {
                    Sprite spriteOfGameObject = gameObject.getSprite();
                    spriteOfGameObject.setPosition(this.drawPositionX, this.drawPositionY);
                    spriteOfGameObject.setScale(scale);
                    spriteOfGameObject.draw(this.batch);
                    if (!creating){
                        if (gameObject.getGameObjectType() == GameObjectType.FLAG){
                            this.font.draw(this.batch,((Flag)gameObject).getFlagNumber()+"",drawPositionX+spriteOfGameObject.getWidth()/2,drawPositionY+spriteOfGameObject.getHeight()/2);
                        }
                        if (gameObject.getGameObjectType() == GameObjectType.TELEPORTER){
                            this.font.draw(this.batch,((Teleporter)gameObject).getTeleporterNr()+"",drawPositionX+spriteOfGameObject.getWidth()/3,drawPositionY+spriteOfGameObject.getHeight()/2+7);
                        }
                    }
                }

                this.drawPositionX += size;    // Moving the horizontal drawPosition, one tile over.
            }
            this.drawPositionY += size;

            if(!creating){
                this.drawPositionX = size * 4; // Resetting the horizontal drawPosition.
            } else {
                this.drawPositionX = startx; // Moving the vertical drawPosition, one tile up.
            }
        }

        // Resetting the vertical drawPosition.
        this.drawPositionY = 0;

        if(!creating){
            //Drawing the go button
            goButton.draw(this.batch);
            if (poweredDown){
                poweredDownButton.draw(this.batch);
            } else {
                powerDownButton.draw(this.batch);
            }
            if (mute){
                muteButton.draw(this.batch);
            } else {
                unMuteButton.draw(this.batch);
            }
            for (int i = 0; i < tileGrid.getRobot(0).getLives(); i++){
                this.lifeHeart.setPosition((12+40*i), 350);
                this.lifeHeart.draw(this.batch);
            }
            font.setColor(0,255,0,1);
            font.draw(this.batch,"HP: "+tileGrid.getRobot(0).getHealth(),80,320);
            font.setColor(255,255,255,1);
        }
    }

    public boolean isInsideSprite(float screenX, float screenY, Sprite sprite){

        // Boolean to see if the coordinates is inside given sprite in the x-axis
        if (screenX >= sprite.getX() && screenX < sprite.getX() + sprite.getWidth()) {
            // Checks y-axis, but considered that the Y given is starting at the top of the screen
            // Moves the given sprite
            return Gdx.graphics.getHeight() - screenY >= sprite.getY() &&
                    Gdx.graphics.getHeight() - screenY < sprite.getY() + sprite.getHeight();
        }
        return false;
    }

    private void setAbilityText(String text){this.abilityText = text;}

    public String getAbilityText(){return this.abilityText;}

    private void setCurrentCard(ProgramCard card){this.currentCard = card;}

    public ProgramCard getCurrentCard() {return this.currentCard; }

    public Sprite getCurrentSprite() {return this.currentCard.getSprite();}

    //Method checks if click is performed on a card
    public boolean isInsideCard(float screenX, float screenY, RRCard card) {
        Sprite sprite = card.getSprite();
        if (isInsideSprite(screenX,screenY,sprite)){
            // Moves the given sprite
            if (card instanceof ProgramCard){
                moveSprite(sprite, screenX - sprite.getWidth() / 2, Gdx.graphics.getHeight() - screenY - sprite.getHeight() / 2);
                setCurrentCard((ProgramCard) card);
            }
            if (card instanceof AbilityCard){
                if(this.abilityText.equals("")){
                    setAbilityText(((AbilityCard) card).getAbility().toString());
                } else {
                    setAbilityText("");
                }
            }
            return true;
        }
        return false;
    }

    //Method to move sprites
    public void moveSprite(Sprite sprite, float newX, float newY) {
        this.batch.begin();
        sprite.setPosition(newX, newY);
        sprite.draw(this.batch);
        this.batch.end();
    }

    public boolean isInsideBack(float screenX, float screenY){
        return isInsideSprite(screenX,screenY,this.cardBack);
    }

    private float hoverScale(){
        inc += 0.1;
        float num = 1+(float)(Math.abs(Math.cos(inc))/3);
        return num;
    }

    public void isHoveringCard(float screenX, float screenY, ArrayList<ProgramCard> hand){
        float scale = hoverScale();
        if (hovered){
            hoverCard.getSprite().setScale(1);
        }
        boolean hover = false;

        for(ProgramCard card : hand){
            if (isInsideSprite(screenX,screenY,card.getSprite())){
                hoverCard = card;
                hover = true;
                hovered = true;
                card.getSprite().setScale(scale);


            }
        }
        if(isInsideGo(screenX, screenY)){
            goButton.setScale(scale);
        } else {
            goButton.setScale(1);
        }
        if (!hover && hovered){
            hoverCard.getSprite().setScale(1);
            inc = 0;
            hovered = false;
        }
    }

    public boolean isInsideGo(float screenX, float screenY){
        return isInsideSprite(screenX,screenY,this.goButton);
    }

    //Method to see if click is performed on powerDown button
    public boolean isInsidePowerDown(float screenX, float screenY) {
        if (isInsideSprite(screenX,screenY,this.powerDownButton)){
            poweredDown = !poweredDown;
            return true;
        }
        return false;
    }

    public void powerUP(){
        poweredDown= false;
    }

    //Method to check if click is performed on mute/unmute button
    public boolean isInsideMute(float screenX, float screenY){
        if (isInsideSprite(screenX,screenY,this.muteButton)){
            mute = !mute;
            return true;
        }
        return false;
    }

    //Method to draw a Textvbox
    public void drawTextBox(String text, int length){
        ArrayList<String> lines = new ArrayList<>();

        String sentence = "";
        int i = 0;
        for (String words : text.split(" ")){
            if ((sentence+words).length() > length){
                lines.add(i,sentence);
                i++;
                sentence = "";
            }
            sentence += words + " ";
        }
        if (sentence.length() > 0){
            lines.add(i,sentence);
        }
        int y = 170+i*20;
        i = 0;
        for (String line : lines){
            this.font.draw(this.batch,line,540,y-i*20);
            i++;
        }
    }

    //Method to draw a text from a abilitycard
    public void drawAbilityText(){
        drawTextBox(this.abilityText, 50);
    }


    //Method for Manual testing of Sprites existence
    public void drawAll(ArrayList<Sprite> sprites){


        try{
            unMuteButton.draw(batch);
            cardSlot.draw(batch);
            goButton.draw(batch);
            muteButton.draw(batch);
            lifeHeart.draw(batch);
            cardBack.draw(batch);
            emptyCard.draw(batch);
            powerDownButton.draw(batch);
            poweredDownButton.draw(batch);

            for(Sprite s :sprites) {
                s.draw(batch);
            }
        }
        catch (Exception e){
            System.out.println("A sprite could not be found");

        }





    }

}
