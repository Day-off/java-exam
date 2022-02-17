package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {

    private final Set<Group> allGroups = new HashSet<>();


    public SocialNetwork() {

    }


    public void registerGroup(Group group) {
        allGroups.add(group);
    }

    public Set<Group> getGroups() {

        return allGroups;
    }

    public Set<Group> findGroupsByUser(User user) {
        Set<Group> userGroup = new HashSet<>();
        for (Group group : allGroups) {
            if (group.getParticipants().contains(user)) {
                userGroup.add(group);
            }
        }
        return userGroup;
    }

    public Set<Message> findAllMessages(Set<Group> userGroup) {
        Set<Message> userAllMessages = new HashSet<>();
        for (Group group : userGroup) {
            userAllMessages.addAll(group.getMessages());
        }
        return userAllMessages;
    }

    public Feed getFeedForUser(User user) {
        //sort groups by user
        Set<Group> userGroup = findGroupsByUser(user);
        //groups sort messages by user
        Set<Message> userAllMessages = findAllMessages(userGroup);

        return new Feed(user, userAllMessages);
    }
}