package sunwoo;

import sunwoo.domain.Band;
import sunwoo.domain.DiscussionItem;
import sunwoo.domain.Song;
import sunwoo.domain.User;
import sunwoo.repository.BandRepository;
import sunwoo.repository.DiscussionRepository;
import sunwoo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectInitializer {

    public static void init() {

        Band tuna = new Band("TuNA", 30);

        BandRepository.initBands(new Band[]{
                new Band("Beatles", 4),
                new Band("TOOL", 4),
                new Band("Metallica", 4),
                tuna});


        User user1 = new User("user1", "pw", tuna);
        User user2 = new User("user2", "pw", tuna);
        User user3 = new User("user3", "pw", tuna);
        UserRepository.initUsers(new User[]{
                user1, user2, user3,
                new User("user4", "pw", tuna),
                new User("user5", "pw", tuna),
                new User("user6", "pw", tuna),
                new User("user7", "pw", tuna),
                new User("user8", "pw", tuna),
                new User("user9", "pw", tuna),
                new User("user10", "pw", tuna),
        });

        DiscussionRepository.initDiscussionItem(new DiscussionItem[]{
                new DiscussionItem(tuna, user1, new Song("Bohemian Rhapsody", "Queen")),
                new DiscussionItem(tuna, user1, new Song("Smells Like Teen Spirit", "Nirvana")),
                new DiscussionItem(tuna, user1, new Song("Wonderwall", "Oasis")),
                new DiscussionItem(tuna, user1, new Song("Hotel California", "Eagles")),
                new DiscussionItem(tuna, user1, new Song("Imagine", "John Lennon")),
        });


    }


}
