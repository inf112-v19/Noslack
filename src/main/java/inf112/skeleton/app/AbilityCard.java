package inf112.skeleton.app;

/**
 * Abillty cards for the game RoboRally.
 * Uses the interface RRCard
 */
public class AbilityCard implements RRCard {
    private Ability ability;


    /**
     * Create ability card
     * @param ability Ability to be assigned to the card
     */
    public AbilityCard (String ability){
        setAbility(ability);
    }

    private void setAbility(String s){
        switch (s) {
            case "Ablative Coat":
                ability=Ability.AblativeCoat;
                break;
            case "Abort Switch":
                ability=Ability.AbortSwitch;
                break;
            case "Brakes":
                ability=Ability.Brakes;
                break;
            case "Circuit Breaker":
                ability=Ability.CircuitBreaker;
                break;
            case "Conditional Program":
                ability=Ability.ConditionalProgram;
                break;
            case "Crab Legs":
                ability=Ability.CrabLegs;
                break;
            case "Double-Barreled Laser":
                ability=Ability.DoubleBarreledLaser;
                break;
            case "Dual Processor":
                ability=Ability.DualProcessor;
                break;
            case "Extra Memory":
                ability=Ability.ExtraMemory;
                break;
            case "Fire Control":
                ability=Ability.FireControl;
                break;
            case "Flywheel":
                ability=Ability.Flywheel;
                break;
            case "Fourth Gear":
                ability=Ability.FourthGear;
                break;
            case "Gyroscopic Stabilizer":
                ability=Ability.GyroscopicStabilizer;
                break;
            case "High-Powered Laser":
                ability=Ability.HighPoweredLaser;
                break;
            case "Mechanical Arm":
                ability=Ability.MechanicalArm;
                break;
            case "Mini Howitzer":
                ability=Ability.MiniHowitzer;
                break;
            case "Power-Down Shield":
                ability=Ability.PowerDownShield;
                break;
            case "Pressor Beam":
                ability=Ability.PressorBeam;
                break;
            case "Radio Control":
                ability=Ability.RadioControl;
                break;
            case "Ramming Gear":
                ability=Ability.RammingGear;
                break;
            case "Rear-Firing Laser":
                ability=Ability.RearFiringLaser;
                break;
            case "Recompile":
                ability=Ability.Recompile;
                break;
            case "Reverse Gear":
                ability=Ability.ReverseGear;
                break;
            case "Scrambler":
                ability= Ability.Scrambler;
            case "Superior Archive":
                ability=Ability.SuperiorArchive;
                break;
            case "Tractor Beam":
                ability=Ability.TractorBeam;
                break;
            default:
                throw new IllegalArgumentException("Invalid ability: " + s);

        }
    }

    /**
     * @return The ability of the card, as an Ability enum.
     */
    public Ability getAbility() {
        return ability;
    }

    @Override
    public void render() {

    }
}
