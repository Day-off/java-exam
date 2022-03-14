package ee.taltech.iti0202.zoo.zoo;

import ee.taltech.iti0202.zoo.animals.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Zoo {

    private final List<Caretaker> stuff = new ArrayList<>();
    private final List<Animal> allAnimals = new ArrayList<>();
    private final List<Animal> hungryAnimals = new ArrayList<>();
    private final Map<Caretaker, Integer> bestCare = new HashMap<>();

    /***
     * Constructor
     */
    public Zoo() {

    }

    /*
    ADD
     */

    /***
     *Adding new stuff
     * @param newStuff
     */
    public void addStuff(Caretaker newStuff) {
        if (!stuff.contains(newStuff)) {
            stuff.add(newStuff);
        }
    }

    /***
     *Adding new animal
     * @param newAnimal
     */
    public void addAnimal(Animal newAnimal) {
        if (!allAnimals.contains(newAnimal)) {
            allAnimals.add(newAnimal);
        }
    }


    /*
    *
    MAIN METHODS
    *
     */

    /***
     * Find all hungry animals in the zoo
     */
    public void findHungryAnimals() {
        for (Animal animal : allAnimals) {
            if (animal.getFeedTimer() != null && animal.getFeedTimer() <= 0 && !hungryAnimals.contains(animal)) {
                hungryAnimals.add(animal);
            }
        }
    }

    /***
     * Check if some animals from Hungry list get fed, then remove them
     */
    public void checkHungryAnimals() {
        hungryAnimals.removeIf(animal -> animal.getFeedTimer() > 0);
    }

    /***
     * Get all animals sounds
     * @return String
     */
    public String animalsOverview() {
        StringBuilder res = new StringBuilder();
        for (Animal animal : allAnimals) {
            res.append(animal.getName()).append(" ").append("(")
                    .append(animal.getType()).append(")").append(":")
                    .append(" '").append(animal.getDefaultSound()).append("'").append("\n");
        }
        return res.toString();
    }

    /***
     * Find most effective stuff
     */
    public void bestCaretaker() {
//        findHungryAnimals();
        for (Caretaker person : stuff) {
            int count = 0;
            for (Animal anim : allAnimals) {
                if (person.getCanFeed().contains(anim.getType())) {
                    count += 1;
                }
            }
            bestCare.put(person, count);
        }
    }

    /***
     * Stuff feed animals
     */
    public void feeding() {
        for (Caretaker stuf : stuff) {
            findHungryAnimals();
            stuf.addTasks(hungryAnimals);
            stuf.feedAnimals();
            checkHungryAnimals();
        }
    }

    /***
     * Next day animals are getting hungrier, and stuff feeding them
     */
    public void nexDay() {
        for (Animal animal : allAnimals) {
            if (animal.getFeedTimer() != null) {
                animal.changeTimer(1);
            }
        }
        feeding();
    }

    /*
     *
     *GETTERS
     *
     */

    public Caretaker getBestCaretaker() {
        bestCaretaker();
        return Collections.max(bestCare.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public List<Caretaker> getStuff() {
        return stuff;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public List<Animal> getHungryAnimals() {
        findHungryAnimals();
        return hungryAnimals;
    }
}
