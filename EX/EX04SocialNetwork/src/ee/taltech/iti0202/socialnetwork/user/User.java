package ee.taltech.iti0202.socialnetwork.user;
public class User {

    private final String nameUser;
    private Integer ageUser;

    /***
     * Constructor
     */
    public User(String name) {
        nameUser = name;
    }

    /***
     * Constructor 2
     */
    public User(String name, Integer age) {
        nameUser = name;
        ageUser = age;

    }

    /***
     * Getter
     */
    public String getName() {

        return nameUser;
    }

    /***
     * Getter
     */
    public Integer getAge() {

        return ageUser;
    }
}
