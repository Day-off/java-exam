package ee.taltech.iti0202.zoo.animals;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Animal {

//    private static final List<String> TYPES = new ArrayList<>();

    private Type type;
    private String defaultSound1;
    private String defaultSound2;
    private String hungrySound = "";
    private Integer feedTimer;
    private Integer needToFeed;
    private String name;

    /***
     * Main constructor
     * @param name
     * @param sound1
     * @param days
     * @param hungrySound
     * @param sound2
     * @param type
     */
    public Animal(String name, Type type, String sound1, String sound2, String hungrySound, Integer days) {
        setName(name);
        setType(type);
        setDefaultSound1(sound1);
        setDefaultSound2(sound2);
        setHungrySound(hungrySound);
        setFeedTimer(days);
    }

    public enum Type {
        MAMMAL, BIRD, FISH, REPTILE, AMPHIBIAN
    }

    /*
    SETTERS
     */

    private void setType(Type type) {
        this.type = type;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDefaultSound2(String defaultSound2) {
        this.defaultSound2 = defaultSound2;
    }


    private void setHungrySound(String hungrySound) {
        this.hungrySound = hungrySound;
    }

    /***
     * set feed timer and amount
     */
    public void setFeedTimer(Integer days) {
        this.needToFeed = days;
        this.feedTimer = needToFeed;
    }

    public void setDefaultSound1(String defaultSound1) {
        this.defaultSound1 = defaultSound1;
    }

    /*
    METHODS
     */

    /***
     * Animals goes hungrier
     * @param day
     */
    public void changeTimer(Integer day) {
        if (feedTimer != null) {
            this.feedTimer -= day;
        }
    }

    /*
    GETTERS
     */

    /***
     * get sounds depends on animal state
     */
    public String getDefaultSound() {
        if (getFeedTimer() != null && getFeedTimer() <= 0) {
            if (getHungrySound() == null) {
                return "";
            }
            return getHungrySound();
        }
        if (defaultSound1 == null) {
            return "";
        }
        if (defaultSound2 != null) {
            List<String> res = new ArrayList<>();
            res.add(defaultSound1);
            res.add(defaultSound2);
            Random r = new Random();
            return res.get(r.nextInt(2));
        }
        return defaultSound1;
    }

    public String getHungrySound() {
        return hungrySound;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Integer getFeedTimer() {
        return feedTimer;
    }

    public Integer getNeedToFeed() {
        return needToFeed;
    }

    /*
    TO STRING
     */
    @Override
    public String toString() {
        return "Animal{"
                + "type='" + getType() + '\''
                + ", feedTimer=" + getFeedTimer()
                + ", needToFeed=" + getNeedToFeed()
                + ", name='" + getName() + '\''
                + '}';
    }
}
