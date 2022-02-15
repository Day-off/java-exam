package ee.taltech.iti0202.socialnetwork.user;
public class User {

    private final String nameUser;
    private Integer ageUser;

    public User(String name) {
        nameUser = name;
    }

    public User(String name, Integer age) {
        nameUser = name;
        ageUser = age;

    }

    public String getName() {

        return nameUser;
    }

    public Integer getAge() {

        return ageUser;
    }
}