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
     * @param color
     */
    public Animal(String color) {
        this.color = color;
    }

    /***
     * getter
     */
    public String getColor() {
        return color;
    }

    /***
     * Setter
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
