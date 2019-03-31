package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.AIHand;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;


public abstract class AI extends Robot {
    AIHand hand;

    /**
     * Decides the program that the AIBot is to run
     * @param target Coordinate of target.
     */
    public void decideProgram(Coordinate target){
        while(this.program.size()<5) {
            turnDirectionOfTarget(target);
            applyMove();
        }
    }

    /**
     * Turns the AIBot towards the intended target
     * @param target Coordinate of target
     */
    private void turnDirectionOfTarget (Coordinate target){
        if(!this.hand.containsTurn()) {
            return;
        }
        Orientation direction = this.position.orientationToPosition(target);
        if(!getOrientation().equals(direction)){
            Program turnNeeded = this.orientation.turnNeeded(direction);
            if(this.hand.contains(turnNeeded)){
                this.program.push(this.hand.get(turnNeeded));
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
                if(this.hand.contains(Program.RIGHT,2)&& this.program.size()<4){
                    this.program.push(hand.get(Program.RIGHT));
                    this.program.push(hand.get(Program.RIGHT));
                }
                else if(this.hand.contains(Program.LEFT,2)&& this.program.size()<4){
                    this.program.push(hand.get(Program.LEFT));
                    this.program.push(hand.get(Program.LEFT));
                }
                else{
                    program.push(hand.findTurn());
                }
                break;
            case RIGHT:
                if(this.hand.contains(Program.U)){
                    this.program.push(hand.get(Program.U));
                    if(this.hand.contains(Program.LEFT)){
                        this.program.push(hand.get(Program.LEFT));
                    }
                }
                else if(this.hand.contains(Program.LEFT,3)&& this.program.size()<4){
                    for (int i =0; i<3; i++){
                        this.program.push(hand.get(Program.LEFT));
                    }
                    return;
                }
                else{
                    program.push(hand.findTurn());
                }
                break;
            case LEFT:
                if(this.hand.contains(Program.U)){
                    this.program.push(hand.get(Program.U));
                    if(this.hand.contains(Program.RIGHT)){
                        this.program.push(hand.get(Program.RIGHT));
                    }
                }
                else if(this.hand.contains(Program.RIGHT,3)&& this.program.size()<4){
                    for (int i =0; i<3; i++) {
                        this.program.push(hand.get(Program.RIGHT));
                    }
                }
                else{
                    program.push(hand.findTurn());
                }
                break;
        }
    }

    private void applyMove(){
        if(this.hand.containsMove()) {
            this.program.push(this.hand.findMove());
        }
        else{
            return;
        }
    }
}