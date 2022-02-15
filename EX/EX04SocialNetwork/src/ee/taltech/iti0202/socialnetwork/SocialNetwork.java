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

        private final Set<Group> groups = new HashSet<>();
        private List<Group> userGroup = new ArrayList<>();

        private Set<Message> userAllMessages = new HashSet<>();


        public void registerGroup(Group group) {
            groups.add(group);
        }

        public Set<Group> getGroups() {

            return groups;
        }

        public void sortByUser(User user){
            for (Group group: groups){
                if (group.getParticipants().contains(user)){
                    userGroup.add(group);
                }
            }
        }

        public void findAllMessages(User user){
            for (Group group: userGroup){
                for (Message post: group.getMessages()){
                    if (post.getAuthor() == user){
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