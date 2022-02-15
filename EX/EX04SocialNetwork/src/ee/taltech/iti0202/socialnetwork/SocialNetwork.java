package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocialNetwork {

    private final Set<Group> allGroups = new HashSet<>();
    private final List<Group> userGroup = new ArrayList<>();
    private final Set<Message> userAllMessages = new HashSet<>();

    public SocialNetwork() {

    }


    public void registerGroup(Group group) {
        allGroups.add(group);
    }

    public Set<Group> getGroups() {

        return allGroups;
    }

    public void sortByUser(User user) {
        for (Group group : allGroups) {
            if (group.getParticipants().contains(user)) {
                userGroup.add(group);
            }
        }
    }

    public void findAllMessages(User user) {
        for (Group group : userGroup) {
            for (Message post : group.getMessages()) {
                if (post.getAuthor() == user) {
                    userAllMessages.add(post);
                }
            }
        }
    }

    public Feed getFeedForUser(User user) {
        //sort groups by user
        sortByUser(user);
        //groups sort messages by user
        findAllMessages(user);

        return new Feed(user, userAllMessages);
    }
}