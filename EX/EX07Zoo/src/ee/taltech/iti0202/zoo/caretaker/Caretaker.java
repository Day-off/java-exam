package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private String name;
    private List<String> canFeed;
    private final List<Animal> tasks = new ArrayList<>();


    /***
     * Constructor
     * @param name
     * @param canFeed
     */
    public Caretaker(String name, List<String> canFeed) {
        setName(name);
        setCanFeed(canFeed);
    }

    /*
    SETTERS
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setCanFeed(List<String> canFeed) {
        this.canFeed = canFeed;
    }

    /*
    MAIN METHODS
     */

    /***
     *Add new tasks to stuff
     * @param newAnimals
     */
    public void addTasks(List<Animal> newAnimals) {
        for (Animal animal : newAnimals) {
            if (this.canFeed.contains(animal.getType()) && !tasks.contains(animal)) {
                this.tasks.add(animal);
            }
        }
    }

    /***
     * feed animals from tasks
     */
    public void feedAnimals() {
        for (Animal animal : tasks) {
            if (this.canFeed.contains(animal.getType())) {
                animal.setFeedTimer(animal.getNeedToFeed());
            }
        }
        checkTasks();
    }

    /***
     * Check if in tasks animals which were fed, and remove them from tasks
     */
    public void checkTasks() {
        tasks.removeIf(animal -> animal.getFeedTimer() > 0);
    }

    /*
    GETTERS
     */

    public String getName() {
        return name;
    }

    public List<Animal> getTasks() {
        return tasks;
    }

    public List<String> getCanFeed() {
        return canFeed;
    }

    @Override
    public String toString() {
        return "Caretaker{" +
                "name='" + name + '\'' +
                ", canFeed=" + canFeed +
                ", tasks=" + tasks +
                '}';
    }
}
