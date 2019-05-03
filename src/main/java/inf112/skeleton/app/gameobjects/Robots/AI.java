package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.AICoordinate;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;


public abstract class AI extends Robot {
    AIHand programHand;
    boolean full;
    AICoordinate trackingCoordinate;

    /**
     * Decides the program that the AIBot is to run
     * @param target Coordinate of target.
     */
    public void decideProgram(Coordinate target){
        isFull();
        this.trackingCoordinate = new AICoordinate(this.position);
        while(!this.full) {
            turnDirectionOfTarget(target);
            applyMove();
            isFull();
        }
        int size=5;
        if(this.health<5){
            size=this.health;
        }
        while(program.size()>size){
            this.program.pop();
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
        Orientation direction = this.trackingCoordinate.orientationToPosition(target);

        if(!getOrientation().equals(direction)){
            Program turnNeeded = this.orientation.turnNeeded(direction);
            if(this.programHand.contains(turnNeeded)){
                this.push(this.programHand.get(turnNeeded));
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
                if(this.programHand.contains(Program.RIGHT,2)&& this.program.size()<this.health){
                    this.push(programHand.get(Program.RIGHT));
                    this.push(programHand.get(Program.RIGHT));
                }
                else if(this.programHand.contains(Program.LEFT,2)&& this.program.size()<4){
                    this.push(programHand.get(Program.LEFT));
                    this.push(programHand.get(Program.LEFT));
                }
                break;
            case RIGHT:
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.LEFT)&&this.program.size()<4){
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.LEFT));
                }
                else if(this.programHand.contains(Program.LEFT,3)&& this.program.size()<3){
                    for (int i =0; i<3; i++){
                        this.push(programHand.get(Program.LEFT));
                    }
                    return;
                }
                break;
            case LEFT:
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.RIGHT)&&this.program.size()<4){
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.RIGHT));
                }
                else if(this.programHand.contains(Program.RIGHT,3)&& this.program.size()<3){
                    for (int i =0; i<3; i++) {
                        this.push(programHand.get(Program.RIGHT));
                    }
                }
                break;
        }
        if(this.programHand.containsTurn()){
            this.push(this.programHand.findTurn());
        }
    }

    private void moveNotFound(Program moveNeeded) {
        switch(moveNeeded) {
            case MOVE1:
                if (this.programHand.contains(Program.MOVE2) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.push(programHand.get(Program.MOVE2));
                    this.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.U) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.BACK) && this.programHand.contains(Program.MOVE2) && program.size() < 4) {
                    this.push(programHand.get(Program.BACK));
                    this.push(programHand.get(Program.MOVE2));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.BACK, 2) && program.size() < 3) {
                    this.push(programHand.get(Program.MOVE3));
                    this.push(programHand.get(Program.BACK));
                    this.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.U)
                        && this.programHand.contains(Program.MOVE2) && program.size() < 3) {
                    this.push(programHand.get(Program.MOVE3));
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.MOVE2));
                } else if (this.programHand.contains(Program.MOVE2) && this.programHand.contains(Program.U)
                        && this.programHand.contains(Program.MOVE1) && program.size() < 3) {
                    this.push(programHand.get(Program.MOVE2));
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.MOVE1));
                }else{
                    this.push(programHand.getFirst());
                }
                break;
            case MOVE2:
                if (this.programHand.contains(Program.MOVE1, 2) && program.size() < 4) {
                    this.push(programHand.get(Program.MOVE1));
                    this.push(programHand.get(Program.MOVE1));
                } else if (this.programHand.contains(Program.MOVE3) && this.programHand.contains(Program.BACK) && program.size() < 4) {
                    this.push(programHand.get(Program.MOVE3));
                    this.push(programHand.get(Program.BACK));
                } else if (this.programHand.contains(Program.U) && this.programHand.contains(Program.BACK, 2) && program.size() < 3) {
                    this.push(programHand.get(Program.U));
                    this.push(programHand.get(Program.BACK));
                    this.push(programHand.get(Program.BACK));
                }
        }
    }

    private void applyMove(){
        if(this.programHand.containsMove()) {
            ProgramCard nextMove = this.programHand.findMove();
            this.push(nextMove);
        }else{
            moveNotFound(Program.MOVE1);
        }
    }
    private void isFull(){
        this.full = this.program.size()>=5;
    }

    public void pushProgram(ArrayList<ProgramCard> selectedCards){

    }
    private void push(ProgramCard card){
        this.program.push(card);
        this.trackingCoordinate.moveAICoordinate(card.getProgram());
    }

    @Override
    public void reset() {
        powerUp();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    @Override
    public boolean isAI(){
        return true;
    }
}