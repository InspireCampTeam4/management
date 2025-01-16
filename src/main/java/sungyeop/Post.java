package sungyeop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@ToString
public class Post {
    private String title;
    private String content;
    private String author;
    private String date;
    private int postNumber;

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n------------------------------------");
            System.out.println("1번 : 포스트 전체 출력");
            System.out.println("2번 : 포스트 등록");
            System.out.println("3번 : 포스트 삭제");
            System.out.println("6번 : 나가기");
            System.out.println("------------------------------------\n");
            System.out.print("번호를 입력해주세요 : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    board.printAllPosts();
                    break;
                case 2 :
                    board.addPost(scanner);
                    break;
                case 3:
                    board.deletePost(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못 입력");
                    break;
            }
        }



    }
}
