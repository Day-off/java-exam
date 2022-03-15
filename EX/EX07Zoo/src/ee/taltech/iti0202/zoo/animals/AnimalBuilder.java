package ee.taltech.iti0202.zoo.animals;

public class AnimalBuilder {
    private String name;
    private Animal.Type type;
    private String sound1;
    private String sound2;
    private String hungrySound;
    private Integer days;

    /*
    SETTERS
     */

    /***
     * setter
     * @param name
     * @return this
     */
    public AnimalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /***
     * setter
     * @param type
     * @return this
     */
    public AnimalBuilder setType(Animal.Type type) {
        this.type = type;
        return this;
    }

    /***
     * setter
     * @param sound1
     * @return this
     */
    public AnimalBuilder setSound1(String sound1) {
        this.sound1 = sound1;
        return this;
    }

    /***
     * setter
     * @param sound2
     * @return this
     */
    public AnimalBuilder setSound2(String sound2) {
        this.sound2 = sound2;
        return this;
    }

    /***
     * setter
     * @param hungrySound
     * @return this
     */
    public AnimalBuilder setHungrySound(String hungrySound) {
        this.hungrySound = hungrySound;
        return this;
    }

    /***
     * setter
     * @param days
     * @return this
     */
    public AnimalBuilder setDays(Integer days) {
        this.days = days;
        return this;
    }

    /***
     * Create animal
     * @return Animal
     */
    public Animal createAnimal() {
        return new Animal(name, type, sound1, sound2, hungrySound, days);
    }
}
