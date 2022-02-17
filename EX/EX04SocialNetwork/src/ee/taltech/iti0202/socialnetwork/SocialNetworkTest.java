package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {

    private final User mari = new User("Mari");
    private final User jan = new User("Jan", 23);
    private final User mark = new User("mark", 13);


    private final Group group1 = new Group("Meme1", mari);
    private final Group group2 = new Group("Meme2", jan);

    private final Message message1 = new Message("Greetings", "Hello!", mari);
    private final Message message2 = new Message("Me", "Buy!", jan);
    private final Message message3 = new Message("Met", "Buy!", mari);
    private final Message message4 = new Message("Met", "Buy!", mark);


    @Test
    public void register() {
        SocialNetwork insta = new SocialNetwork();
        insta.registerGroup(group1);
        group1.publishMessage(message1);
        assertEquals(group1, insta.getGroups().iterator().next());
        assertEquals(new HashSet<>(List.of(message1)), insta.getFeedForUser(mari).getMessages());

        insta.registerGroup(group2);
        group2.addUser(mari);
        group2.publishMessage(message4);
        group2.publishMessage(message3);
        assertEquals(1,group2.getMessages().size());
        Feed feed = insta.getFeedForUser(mari);
        assertEquals(new HashSet<>(List.of(message1, message3)), feed.getMessages());

    }

}