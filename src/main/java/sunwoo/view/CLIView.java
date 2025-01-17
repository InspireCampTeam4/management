package sunwoo.view;

import sunwoo.Role;
import sunwoo.service.DiscussionService;
import sunwoo.util.InputHandler;
import sunwoo.util.LogHandler;
import sunwoo.service.UserService;

import java.io.IOException;
import java.util.Scanner;

import static sunwoo.service.DiscussionService.getItem;

public class CLIView {
    private static String page1CLI = 
        "밴드 관리 시스템에 오신 것을 환영합니다. \n" +
        "로그인 후 서비스를 이용해보세요. 이용하실 메뉴의 번호를 입력하세요.\n" +
        "-------------------------------------\n" +
        "1. 로그인\n" +
        "2. 회원가입\n" +
        "3. 시스템 종료\n" +
        "--------------------------------------\n" +
        "Select Menu:\n";

    private static String page2CLI = 
        "메뉴 아이템을 선택하세요\n" +
        "-------------------------------------\n" +
        "1. 선곡회의\n" +
        "2. 선곡회의에 곡 추가하기\n" +
        "3. 밴드 멤버 조회\n" +
        "4. 시스템 종료\n" +
        "--------------------------------------\n" +
        "Select Menu:\n";

    public static void cliMain(Scanner scanner) throws IOException {
        AsciiArtPrinter.printAsciiArt();
        cliStart(scanner);

    }

    public static void cliStart(Scanner scanner) throws IOException {

        System.out.println(page1CLI);

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                login(scanner);
                break;
            case 2:
                join(scanner);
                break;
            case 3:
                System.out.println("Bye");
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택하세요");
                break;

        }

    }

    public static void cliHome(Scanner scanner) throws IOException {

        System.out.println(page2CLI);

        int choice = InputHandler.getIntValue(scanner);
        switch (choice) {
            case 1:
                startDiscussion(scanner);
                break;
            case 2:
                addDisscussionItem(scanner);
                break;
            case 3:
                LogHandler.warn("아직 구현 전입니다 ㅎ");
                cliHome(scanner);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택하세요");
                break;

        }

    }

    private static void addDisscussionItem(Scanner scanner) throws IOException {
        System.out.println("---------선곡회의에 곡 추가하기-------");
        System.out.println("곡 제목을 입력하세요");
        String title = scanner.nextLine().trim();
        System.out.println("아티스트를 입력하세요");
        String artist = scanner.nextLine().trim();

        DiscussionService.addSongToDiscussion(title, artist);
       LogHandler.confirm(title+" 이(가) 회의에 추가되었습니다.");
        cliHome(scanner);
    }


    public static void login(Scanner scanner) throws IOException {
        UserService.login(scanner);
        cliHome(scanner);
    }

    public static void join(Scanner scanner) throws IOException {
        UserService.join(scanner);
        LogHandler.confirm("로그인으로 이동합니다");
        login(scanner);
    }

    public static void startDiscussion(Scanner scanner) throws IOException {
        DiscussionService.listAllItem();
        System.out.println("뒤로 가기: b | 항목 상세 보기: (ID)");
        String choice1 = scanner.nextLine().trim().toLowerCase();
        if (choice1.equals("b")) {
            cliHome(scanner);
        } else {
            searchItemDetail(choice1, scanner);

            while (true) {
                System.out.println("뒤로 가기: 0 | 세션 참여:(Vocal: 1 |Lead G: 2 |Rhythm G: 3 |Bass: 4 |KeyBoard: 5 |Drum: 6)");
                int choice2 = InputHandler.getIntValue(scanner);

                switch (choice2) {
                    case 0:
                        cliHome(scanner);
                        break;
                    case 1:
                        DiscussionService.participateSession(choice1,Role.VOCAL, scanner);
                        break;
                    case 2:
                        DiscussionService.participateSession(choice1,Role.GUITAR_LEAD, scanner);
                        break;
                    case 3:
                        DiscussionService.participateSession(choice1,Role.GUITAR_RHYTHM, scanner);
                        break;
                    case 4:
                        DiscussionService.participateSession(choice1,Role.BASS, scanner);
                        break;
                    case 5:
                        DiscussionService.participateSession(choice1,Role.KEYBOARD, scanner);
                        break;
                    case 6:
                        DiscussionService.participateSession(choice1,Role.DRUM, scanner);
                        break;
                    default:
                        LogHandler.warn("잘못된 입력입니다");
                        break;

                }
                getItem(choice1,scanner);

            }
        }

    }


    private static void searchItemDetail(String id, Scanner scanner) {
        getItem(id, scanner);
    }





}
