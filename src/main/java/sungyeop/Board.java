package sungyeop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private List<Post> posts = new ArrayList<>();

    public void addPost(Scanner scanner) {
        System.out.printf("포스트 제목을 입력해주세요 : ");
        String title = scanner.nextLine();

        System.out.printf("포스트 작성 날짜를 입력해주세요 (YYYY-MM-DD) : ");
        String date = scanner.nextLine();

        System.out.printf("포스트 작성자를 입력해주세요 : ");
        String author = scanner.nextLine();

        System.out.println("포스트 내용을 입력해주세요");
        String content = scanner.next();

        Post post = new Post(title, date, author, content);
        System.out.println(post);
    }
}
