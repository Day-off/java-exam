package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animals.Animal;
import ee.taltech.iti0202.zoo.animals.AnimalBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaretakerTest {
    Caretaker s1 = new Caretaker("Mari", List.of(Animal.Type.MAMMAL, Animal.Type.FISH));
    Caretaker s2 = new Caretaker("Kati", List.of(Animal.Type.MAMMAL, Animal.Type.AMPHIBIAN, Animal.Type.FISH));

    Animal a1 = new AnimalBuilder().setName("Dog").setType(Animal.Type.MAMMAL)
            .setSound1("BARK-BARK").setDays(1).createAnimal();
    Animal a2 = new AnimalBuilder().setName("Turtle").setType(Animal.Type.AMPHIBIAN).setDays(3).createAnimal();
    Animal a3 = new AnimalBuilder().setName("Monkey").setType(Animal.Type.MAMMAL).setSound1("uuh").setSound2("aah")
            .setHungrySound("BANANA").setDays(1).createAnimal();

    List<Animal> ex = new ArrayList<>();

    @Test
    public void PersonElements() {
        assertEquals("Caretaker{name='Kati', canFeed=[MAMMAL, AMPHIBIAN, FISH], tasks=[]}", s2.toString());
    }

    @Test
    public void feeding() {
        ex.add(a1);
        ex.add(a2);
        ex.add(a3);

        a1.changeTimer(1);
        a2.changeTimer(1);
        a3.changeTimer(1);

        s1.addTasks(ex);
        assertEquals(2, s1.getTasks().size());
        s1.feedAnimals();
        assertEquals(0, s1.getTasks().size());

    }
}