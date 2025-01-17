package sunwoo.service;


public class IdGenerator {
    private static int userId = 0;
    private static int bandId = 0;
    private static int discussionItemId= 0;

    public static String generateUserId(String username){
        int nowUserId = userId;
        userId++;
        return username+nowUserId;
    }

    public static String generateBandId(String bandName){
        int nowUserId = userId;
        userId++;
        return bandName+nowUserId;
    }

    public static String generateDiscussionItemId(String bandName){
        int nowUserId = userId;
        userId++;
        return bandName+nowUserId;
    }
}
