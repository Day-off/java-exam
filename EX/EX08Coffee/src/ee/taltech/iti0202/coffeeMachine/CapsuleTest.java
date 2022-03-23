package ee.taltech.iti0202.coffeeMachine;

import ee.taltech.iti0202.drinks.Drinks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapsuleTest {

    @Test
    public void testCapsule(){
        Capsule a = new Capsule(Drinks.DrinksTypes.CAPPUCCINO);
        a.useCapsule();
        assertTrue(a.getIsUsed());
    }

}