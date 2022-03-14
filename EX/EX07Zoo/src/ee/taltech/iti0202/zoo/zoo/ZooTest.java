package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.animals.Animal;
import ee.taltech.iti0202.zoo.animals.AnimalBuilder;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZooTest {
    Animal a1 = new AnimalBuilder().setName("Dog").setType(Animal.Type.MAMMAL)
            .setSound1("BARK-BARK").setDays(1).createAnimal();
    Animal a2 = new AnimalBuilder().setName("Turtle").setType(Animal.Type.AMPHIBIAN).setDays(3).createAnimal();
    Animal a3 = new AnimalBuilder().setName("Monkey").setType(Animal.Type.MAMMAL).setSound1("uuh").setSound2("aah")
            .setHungrySound("BANANA").setDays(1).createAnimal();
    Animal a4 = new AnimalBuilder().setName("Lammas").setType(Animal.Type.MAMMAL).setSound1("MAA").createAnimal();
    Animal a5 = new AnimalBuilder().setName("Parrot").setType(Animal.Type.BIRD).setSound1("HI-HI").setDays(2).createAnimal();

    Zoo zoo = new Zoo();

    Caretaker s1 = new Caretaker("Mari", List.of(Animal.Type.MAMMAL, Animal.Type.FISH));
    Caretaker s2 = new Caretaker("Kati", List.of(Animal.Type.MAMMAL, Animal.Type.AMPHIBIAN, Animal.Type.FISH));

    @Test
    public void addAllElements() {
        zoo.addAnimal(a1);
        zoo.addAnimal(a2);
        zoo.addAnimal(a3);
        zoo.addAnimal(a4);
        zoo.addAnimal(a5);

        zoo.addStuff(s1);
        zoo.addStuff(s2);

        assertEquals(5, zoo.getAllAnimals().size());
        assertEquals(2, zoo.getStuff().size());

        System.out.println(zoo.animalsOverview());

    }

    @Test
    public void bestCaretaker() {
        addAllElements();
        assertEquals(s2, zoo.getBestCaretaker());
    }

    @Test
    public void nextDay() {
        addAllElements();
        zoo.nexDay();
        zoo.nexDay();
        zoo.nexDay();
        assertEquals(1, zoo.getHungryAnimals().size());
    }

}