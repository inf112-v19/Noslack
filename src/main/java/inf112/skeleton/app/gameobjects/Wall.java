package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.Program;

public class Wall implements GameObject {
    private Sprite sprite;
    private Orientation orientation;
    private GameObjectType type= GameObjectType.WALL;

    public Wall() {
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }
    public Wall(Orientation orientation1) {
        this.orientation = orientation1;
        evaluateSprite();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void evaluateSprite() {
        try{
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/oneWall32x32.png"));
            this.sprite = new Sprite(texture);
            switch (orientation) {
                default:
                    sprite.setRotation(0);
                    break;
                case FACING_NORTH:
                    sprite.setRotation(0);
                    break;
                case FACING_EAST:
                    sprite.setRotation(270);
                    break;
                case FACING_WEST:
                    sprite.setRotation(90);
                    break;
                case FACING_SOUTH:
                    sprite.setRotation(180);
                    break;
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    /**
     * Checks if a players orientation is such that it kan be stopped by a wall.
     * @param orientation the Players orientation
     * @return If the player may be effected by the wall.
     */
    public boolean possibleEffectPlayer(Orientation orientation){
        return (orientation.equals(this.orientation)||orientation.equals(this.orientation.opposite()));
    }

    /**
     * Checks if the player will hit the wall, and resets the players current move.
     * @param player Player on the tile
     * @param onTile Is player on the tile in question
     * @return does the player hit the wall with current move.
     */
    public boolean playerHitWall(Player player, boolean onTile){
        if(!possibleEffectPlayer(player.getOrientation()) && !player.getCurrentMove().isMove())
            return false;
        if(onTile){
            if (player.getCurrentMove().equals(Program.BACK) &&
                    this.orientation.equals(player.getOrientation().opposite())) {
                player.stopMove();
                return true;
            }
            if (!player.getCurrentMove().equals(Program.BACK) &&
                    this.orientation.equals(player.getOrientation())) {
                player.stopMove();
                return true;
            }
        }
        if(!onTile){
            if(player.getCurrentMove().equals(Program.BACK) &&
                    this.orientation.equals(player.getOrientation())) {
                player.stopMove();
                return true;
            }
            if(!player.getCurrentMove().equals(Program.BACK) &&
                    this.orientation.equals(player.getOrientation().opposite())) {
                player.stopMove();
                return true;
            }
        }
        return false;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        if(((GameObject)o).getGameObjectType() == GameObjectType.WALL){
            return -1;
        } else {
            return 1;
        }
    }

}
