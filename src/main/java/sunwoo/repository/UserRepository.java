package sunwoo.repository;

import sunwoo.util.LogHandler;
import sunwoo.domain.User;

import java.util.*;

public class UserRepository {
    private static List<User> users = new ArrayList<>();

    public static void addUser(User user){
        if(isUserExist(user.getUsername())){
            LogHandler.warn("이미 등록된 유저입니다.");
            return;
        }
        users.add(user);
    }

    public static void deleteUser(User user){
        if(!isUserExist(user.getUsername())){
            LogHandler.warn("존재하지 않는 유저입니다.");
            return;
        }
        users.remove(user);
    }

    public static List<User> findAllUser(){
        return users;
    };
    public static User findUserByUsername(String username){
        return users
                .stream()
                .filter(u->u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("WARN: 존재하지 않는 유저입니다."));
    }

    public static boolean isUserExist(String username) {
        Optional<User> user = users
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        if (user.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAnyUserExist() {
        return users.size()==0 ? false : true;
    }

    private static void addUserWithoutLog(User user){
        users.add(user);
    }
    public static void initUsers(User[] initData) {
        Arrays.asList(initData).forEach(UserRepository::addUserWithoutLog);
    }
}
