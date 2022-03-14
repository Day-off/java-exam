package ee.taltech.iti0202.zoo.animals;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Animal {

    private static final List<String> types = new ArrayList<>();

    private String type;
    private String defaultSound1;
    private String defaultSound2;
    private String hungrySound = "";
    private Integer feedTimer;
    private Integer needToFeed;
    private String name;

    /***
     * Main constructor
     */
    public Animal(String name, String type, String sound1, String sound2, String hungrySound, Integer days) {
        setName(name);
        setType(type);
        setDefaultSound1(sound1);
        setDefaultSound2(sound2);
        setHungrySound(hungrySound);
        setFeedTimer(days);
    }

    /*
    SETTERS
     */

    private void setType(String type) {
        addType(type);
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
     * Add new animal type if it isn't exist
     */
    public void addType(String type) {
        if (!types.contains(type)) {
            types.add(type);
        }
    }

    /***
     * Animals goes hungrier
     */
    public void changeTimer(Integer day) {
        if (feedTimer != null) {
            this.feedTimer -= day;
        }
    }

    /*
    GETTERS
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

    public String getType() {
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
        return "Animal{" +
                "type='" + type + '\'' +
                ", feedTimer=" + feedTimer +
                ", needToFeed=" + needToFeed +
                ", name='" + name + '\'' +
                '}';
    }
}
