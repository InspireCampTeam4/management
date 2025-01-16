package sungyeop;

import java.util.Scanner;

public class BoardEntry {
    public void run() {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n------------------------------------");
            System.out.println("1번 : 포스트 등록");
            System.out.println("2번 : 포스트 삭제");
            System.out.println("3번 : 포스트 전체 출력");
            System.out.println("4번 : 특정 포스트 확인하기");
            System.out.println("5번 : 전체 포스트 조회수 확인");
            System.out.println("6번 : 특정 포스트 조회수 확인");
            System.out.println("7번 : 나가기");
            System.out.println("------------------------------------\n");
            System.out.print("번호를 입력해주세요 : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    board.addPost(scanner);
                    break;
                case 2 :
                    board.deletePost(scanner);
                    break;
                case 3:
                    board.printAllPosts();
                    break;
                case 4:
                    board.viewOnePost(scanner);
                    break;
                case 5:
                    board.viewAllStatistics();
                    break;
                case 6:
                    board.viewOneStatistics(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("잘못 입력");
                    break;
            }
        }
    }
}
