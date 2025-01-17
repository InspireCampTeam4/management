package sunwoo.service;

import sunwoo.Auth;
import sunwoo.util.LogHandler;
import sunwoo.domain.Band;
import sunwoo.domain.User;
import sunwoo.repository.BandRepository;
import sunwoo.repository.UserRepository;

import java.util.Scanner;

public class UserService {

    //회원가입
    public static void join(Scanner scanner) {

        String username;
        String password;
        Band band;


        //1. 유저네임 중복 확인
        while(true){
            System.out.println("사용자 이름을 입력하세요: ");
            String inputUser = scanner.nextLine().trim();
            if(!UserRepository.isUserExist(inputUser)){
                LogHandler.confirm("사용 가능한 유저명입니다.");
                username = inputUser;
                break;
            }else{
                LogHandler.warn("이미 존재하는 유저명입니다!");
            }
        }
        //2. 비밀번호 입력
        while(true){
            System.out.println("비밀번호를 입력하세요: ");
            String inputPw1 = scanner.nextLine().trim();
            System.out.println("다시 한번 동일한 비밀번호를 입력하세요: ");
            String inputPw2 = scanner.nextLine().trim();

            if(!inputPw1.equals(inputPw2)){
                LogHandler.warn("잘못된 비밀번호입니다. 다시 입력하세요");
            }else{
                password = inputPw2;
                break;
            }
        }
        if(!BandRepository.isAnyBandExist()){
            System.out.println("아직 생성된 밴드가 없습니다. 밴드를 생성하고 즉시 가입하세요");
            band = BandService.createBand(scanner);
        }else{
            System.out.println("가입할 밴드를 입력하세요 ");
            Band searchedBand = BandService.findBand(scanner);
            band  = searchedBand;
        }

        UserRepository.addUser(new User(username, password, band));
    }
    //로그인
    public static void login(Scanner scanner) {
        String username;

        System.out.println("***** Login *****");
        //1. 유저네임 중복 확인
        while(true){
            System.out.println("username: ");
            String inputUsername = scanner.nextLine().trim();
            //System.out.println(inputUsername);
            if(!UserRepository.isUserExist(inputUsername)){
                LogHandler.warn("존재하지 않는 유저입니다. ");

            }else{
                username = inputUsername;
                break;
            }
        }
        User isUser = UserRepository.findUserByUsername(username);
        while(true){
            System.out.println("password: ");
            String inputPassword = scanner.nextLine().trim();
            if(isUser.getPassword().equals(inputPassword)){
                LogHandler.confirm(username+"님, 환영합니다.");
                Auth.setUserAuth(isUser);
                break;
            }else{
                LogHandler.warn("잘못된 비밀번호입니다. 다시 입력하세요.");
            }
        }
    }

    //로그아웃
    public static void logout(){
        if(Auth.isLoggedIn()){
            Auth.deleteNowUser();
            LogHandler.confirm("로그아웃되었습니다");
        }
    }

}
