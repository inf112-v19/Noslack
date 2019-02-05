package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ProgramCard {
    private int priority;
    private ProgramType programType;
    private Texture texture;
    private Sprite sprite;

    public ProgramCard(int priority, ProgramType programType){
        this.priority = priority;
        this.programType = programType;
        evaluateSprite();
    }

    private void evaluateSprite(){
        switch(programType){
            case MOVE_ONE:
                texture = new Texture(Gdx.files.internal("/assets/moveOneSprite.png"));
                sprite = new Sprite(texture);
                break;
            case MOVE_TWO:
                texture = new Texture(Gdx.files.internal("/assets/moveTwoSprite.png"));
                sprite = new Sprite(texture);
                break;
            case MOVE_THREE:
                texture = new Texture(Gdx.files.internal("/assets/moveThreeSprite.png"));
                sprite = new Sprite(texture);
                break;
            case BACKUP:
                texture = new Texture(Gdx.files.internal("/assets/backupSprite.png"));
                sprite = new Sprite(texture);
                break;
            case TURN_LEFT:
                texture = new Texture(Gdx.files.internal("/assets/turnLeftSprite.png"));
                sprite = new Sprite(texture);
                break;
            case TURN_RIGHT:
                texture = new Texture(Gdx.files.internal("/assets/turnRightSprite.png"));
                sprite = new Sprite(texture);
                break;
            case U_TURN:
                texture = new Texture(Gdx.files.internal("/assets/uTurnSprite.png"));
                sprite = new Sprite(texture);
                break;
            default:
                texture = new Texture(Gdx.files.internal("/assets/error.png"));
                sprite = new Sprite(texture);
                break;
        }
    }

    public Sprite getSprite(){
        return sprite;
    }

    public int getPriority(){
        return priority;
    }

}
