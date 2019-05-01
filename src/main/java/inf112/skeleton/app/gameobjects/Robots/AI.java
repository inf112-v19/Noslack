package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;


public abstract class AI extends Robot {
    AIHand programHand;
    boolean full;
    TileGrid tileGrid;

    /**
     * Get the TileGrid of the map so the AI kan keep track of its surroundings.
     * @param tileGrid The TileGrid
     */
    public void getTileGrid(TileGrid tileGrid){
        this.tileGrid = tileGrid;
    }

    /**
     * Decides the program that the AIBot is to run
     * @param target Coordinate of target.
     */
    public void decideProgram(Coordinate target){
        isFull();
        while(!this.full) {
            System.out.println("Target : " + target);
            System.out.println("ProgramSize: " + this.program.size());
            turnDirectionOfTarget(target);
            applyMove();
            isFull();
        }
    }

    /**
     * Turns the AIBot towards the intended target
     * @param target Coordinate of target
     */
    private void turnDirectionOfTarget (Coordinate target){
        if(!this.programHand.containsTurn()) {
            return;
        }
        Orientation direction = this.position.orientationToPosition(target);
        System.out.println("Direction: " + direction);

        if(!getOrientation().equals(direction)){
            Program turnNeeded = this.orientation.turnNeeded(direction);
            if(this.programHand.contains(turnNeeded)){
                this.program.push(this.programHand.get(turnNeeded));
            }
            else{
                turnNotFound(turnNeeded);
            }
        }else{
            return;
        }

    }

    /**
     * Tries to find a turning alternative if most effective program is not present
     * @param turnNeeded The ideal program.
     */
    private void turnNotFound(Program turnNeeded){
        switch (turnNeeded){
            case U:
                if(this.programHand.contains(Program.RIGHT,2)&& this.program.size()<4){
                    this.program.push(programHand.get(Program.RIGHT));
                    this.program.push(programHand.get(Program.RIGHT));
                }
                else if(this.programHand.contains(Program.LEFT,2)&& this.program.size()<4){
                    this.program.push(programHand.get(Program.LEFT));
                    this.program.push(programHand.get(Program.LEFT));
                }
                break;
            case RIGHT:
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.LEFT)&&this.program.size()<4){
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.LEFT));
                }
                else if(this.programHand.contains(Program.LEFT,3)&& this.program.size()<3){
                    for (int i =0; i<3; i++){
                        this.program.push(programHand.get(Program.LEFT));
                    }
                    return;
                }
                break;
            case LEFT:
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.RIGHT)&&this.program.size()<4){
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.RIGHT));
                }
                else if(this.programHand.contains(Program.RIGHT,3)&& this.program.size()<3){
                    for (int i =0; i<3; i++) {
                        this.program.push(programHand.get(Program.RIGHT));
                    }
                }
                break;
        }
        if(this.programHand.containsTurn()){
            this.program.push(this.programHand.findTurn());
        }
    }

    private void moveNotFound(Program moveNeeded) {
        switch(moveNeeded) {
            case MOVE1:
                if (this.programHand.contains(Program.MOVE2) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.program.push(programHand.get(Program.MOVE2));
                    this.program.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.U) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.BACK) && this.programHand.contains(Program.MOVE2) && program.size() < 4) {
                    this.program.push(programHand.get(Program.BACK));
                    this.program.push(programHand.get(Program.MOVE2));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.BACK, 2) && program.size() < 3) {
                    this.program.push(programHand.get(Program.MOVE3));
                    this.program.push(programHand.get(Program.BACK));
                    this.program.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.U)
                        && this.programHand.contains(Program.MOVE2) && program.size() < 3) {
                    this.program.push(programHand.get(Program.MOVE3));
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.MOVE2));
                } else if (this.programHand.contains(Program.MOVE2) && this.programHand.contains(Program.U)
                        && this.programHand.contains(Program.MOVE1) && program.size() < 3) {
                    this.program.push(programHand.get(Program.MOVE2));
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.MOVE1));
                }else{
                    this.program.push(programHand.getFirst());
                }
                break;
            case MOVE2:
                if (this.programHand.contains(Program.MOVE1, 2) && program.size() < 4) {
                    this.program.push(programHand.get(Program.MOVE1));
                    this.program.push(programHand.get(Program.MOVE1));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.program.push(programHand.get(Program.MOVE3));
                    this.program.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.U) && this.programHand.contains(Program.BACK, 2) && program.size() < 3) {
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.BACK));
                    this.program.push(programHand.get(Program.BACK));
                }
        }
    }

    private void applyMove(){
        if(this.programHand.containsMove()) {
            ProgramCard nextMove = this.programHand.findMove();
            System.out.println("NextMove: " + nextMove);
            this.program.push(nextMove);
        }else{
            System.out.println("NextMove: Does not contain move");
            moveNotFound(Program.MOVE1);
        }
    }
    private void isFull(){
        this.full = this.program.size()>=5;
    }

    public void pushProgram(ArrayList<ProgramCard> selectedCards){

    }

    @Override
    public boolean isAI(){
        return true;
    }
}