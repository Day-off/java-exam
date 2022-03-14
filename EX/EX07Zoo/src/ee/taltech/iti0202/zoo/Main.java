package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animals.Animal;
import ee.taltech.iti0202.zoo.animals.AnimalBuilder;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;
import ee.taltech.iti0202.zoo.zoo.Zoo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Animal a1 = new AnimalBuilder().setName("Dog").setType(Animal.Type.MAMMAL)
                .setSound1("BARK-BARK").setDays(1).createAnimal();
        Animal a2 = new AnimalBuilder().setName("Turtle").setType(Animal.Type.AMPHIBIAN).setDays(3).createAnimal();
        Animal a3 = new AnimalBuilder().setName("Monkey").setType(Animal.Type.MAMMAL).setSound1("uuh").setSound2("aah")
                .setHungrySound("BANANA").setDays(1).createAnimal();
        Animal a4 = new AnimalBuilder().setName("Lammas").setType(Animal.Type.MAMMAL).setSound1("MAA").createAnimal();
        Animal a5 =new AnimalBuilder().setName("Parrot").setType(Animal.Type.BIRD).setSound1("HI-HI").setDays(2).createAnimal();

        Zoo zoo = new Zoo();

        Caretaker s1 = new Caretaker("Mari", List.of(Animal.Type.MAMMAL, Animal.Type.FISH));
        Caretaker s2 = new Caretaker("Kati", List.of(Animal.Type.MAMMAL,Animal.Type.AMPHIBIAN,Animal.Type.FISH));


        zoo.addAnimal(a1);
        zoo.addAnimal(a2);
        zoo.addAnimal(a3);
        zoo.addAnimal(a4);
        zoo.addAnimal(a5);

        zoo.addStuff(s1);
        zoo.addStuff(s2);

        System.out.println(zoo.getAllAnimals());
        System.out.println(zoo.getStuff());

        System.out.println(zoo.animalsOverview());
        System.out.println(zoo.animalsOverview());

        zoo.nexDay();
        zoo.nexDay();
        zoo.nexDay();
        zoo.getHungryAnimals().forEach(System.out::println);
        System.out.println(zoo.getBestCaretaker());
        System.out.println(zoo.animalsOverview());


    }
}
