package ee.taltech.iti0202.zoo.animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnimalTest {
    Animal a1 = new AnimalBuilder().setName("Dog").setType(Animal.Type.MAMMAL)
            .setSound1("BARK-BARK").setDays(1).createAnimal();
    Animal a2 = new AnimalBuilder().setName("Turtle").setType(Animal.Type.AMPHIBIAN).setDays(3).createAnimal();
    Animal a3 = new AnimalBuilder().setName("Monkey").setType(Animal.Type.MAMMAL).setSound1("uuh").setSound2("aah")
            .setHungrySound("BANANA").setDays(1).createAnimal();
    Animal a4 = new AnimalBuilder().setName("Lammas").setType(Animal.Type.MAMMAL).setSound1("MAA").createAnimal();


    @Test
    public void animal() {
        assertEquals("Animal{type='MAMMAL', feedTimer=1, needToFeed=1, name='Dog'}", a1.toString());
    }

    @Test
    public void changeSound() {
        a1.changeTimer(1);
        a3.changeTimer(1);
        assertEquals("", a1.getDefaultSound());
        assertEquals("BANANA", a3.getDefaultSound());
    }

    @Test
    public void turtle() {
        assertEquals("", a2.getDefaultSound());
    }

    @Test
    public void monkey() {
        assertEquals("BANANA", a3.getHungrySound());
    }

    @Test
    public void sheep() {
        assertNull(a4.getFeedTimer());
    }

}
