package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {

    private final String titleMessage;
    private final String messageText;
    private final User user;

    /***
     * Constructor
     */
    public Message(String title, String message, User author) {
        titleMessage = title;
        messageText = message;
        user = author;
    }

    /***
     * getter
     */
    public String getTitle() {
        return titleMessage;
    }

    /***
     * Getter
     */
    public String getMessage() {
        return messageText;
    }

    /***
     * Getter
     */
    public User getAuthor() {
        return user;
    }


}
