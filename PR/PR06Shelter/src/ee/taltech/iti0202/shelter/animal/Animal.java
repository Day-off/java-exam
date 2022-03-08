package ee.taltech.iti0202.shelter.animal;

public abstract class Animal {

    /***
     * Type
     */
    public enum Type {
        HIROLA, TARANTULA, WOMBAT
    }

    private String color;

    /***
     * Constructor
     */
    public Animal(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
