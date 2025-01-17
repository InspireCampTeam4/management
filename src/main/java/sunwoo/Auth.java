package sunwoo;

import sunwoo.domain.User;

public class Auth {
    private static User nowUser;

    public static void setUserAuth(User user){
        nowUser = user;
    }

    public static User getNowUser(){
        return nowUser;
    }

    public static void deleteNowUser(){
        nowUser = null;
    }

    public static boolean isLoggedIn(){
        return  nowUser==null ? false : true;
    }

}
