package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;


public abstract class AI extends Robot {
    AIHand ProgramHand;
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
        if(!this.ProgramHand.containsTurn()) {
            return;
        }
        Orientation direction = this.position.orientationToPosition(target);
        if(!getOrientation().equals(direction)){
            Program turnNeeded = this.orientation.turnNeeded(direction);
            if(this.ProgramHand.contains(turnNeeded)){
                this.program.push(this.ProgramHand.get(turnNeeded));
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
                if(this.ProgramHand.contains(Program.RIGHT,2)&& this.program.size()<4){
                    this.program.push(ProgramHand.get(Program.RIGHT));
                    this.program.push(ProgramHand.get(Program.RIGHT));
                }
                else if(this.ProgramHand.contains(Program.LEFT,2)&& this.program.size()<4){
                    this.program.push(ProgramHand.get(Program.LEFT));
                    this.program.push(ProgramHand.get(Program.LEFT));
                }
                else{
                    program.push(ProgramHand.findTurn());
                }
                break;
            case RIGHT:
                if(this.ProgramHand.contains(Program.U)){
                    this.program.push(ProgramHand.get(Program.U));
                    if(this.ProgramHand.contains(Program.LEFT)){
                        this.program.push(ProgramHand.get(Program.LEFT));
                    }
                }
                else if(this.ProgramHand.contains(Program.LEFT,3)&& this.program.size()<4){
                    for (int i =0; i<3; i++){
                        this.program.push(ProgramHand.get(Program.LEFT));
                    }
                    return;
                }
                else{
                    program.push(ProgramHand.findTurn());
                }
                break;
            case LEFT:
                if(this.ProgramHand.contains(Program.U)){
                    this.program.push(ProgramHand.get(Program.U));
                    if(this.ProgramHand.contains(Program.RIGHT)){
                        this.program.push(ProgramHand.get(Program.RIGHT));
                    }
                }
                else if(this.ProgramHand.contains(Program.RIGHT,3)&& this.program.size()<4){
                    for (int i =0; i<3; i++) {
                        this.program.push(ProgramHand.get(Program.RIGHT));
                    }
                }
                else{
                    program.push(ProgramHand.findTurn());
                }
                break;
        }
    }

    private void applyMove(){
        if(this.ProgramHand.containsMove()) {
            this.program.push(this.ProgramHand.findMove());
        }
        else{
            return;
        }
    }
    private void isFull(){
        this.full = this.program.size()==5;
    }
}