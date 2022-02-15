package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

    private String NAME;
    private final User groupOwner;
    private final Set<User> members = new HashSet<>();
    private final List<Message> posts = new ArrayList<>();

    public Group(String name, User owner) {
        setName(name);
        groupOwner = owner;
        members.add(groupOwner);
    }

    public String getName() {

        return NAME;
    }

    public void setName(String name) {
        NAME = name;
    }

    public User getOwner() {

        return groupOwner;
    }

    public void addUser(User user) {
        members.add(user);
    }

    public Set<User> getParticipants() {

        return members;
    }

    public void publishMessage(Message message) {
        if (members.contains(message.getAuthor())){

            posts.add(message);
        }
    }

    public List<Message> getMessages() {
        return posts;
    }
}