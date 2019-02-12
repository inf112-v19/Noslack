package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.GameObjectType;

public class ConveyorNorth implements GameObject {
    private Texture texture;
    private Sprite sprite;
    private Orientation orientation;

    public ConveyorNorth(){
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }

    public ConveyorNorth(Orientation orientation){
        this.orientation = orientation;
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.CONVEYOR_NORTH;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/conveyorFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/conveyorFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/conveyorFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/conveyor/conveyorFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public int compareTo(Object other) {
        if(((GameObject) other).getGameObjectType() == GameObjectType.PLAYER){
            return -1;
        }else{
            return 1;
        }
    }
}
