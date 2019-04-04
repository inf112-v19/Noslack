package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;


public abstract class AI extends Robot {
    AIHand programHand;
    boolean full;

    /**
     * Decides the program that the AIBot is to run
     * @param target Coordinate of target.
     */
    public void decideProgram(Coordinate target){
        isFull();
        while(!this.full) {
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
        if(!getOrientation().equals(direction)){
            Program turnNeeded = this.orientation.turnNeeded(direction);
            if(this.programHand.contains(turnNeeded)){
                this.program.push(this.programHand.get(turnNeeded));
            }
            else{
                turnNotFound(turnNeeded);
            }
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
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.LEFT)){
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.LEFT));
                }
                else if(this.programHand.contains(Program.LEFT,3)&& this.program.size()<4){
                    for (int i =0; i<3; i++){
                        this.program.push(programHand.get(Program.LEFT));
                    }
                    return;
                }
                break;
            case LEFT:
                if(this.programHand.contains(Program.U)&&this.programHand.contains(Program.RIGHT)){
                    this.program.push(programHand.get(Program.U));
                    this.program.push(programHand.get(Program.RIGHT));
                }
                else if(this.programHand.contains(Program.RIGHT,3)&& this.program.size()<4){
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
                if (this.ProgramHand.contains(Program.MOVE2) && this.ProgramHand.contains(Program.BACK)) {
                    this.program.push(ProgramHand.get(Program.MOVE2));
                    this.program.push(ProgramHand.get(Program.BACK));
                } else if (this.ProgramHand.contains(Program.U) && this.ProgramHand.contains(Program.BACK)) {
                    this.program.push(ProgramHand.get(Program.U));
                    this.program.push(ProgramHand.get(Program.BACK));
                }
                break;
        }
    }

    private void applyMove(){
        if(this.programHand.containsMove()) {
            this.program.push(this.programHand.findMove());
        }
    }
    private void isFull(){
        this.full = this.program.size()==5;
    }

    public void pushProgram(ArrayList<ProgramCard> selectedCards){

    }
}