package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.Set;

public class Feed {

    private final User userFeed;
    private Set<Message> messagesUser;


    public Feed(User user, Set<Message> messages){
        userFeed = user;
        messagesUser = messages;
    }

    public User getUser(){
        return userFeed;
    }

    public Set<Message> getMessages(){
        return messagesUser;
    }


}
