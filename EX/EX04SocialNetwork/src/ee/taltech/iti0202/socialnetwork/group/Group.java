package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

    private String nameGroup;
    private final User groupOwner;
    private final Set<User> members = new HashSet<>();
    private final List<Message> posts = new ArrayList<>();

    /***
     * Constructor
     */
    public Group(String name, User owner) {
        setName(name);
        groupOwner = owner;
        members.add(groupOwner);
    }

    /***
     * getter
     */
    public String getName() {

        return nameGroup;
    }

    /***
     * setter
     */
    public void setName(String name) {
        nameGroup = name;
    }

    /***
     * Getter
     */
    public User getOwner() {

        return groupOwner;
    }

    /***
     * add
     */
    public void addUser(User user) {
        members.add(user);
    }

    /***
     * getter
     */
    public Set<User> getParticipants() {

        return members;
    }

    /***
     * publish
     */
    public void publishMessage(Message message) {
        if (members.contains(message.getAuthor())) {

            posts.add(message);
        }
    }

    /***
     * getter
     */
    public List<Message> getMessages() {
        return posts;
    }
}