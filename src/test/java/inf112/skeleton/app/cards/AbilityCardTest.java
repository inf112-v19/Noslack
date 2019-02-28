package inf112.skeleton.app.cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbilityCardTest {

    @Test
    public void getAbility() {
        String ability = "Fire Control";
        AbilityCard card = new AbilityCard(ability);
        assertSame(card.getAbility(),Ability.FireControl);
    }

    @Test
    public void getAbility2() {
        String ability = "Crab Legs";
        AbilityCard card = new AbilityCard(ability);
        assertSame(card.getAbility(),Ability.CrabLegs);
    }

    @Test
    public void TestToString() {
        String ability = "Crab Legs";
        AbilityCard card = new AbilityCard(ability);
        String s = Ability.CrabLegs.toString();
        assertSame(card.toString(),s);
    }
}