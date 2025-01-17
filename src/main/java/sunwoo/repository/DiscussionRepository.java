package sunwoo.repository;

import sunwoo.Auth;
import sunwoo.Role;
import sunwoo.domain.Band;
import sunwoo.domain.DiscussionItem;
import sunwoo.domain.User;

import java.util.*;

public class DiscussionRepository {

    private static List<DiscussionItem> discussionItems = new ArrayList<>();

    public static DiscussionItem addDiscussionItem(DiscussionItem discussionItem){
        discussionItems.add(discussionItem);
        return discussionItem;
    }

    public static List<DiscussionItem> findAllDiscussionItem(Band band){
        return discussionItems.stream()
                .filter(d->d.getBand().equals(band))
                .toList();
    };
    public static Optional<DiscussionItem> findDiscussionItemById(String discussionItemId){
        return discussionItems.stream().filter(d -> d.getId().equals(discussionItemId)).findFirst();
    }

    public static void updateDiscussionItemSession(String id, Role role) {
        discussionItems.stream().filter(d -> d.getId().equals(id)).forEach(d->d.getSession().put(role, Auth.getNowUser().getUsername()));
    }

    private static void addDiscussionItemWithoutLog(DiscussionItem discussionItem){
        discussionItems.add(discussionItem);
    }


    public static void initDiscussionItem(DiscussionItem[] initData) {
        Arrays.asList(initData).forEach(DiscussionRepository::addDiscussionItemWithoutLog);
    }
}
