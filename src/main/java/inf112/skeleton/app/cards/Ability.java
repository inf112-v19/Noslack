package inf112.skeleton.app.cards;

public enum Ability {
    AblativeCoat, AbortSwitch,Brakes,CircuitBreaker, ConditionalProgram,
    CrabLegs,DoubleBarreledLaser,DualProcessor, ExtraMemory, FireControl, Flywheel,
    FourthGear, GyroscopicStabilizer, HighPoweredLaser, MechanicalArm, MiniHowitzer,
    PowerDownShield, PressorBeam, RadioControl, RammingGear, RearFiringLaser,
    Recompile, ReverseGear, Scrambler, SuperiorArchive, TractorBeam;

    @Override
    public String toString() {
        if(this.equals(AblativeCoat)) return "Ablatice Coat: Ablative Coat absorbs " +
                "the next 3 Damage your robot recieves. Put those Damage tokens " +
                "onto this ard instead of onto your Program Sheet. Discard this card " +
                "and the tokens when you put the third one on.";
        if (this.equals(AbortSwitch)) return "Abort Switch: Once each turn, you may " +
                "replace one of the Program cards you reveal with the top card from " +
                "the dek. If you do, you must replace the Program cards for all your " +
                "remaining register the same way that turn.";
        if (this.equals(Brakes)) return "Brakes: Whenever you execute a Move 1, you " +
                "may move your robot 0 spaces instead of 1. Priority is that of " +
                "the Move 1.";
        if (this.equals(CircuitBreaker)) return "Circuit Breaker: If you have 3 or more " +
                "Damage tokens on your Program Sheet at the end of your turn, your robot " +
                "will begin the next turn powered down.";
        if (this.equals(ConditionalProgram)) return "Conditional Program: After you program " +
                "registers each turn, you may put one of the Program cards left in your hand " +
                "face down onto this Option instead of discarding it. Later that turn, you can " +
                "substitute that card for one you had programmed in any register, discarding the " +
                "original card. Announce the change before anyone reveals Program cards for that " +
                "register. If you put a card on this option and don't use it, discard it at the " +
                "end of the turn";
        if (this.equals(CrabLegs)) return "Crab Legs: When programming your registers, you may put " +
                "a Move 1 card in the same register as a Rotate Left or Rotate Right card. If you do, " +
                "during that register your robot will move 1 space to left or right, respectively, " +
                "without rotating. Priority is that of the Move 1.";
        if (this.equals(DoubleBarreledLaser)) return "Double Barreled Laser: Whenever your robot fires " +
                "its main laser, it fires two shots instead of one. You mat use thus Option with Fire " +
                "Control and/or High-Power Laser.";
        if (this.equals(DualProcessor)) return "Dual Processor: ";

        else return "No ability assigned";
    }
}