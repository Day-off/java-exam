package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {

    private final String titleMessage;
    private final String messageText;
    private final User user;

    public Message(String title, String message, User author) {
        titleMessage = title;
        messageText = message;
        user = author;
    }

    public String getTitle() {
        return titleMessage;
    }

    public String getMessage() {
        return messageText;
    }

    public User getAuthor() {
        return user;
    }


}
